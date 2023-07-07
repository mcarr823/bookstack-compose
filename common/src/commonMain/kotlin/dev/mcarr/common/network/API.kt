package dev.mcarr.common.network

import dev.mcarr.common.data.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

class API(
    val apiUrl: String,
    val tokenId: String,
    val tokenSecret: String,
    val disableHttpsVerification: Boolean = false,
    val testing: Boolean = false
) {

    private val httpClient = HttpClient(CIO) {
        if (testing) {
            this.developmentMode = true
        }
        engine {
            this.requestTimeout = 60_000
            if (disableHttpsVerification) {
                this.https.trustManager = object : X509TrustManager {
                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                    override fun checkClientTrusted(chain: Array<X509Certificate>?, authType: String?) = Unit
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) = Unit
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    private suspend fun performRequest(method: HttpMethod, path: String, setBodyCallback: HttpRequestBuilder.() -> Unit): HttpResponse {
        val httpResponse = httpClient.request("$apiUrl/api/$path"){
            this.method = method
            headers {
                append(HttpHeaders.Authorization, "Token $tokenId:$tokenSecret")
                append(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            this.setBodyCallback()
        }
        if (testing) {
            try {
                println(httpResponse.bodyAsText())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (httpResponse.status.value in 200..299) {
            return httpResponse
        }else{
            throw Exception() //TODO: parse error message
        }
    }

    private suspend fun get(path: String): HttpResponse =
        performRequest(HttpMethod.Get, path){}

    private suspend fun post(path: String, setBodyCallback: HttpRequestBuilder.() -> Unit): HttpResponse =
        performRequest(HttpMethod.Post, path, setBodyCallback)

    private suspend fun put(path: String, setBodyCallback: HttpRequestBuilder.() -> Unit): HttpResponse =
        performRequest(HttpMethod.Put, path, setBodyCallback)

    private suspend fun delete(path: String): Boolean =
        try{
            performRequest(HttpMethod.Delete, path){}
            true
        }catch (e: Exception){
            throw e
        }






    /* Docs */

    suspend fun getDocsHtml(): String = get("docs").body()

    suspend fun getDocsJson(): String = get("docs.json").body()



    /* Attachments */

    suspend fun getAttachments(): Attachments = get("attachments").body()

    //TODO: create attachment

    suspend fun getAttachment(id: Int): FullAttachment = get("attachments/$id").body()

    //TODO: update attachment

    suspend fun deleteAttachment(id: Int): Boolean = delete("attachments/$id")



    /* Books */

    suspend fun getBooks(): Books = get("books").body()

    suspend fun createBook(book: CreateBookRequest): CreateBookResponse =
        post("books") {
            setBody(book)
        }.body()

    suspend fun getBook(id: Int): FullBook = get("books/$id").body()

    suspend fun updateBook(id: Int, book: CreateBookRequest): CreateBookResponse =
        put("books/$id"){
            setBody(book)
        }.body()

    suspend fun deleteBook(id: Int): Boolean = delete("books/$id")

    suspend fun exportBook(id: Int, format: ExportFormat): String {
        return get("books/$id/export/${format.value}").body()
    }



    /* Chapters */

    suspend fun getChapters(): Chapters = get("chapters").body()

    suspend fun createChapter(chapter: CreateChapterRequest): CreateChapterResponse =
        post("chapters"){
            setBody(chapter)
        }.body()

    suspend fun getChapter(id: Int): FullChapter = get("chapters/$id").body()

    suspend fun updateChapter(id: Int, chapter: CreateChapterRequest): CreateChapterResponse =
        put("chapters/$id"){
            setBody(chapter)
        }.body()

    suspend fun deleteChapter(id: Int): Boolean = delete("chapters/$id")

    suspend fun exportChapter(id: Int, format: ExportFormat): String {
        return get("chapters/$id/export/${format.value}").body()
    }



    /* Pages */

    suspend fun getPages(): Pages = get("pages").body()

    suspend fun createPage(page: CreatePageRequest): CreatePageResponse =
        post("pages"){
            setBody(page)
        }.body()

    suspend fun getPage(id: Int): FullPage = get("pages/$id").body()

    suspend fun updatePage(id: Int, page: CreatePageRequest): CreatePageResponse =
        put("pages/$id"){
            setBody(page)
        }.body()

    suspend fun deletePage(id: Int): Boolean = delete("pages/$id")

    suspend fun exportPage(id: Int, format: ExportFormat): String {
        return get("pages/$id/export/${format.value}").body()
    }

}
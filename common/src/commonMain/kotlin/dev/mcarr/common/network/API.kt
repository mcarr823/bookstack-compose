package dev.mcarr.common.network

import dev.mcarr.common.data.classes.Attachments
import dev.mcarr.common.data.classes.Books
import dev.mcarr.common.data.classes.BookstackException
import dev.mcarr.common.data.classes.BookstackExceptionMessage
import dev.mcarr.common.data.classes.Chapters
import dev.mcarr.common.data.classes.CreateBookRequest
import dev.mcarr.common.data.classes.CreateBookResponse
import dev.mcarr.common.data.classes.CreateChapterRequest
import dev.mcarr.common.data.classes.CreateChapterResponse
import dev.mcarr.common.data.classes.CreatePageRequest
import dev.mcarr.common.data.classes.CreatePageResponse
import dev.mcarr.common.data.classes.ExportFormat
import dev.mcarr.common.data.classes.FullAttachment
import dev.mcarr.common.data.classes.FullBook
import dev.mcarr.common.data.classes.FullChapter
import dev.mcarr.common.data.classes.FullPage
import dev.mcarr.common.data.classes.Pages
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
) : ApiInterface {

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

    @Throws(Exception::class)
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
            val exc =
                try {
                    httpResponse.body() as BookstackException
                }catch (e: Exception){
                    BookstackException(
                        BookstackExceptionMessage(
                            message = "Failed to parse server response",
                            code = 400
                        )
                    )
                }
            throw exc
        }
    }

    @Throws(Exception::class)
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

    @Throws(Exception::class)
    override suspend fun getDocsHtml(): String = get("docs").body()

    @Throws(Exception::class)
    override suspend fun getDocsJson(): String = get("docs.json").body()



    /* Attachments */

    @Throws(Exception::class)
    override suspend fun getAttachments(): Attachments = get("attachments").body()

    //TODO: create attachment

    @Throws(Exception::class)
    override suspend fun getAttachment(id: Int): FullAttachment = get("attachments/$id").body()

    //TODO: update attachment

    override suspend fun deleteAttachment(id: Int): Boolean = delete("attachments/$id")



    /* Books */

    @Throws(Exception::class)
    override suspend fun getBooks(): Books = get("books").body()

    @Throws(Exception::class)
    override suspend fun createBook(book: CreateBookRequest): CreateBookResponse =
        post("books") {
            setBody(book)
        }.body()

    @Throws(Exception::class)
    override suspend fun getBook(id: Int): FullBook = get("books/$id").body()

    @Throws(Exception::class)
    override suspend fun updateBook(id: Int, book: CreateBookRequest): CreateBookResponse =
        put("books/$id"){
            setBody(book)
        }.body()

    override suspend fun deleteBook(id: Int): Boolean = delete("books/$id")

    @Throws(Exception::class)
    override suspend fun exportBook(id: Int, format: ExportFormat): String {
        return get("books/$id/export/${format.value}").body()
    }



    /* Chapters */

    @Throws(Exception::class)
    override suspend fun getChapters(): Chapters = get("chapters").body()

    @Throws(Exception::class)
    override suspend fun createChapter(chapter: CreateChapterRequest): CreateChapterResponse =
        post("chapters"){
            setBody(chapter)
        }.body()

    @Throws(Exception::class)
    override suspend fun getChapter(id: Int): FullChapter = get("chapters/$id").body()

    @Throws(Exception::class)
    override suspend fun updateChapter(id: Int, chapter: CreateChapterRequest): CreateChapterResponse =
        put("chapters/$id"){
            setBody(chapter)
        }.body()

    override suspend fun deleteChapter(id: Int): Boolean = delete("chapters/$id")

    @Throws(Exception::class)
    override suspend fun exportChapter(id: Int, format: ExportFormat): String {
        return get("chapters/$id/export/${format.value}").body()
    }



    /* Pages */

    @Throws(Exception::class)
    override suspend fun getPages(): Pages = get("pages").body()

    @Throws(Exception::class)
    override suspend fun createPage(page: CreatePageRequest): CreatePageResponse =
        post("pages"){
            setBody(page)
        }.body()

    @Throws(Exception::class)
    override suspend fun getPage(id: Int): FullPage = get("pages/$id").body()

    @Throws(Exception::class)
    override suspend fun updatePage(id: Int, page: CreatePageRequest): CreatePageResponse =
        put("pages/$id"){
            setBody(page)
        }.body()

    override suspend fun deletePage(id: Int): Boolean = delete("pages/$id")

    @Throws(Exception::class)
    override suspend fun exportPage(id: Int, format: ExportFormat): String {
        return get("pages/$id/export/${format.value}").body()
    }

}
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
                encodeDefaults = true
            })
        }
    }

    private suspend fun get(path: String): HttpResponse {
        val httpResponse = httpClient.get("$apiUrl/api/$path"){
            headers {
                append(HttpHeaders.Authorization, "Token $tokenId:$tokenSecret")
            }
        }
        if (httpResponse.status.value in 200..299) {
            if (testing) {
                try {
                    println(httpResponse.bodyAsText())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return httpResponse
        }else{
            throw Exception() //TODO: parse error message
        }
    }

    private suspend fun delete(path: String): HttpResponse {
        return httpClient.delete("$apiUrl/api/$path"){
            headers {
                append(HttpHeaders.Authorization, "Token $tokenId:$tokenSecret")
            }
        }
    }




    /* Attachments */

    suspend fun getAttachments(): Attachments = get("attachments").body()

    //TODO: create attachment

    suspend fun getAttachment(id: Int): FullAttachment = get("attachments/$id").body()

    //TODO: update attachment

    suspend fun deleteAttachment(id: Int){
        return delete("attachments/$id").body()
    }



    /* Pages */

    suspend fun getPages(): Pages = get("pages").body()

    //TODO: create page

    suspend fun getPage(id: Int): FullPage = get("pages/$id").body()

    //TODO: update page

    suspend fun deletePage(id: Int): String {
        return delete("pages/$id").body()
    }

    suspend fun exportPage(id: Int, format: ExportFormat): String {
        return get("pages/$id/export/${format.value}").body()
    }

}
import dev.mcarr.common.data.CreateBookRequest
import dev.mcarr.common.data.ExportFormat
import dev.mcarr.common.network.API
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class ApiTest {

    private val api = API(
        apiUrl = "https://my.website.com",
        tokenId = "tokenId",
        tokenSecret = "tokenSecret",
        disableHttpsVerification = true,
        testing = true
    )

    @Test
    fun testDocs(){

        runTest {
            val docsHtml = api.getDocsHtml()
            println(docsHtml)
        }

        runTest {
            val docsJson = api.getDocsJson()
            println(docsJson)
        }

    }

    @Test
    fun testAttachments(){

        runTest {
            val attachments = api.getAttachments().data
            attachments.forEach {
                println("Attachment ${it.id}: ${it.name}")
                val fullAttachment = api.getAttachment(it.id)
                println("Full attachment ${fullAttachment.content}")
            }
        }

    }

    @Test
    fun testBooks(){

        runTest {
            val books = api.getBooks().data
            books.forEach {
                println("Book ${it.id}: ${it.name}")
                val fullBook = api.getBook(it.id)
                fullBook.contents.forEach {
                    println("Full book content: ${it.type}")
                }

                val exportHtml = api.exportBook(it.id, ExportFormat.HTML)
                println("Book export HTML: $exportHtml")
                val exportPlainText = api.exportBook(it.id, ExportFormat.PLAIN_TEXT)
                println("Book export Plain: $exportPlainText")
                val exportPdf = api.exportBook(it.id, ExportFormat.PDF)
                println("Book export PDF: $exportPdf")
                val exportMarkdown = api.exportBook(it.id, ExportFormat.MARK_DOWN)
                println("Book export Markdown: $exportMarkdown")
            }
        }

    }

    @Test
    fun testCreateBook(){

        runTest {
            val book = CreateBookRequest(
                name = "Test book",
                description = "Test description"
            )
            val response = api.createBook(book)

            val book2 = CreateBookRequest(
                name = "Updated book",
                description = "Updated description"
            )
            val response2 = api.updateBook(response.id, book2)

            api.deleteBook(response.id)
        }

    }

    @Test
    fun testChapters(){

        runTest {
            val chapters = api.getChapters().data
            chapters.forEach {
                println("Chapter ${it.id}: ${it.name}")
                val fullChapter = api.getChapter(it.id)
                fullChapter.pages.forEach {
                    println("Full chapter content: ${it.url}")
                }

                val exportHtml = api.exportChapter(it.id, ExportFormat.HTML)
                println("Chapter export HTML: $exportHtml")
                val exportPlainText = api.exportChapter(it.id, ExportFormat.PLAIN_TEXT)
                println("Chapter export Plain: $exportPlainText")
                val exportPdf = api.exportChapter(it.id, ExportFormat.PDF)
                println("Chapter export PDF: $exportPdf")
                val exportMarkdown = api.exportChapter(it.id, ExportFormat.MARK_DOWN)
                println("Chapter export Markdown: $exportMarkdown")
            }
        }

    }

    @Test
    fun testPages(){

        runTest {
            val pages = api.getPages().data
            pages.forEach {
                println("Page ${it.id}: ${it.name}")
                val fullPage = api.getPage(it.id)
                println("Full page html: ${fullPage.html}")

                val exportHtml = api.exportPage(it.id, ExportFormat.HTML)
                println("Page export HTML: $exportHtml")
                val exportPlainText = api.exportPage(it.id, ExportFormat.PLAIN_TEXT)
                println("Page export Plain: $exportPlainText")
                val exportPdf = api.exportPage(it.id, ExportFormat.PDF)
                println("Page export PDF: $exportPdf")
                val exportMarkdown = api.exportPage(it.id, ExportFormat.MARK_DOWN)
                println("Page export Markdown: $exportMarkdown")
            }
        }
    }

}
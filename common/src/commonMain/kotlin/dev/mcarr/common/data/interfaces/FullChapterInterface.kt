package dev.mcarr.common.data.interfaces

import dev.mcarr.common.data.Author
import dev.mcarr.common.data.Page
import dev.mcarr.common.data.Tag
import kotlinx.datetime.Instant

interface FullChapterInterface : ParentChapterInterface {
    val created_by: Author
    val updated_by: Author
    val owned_by: Author
    val tags: List<Tag>
    val pages: List<Page>
}
package dev.mcarr.common.data.interfaces

import dev.mcarr.common.data.classes.Author
import dev.mcarr.common.data.classes.Page
import dev.mcarr.common.data.classes.Tag

interface FullChapterInterface : ParentChapterInterface {
    val created_by: Author
    val updated_by: Author
    val owned_by: Author
    val tags: List<Tag>
    val pages: List<Page>
}
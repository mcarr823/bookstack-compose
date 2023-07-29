package dev.mcarr.common.data.interfaces

import dev.mcarr.common.data.Author
import dev.mcarr.common.data.Tag

interface FullPageInterface : ParentPageInterface {
    val created_by: Author
    val updated_by: Author
    val owned_by: Author
    val tags: List<Tag>
    val revision_count: Int
    val html: String
    val markdown: String
}
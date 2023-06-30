package dev.mcarr.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface BookstackResponse<T>{
    val data: List<T>
    val total: Int
}
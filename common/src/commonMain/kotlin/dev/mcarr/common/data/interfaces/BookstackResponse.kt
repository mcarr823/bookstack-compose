package dev.mcarr.common.data.interfaces

interface BookstackResponse<T>{
    val data: List<T>
    val total: Int
}
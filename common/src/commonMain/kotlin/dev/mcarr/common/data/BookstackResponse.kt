package dev.mcarr.common.data

interface BookstackResponse<T>{
    val data: List<T>
    val total: Int
}
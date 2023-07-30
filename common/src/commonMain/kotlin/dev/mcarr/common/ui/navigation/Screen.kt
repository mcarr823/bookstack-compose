package dev.mcarr.common.ui.navigation

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

abstract class Screen : Parcelable {
    @Parcelize
    object List : Screen()


    @Parcelize
    object Setup : Screen()

    @Parcelize
    object BookList : Screen()

    @Parcelize
    data class Book(val bookId: Int) : Screen()

    @Parcelize
    data class Chapter(val chapterId: Int) : Screen()

    @Parcelize
    data class Page(val pageId: Int) : Screen()

}
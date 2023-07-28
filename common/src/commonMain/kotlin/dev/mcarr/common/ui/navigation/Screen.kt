package dev.mcarr.common.ui.navigation

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

abstract class Screen : Parcelable {
    @Parcelize
    object List : Screen()

}
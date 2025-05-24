package com.example.skilllink.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    data object LoginScreen1

    @Serializable
    data object LoginScreen2

    @Serializable
    data object LoginScreen3

    @Serializable
    data object DashBoard
}
package com.example.skilllink.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    object LoginScreen

    @Serializable
    object DashBoard
}
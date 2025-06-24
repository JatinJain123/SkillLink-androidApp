package com.example.skilllink.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    sealed class Auth{
        @Serializable
        data object LoginScreen

        @Serializable
        data object UsernameScreen

        @Serializable
        data object SecretPinScreen
    }


    sealed class Home {
        @Serializable
        data object HomeScreen

        @Serializable
        data object NotificationScreen
    }


    sealed class ChatRoom {
        @Serializable
        data object ChatRoomScreen
    }


    sealed class Search {
        @Serializable
        data object SearchScreen
    }


    sealed class Profile {
        @Serializable
        data object ProfileScreen
    }


    sealed class MyLearning {
        @Serializable
        data object MyLearningScreen
    }


    sealed class Creator {
        @Serializable
        data object CreatorScreen
    }
}
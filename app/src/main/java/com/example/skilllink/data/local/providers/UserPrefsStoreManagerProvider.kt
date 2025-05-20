package com.example.skilllink.data.local.providers

import android.content.Context
import androidx.datastore.core.DataStoreFactory
import com.example.skilllink.data.local.managers.UserPrefsStoreManager
import com.example.skilllink.data.local.serializers.UserPrefsStoreSerializer
import java.io.File

object UserPrefsStoreManagerProvider {
    fun provideUserPrefsStoreManager(
        context: Context,
        userId: String
    ): UserPrefsStoreManager {
        val userPrefsStore = DataStoreFactory.create(
            serializer = UserPrefsStoreSerializer,
            produceFile = { File(context.filesDir, "user_${userId}.pb") }
        )

        return UserPrefsStoreManager(userPrefsStore = userPrefsStore)
    }
}
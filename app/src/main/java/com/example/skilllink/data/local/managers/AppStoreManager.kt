package com.example.skilllink.data.local.managers

import androidx.datastore.core.DataStore
import com.example.skilllink.data.local.serializers.AppUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppStoreManager @Inject constructor(
    private val appStore: DataStore<AppUser>
) {
    suspend fun setCurrentUser(userId: String) {
        appStore.updateData {
            it.toBuilder().setCurrentUserId(userId).build()
        }
    }

    suspend fun setCurrentEmail(email: String) {
        appStore.updateData {
            it.toBuilder().setCurrentEmail(email).build()
        }
    }

    val currentUser: Flow<String> = appStore.data
        .map { it.currentUserId }

    val currentEmail: Flow<String> = appStore.data
        .map { it.currentEmail }
}
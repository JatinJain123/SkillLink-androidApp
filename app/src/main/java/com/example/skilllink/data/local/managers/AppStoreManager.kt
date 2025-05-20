package com.example.skilllink.data.local.managers

import androidx.datastore.core.DataStore
import com.example.skilllink.data.local.serializers.AppUser
import com.example.skilllink.utils.hashUserId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppStoreManager @Inject constructor(
    private val appStore: DataStore<AppUser>
) {
    suspend fun setCurrentUser(userId: String) {
        val hashedString = hashUserId(userId = userId)
        appStore.updateData { it ->
            it.toBuilder().setCurrentUserId(hashedString).build()
        }
    }

    suspend fun setNotifications(enabled: Boolean) {
        appStore.updateData { it ->
            it.toBuilder().setNotificationsEnabled(enabled).build()
        }
    }

    val currentUser: Flow<String> = appStore.data
        .map { it.currentUserId }

    val notificationsEnabled: Flow<Boolean> = appStore.data
        .map { it.notificationsEnabled }
}
package com.example.skilllink.data.local.managers

import androidx.datastore.core.DataStore
import com.example.skilllink.data.local.serializers.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPrefsStoreManager(
    private val userPrefsStore: DataStore<User>
) {
    suspend fun setId(userId: String) {
        userPrefsStore.updateData {
            it.toBuilder().setId(userId).build()
        }
    }

    suspend fun setEmail(email: String) {
        userPrefsStore.updateData {
            it.toBuilder().setEmail(email).build()
        }
    }

    suspend fun setUsername(username: String) {
        userPrefsStore.updateData {
            it.toBuilder().setUsername(username).build()
        }
    }

    suspend fun setHasSecretPin(hasSpin: Boolean) {
        userPrefsStore.updateData {
            it.toBuilder().setHasSecretPin(hasSpin).build()
        }
    }

    suspend fun setLightMode(state: Boolean) {
        userPrefsStore.updateData {
            it.toBuilder().setLightMode(state).build()
        }
    }

    suspend fun setProfilePic(uri: String) {
        userPrefsStore.updateData {
            it.toBuilder().setProfileImagePath(uri).build()
        }
    }

    val id: Flow<String> = userPrefsStore.data
        .map{ it.id }

    val email: Flow<String> = userPrefsStore.data
        .map{ it.email }

    val userName: Flow<String> = userPrefsStore.data
        .map{ it.username }

    val hasSpin: Flow<Boolean> = userPrefsStore.data
        .map{ it.hasSecretPin }

    val lightMode: Flow<Boolean> = userPrefsStore.data
        .map{ it.lightMode }

    val profilePic: Flow<String> = userPrefsStore.data
        .map { it.profileImagePath }
}
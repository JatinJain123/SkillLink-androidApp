package com.example.skilllink.data.local.managers

import androidx.datastore.core.DataStore
import com.example.skilllink.data.local.serializers.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPrefsStoreManager(
    private val userPrefsStore: DataStore<User>
) {
    suspend fun setId(userId: String) {
        userPrefsStore.updateData { it ->
            it.toBuilder().setId(userId).build()
        }
    }

    suspend fun setEmail(email: String) {
        userPrefsStore.updateData { it ->
            it.toBuilder().setEmail(email).build()
        }
    }

    suspend fun setUsername(username: String) {
        userPrefsStore.updateData { it ->
            it.toBuilder().setUserName(username).build()
        }
    }

    suspend fun setHasSpin(hasSpin: Boolean) {
        userPrefsStore.updateData { it ->
            it.toBuilder().setHasSpin(hasSpin).build()
        }
    }

    suspend fun setLightMode(state: Boolean) {
        userPrefsStore.updateData { it ->
            it.toBuilder().setLightMode(state).build()
        }
    }

    val id: Flow<String> = userPrefsStore.data
        .map{ it -> it.id }

    val email: Flow<String> = userPrefsStore.data
        .map{ it -> it.email }

    val userName: Flow<String> = userPrefsStore.data
        .map{ it -> it.userName }

    val hasSpin: Flow<Boolean> = userPrefsStore.data
        .map{ it -> it.hasSpin }

    val lightMode: Flow<Boolean> = userPrefsStore.data
        .map{ it -> it.lightMode }
}
package com.example.skilllink.data.local

import androidx.datastore.core.DataStore
import com.example.skilllink.data.local.serializers.User
import javax.inject.Inject

class UserPrefsStoreManager @Inject constructor(
    private val userPrefsStore: DataStore<User>
) {

}
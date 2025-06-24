package com.example.skilllink.data.local.serializers

import androidx.datastore.core.Serializer
import com.example.skilllink.utils.Crypto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.util.Base64

object UserPrefsStoreSerializer: Serializer<User> {
    override val defaultValue: User
        get() = User.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): User {
        val encryptedBytesBase64 = withContext(Dispatchers.IO) {
            input.use{ it.readBytes() }
        }
        val encryptedBytes = Base64.getDecoder().decode(encryptedBytesBase64)
        val bytes = Crypto.decrypt(encryptedBytes)
        return User.parseFrom(bytes)
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        val bytes = t.toByteArray()
        val encryptedBytes = Crypto.encrypt(bytes)
        val encryptedBytesBase64 = Base64
            .getEncoder()
            .encode(encryptedBytes)
        withContext(Dispatchers.IO) {
            output.use {
                it.write(encryptedBytesBase64)
            }
        }
    }
}
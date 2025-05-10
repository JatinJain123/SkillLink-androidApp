package com.example.skilllink.utils

import java.security.MessageDigest

fun hashUserId(userId: String): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val hashBytes = digest.digest(userId.toByteArray())
    return hashBytes.joinToString("") { "%02x".format(it) }
}
package com.example.skilllink.domain.model.remote

import java.util.Date

data class Reel (
    val id: String,
    val title: String,
    val description: String,
    val authorEmail: String,
    val thumbnailUrl: String?,
    val contentUrl: String,
    var likes: Int,
    var views: Int,
    val tags: List<String>,
    val createdAt: Date
)

data class ReelsResponse (
    val status: Boolean,
    val message: String,
    val size: Int,
    val reels: List<Reel>
)
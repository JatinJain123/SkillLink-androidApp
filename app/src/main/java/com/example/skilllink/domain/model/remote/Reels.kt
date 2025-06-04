package com.example.skilllink.domain.model.remote

import java.util.Date

data class Reel (
    val id: String,
    val title: String,
    val description: String,
    val authorName: String,
    val authorEmail: String,
    val authorProfileImageUrl: String,
    val thumbnailUrl: String?,
    val contentUrl: String,
    val aspectRatio: Float,
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
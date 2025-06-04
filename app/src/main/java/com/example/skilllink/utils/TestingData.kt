package com.example.skilllink.utils

import com.example.skilllink.domain.model.remote.Reel
import com.example.skilllink.domain.model.remote.ReelsResponse
import java.util.Date

val testReels = listOf(
    Reel(
        id = "1",
        title = "Mountain Adventure",
        description = "Exploring snowy peaks.",
        authorName = "Name1",
        authorEmail = "adventurer@demo.com",
        authorProfileImageUrl = "https://randomuser.me/api/portraits/men/10.jpg",
        thumbnailUrl = null,
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        aspectRatio = 16f / 9f,
        likes = 1210,
        views = 4200,
        tags = listOf("travel", "mountains", "explore"),
        createdAt = Date()
    ),
    Reel(
        id = "2",
        title = "Cooking Vibes",
        description = "Tasty pasta in 5 minutes!",
        authorName = "Name2",
        authorEmail = "chef@demo.com",
        authorProfileImageUrl = "https://randomuser.me/api/portraits/women/12.jpg",
        thumbnailUrl = null,
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        aspectRatio = 16f / 9f,
        likes = 1880,
        views = 5100,
        tags = listOf("cooking", "food", "vlog"),
        createdAt = Date()
    ),
    Reel(
        id = "3",
        title = "City Nights",
        description = "Timelapse of downtown LA. Timelapse of downtown LA. Timelapse of downtown LA. Timelapse of downtown LA. Timelapse of downtown LA. Timelapse of downtown LA.",
        authorName = "Name3",
        authorEmail = "cityvibes@demo.com",
        authorProfileImageUrl = "https://randomuser.me/api/portraits/men/25.jpg",
        thumbnailUrl = null,
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        aspectRatio = 16f / 9f,
        likes = 950,
        views = 3200,
        tags = listOf("city", "timelapse", "urban"),
        createdAt = Date()
    ),
    Reel(
        id = "4",
        title = "Beach Sunset",
        description = "Golden hour at the beach.",
        authorName = "Name4",
        authorEmail = "sunsetlover@demo.com",
        authorProfileImageUrl = "https://randomuser.me/api/portraits/women/45.jpg",
        thumbnailUrl = null,
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        aspectRatio = 16f / 9f,
        likes = 1030,
        views = 3900,
        tags = listOf("nature", "sunset", "relax"),
        createdAt = Date()
    ),
    Reel(
        id = "5",
        title = "Tech Explained",
        description = "How AI is changing the world.",
        authorName = "Name5",
        authorEmail = "techie@demo.com",
        authorProfileImageUrl = "https://randomuser.me/api/portraits/men/35.jpg",
        thumbnailUrl = null,
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        aspectRatio = 16f / 9f,
        likes = 2040,
        views = 6300,
        tags = listOf("technology", "AI", "future"),
        createdAt = Date()
    )
)

val testReelData = ReelsResponse(
    status = true,
    message = "success",
    size = 5,
    reels = testReels
)
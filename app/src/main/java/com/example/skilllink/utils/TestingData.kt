package com.example.skilllink.utils

import com.example.skilllink.domain.model.remote.Reel
import com.example.skilllink.domain.model.remote.ReelsResponse
import java.util.Date

val testReels = listOf(
    Reel(
        id = "1",
        title = "Mountain Adventure",
        description = "Exploring snowy peaks.",
        authorEmail = "adventurer@demo.com",
        thumbnailUrl = "https://randomuser.me/api/portraits/men/10.jpg",
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
        likes = 1210,
        views = 4200,
        tags = listOf("travel", "mountains", "explore"),
        createdAt = Date()
    ),
    Reel(
        id = "2",
        title = "Cooking Vibes",
        description = "Tasty pasta in 5 minutes!",
        authorEmail = "chef@demo.com",
        thumbnailUrl = "https://randomuser.me/api/portraits/women/12.jpg",
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
        likes = 1880,
        views = 5100,
        tags = listOf("cooking", "food", "vlog"),
        createdAt = Date()
    ),
    Reel(
        id = "3",
        title = "City Nights",
        description = "Timelapse of downtown LA.",
        authorEmail = "cityvibes@demo.com",
        thumbnailUrl = "https://randomuser.me/api/portraits/men/25.jpg",
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
        likes = 950,
        views = 3200,
        tags = listOf("city", "timelapse", "urban"),
        createdAt = Date()
    ),
    Reel(
        id = "4",
        title = "Beach Sunset",
        description = "Golden hour at the beach.",
        authorEmail = "sunsetlover@demo.com",
        thumbnailUrl = "https://randomuser.me/api/portraits/women/45.jpg",
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        likes = 1030,
        views = 3900,
        tags = listOf("nature", "sunset", "relax"),
        createdAt = Date()
    ),
    Reel(
        id = "5",
        title = "Tech Explained",
        description = "How AI is changing the world.",
        authorEmail = "techie@demo.com",
        thumbnailUrl = "https://randomuser.me/api/portraits/men/35.jpg",
        contentUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
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
package com.example.skilllink.ui.reusableComponents.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import com.example.skilllink.R
import com.example.skilllink.domain.model.remote.Reel
import com.example.skilllink.ui.reusableComponents.videoPlayers.FeedVideoPlayer
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.ui.theme.LightNavyBlue

@Composable
fun FeedReelCard(
    reel: Reel,
    exoPlayer: ExoPlayer,
    isPlaying: Boolean,
    mikeOn: Boolean,
    onMikeClick: () -> Unit,
    customFields: CustomFields
) {
    var isExpanded by rememberSaveable(key = reel.id) { mutableStateOf(false) }
    var isLiked by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = customFields.midPadding)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = customFields.midPadding,
                    vertical = customFields.smallPadding
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = reel.authorProfileImageUrl,
                contentDescription = "Author Thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(2.dp, LightNavyBlue, CircleShape)
                    .clickable {  }
            )

            Spacer(modifier = Modifier.width(customFields.smallSpacing))

            Text(
                text = reel.authorName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = customFields.primaryTextColor,
                modifier = Modifier.clickable {  }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(reel.aspectRatio)
                .background(MaterialTheme.colorScheme.background)
        ) {
            FeedVideoPlayer(
                exoPlayer = exoPlayer,
                videoPath = reel.contentUrl,
                isPlaying = isPlaying
            )

            if(mikeOn) {
                Icon(
                    painter = painterResource(R.drawable.mic_on_icon),
                    contentDescription = "volume on",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onMikeClick() }
                        .align(Alignment.BottomEnd)
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.mic_off_icon),
                    contentDescription = "volume on",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onMikeClick() }
                        .align(Alignment.BottomEnd)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = customFields.smallPadding,
                    start = customFields.midPadding,
                    end = customFields.midPadding
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = reel.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                color = customFields.primaryTextColor,
                modifier = Modifier.weight(1f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "Likes",
                    tint = if(isLiked) Color.Red else customFields.iconTint,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable {
                            isLiked = !isLiked
                            if(isLiked) reel.likes += 1 else reel.likes -= 1
                        }
                )

                Spacer(modifier = Modifier.width(customFields.extraSmallSpacing))

                Text(
                    text = "${reel.likes}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = customFields.secondaryTextColor,
                    modifier = Modifier.clickable {
                        isLiked = !isLiked
                        if(isLiked) reel.likes += 1 else reel.likes -= 1
                    }
                )

                Spacer(modifier = Modifier.width(customFields.midSpacing))

                Icon(
                    painter = painterResource(R.drawable.visibility_icon),
                    contentDescription = "Views",
                    tint = customFields.iconTint,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(customFields.extraSmallSpacing))

                Text(
                    text = "${reel.views}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = customFields.secondaryTextColor
                )
            }
        }

        Spacer(modifier = Modifier.height(customFields.smallSpacing))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = customFields.midPadding)
        ) {
            if (isExpanded) {
                Text(
                    text = reel.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = customFields.secondaryTextColor
                )

                AnimatedVisibility(
                    visible = reel.tags.isNotEmpty(),
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(customFields.smallSpacing))
                        Text(
                            text = "Tags: ${reel.tags.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall,
                            color = customFields.secondaryTextColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(customFields.smallSpacing))

                Text(
                    text = "Show less",
                    style = MaterialTheme.typography.bodySmall,
                    color = customFields.primaryFocusedColor,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = customFields.extraSmallPadding)
                        .clickable { isExpanded = false }
                )
            } else {
                val truncatedDescription = if (reel.description.length > 100) {
                    reel.description.take(20) + "..."
                } else {
                    reel.description
                }

                val displayText = buildAnnotatedString {
                    append(truncatedDescription)
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = customFields.primaryFocusedColor,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append("more")
                    }
                }

                Text(
                    text = displayText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = customFields.secondaryTextColor,
                    modifier = Modifier.clickable { isExpanded = true }
                )
            }
        }
    }
}

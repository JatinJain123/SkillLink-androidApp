package com.example.skilllink.ui.screens.reusableComponents.inputFields

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.skilllink.ui.theme.CustomFields
import com.example.skilllink.utils.AppConstants

@Composable
fun OtpInputField(
    otpLength: Int = 4,
    customFields: CustomFields,
    onOtpComplete: (String) -> Unit
) {
    var otpValue by remember { mutableStateOf("") }

    val infiniteTransition = rememberInfiniteTransition(label = "cursor")
    val cursorAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = AppConstants.CURSOR_BLINK_TIME),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cursorAlpha"
    )

    BasicTextField(
        value = otpValue,
        onValueChange = { value ->
            if (value.length <= otpLength && value.all { it.isDigit() }) {
                otpValue = value
                if (value.length == otpLength) {
                    onOtpComplete(value)
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        textStyle = TextStyle.Default.copy(color = Color.Transparent),
        decorationBox = { innerTextField ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(customFields.smallSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(otpLength) { index ->
                    val char = otpValue.getOrNull(index)?.toString() ?: ""
                    val isFocused = otpValue.length == index

                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .border(
                                width = if(isFocused) 2.dp else 1.dp,
                                color = if(isFocused) customFields.primaryFocusedColor else customFields.primaryUnfocusedColor
                            )
                            .background(MaterialTheme.colorScheme.background),
                        contentAlignment = Alignment.Center
                    ) {
                        when {
                            char.isNotEmpty() -> {
                                Text(
                                    text = char,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                                    ),
                                    color = customFields.secondaryTextColor
                                )
                            }

                            isFocused -> {
                                Text(
                                    text = "|",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                                    ),
                                    color = customFields.secondaryTextColor.copy(alpha = cursorAlpha)
                                )
                            }
                        }
                    }
                }
            }
            innerTextField()
        }
    )
}

package com.example.skilllink.ui.screens.reusableComponents.inputFields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.skilllink.R
import com.example.skilllink.ui.theme.CustomFields

@Composable
fun StyledInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    leadingIcon: ImageVector,
    customFields: CustomFields,
    isError: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label, color = customFields.secondaryTextColor)
        },
        singleLine = true,
        leadingIcon = {
            Icon(leadingIcon, contentDescription = null, tint = customFields.iconTint)
        },
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) ImageVector.vectorResource(R.drawable.visibility_icon) else ImageVector.vectorResource(
                            R.drawable.visibility_off_icon),
                        contentDescription = null,
                        tint = customFields.iconTint
                    )
                }
            }
        },
        isError = isError,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = customFields.primaryFocusedColor,
            unfocusedBorderColor = customFields.primaryUnfocusedColor,
            focusedLabelColor = customFields.primaryFocusedColor,
            unfocusedLabelColor = customFields.primaryUnfocusedColor,
            cursorColor = customFields.primaryFocusedColor,
            focusedTextColor = customFields.primaryFocusedColor,
            unfocusedTextColor = customFields.primaryFocusedColor,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
package com.example.softmarket.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun MyTextField(value: TextFieldValue,
                onValueChange: (TextFieldValue) -> Unit = {},
                label: @Composable () -> Unit = {},
                readOnly: Boolean = false,
                trailingIcon: @Composable (() -> Unit) = {}
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = Modifier.fillMaxWidth(),
        readOnly = readOnly,
        trailingIcon = trailingIcon
    )
}
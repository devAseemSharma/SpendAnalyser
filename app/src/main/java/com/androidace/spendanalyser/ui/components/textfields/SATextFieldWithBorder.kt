package com.androidace.spendanalyser.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidace.spendanalyser.ui.theme.AppTheme
import com.androidace.spendanalyser.ui.theme.stateLayerOnBackground

@Composable
fun SATextFieldWithBorder(
    text: String,
    errorText: String = "",
    hintText: String,
    enabled: Boolean = true,
    hintTextStyle: TextStyle,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        enabled = enabled,
        onValueChange = onValueChange,
        textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onSurface),
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        isError = errorText.isNotEmpty(),
        supportingText = {
            if (errorText.isNotEmpty()) {
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors().copy(
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        ),
        // The decorationBox allows us to provide custom UI elements (like the hint and background)
        placeholder = {
            Text(
                text = hintText,
                style = hintTextStyle.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(0.38f)
                )
            )
        },

        )
}

@Preview(showBackground = true)
@Composable
private fun SATextFieldWithBorderPreview() {
    var valueText by remember { mutableStateOf("") }
    AppTheme {
        SATextFieldWithBorder(
            text = valueText,
            hintText = "Username",
            errorText = "Invalid user name",
            enabled = true,
            hintTextStyle = MaterialTheme.typography.bodyMedium,
            textStyle = MaterialTheme.typography.bodyMedium,
            onValueChange = {
                valueText = it
            }, modifier = Modifier.padding(16.dp)
        )
    }
}
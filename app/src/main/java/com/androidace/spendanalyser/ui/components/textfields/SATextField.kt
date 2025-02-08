package com.androidace.spendanalyser.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.androidace.spendanalyser.ui.theme.stateLayerOnBackground


@Composable
fun SATextField(
    text: String,
    hintText: String,
    enabled: Boolean,
    hintTextStyle: TextStyle,
    textStyle: TextStyle,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = text,
        enabled = enabled,
        onValueChange = onValueChange,
        textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onSurface),
        modifier = modifier,
        // The decorationBox allows us to provide custom UI elements (like the hint and background)
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .background(color = stateLayerOnBackground, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = hintText,
                        style = hintTextStyle.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(0.38f)
                        )
                    )
                }
                innerTextField()
            }
        }
    )
}
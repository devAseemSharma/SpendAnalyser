package com.androidace.spendanalyser.ui.components.pin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.androidace.spendanalyser.ui.components.pin.model.KeyPadEntry
import com.androidace.spendanalyser.ui.components.pin.model.keypadKeys
import com.androidace.spendanalyser.ui.theme.onPrimaryFixed
import com.androidace.spendanalyser.ui.theme.primary
import com.androidace.spendanalyser.ui.theme.primaryFixed

val singleItemPadding = 4.dp
val maxPadding = singleItemPadding * 3

@Composable
fun SAPinKeypad(
    onClick: (key: KeyPadEntry) -> Unit,
    deleteImage: Int,
    modifier: Modifier = Modifier,
    keys: List<KeyPadEntry> = keypadKeys
) {
    PinKeyPadLayout(
        modifier = modifier.then(
            Modifier.sizeIn(
                maxWidth = 332.dp,
                maxHeight = 444.dp
            )
        ), content = {
            keys.forEachIndexed { _, item ->
                when (item) {
                    KeyPadEntry.BackSpace -> KeypadButton(
                        text = "",
                        imageId = deleteImage,
                        color = Color(0XFFEADDFF).copy(alpha = 0.3f),
                        onClick = { onClick(KeyPadEntry.BackSpace) })

                    is KeyPadEntry.Char -> KeypadButton(
                        text = item.value,
                        onClick = {
                            onClick(KeyPadEntry.Char(it))
                        })

                    KeyPadEntry.EmptyKey -> KeypadButton(modifier = Modifier,
                        text = "",
                        color = MaterialTheme.colorScheme.onPrimary,
                        onClick = {}
                    )

                }
            }
        })
}

@Composable
fun KeypadButton(
    text: String,
    modifier: Modifier = Modifier,
    imageId: Int? = null,
    textStyle: TextStyle = MaterialTheme.typography.headlineLarge,
    color: Color = primaryFixed,
    onClick: (key: String) -> Unit
) {
    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(color = color, shape = RoundedCornerShape(32.dp))
                .clickable { onClick(text) }), contentAlignment = Alignment.Center
    ) {
        imageId?.let { image ->
            Image(
                painter = painterResource(id = image), contentDescription = null
            )
        } ?: run {
            Text(
                text = text, style = textStyle, color = onPrimaryFixed
            )
        }
    }
}
package com.androidace.spendanalyser.ui.components.pin

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidace.spendanalyser.R
import com.androidace.spendanalyser.ui.components.pin.model.KeyPadEntry
import com.androidace.spendanalyser.ui.components.pin.model.PinCellModel
import com.androidace.spendanalyser.ui.components.pin.model.PinState
import com.androidace.spendanalyser.ui.components.pin.model.pinKeypadKeys
import kotlinx.collections.immutable.PersistentList

@Composable
fun PinLayout(
    it: PaddingValues,
    sectionTitle: Int,
    timer: String,
    otpPinState: PinState,
    pinCellList: PersistentList<PinCellModel>,
    onValueChange: (KeyPadEntry) -> Unit,
    resetTimer: () -> Unit,
    @DrawableRes deleteImage: Int,
    modifier: Modifier = Modifier,
) {
    val resendEnabled = timer == "00:00"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(it)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(R.drawable.ic_sa_screen), contentDescription = "Icon SA")
        Text(
            text = stringResource(id = sectionTitle),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        PinInputLayout(pinCellList = pinCellList, otpPinState)
        if (otpPinState.attemptMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = otpPinState.attemptMessage,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        SAPinKeypad(
            onClick = onValueChange,
            deleteImage = deleteImage,
            modifier = modifier.padding(horizontal = 36.dp, vertical = 16.dp),
            keys = pinKeypadKeys
        )
    }

}
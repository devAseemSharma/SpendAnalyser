package com.androidace.spendanalyser.ui.components.pin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.androidace.spendanalyser.ui.components.pin.model.PinCellModel
import com.androidace.spendanalyser.ui.components.pin.model.PinState
import com.androidace.spendanalyser.ui.components.pin.model.PinVerifyState
import com.androidace.spendanalyser.ui.theme.success
import kotlinx.collections.immutable.PersistentList

private val MIN_HEIGHT_CELL_SIZE = 18.dp

@Composable
fun PinInputLayout(
    pinCellList: PersistentList<PinCellModel>,
    otpPinState: PinState,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.then(Modifier.fillMaxWidth())
    ) {

        repeat(pinCellList.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(MIN_HEIGHT_CELL_SIZE)
                    .border(0.dp, Color.Transparent, CircleShape)
                    .shadow(4.dp, shape = CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
            ) {
                if (pinCellList[it].data.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                getColorBasedOnPinState(otpPinState = otpPinState), CircleShape
                            )
                    )
                }
            }
            if (it < (pinCellList.size - 1)) {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun getColorBasedOnPinState(otpPinState: PinState): Color {
    return when (otpPinState.pinStatus) {
        PinVerifyState.UN_VERIFIED -> {
            MaterialTheme.colorScheme.primary
        }

        PinVerifyState.SUCCESS -> {
            success
        }

        PinVerifyState.ERROR -> {
            MaterialTheme.colorScheme.error
        }

        else -> {
            Color.Transparent
        }
    }
}
package com.androidace.spendanalyser.ui.components.buttons

import android.util.Log
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androidace.spendanalyser.ui.theme.primary
import com.androidace.spendanalyser.ui.theme.stateLayerOnSurfaceOpacity12
import com.androidace.spendanalyser.ui.theme.statePrimaryContainer
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun FilledButton(
    buttonText: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors()
            .copy(
                containerColor = primary,
                disabledContainerColor = stateLayerOnSurfaceOpacity12,
                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ), modifier = modifier
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}

data class Separator(
    override val title: String
) : SegmentData


@Composable
fun <T : SegmentData> AnimatedSegmentsBar(
    tabList: List<T>,
    defaultSelectedItemIndex: Int = 0,
    backgroundColor: Color,
    onTabSelected: (tabPage: T) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = remember { mutableStateOf(defaultSelectedItemIndex) }

    Column(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(16.dp),
                color = statePrimaryContainer
            )
            .padding(4.dp)
    ) {
        TabRow(
            selectedTabIndex = selectedIndex.value,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = Color.Transparent,
            divider = {},

            indicator = { tabPositions ->
                SegmentSelectionIndicator(
                    tabPositions.toPersistentList(),
                    selectedIndex.value,
                    tabList[selectedIndex.value].title
                )
            },
            modifier = Modifier
                .height(48.dp)
        ) {
            repeat(tabList.size) {
                SegmentButton(
                    title = tabList[it].title,
                    textColor = if (it == selectedIndex.value) backgroundColor else Color(0XFF472977),
                    onClick = {
                        onTabSelected(tabList[it])
                        selectedIndex.value = it
                    }
                )
            }


        }
    }
}

@Composable
fun SegmentSelectionIndicator(
    tabPositions: PersistentList<TabPosition>,
    selectedIndex: Int,
    tabName: String,
    modifier: Modifier = Modifier
) {
    // this is the  state we will update while dragging

    val transition = updateTransition(
        selectedIndex,
        label = "Tab indicator"
    )
    val indicatorLeft by transition.animateDp(
        transitionSpec = {
            tween()
        },
        label = "Indicator left"
    ) { page ->
        tabPositions[page].left
    }
    val indicatorRight by transition.animateDp(
        transitionSpec = {
            tween()
        },
        label = "Indicator right"
    ) { page ->
        tabPositions[page].right
    }

    Box(
        modifier = modifier
            .wrapContentSize(align = Alignment.CenterStart)
            .offset(indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                RoundedCornerShape(16.dp)
            )
    ) {
        Text(
            text = tabName,
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .align(
                    Alignment.Center
                )
                .padding(vertical = 12.dp)
        )
    }
}

@Composable
fun SegmentButton(
    title: String,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    color = statePrimaryContainer,
                    bounded = true,
                    radius = 65.dp
                )
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium.copy(color = textColor))
    }
}


interface SegmentData {
    val title: String
}

@Preview
@Composable
fun AnimatedSegmentedButton() {
    val options =
        listOf(Separator("Favorites"), Separator("Trending"), Separator("Saved"))
    Column {
        FilledButton(
            buttonText = "Label",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AnimatedSegmentsBar(
            tabList = options,
            backgroundColor = statePrimaryContainer,
            defaultSelectedItemIndex = 0,
            onTabSelected = {
                Log.d("Item Selected", it.title)
            },
            modifier = Modifier.padding(16.dp)
        )
    }

}
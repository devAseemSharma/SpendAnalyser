package com.androidace.spendanalyser.ui.components.pin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints

@Composable
fun PinKeyPadLayout(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    val numColumn = 3
    Layout(
        modifier = modifier, content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val maxWidth = constraints.maxWidth - maxPadding.toPx()
        val maxHeight = constraints.maxHeight - maxPadding.toPx()
        val singleItemWidth = (maxWidth / numColumn).toInt()
        val singleItemHeight = (maxHeight / 4).toInt()
        val count = measurables.size
        val placeables = measurables.mapIndexed { index, measurable ->
            // Measure each children
            val lastItemHeight = if (count % 2 == 0) {
                singleItemHeight
            } else {
                (singleItemHeight * 2) + singleItemPadding.toPx().toInt()
            }
            measurable.measure(
                Constraints.fixed(
                    singleItemWidth, if (index == count - 1) {
                        lastItemHeight
                    } else {
                        singleItemHeight
                    }
                )
            )
        }
        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Track the y co-ord we have placed children up to
            var yPosition = 0
            var xPosition = 0
            // Place children in the parent layout
            placeables.chunked(4).forEach { singleRow ->
                // Position item on the screen
                singleRow.forEach {
                    it.placeRelative(x = xPosition, y = yPosition)
                    yPosition += singleItemHeight + singleItemPadding.toPx().toInt()
                }
                // Record the y co-ord placed up to
                // yPosition += singleItemHeight
                yPosition = 0
                xPosition += singleItemWidth + singleItemPadding.toPx().toInt()
            }
        }
    }
}
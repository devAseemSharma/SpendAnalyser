package com.androidace.spendanalyser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.launch

@Immutable
sealed interface StateEvent {

    @Immutable
    data object Triggered : StateEvent

    @Immutable
    data object Consumed : StateEvent

}

@Immutable
sealed interface StateEventWithData<T> {

    @Immutable
    class Triggered<T>(val data: T) : StateEventWithData<T>

    @Immutable
    class Consumed<T> : StateEventWithData<T>

}

fun <T> triggered(data: T) = StateEventWithData.Triggered(data)
fun <T> consumed() = StateEventWithData.Consumed<T>()


@Composable
fun NavigationEventEffect(event: StateEvent, onConsumed: () -> Unit, action: suspend () -> Unit) {
    val consumedCallback by rememberUpdatedState(newValue = onConsumed)
    val actionCallback by rememberUpdatedState(newValue = action)
    val lifecycle = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    DisposableEffect(key1 = event, key2 = onConsumed) {
        val observer = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                scope.launch {
                    if (event is StateEvent.Triggered) {
                        consumedCallback()
                        actionCallback()
                    }
                }
            }
        }
        lifecycle.lifecycle.addObserver(observer)
        onDispose {
            lifecycle.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun <T> NavigationEventEffect(
    event: StateEventWithData<T>,
    onConsumed: () -> Unit,
    action: suspend (T) -> Unit
) {
    val consumedCallback by rememberUpdatedState(newValue = onConsumed)
    val actionCallback by rememberUpdatedState(newValue = action)
    val lifecycle = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    DisposableEffect(key1 = event, key2 = onConsumed) {
        val observer = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                scope.launch {
                    if (event is StateEventWithData.Triggered<T>) {
                        consumedCallback()
                        actionCallback(event.data)
                    }
                }
            }
        }
        lifecycle.lifecycle.addObserver(observer)
        onDispose {
            lifecycle.lifecycle.removeObserver(observer)
        }
    }
}
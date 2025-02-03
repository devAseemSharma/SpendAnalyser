package com.androidace.spendanalyser.ui.components.pin.model

data class PinState(
    val pinStatus: PinVerifyState,
    val numberOfAttempts: Int,
    val remainingAttempts: Int,
    val attemptMessage: String = "",
    val maxAttempt: MaxAttemptState?
)

data class MaxAttemptState(
    val message: String
)


enum class PinVerifyState {
    UN_VERIFIED, SUCCESS, ERROR, MAX_ATTEMPT_REACHED
}
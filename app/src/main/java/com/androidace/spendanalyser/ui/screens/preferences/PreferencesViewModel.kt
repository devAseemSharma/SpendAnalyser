package com.androidace.spendanalyser.ui.screens.preferences

import androidx.lifecycle.ViewModel
import com.androidace.spendanalyser.ui.components.common.UIStateHandlerImpl
import com.androidace.spendanalyser.ui.components.common.UiStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val uiStateHandlerImpl: UIStateHandlerImpl
) : ViewModel(), UiStateHandler by uiStateHandlerImpl {

}
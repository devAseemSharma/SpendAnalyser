package com.androidace.spendanalyser.di

import com.androidace.spendanalyser.ui.components.common.UIStateHandlerImpl
import com.androidace.spendanalyser.ui.components.common.UiStateHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun provideUiStateHandler(impl: UIStateHandlerImpl): UiStateHandler
}
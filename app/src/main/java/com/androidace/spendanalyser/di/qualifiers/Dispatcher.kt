package com.androidace.spendanalyser.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val hellGroupDispatchers: SADispatchers)
enum class SADispatchers {
    Default,
    IO,
}
package com.kok1337.glpm_local_mobile_app.di.activity

import com.kok1337.glpm_local_mobile_app.glue.toolbar.DefaultToolbarNavigator
import com.kok1337.toolbar.presentation.navigator.ToolbarNavigator
import dagger.Binds
import dagger.Module

@Module
internal interface MainActivityModule {
    companion object {
    }

    @Binds
    @Suppress("FunctionName")
    fun DefaultToolbarNavigator_to_ToolbarNavigator(impl: DefaultToolbarNavigator): ToolbarNavigator
}
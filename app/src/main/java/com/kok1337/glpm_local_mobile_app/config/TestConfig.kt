package com.kok1337.glpm_local_mobile_app.config

import com.kok1337.glpm_local_mobile_app.di.AppConfig
import com.kok1337.main_activity.di.deps.InitialFragmentFactory
import com.kok1337.toolbar.presentation.fragment.ToolbarFragment

internal object TestConfig : AppConfig {
    override val initialFragmentFactory: InitialFragmentFactory get() = InitialFragmentFactory { ToolbarFragment() }
}
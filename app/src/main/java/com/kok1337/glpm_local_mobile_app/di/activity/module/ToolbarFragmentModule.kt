package com.kok1337.glpm_local_mobile_app.di.activity.module

import com.kok1337.glpm_local_mobile_app.di.activity.MainActivityComponent
import com.kok1337.providing_dependencies.Dependencies
import com.kok1337.providing_dependencies.DependenciesKey
import com.kok1337.toolbar.di.ToolbarFragmentComponentDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface ToolbarFragmentModule {
    @Binds
    @IntoMap
    @DependenciesKey(ToolbarFragmentComponentDeps::class)
    fun bindFeaturePpnDescriptionFragmentDeps(impl: MainActivityComponent): Dependencies
}
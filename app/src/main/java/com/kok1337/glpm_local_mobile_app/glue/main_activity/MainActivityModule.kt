package com.kok1337.glpm_local_mobile_app.glue.main_activity

import com.kok1337.glpm_local_mobile_app.di.AppComponent
import com.kok1337.glpm_local_mobile_app.di.AppScope
import com.kok1337.main_activity.di.MainActivityDeps
import com.kok1337.providing_dependencies.Dependencies
import com.kok1337.providing_dependencies.DependenciesKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface MainActivityModule {
    @Binds
    @IntoMap
    @DependenciesKey(MainActivityDeps::class)
    fun bindMainActivityDeps(impl: AppComponent): Dependencies
}
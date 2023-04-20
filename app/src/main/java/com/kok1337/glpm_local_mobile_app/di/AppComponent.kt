package com.kok1337.glpm_local_mobile_app.di

import com.kok1337.glpm_local_mobile_app.App
import com.kok1337.glpm_local_mobile_app.glue.main_activity.MainActivityModule
import com.kok1337.main_activity.di.MainActivityDeps
import dagger.Component

@AppScope
@Component(
    dependencies = [AppConfig::class],
    modules = [
        AppModule::class,
        MainActivityModule::class,
    ]
)
internal interface AppComponent : MainActivityDeps {
    @Component.Factory
    interface Factory {
        fun create(
            appConfig: AppConfig,
        ): AppComponent
    }

    fun inject(app: App)
}
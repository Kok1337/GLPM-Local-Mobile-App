package com.kok1337.glpm_local_mobile_app

import android.app.Application
import android.util.Log
import com.kok1337.glpm_local_mobile_app.config.TestConfig
import com.kok1337.glpm_local_mobile_app.di.DaggerAppComponent
import com.kok1337.providing_dependencies.DepsMap
import com.kok1337.providing_dependencies.HasDependencies
import javax.inject.Inject

class App : Application(), HasDependencies {
    @Inject
    override lateinit var depsMap: DepsMap

    override fun onCreate() {
        super.onCreate()
        val appConfig = TestConfig
        DaggerAppComponent.factory()
            .create(appConfig)
            .inject(this)
        Log.e("App", depsMap.toString())
    }
}
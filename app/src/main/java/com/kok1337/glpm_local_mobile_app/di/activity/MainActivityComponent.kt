package com.kok1337.glpm_local_mobile_app.di.activity

import androidx.fragment.app.FragmentManager
import com.kok1337.glpm_local_mobile_app.di.activity.module.ToolbarFragmentModule
import com.kok1337.glpm_local_mobile_app.presentation.activity.MainActivity
import com.kok1337.toolbar.di.ToolbarFragmentComponentDeps
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        MainActivityComponentDeps::class,
    ],
    modules = [
        MainActivityModule::class,
        ToolbarFragmentModule::class,
    ]
)
@MainActivityScope
internal interface MainActivityComponent :
    ToolbarFragmentComponentDeps {
    @Component.Factory
    interface Factory {
        fun create(
            deps: MainActivityComponentDeps,
            @BindsInstance supportFragmentManager: FragmentManager,
        ): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}
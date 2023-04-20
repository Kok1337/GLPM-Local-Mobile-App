package com.kok1337.main_activity.di

import com.kok1337.main_activity.presentation.activity.MainActivity
import dagger.Component

@MainActivityScope
@Component(
    dependencies = [MainActivityDeps::class],
    modules = [
        MainActivityModule::class,
    ]
)
internal interface MainActivityComponent {
    @Component.Factory
    interface Factory {
        fun create(
            mainActivityDeps: MainActivityDeps,
        ): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}
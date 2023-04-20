package com.kok1337.main_activity.di

import com.kok1337.main_activity.di.deps.InitialFragmentFactory
import com.kok1337.providing_dependencies.Dependencies

interface MainActivityDeps : Dependencies {
    val initialFragmentFactory: InitialFragmentFactory
}
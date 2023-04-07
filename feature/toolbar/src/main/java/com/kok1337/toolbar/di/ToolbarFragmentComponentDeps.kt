package com.kok1337.toolbar.di

import com.kok1337.providing_dependencies.Dependencies
import com.kok1337.toolbar.presentation.navigator.ToolbarNavigator

interface ToolbarFragmentComponentDeps : Dependencies {
    val toolbarNavigator: ToolbarNavigator
}
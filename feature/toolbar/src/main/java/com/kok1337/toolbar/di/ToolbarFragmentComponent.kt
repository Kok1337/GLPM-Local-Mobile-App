package com.kok1337.toolbar.di

import com.kok1337.toolbar.presentation.fragment.ToolbarFragment
import dagger.Component

@Component(
    dependencies = [
        ToolbarFragmentComponentDeps::class,
    ],
    modules = [
        ToolbarFragmentModule::class,
    ]
)
@ToolbarFragmentScope
internal interface ToolbarFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            deps: ToolbarFragmentComponentDeps,
        ): ToolbarFragmentComponent
    }

    fun inject(toolbarFragment: ToolbarFragment)
}
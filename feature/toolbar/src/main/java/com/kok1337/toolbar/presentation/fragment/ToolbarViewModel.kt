package com.kok1337.toolbar.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kok1337.toolbar.presentation.navigator.ToolbarNavigator
import javax.inject.Inject

internal class ToolbarViewModel(
    val toolbarNavigator: ToolbarNavigator,
) : ViewModel() {
    class Factory @Inject constructor(
        private val toolbarNavigator: ToolbarNavigator,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ToolbarViewModel::class.java)
            return ToolbarViewModel(
                toolbarNavigator = toolbarNavigator,
            ) as T
        }
    }

    fun openMap() = toolbarNavigator.launchMapScreen()
    fun openCamera() = toolbarNavigator.launchCameraScreen()
    fun openWorkTypes() = toolbarNavigator.launchWorkTypesScreen()
    fun openSynchronization() = toolbarNavigator.launchSynchronizationScreen()
    fun openSettings() = toolbarNavigator.launchSettingsScreen()
}
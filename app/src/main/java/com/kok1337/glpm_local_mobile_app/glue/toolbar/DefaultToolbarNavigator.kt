package com.kok1337.glpm_local_mobile_app.glue.toolbar

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.kok1337.glpm_local_mobile_app.di.activity.MainActivityScope
import com.kok1337.map.presentation.fragment.MapFragment
import com.kok1337.termux_preparation.presentation.fragment.TermuxPreparationFragment
import com.kok1337.toolbar.R
import com.kok1337.toolbar.presentation.navigator.ToolbarNavigator
import javax.inject.Inject

@MainActivityScope
internal class DefaultToolbarNavigator @Inject constructor(
    private val supportFragmentManager: FragmentManager,
) : ToolbarNavigator {
    init {
        supportFragmentManager.commit {
            Log.e("DefaultToolbarNavigator", "  supportFragmentManager.commitNow ")
            add(R.id.toolbar_fragment_container, TermuxPreparationFragment())
        }
    }

    override fun goBack() {
        TODO("Not yet implemented")
    }

    override fun launchMapScreen() {
        supportFragmentManager.commit {
            val fragment = MapFragment()
            addToBackStack(fragment.javaClass.name)
            replace(R.id.toolbar_fragment_container, fragment)
        }
    }

    override fun launchCameraScreen() {
        TODO("Not yet implemented")
    }

    override fun launchWorkTypesScreen() {
        TODO("Not yet implemented")
    }

    override fun launchSynchronizationScreen() {
        TODO("Not yet implemented")
    }

    override fun launchSettingsScreen() {
        TODO("Not yet implemented")
    }

    override fun setupStartFragment() {
        TODO("Not yet implemented")
    }
}
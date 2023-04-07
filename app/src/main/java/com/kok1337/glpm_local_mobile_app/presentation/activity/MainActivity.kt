package com.kok1337.glpm_local_mobile_app.presentation.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kok1337.glpm_local_mobile_app.R
import com.kok1337.glpm_local_mobile_app.di.activity.DaggerMainActivityComponent
import com.kok1337.glpm_local_mobile_app.di.activity.MainActivityComponentDeps
import com.kok1337.providing_dependencies.DepsMap
import com.kok1337.providing_dependencies.HasDependencies
import com.kok1337.toolbar.presentation.fragment.ToolbarFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), HasDependencies {
    @Inject
    override lateinit var depsMap: DepsMap

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        DaggerMainActivityComponent.factory().create(
            deps = object : MainActivityComponentDeps {
                                                      init {
                                                          Log.e("MainActivity", "Component created")
                                                      }
                                                      },
            supportFragmentManager = supportFragmentManager,
        ).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.addOnBackStackChangedListener {
            val backStackEntryCount = supportFragmentManager.backStackEntryCount
            Log.e("DefaultToolbarNavigator", "OnBackStackChangedListener $backStackEntryCount")
            (0 until  backStackEntryCount).forEach { index ->
                val entity = supportFragmentManager.getBackStackEntryAt(index)
                Log.e("DefaultToolbarNavigator", "${entity.name}")
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, ToolbarFragment())
                .commitNow()
        }
    }
}
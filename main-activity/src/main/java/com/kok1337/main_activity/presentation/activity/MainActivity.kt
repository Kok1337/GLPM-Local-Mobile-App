package com.kok1337.main_activity.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kok1337.main_activity.R
import com.kok1337.main_activity.di.DaggerMainActivityComponent
import com.kok1337.main_activity.di.deps.InitialFragmentFactory
import com.kok1337.providing_dependencies.findDependencies
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var initialFragmentFactory: InitialFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainActivityComponent.factory()
            .create(findDependencies())
            .inject(this)

        Log.e("MainActivity", initialFragmentFactory.toString())

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, initialFragmentFactory.create())
                .commitNow()
        }
    }
}
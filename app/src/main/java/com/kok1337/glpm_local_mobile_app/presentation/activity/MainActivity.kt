package com.kok1337.glpm_local_mobile_app.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kok1337.glpm_local_mobile_app.R
import com.kok1337.toolbar.presentation.fragment.ToolbarFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, ToolbarFragment())
                .commitNow()
        }
    }
}
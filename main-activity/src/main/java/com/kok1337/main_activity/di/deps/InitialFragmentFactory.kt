package com.kok1337.main_activity.di.deps

import androidx.fragment.app.Fragment

fun interface InitialFragmentFactory {
    fun create(): Fragment
}
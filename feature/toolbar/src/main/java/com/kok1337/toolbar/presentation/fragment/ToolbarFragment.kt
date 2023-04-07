package com.kok1337.toolbar.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.providing_dependencies.findDependencies
import com.kok1337.toolbar.R
import com.kok1337.toolbar.databinding.FragmentToolbarBinding
import com.kok1337.toolbar.di.DaggerToolbarFragmentComponent
import dagger.Lazy
import javax.inject.Inject

class ToolbarFragment : Fragment(R.layout.fragment_toolbar) {
    private val binding by viewBinding(FragmentToolbarBinding::bind)

    @Inject
    internal lateinit var viewModelFactory: Lazy<ToolbarViewModel.Factory>
    private val viewModel: ToolbarViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerToolbarFragmentComponent.factory().create(
            deps = findDependencies()
        ).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("ToolbarFragment", "${viewModel.toolbarNavigator}")
        binding.apply {
            toolbarMap.setOnClickListener { viewModel.openMap() }
            toolbarCamera.setOnClickListener { viewModel.openCamera() }
            toolbarWorkTypes.setOnClickListener { viewModel.openWorkTypes() }
            toolbarSynchronization.setOnClickListener { viewModel.openSynchronization() }
            toolbarSettings.setOnClickListener { viewModel.openSettings() }
        }
    }
}
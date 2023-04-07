package com.kok1337.toolbar.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kok1337.toolbar.R
import com.kok1337.toolbar.presentation.view.ToolbarIcon

class ToolbarFragment : Fragment(R.layout.fragment_toolbar) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ToolbarIcon>(R.id.toolbar_camera).isEnabled = false
        view.findViewById<ToolbarIcon>(R.id.toolbar_edit).isChecked = true
        view.findViewById<ToolbarIcon>(R.id.toolbar_save).isSelected = true
    }
}
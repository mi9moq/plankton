package com.example.plancton.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plancton.R
import com.example.plancton.databinding.FragmentEntryBinding

class EntryFragment : Fragment() {

    private var _binding: FragmentEntryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            getString(R.string.null_binding)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
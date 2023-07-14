package com.example.plancton.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plancton.databinding.FragmentEditUserDataBinding

class EditUserDataFragment : Fragment() {

    private var _binding: FragmentEditUserDataBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }
}
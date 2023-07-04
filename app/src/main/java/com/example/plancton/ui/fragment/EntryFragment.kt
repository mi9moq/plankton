package com.example.plancton.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plancton.PlanctonApp
import com.example.plancton.R
import com.example.plancton.databinding.FragmentEntryBinding
import com.example.plancton.presentation.viewmodel.EntryViewModel
import com.example.plancton.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class EntryFragment : Fragment() {

    private var _binding: FragmentEntryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            getString(R.string.null_binding)
        }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EntryViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as PlanctonApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEntryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            bSignIn.setOnClickListener {
                viewModel.openLogin()
            }

            bSignUp.setOnClickListener {
                viewModel.openRegistration()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
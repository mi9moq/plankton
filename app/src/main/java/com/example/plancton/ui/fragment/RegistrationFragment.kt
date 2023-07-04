package com.example.plancton.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plancton.PlanctonApp
import com.example.plancton.R
import com.example.plancton.databinding.FragmentRegistrationBinding
import com.example.plancton.domain.entity.ErrorType
import com.example.plancton.domain.entity.RegistrationRequest
import com.example.plancton.presentation.state.RegistrationState
import com.example.plancton.presentation.state.RegistrationState.Error
import com.example.plancton.presentation.state.RegistrationState.Initial
import com.example.plancton.presentation.state.RegistrationState.Loading
import com.example.plancton.presentation.viewmodel.RegistrationViewModel
import com.example.plancton.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            getString(R.string.null_binding)
        }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]
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
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            bSignUp.setOnClickListener {
                val registrationRequest = getRegistrationRequest()

                viewModel.register(registrationRequest)
            }
        }
    }

    private fun getRegistrationRequest(): RegistrationRequest =
        with(binding) {
            val fullName = etFullName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            return RegistrationRequest(email, fullName, password)
        }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun applyState(state: RegistrationState) {
        when (state) {
            Initial -> Unit

            is Loading -> applyLoadingState()

            is Error -> applyErrorState(state.errorType)
        }
    }

    private fun applyLoadingState() {
        with(binding) {
            tvError.isVisible = false
            progressBar.isVisible = true

            tilFullName.isVisible = false
            tilEmail.isVisible = false
            tilPassword.isVisible = false
            bSignUp.isVisible = false
        }
    }

    private fun applyErrorState(errorType: ErrorType) {
        setErrorVisibility()

        when (errorType) {
            ErrorType.UNKNOWN -> binding.tvError.text = getString(R.string.error_unknown)
        }
    }

    private fun setErrorVisibility() {
        with(binding) {
            tvError.isVisible = true
            progressBar.isVisible = false

            tilFullName.isVisible = false
            tilEmail.isVisible = false
            tilPassword.isVisible = false
            bSignUp.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
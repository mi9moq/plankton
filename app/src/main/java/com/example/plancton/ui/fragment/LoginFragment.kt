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
import com.example.plancton.databinding.FragmentLoginBinding
import com.example.plancton.domain.entity.Auth
import com.example.plancton.domain.entity.ErrorType
import com.example.plancton.domain.entity.ErrorType.UNKNOWN
import com.example.plancton.presentation.ViewModelFactory
import com.example.plancton.presentation.login.LoginState
import com.example.plancton.presentation.login.LoginState.Error
import com.example.plancton.presentation.login.LoginState.Initial
import com.example.plancton.presentation.login.LoginState.Loading
import com.example.plancton.presentation.login.LoginViewModel
import javax.inject.Inject

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            bSignIn.setOnClickListener {
                val auth = getAuth()

                viewModel.login(auth)
            }
        }
    }

    private fun getAuth(): Auth =
        with(binding) {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            return Auth(email, password)
        }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun applyState(state: LoginState) {
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

            tilEmail.isVisible = false
            tilPassword.isVisible = false
            bSignIn.isVisible = false
        }
    }

    private fun applyErrorState(errorType: ErrorType) {
        setErrorVisibility()

        when (errorType) {
            UNKNOWN -> binding.tvError.text = getString(R.string.error_unknown)
        }
    }

    private fun setErrorVisibility() {
        with(binding) {
            tvError.isVisible = true
            progressBar.isVisible = false

            tilEmail.isVisible = false
            tilPassword.isVisible = false
            bSignIn.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
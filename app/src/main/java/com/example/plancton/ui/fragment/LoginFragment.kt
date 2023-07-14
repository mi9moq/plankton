package com.example.plancton.ui.fragment

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plancton.PlanctonApp
import com.example.plancton.R
import com.example.plancton.core.auth.domain.entity.AuthErrorType
import com.example.plancton.core.auth.domain.entity.AuthErrorType.CONNECTION
import com.example.plancton.core.auth.domain.entity.AuthErrorType.UNKNOWN
import com.example.plancton.core.auth.domain.entity.AuthErrorType.USER_NOT_FOUND
import com.example.plancton.core.auth.domain.entity.AuthErrorType.WRONG_EMAIL
import com.example.plancton.core.auth.domain.entity.AuthErrorType.WRONG_PASSWORD
import com.example.plancton.databinding.FragmentLoginBinding
import com.example.plancton.presentation.ViewModelFactory
import com.example.plancton.presentation.login.LoginState
import com.example.plancton.presentation.login.LoginState.Error
import com.example.plancton.presentation.login.LoginState.Initial
import com.example.plancton.presentation.login.LoginState.Loading
import com.example.plancton.presentation.login.LoginViewModel
import com.example.plancton.ui.animation.shake
import com.example.plancton.ui.utils.addTextWatcher
import javax.inject.Inject

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

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

        addTextChangeListener()

        initListeners()

        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            bSignIn.setOnClickListener {
                viewModel.login(etEmail.text.toString(), etPassword.text.toString())
            }
            signUp.setOnClickListener {
                viewModel.openSignup()
            }
        }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun addTextChangeListener() {
        with(binding) {
            etEmail.addTextChangedListener(addTextWatcher(viewModel::resetError))
            etPassword.addTextChangedListener(addTextWatcher(viewModel::resetError))
        }
    }

    private fun applyState(state: LoginState) {
        when (state) {
            Initial -> applyInitialState()

            is Loading -> applyLoadingState()

            is Error -> applyErrorState(state.authErrorType)
        }
    }

    private fun applyInitialState() {
        with(binding) {
            tilEmail.error = null
            tilPassword.error = null
        }
    }

    private fun applyLoadingState() {

        with(binding) {
            contentContainer.isVisible = false
            tvError.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun applyErrorState(authErrorType: AuthErrorType) {

        with(binding) {
            contentContainer.isVisible = true
            tvError.isVisible = true
            tvError.setTextColor(ColorStateList.valueOf(Color.RED))
            progressBar.isVisible = false
        }

        when (authErrorType) {
            CONNECTION -> showInternetError()

            USER_NOT_FOUND -> showUserNotFound()

            UNKNOWN -> showUnknownError()
            WRONG_EMAIL -> showWrongEmail()
            WRONG_PASSWORD -> showWrongPassword()
        }
    }

    private fun showInternetError() {
        with(binding) {
            tvError.text = getString(R.string.error_internet)
        }
    }

    private fun showUserNotFound() {
        with(binding) {
            tilEmail.error = getString(R.string.user_not_found)
        }
    }

    private fun showWrongEmail() {
        binding.tilEmail.apply {
            error = getString(R.string.wrong_email)
            shake()
        }
    }

    private fun showWrongPassword() {
        binding.tilPassword.apply {
            error = getString(R.string.wrong_password)
            shake()
        }
    }

    private fun showUnknownError() {
        with(binding) {
            tvError.text = getString(R.string.error_unknown)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
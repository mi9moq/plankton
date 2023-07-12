package com.example.plancton.feature.auth.login.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.plancton.core.auth.domain.entity.Auth
import com.example.plancton.core.auth.domain.entity.AuthErrorType
import com.example.plancton.core.auth.domain.entity.AuthErrorType.HTTP400
import com.example.plancton.core.auth.domain.entity.AuthErrorType.HTTP401
import com.example.plancton.core.auth.domain.entity.AuthErrorType.INTERNET
import com.example.plancton.core.auth.domain.entity.AuthErrorType.UNKNOWN
import com.example.plancton.feature.auth.login.R
import com.example.plancton.feature.auth.login.databinding.FragmentLoginBinding
import com.example.plancton.feature.auth.login.di.component.getComponent
import com.example.plancton.feature.auth.login.presentation.LoginState
import com.example.plancton.feature.auth.login.presentation.LoginState.Error
import com.example.plancton.feature.auth.login.presentation.LoginState.Initial
import com.example.plancton.feature.auth.login.presentation.LoginState.Loading
import com.example.plancton.feature.auth.login.presentation.LoginViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: LoginViewModel by lazy {
        getComponent().viewModel()
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

            is Error -> applyErrorState(state.authErrorType)
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

        //TODO убрать фокус с полей ввода на всех фрагментах где они есть при переходе между стейтами
        with(binding) {
            contentContainer.isVisible = true
            tvError.isVisible = true
            tvError.setTextColor(ColorStateList.valueOf(Color.RED))
            progressBar.isVisible = false
        }

        when (authErrorType) {
            INTERNET -> showInternetError()

            //TODO другие названия функций для 400 и 401
            HTTP400 -> showEnteredDataError()

            HTTP401 -> showInvalidDataError()

            UNKNOWN -> showUnknownError()
        }
    }

    private fun showInternetError() {
        with(binding) {
            //TODO добавить картинку
            //TODO добавить анимации
            tvError.text = getString(R.string.error_internet)
        }
    }

    private fun showEnteredDataError() {
        with(binding) {
            tvError.text = getString(R.string.error_entered_data)
        }
    }

    private fun showInvalidDataError() {
        with(binding) {
            tvError.text = getString(R.string.error_invalid_data)
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
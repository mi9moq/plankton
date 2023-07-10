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
import com.example.plancton.databinding.FragmentRegistrationBinding
import com.example.plancton.core.auth.domain.entity.AuthErrorType
import com.example.plancton.core.auth.domain.entity.AuthErrorType.HTTP400
import com.example.plancton.core.auth.domain.entity.AuthErrorType.HTTP401
import com.example.plancton.core.auth.domain.entity.AuthErrorType.INTERNET
import com.example.plancton.core.auth.domain.entity.AuthErrorType.UNKNOWN
import com.example.plancton.core.auth.domain.entity.RegistrationRequest
import com.example.plancton.presentation.ViewModelFactory
import com.example.plancton.presentation.registration.RegistrationState
import com.example.plancton.presentation.registration.RegistrationState.Error
import com.example.plancton.presentation.registration.RegistrationState.Initial
import com.example.plancton.presentation.registration.RegistrationState.Loading
import com.example.plancton.presentation.registration.RegistrationViewModel
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding
        get() = _binding!!

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
        with(binding) {
            contentContainer.isVisible = true
            tvError.isVisible = true
            tvError.setTextColor(ColorStateList.valueOf(Color.RED))
            progressBar.isVisible = false
        }

        when (authErrorType) {
            INTERNET -> showInternetError()

            //TODO другие названия функций для 400
            HTTP400 -> showEnteredDataError()

            UNKNOWN -> showUnknownError()

            HTTP401 -> Unit //Не приходит с бэка
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
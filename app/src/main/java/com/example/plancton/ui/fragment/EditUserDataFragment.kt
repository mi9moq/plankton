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
import com.example.plancton.core.user.domain.entity.User
import com.example.plancton.core.user.domain.entity.UserErrorType
import com.example.plancton.core.user.domain.entity.UserErrorType.CONNECTION
import com.example.plancton.core.user.domain.entity.UserErrorType.NOT_FOUND
import com.example.plancton.core.user.domain.entity.UserErrorType.UNAUTHORIZED
import com.example.plancton.core.user.domain.entity.UserErrorType.UNKNOWN
import com.example.plancton.databinding.FragmentEditUserDataBinding
import com.example.plancton.presentation.ViewModelFactory
import com.example.plancton.presentation.user.UserState
import com.example.plancton.presentation.user.UserState.Content
import com.example.plancton.presentation.user.UserState.Error
import com.example.plancton.presentation.user.UserState.Initial
import com.example.plancton.presentation.user.UserState.Loading
import com.example.plancton.presentation.user.UserViewModel
import com.example.plancton.ui.utils.showUnauthorizedDialog
import javax.inject.Inject

class EditUserDataFragment : Fragment() {

    private var _binding: FragmentEditUserDataBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
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
        _binding = FragmentEditUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            bSave.setOnClickListener {
                val fullName = etFullName.text.toString()
                viewModel.change(fullName)
            }
        }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun applyState(state: UserState) {
        when (state) {
            Initial -> applyInitialState()
            Loading -> applyLoadingState()
            is Content -> applyContentState(state.user)
            is Error -> applyErrorState(state.type)
        }
    }

    private fun applyInitialState() {
        with(binding) {
            errorContainer.isVisible = false
            progressBar.isVisible = false
            contentContainer.isVisible = true
        }
    }

    private fun applyLoadingState() {
        with(binding) {
            errorContainer.isVisible = false
            progressBar.isVisible = true
            contentContainer.isVisible = false
        }
    }

    private fun applyContentState(user: User) {
        with(binding) {
            errorContainer.isVisible = false
            progressBar.isVisible = false
            contentContainer.isVisible = true
            etFullName.setText(user.fullName)
        }
    }

    private fun applyErrorState(type: UserErrorType) {
        with(binding) {
            errorContainer.isVisible = true
            progressBar.isVisible = false
            contentContainer.isVisible = false
        }

        when (type) {
            UNAUTHORIZED -> showUnauthorizedDialog(viewModel::reLogin)
            CONNECTION -> applyConnectionError()
            NOT_FOUND -> Unit
            UNKNOWN -> applyUnknownError()
        }
    }

    private fun applyConnectionError() {
        with(binding) {
            tvError.text = getString(R.string.connection_error_message)
            bError.setOnClickListener {
                viewModel.tryAgain()
            }
        }
    }

    private fun applyUnknownError() {
        with(binding) {
            tvError.text = getString(R.string.unknown_error_message)
            bError.setOnClickListener {
                viewModel.tryAgain()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.example.plancton.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plancton.PlanctonApp
import com.example.plancton.core.user.domain.entity.ChangeUserRequest
import com.example.plancton.core.user.domain.entity.User
import com.example.plancton.databinding.FragmentEditUserDataBinding
import com.example.plancton.presentation.ViewModelFactory
import com.example.plancton.presentation.user.UserState
import com.example.plancton.presentation.user.UserViewModel
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
                val changeUserRequest = getChangeUserRequest()
                viewModel.change(changeUserRequest)
            }
        }
    }

    private fun getChangeUserRequest(): ChangeUserRequest =
        with(binding) {
            val fullName = etFullName.text.toString()
            val email = etEmail.text.toString()
            ChangeUserRequest(fullName, email)
        }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun applyState(state: UserState) {
        when (state) {
            UserState.Initial -> Unit

            is UserState.Content -> applyContentState(state.user)
        }
    }

    private fun applyContentState(user: User) {
        with(binding) {
            etFullName.setText(user.fullName)
            etEmail.setText(user.email)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
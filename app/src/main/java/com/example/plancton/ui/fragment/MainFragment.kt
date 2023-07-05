package com.example.plancton.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plancton.databinding.FragmentMainScreenBinding
import com.example.plancton.domain.entity.UserEvent
import com.example.plancton.presentation.ViewModelFactory
import com.example.plancton.presentation.main.MainState
import com.example.plancton.presentation.main.MainState.Content
import com.example.plancton.presentation.main.MainState.Error
import com.example.plancton.presentation.main.MainState.Initial
import com.example.plancton.presentation.main.MainState.Loading
import com.example.plancton.presentation.main.MainViewModel
import com.example.plancton.ui.activity.MainActivity
import com.example.plancton.ui.adapter.EventAdapter
import javax.inject.Inject

class MainFragment : Fragment() {
    companion object {

        fun newInstance() = MainFragment()
    }

    private val component by lazy {
        (requireActivity() as MainActivity).component
    }

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val eventAdapter = EventAdapter(onClick = { Unit })

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addClickListeners()
        observeViewModel()
    }

    private fun addClickListeners() {
        binding.add.setOnClickListener {
            viewModel.openAdd()
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
    }

    private fun applyState(state: MainState) {
        when (state) {
            Initial -> Unit
            Loading -> applyLoadingState()
            is Content -> applyContentSate(state.content)
            is Error -> applyErrorState(state.message)
        }
    }

    private fun applyErrorState(message: String) {
    }

    private fun applyContentSate(content: List<UserEvent>) {
        binding.eventsList.adapter = eventAdapter
        eventAdapter.submitList(content)
    }

    private fun applyLoadingState() {
    }
}
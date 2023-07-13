package com.example.plancton.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.examlpe.plancton.core.event.domain.entity.EventErrorType
import com.examlpe.plancton.core.event.domain.entity.EventErrorType.CONNECTION
import com.examlpe.plancton.core.event.domain.entity.EventErrorType.NOT_FOUND
import com.examlpe.plancton.core.event.domain.entity.EventErrorType.UNAUTHORIZED
import com.examlpe.plancton.core.event.domain.entity.EventErrorType.UNKNOWN
import com.examlpe.plancton.core.event.domain.entity.UserEvent
import com.example.plancton.R
import com.example.plancton.databinding.FragmentMainScreenBinding
import com.example.plancton.presentation.ViewModelFactory
import com.example.plancton.presentation.main.MainState
import com.example.plancton.presentation.main.MainState.Content
import com.example.plancton.presentation.main.MainState.Error
import com.example.plancton.presentation.main.MainState.Initial
import com.example.plancton.presentation.main.MainState.Loading
import com.example.plancton.presentation.main.MainViewModel
import com.example.plancton.ui.activity.MainActivity
import com.example.plancton.ui.adapter.EventAdapter
import com.example.plancton.ui.utils.showUnauthorizedDialog
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

    private val eventAdapter = EventAdapter(
        onClick = { Unit },
        onLongClickListener = { event ->
            showDeleteDialog(event.id)
        }
    )

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
        setupSwipeRefreshListener()
        observeViewModel()
    }

    private fun addClickListeners() {
        with(binding) {
            add.setOnClickListener {
                viewModel.openAdd()
            }
            showCalendar.setOnClickListener {
                showEventsCalendar()
            }
            showList.setOnClickListener {
                showEventsList()
            }
        }
    }

    private fun showEventsCalendar() {
        with(binding) {
            eventsList.visibility = View.GONE
            calendar.visibility = View.VISIBLE
        }
    }

    private fun showEventsList() {
        with(binding) {
            eventsList.visibility = View.VISIBLE
            calendar.visibility = View.GONE
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
            is Error -> applyErrorState(state.type)
        }
    }

    private fun applyErrorState(type: EventErrorType) {
        with(binding) {
            errorContainer.isVisible = true
            contentContainer.isVisible = false
            swipeLayout.isRefreshing = false
        }
        when (type) {
            UNAUTHORIZED -> showUnauthorizedDialog(viewModel::reLogin)
            CONNECTION -> applyConnectionError()
            NOT_FOUND -> Unit
            UNKNOWN -> applyUnknownError()
        }
    }

    private fun applyContentSate(content: List<UserEvent>) {
        with(binding) {
            contentContainer.isVisible = true
            errorContainer.isVisible = false
            eventsList.adapter = eventAdapter
            eventAdapter.submitList(content)
            swipeLayout.isRefreshing = false
        }
    }

    private fun applyLoadingState() {
        with(binding) {
            swipeLayout.isRefreshing = true
            contentContainer.isVisible = false
            errorContainer.isVisible = false
        }
    }

    private fun setupSwipeRefreshListener() {
        binding.swipeLayout.setOnRefreshListener {
            viewModel.loadEvents()
        }
    }

    private fun showDeleteDialog(id: String) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.delete_event_title)
            setMessage(R.string.delete_event_message)
            setPositiveButton(getString(R.string.positive_button)) { _, _ ->
                viewModel.deleteEvent(id)
            }
            setNegativeButton(getString(R.string.negative_button)) { _, _ ->
            }
            create()
            show()
        }
    }

    private fun applyConnectionError() {
        with(binding) {
            errorMessage.text = getString(R.string.connection_error_message)
            errorButton.setOnClickListener {
                viewModel.loadEvents()
            }
        }
    }

    private fun applyUnknownError() {
        with(binding) {
            errorMessage.text = getString(R.string.unknown_error_message)
            errorButton.setOnClickListener {
                viewModel.loadEvents()
            }
        }
    }
}
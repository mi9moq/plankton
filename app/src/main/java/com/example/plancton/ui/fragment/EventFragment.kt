package com.example.plancton.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plancton.databinding.FragmentCreateBinding
import com.example.plancton.domain.entity.UserEvent
import com.example.plancton.presentation.ViewModelFactory
import com.example.plancton.presentation.event.EventState
import com.example.plancton.presentation.event.EventState.*
import com.example.plancton.presentation.event.EventViewModel
import com.example.plancton.ui.activity.MainActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class EventFragment : Fragment() {

    companion object {

        fun newInstanceAddEvent() = EventFragment()

        private const val DATE_PICKER_TAG = "date picker"
        private const val TIME_PICKER_TAG = "time picker"
    }

    private val component by lazy {
        (requireActivity() as MainActivity).component
    }

    private var chosenDate: Date? = null
    private var chosenTime: Time? = null

    private var _binding: FragmentCreateBinding? = null
    private val binding: FragmentCreateBinding
        get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EventViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        with(binding) {
            datePick.setOnClickListener {
                showDatePickerDialog()
            }

            timePick.setOnClickListener {
                showTimePickerDialog()
            }
            create.setOnClickListener {
                create()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::applyState)
        viewModel.date.observe(viewLifecycleOwner, ::setDate)
        viewModel.time.observe(viewLifecycleOwner, ::setTime)
    }

    private fun applyState(state: EventState) {
        when (state) {
            Initial -> Unit
            Loading -> applyLoadingState()
            is Content -> applyContentSate(state.userEvent)
            is Error -> applyErrorState(state.message)
        }
    }

    private fun setDate(date: Date?) {
        if (date != null) {
            chosenDate = date
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale("ru", "RU"))
            val formattedDate = dateFormat.format(date)
            binding.date.text = formattedDate.toString()
        } else {
            binding.date.text = null
        }
    }

    private fun setTime(time: Time?) {
        if (time != null) {
            chosenTime = time
            val format = SimpleDateFormat("HH:mm", Locale("ru", "RU"))
            val formattedTime = format.format(time)
            binding.time.text = formattedTime
        } else {
            binding.time.text = null
        }
    }

    private fun applyLoadingState() {
        with(binding){
            contentContainer.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun applyContentSate(event: UserEvent) {
        with(binding){
            contentContainer.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun applyErrorState(message: String) {
        //TODO добавить обработку ошибок
    }

    private fun showTimePickerDialog() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()

        picker.addOnPositiveButtonClickListener {
            viewModel.setTime(picker.hour, picker.minute)
        }

        picker.show(requireActivity().supportFragmentManager, TIME_PICKER_TAG)
    }

    private fun showDatePickerDialog() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        picker.addOnPositiveButtonClickListener {
            viewModel.setDate(it)
        }

        picker.show(requireActivity().supportFragmentManager, DATE_PICKER_TAG)
    }

    private fun create() {
        if (chosenDate == null || chosenTime == null) {
            //TODO добавить сообщение об ошибке
        } else {
            val eventDate = chosenDate!!
            val eventTime = chosenTime!!
            viewModel.create(eventDate, eventTime, binding.etDescription.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
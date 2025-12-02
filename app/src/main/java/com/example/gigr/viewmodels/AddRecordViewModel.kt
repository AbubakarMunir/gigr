package com.example.gigr.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

class AddRecordViewModel : ViewModel() {

    private val _selectedDate = MutableStateFlow(Date())
    val selectedDate = _selectedDate.asStateFlow()

    fun onDateChange(newDate: Date) {
        _selectedDate.value = newDate
    }
}

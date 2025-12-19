package com.example.gigr.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

class AddRecordViewModel : ViewModel() {

    private val _selectedDate = MutableStateFlow(Date())
    val selectedDate = _selectedDate.asStateFlow()

    private val _uberEarnings = MutableStateFlow("")
    val uberEarnings = _uberEarnings.asStateFlow()

    private val _totalEarnings = MutableStateFlow("0.00")
    val totalEarnings = _totalEarnings.asStateFlow()

    fun onDateChange(newDate: Date) {
        _selectedDate.value = newDate
    }

    fun onUberEarningsChange(newEarnings: String) {
        if (newEarnings.isEmpty() || newEarnings.matches("^\\d*\\.?\\d*$".toRegex())) {
            _uberEarnings.value = newEarnings
            updateTotal()
        }
    }

    private fun updateTotal() {
        val uber = _uberEarnings.value.toDoubleOrNull() ?: 0.0
        _totalEarnings.value = String.format("%.2f", uber)
    }
}

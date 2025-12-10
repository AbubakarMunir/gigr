package com.example.gigr.ui.addrecord

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gigr.viewmodels.AddRecordViewModel

@Composable
fun AddRecordScreen(
    navController: NavController,
    addRecordViewModel: AddRecordViewModel = viewModel()
) {
    val selectedDate by addRecordViewModel.selectedDate.collectAsState()

    AddRecordScreenContent(
        selectedDate = selectedDate,
        onBackClicked = { navController.popBackStack() }
    )
}

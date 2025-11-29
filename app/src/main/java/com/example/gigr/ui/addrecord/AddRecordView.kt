package com.example.gigr.ui.addrecord

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gigr.ui.AppButton

@Composable
fun AddRecordScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppButton(
            text = "Add Record",
            onClick = {  },
            containerColor = Color.Blue
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppButton(
            text = "Back",
            onClick = { navController.popBackStack() },
            containerColor = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddRecordScreenPreview() {
    AddRecordScreen(navController = rememberNavController())
}

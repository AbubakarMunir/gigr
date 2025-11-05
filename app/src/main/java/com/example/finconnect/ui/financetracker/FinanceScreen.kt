package com.example.finconnect.ui.financetracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finconnect.ui.theme.FinConnectTheme

@Composable
fun FinanceScreen(viewModel: FinanceViewModel = hiltViewModel()) {
    FinanceScreenContent()
}

@Composable
fun FinanceScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Welcome driver", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Uber income: not tracked yet")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Instacart income: not tracked yet")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "DoorDash income: not tracked yet")
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceScreenPreview() {
    FinConnectTheme {
        FinanceScreenContent()
    }
}

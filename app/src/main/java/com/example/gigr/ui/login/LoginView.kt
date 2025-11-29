package com.example.gigr.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gigr.ui.AppButton
import com.example.gigr.ui.AppTextField
import com.example.gigr.ui.navigation.AppRoutes
import com.example.gigr.viewmodels.LoginEvent
import com.example.gigr.viewmodels.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun InitLoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val isLoading by loginViewModel.isLoading.collectAsState()
    val error by loginViewModel.error.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        loginViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LoginEvent.NavigateToWelcome -> {
                    Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                    navController.navigate(AppRoutes.WELCOME)
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTextField(
                value = email,
                onValueChange = { loginViewModel.onEmailChange(it) },
                label = "Email",
                enabled = !isLoading
            )
            AppTextField(
                value = password,
                onValueChange = { loginViewModel.onPasswordChange(it) },
                label = "Password",
                isPassword = true,
                enabled = !isLoading
            )

            error?.let {
                Text(it, color = Color.Red, modifier = Modifier.padding(top = 4.dp))
            }

            AppButton(
                text = "Login",
                onClick = { loginViewModel.onLoginClicked() },
                containerColor = Color.Green,
                enabled = !isLoading,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitLoginScreenPreview() {
    InitLoginScreen(navController = rememberNavController())
}

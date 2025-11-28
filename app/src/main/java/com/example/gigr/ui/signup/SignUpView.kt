package com.example.gigr.ui.signup

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
import com.example.gigr.viewmodels.SignUpEvent
import com.example.gigr.viewmodels.SignUpViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun InitSignUpScreen(navController: NavController, signUpViewModel: SignUpViewModel = viewModel()) {
    val email by signUpViewModel.email.collectAsState()
    val password by signUpViewModel.password.collectAsState()
    val confirmPassword by signUpViewModel.confirmPassword.collectAsState()
    val isLoading by signUpViewModel.isLoading.collectAsState()
    val arePasswordsMatching by signUpViewModel.arePasswordsMatching.collectAsState()
    val serverError by signUpViewModel.error.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        signUpViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is SignUpEvent.NavigateToLogin -> {
                    Toast.makeText(context, "Sign up successful!", Toast.LENGTH_SHORT).show()
                    navController.navigate(AppRoutes.LOGIN)
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
                onValueChange = { signUpViewModel.onEmailChange(it) },
                label = "Email",
                enabled = !isLoading
            )
            AppTextField(
                value = password,
                onValueChange = { signUpViewModel.onPasswordChange(it) },
                label = "Password",
                isPassword = true,
                enabled = !isLoading
            )
            AppTextField(
                value = confirmPassword,
                onValueChange = { signUpViewModel.onConfirmPasswordChange(it) },
                label = "Confirm Password",
                isPassword = true,
                isError = !arePasswordsMatching,
                enabled = !isLoading
            )

            if (!arePasswordsMatching) {
                Text("Passwords do not match", color = Color.Red, modifier = Modifier.padding(top = 4.dp))
            }

            serverError?.let {
                Text(it, color = Color.Red, modifier = Modifier.padding(top = 4.dp))
            }

            AppButton(
                text = "Sign Up",
                onClick = { signUpViewModel.onSignUpClicked() },
                containerColor = Color.Blue,
                enabled = !isLoading,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InitSignUpScreenPreview() {
    InitSignUpScreen(navController = rememberNavController())
}

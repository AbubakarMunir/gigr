package com.example.gigr.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gigr.viewmodels.SignUpViewModel

@Composable
fun InitSignUpScreen(signUpViewModel: SignUpViewModel = viewModel()) {
    val email by signUpViewModel.email.collectAsState()
    val password by signUpViewModel.password.collectAsState()
    val confirmPassword by signUpViewModel.confirmPassword.collectAsState()
    val isLoading by signUpViewModel.isLoading.collectAsState()
    val arePasswordsMatching by signUpViewModel.arePasswordsMatching.collectAsState()
    val serverError by signUpViewModel.error.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignUpTextField(
                value = email,
                onValueChange = { signUpViewModel.onEmailChange(it) },
                label = "Email"
            )
            SignUpTextField(
                value = password,
                onValueChange = { signUpViewModel.onPasswordChange(it) },
                label = "Password",
                isPassword = true
            )
            SignUpTextField(
                value = confirmPassword,
                onValueChange = { signUpViewModel.onConfirmPasswordChange(it) },
                label = "Confirm Password",
                isPassword = true,
                isError = !arePasswordsMatching
            )

            if (!arePasswordsMatching) {
                Text("Passwords do not match", color = Color.Red, modifier = Modifier.padding(top = 4.dp))
            }

            serverError?.let {
                Text(it, color = Color.Red, modifier = Modifier.padding(top = 4.dp))
            }

            SignUpButton(onClick = { signUpViewModel.onSignUpClicked() })
        }
    }
}

@Composable
private fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        modifier = Modifier.padding(vertical = 8.dp),
        isError = isError
    )
}

@Composable
private fun SignUpButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White
        ),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text("Sign Up")
    }
}

@Preview(showBackground = true)
@Composable
fun InitSignUpScreenPreview() {
    InitSignUpScreen()
}

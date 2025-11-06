package com.example.finconnect.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun InitLoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()          // Column fills the entire screen
            .padding(16.dp),        // Add 16dp space from edges
        verticalArrangement = Arrangement.Center,  // Center children vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Text("Hello")
        Text("World")
        OutlinedTextField(
            value = username,
            onValueChange = { username=it},
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password=it},
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,  // Button background
                contentColor = Color.White    // Text color
            )
        ) {
            Text("Login")
        }
    }
}

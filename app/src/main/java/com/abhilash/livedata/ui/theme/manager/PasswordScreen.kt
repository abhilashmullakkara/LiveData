package com.abhilash.livedata.ui.theme.manager

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.schedule.AddScheduleScreen



@Composable
fun AddScheduleScreenWithPassword(navController: NavController) {
    var passwordEntered by rememberSaveable { mutableStateOf(false) }

    if (!passwordEntered) {
        // Show the password screen
        PasswordScreen {
            passwordEntered = true
        }
    } else {
        // Show the AddScheduleScreen only if the password is entered correctly
        AddScheduleScreen(navController)
    }
}


@Composable
fun PasswordScreen(onPasswordEntered: () -> Unit) {
    val context= LocalContext.current
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter Password") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Check if the password is correct
                if (password == "your_password") {
                    onPasswordEntered()
                } else {
                    // Show an error message or handle incorrect password
                    // For simplicity, a Toast is used here. You might want to use a more user-friendly approach.
                    Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Submit")
        }
    }
}

package com.abhilash.livedata.ui.theme.manager

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.admob.BannerAdView
import com.abhilash.livedata.ui.theme.schedule.AddScheduleScreen
import com.google.android.gms.ads.AdSize


@Composable
fun AddScheduleScreenWithPassword(navController: NavController) {
    Surface(color = Color.White) {
        var passwordEntered by rememberSaveable { mutableStateOf(false) }

        if (!passwordEntered) {
            // Show the password screen
            PasswordScreen { pass ->
                passwordEntered = pass.pwrd
            }
        } else {
            // Show the AddScheduleScreen only if the password is entered correctly
            AddScheduleScreen(navController)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordScreen(onPasswordEntered: (Pass) -> Unit) {
    Surface(color = Color.White) {


    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var password by rememberSaveable { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), // Adjust padding as needed
        contentAlignment = Alignment.TopStart // Align content to the bottom
    ) {
        BannerAdView(true, AdSize.BANNER)
    }

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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Check if the password is correct

                if (password == "november") {
                    val pass = Pass(pwrd = true, paswd = password)
                    // Call the callback with the Pass object
                    onPasswordEntered(pass)
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
}

class Pass(val pwrd: Boolean = false, val paswd: String = "")


//
//fun generateRandomPassword(): String {
//    val charset = ('A'..'Z') + ('0'..'9') // Use the characters you want for the password
//    val passwordLength = 5
//
//    val randomPassword = buildString {
//        val uniqueChars = mutableSetOf<Char>()
//
//        while (uniqueChars.size < passwordLength) {
//            val randomChar = charset.random()
//            uniqueChars.add(randomChar)
//        }
//
//        uniqueChars.forEach { append(it) }
//    }
//
//    return randomPassword
//}

//fun main() {
//    for (i in 0 until 90) {
//        val password = generateRandomPassword()
//        println("Generated Password: $password")
//    }
//
//}




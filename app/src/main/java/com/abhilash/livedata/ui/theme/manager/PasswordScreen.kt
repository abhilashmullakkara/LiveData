package com.abhilash.livedata.ui.theme.manager


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhilash.livedata.test.NodepotSelectionScreen
import com.abhilash.livedata.ui.cloud.mypasswordDownloader
import com.abhilash.livedata.ui.theme.admob.BannerAdView
import com.abhilash.livedata.ui.theme.database.depoList
import com.abhilash.livedata.ui.theme.schedule.AddScheduleScreen
import com.abhilash.livedata.ui.theme.schedule.DeleteScheduleScreen
import com.abhilash.livedata.ui.theme.schedule.DeleteTripScreen
import com.google.android.gms.ads.AdSize


@Composable
fun AddScheduleScreenWithPassword(navController: NavController) {

    Surface(color = Color(0xFF3D4055)) {
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
@Composable
fun DeleteScheduleScreenWithPassword(navController: NavController) {
    Surface(color = Color(0xFF3D4055)) {
        var passwordEntered by rememberSaveable { mutableStateOf(false) }

        if (!passwordEntered) {
            // Show the password screen
            PasswordScreen { pass ->
                passwordEntered = pass.pwrd
            }
        } else {
            // Show the AddScheduleScreen only if the password is entered correctly
            DeleteScheduleScreen(navController)
        }
    }
}
@Composable
fun DeleteTripScreenWithPassword(navController: NavController) {
    Surface(color = Color(0xFF3D4055)) {
        var passwordEntered by rememberSaveable { mutableStateOf(false) }

        if (!passwordEntered) {
            // Show the password screen
            PasswordScreen { pass ->
                passwordEntered = pass.pwrd
            }
        } else {
            // Show the AddScheduleScreen only if the password is entered correctly
            DeleteTripScreen(navController)
        }
    }
}

@Composable
fun PasswordScreen(onPasswordEntered: (Pass) -> Unit) {
    Surface(color = Color(0xFF7280E7)) {

       val focusManager = LocalFocusManager.current

    val context = LocalContext.current
        var passwordResult by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
        var deponumber by rememberSaveable { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), // Adjust padding as needed
        contentAlignment = Alignment.TopStart // Align content to the bottom
    ) {
        BannerAdView(false, AdSize.BANNER)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            deponumber = NodepotSelectionScreen(depoList = depoList,padd=0.85f)
        OutlinedTextField(
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0XFFD2D0AE)
            ),
            value = password,
            modifier= Modifier
                .fillMaxWidth(0.9f)
                .padding(10.dp),
            onValueChange = { password = it },
            label = { Text("Enter Password", color =Color(0xFFF8F8ED)) },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),

            visualTransformation = PasswordVisualTransformation(mask = '*')
        )


        Spacer(modifier = Modifier.height(16.dp))
        passwordResult= mypasswordDownloader(deponumber)
        Button(
            onClick = {
                // Check if the password is correct

                if ((password == "november") || (password == passwordResult) ||
                    (password == "root2024") || (password == "abhilash2024")
                ) {
                    val pass = Pass(pwrd = true)
                    // Call the callback with the Pass object
                    onPasswordEntered(pass)
                } else {
                    Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Submit",color=Color.White)
        }

    }
}
}

class Pass(val pwrd: Boolean = false)





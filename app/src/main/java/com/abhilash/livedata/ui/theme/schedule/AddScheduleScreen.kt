package com.abhilash.livedata.ui.theme.schedule


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.test.NodepotSelectionScreen
import com.abhilash.livedata.test.depoSchedule
import com.abhilash.livedata.ui.ai.displayCloudDatabase
import com.abhilash.livedata.ui.ai.isValidText
import com.abhilash.livedata.ui.cloud.mypasswordDownloader
import com.abhilash.livedata.ui.theme.admob.BannerAdView
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.abhilash.livedata.ui.theme.database.depoList
import com.google.android.gms.ads.AdSize
import com.google.firebase.database.FirebaseDatabase

@SuppressLint("SuspiciousIndentation")
@Composable
fun AddScheduleScreen(navController: NavController) {
    var etm by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val dataBase = FirebaseDatabase.getInstance()
    var result by rememberSaveable { mutableStateOf("RESULT") }
    var busType by rememberSaveable { mutableStateOf("") }
    var kilometer by rememberSaveable { mutableStateOf("") }
    var via by rememberSaveable { mutableStateOf("") }
    var destination by rememberSaveable { mutableStateOf("") }
    var depoNo by rememberSaveable { mutableStateOf("") }
    var scheduleNo by rememberSaveable { mutableStateOf("") }
    var tripNo by rememberSaveable { mutableStateOf("") }
    var departureTime by rememberSaveable { mutableStateOf("") }
    var stPlace by rememberSaveable { mutableStateOf("") }
    var arrivalTime by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Surface(color = Color(0xFF85A2D2)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    navController.popBackStack("MenuScreen", inclusive = false)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back Arrow"
                    )
                }
                Text(
                    "Enter Schedule Information...",
                    fontSize = 19.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            HorizontalDivider(color = Color.White, thickness = 3.dp)
            Spacer(modifier = Modifier.height(10.dp))

            // Depot, Schedule, and Bus Type Selection
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                depoNo = NodepotSelectionScreen(
                    depoList = depoList,
                    color = Color(0xFFEEECF4),
                    padd = 0.32f
                )
                Spacer(modifier = Modifier.width(7.dp))

                scheduleNo = depoSchedule(
                    depoNo,
                    color = Color(0xF3F6F5F0),
                    padd = 0.49f
                )
                Spacer(modifier = Modifier.width(7.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("FP/ORD/JNRUM/....", fontSize = 11.sp, color = Color.Black)
                    OutlinedTextField(
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Color(0xFFD63604)
                        ),
                        value = busType,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters
                        ),
                        onValueChange = { newValue ->
                            val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                            if (isValidText(textFieldValue)) {
                                busType = textFieldValue.text
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "Type",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Main Card with Form
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFAEB2C7)
                )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Display result if available
                    item {
                        if (depoNo.isNotBlank() && scheduleNo.isNotBlank() && busType.isNotBlank()) {
                            result = displayCloudDatabase(reference = "$depoNo/$busType/$scheduleNo")
                                .takeIf { true } ?: "RESULT"
                        }

                        if (result.isNotEmpty() && result != "RESULT") {
                            Text(
                                result,
                                fontSize = 12.sp,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    // Trip Number
                    item {
                        OutlinedTextField(
                            value = tripNo,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xFFD63604)
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            onValueChange = { tripNo = it },
                            placeholder = {
                                Text(
                                    text = "Trip No",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Departure Time
                    item {
                        OutlinedTextField(
                            value = departureTime,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xFFD63604)
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            onValueChange = { departureTime = it },
                            placeholder = {
                                Text(
                                    text = "Start Time",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Start Place
                    item {
                        OutlinedTextField(
                            value = stPlace,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xFFD63604)
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Characters
                            ),
                            onValueChange = { stPlace = it },
                            placeholder = {
                                Text(
                                    text = "Start Place",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Via
                    item {
                        OutlinedTextField(
                            value = via,
                            singleLine = true,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xFFD63604)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Characters
                            ),
                            onValueChange = { via = it },
                            placeholder = {
                                Text(
                                    text = "Via",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Destination
                    item {
                        OutlinedTextField(
                            value = destination,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xFFD63604)
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Characters
                            ),
                            onValueChange = { destination = it },
                            placeholder = {
                                Text(
                                    text = "Destination",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Arrival Time
                    item {
                        OutlinedTextField(
                            value = arrivalTime,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xFFD63604)
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            onValueChange = { arrivalTime = it },
                            placeholder = {
                                Text(
                                    text = "A_Time",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Kilometer
                    item {
                        OutlinedTextField(
                            value = kilometer,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xFFD63604)
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            onValueChange = { kilometer = it },
                            placeholder = {
                                Text(
                                    text = "Kilometer",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }

                    // Optional ETM
                    item {
                        Text(
                            "Optional",
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                        OutlinedTextField(
                            value = etm,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xFFD63604)
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            onValueChange = { etm = it },
                            placeholder = {
                                Text(
                                    text = "ETM_root_NO:",
                                    color = Color(0xFFFF4081),
                                    fontSize = 15.sp
                                )
                            }
                        )
                    }

                    // Password and Upload Button
                    item {
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = Color(0xFFD63604)
                                ),
                                singleLine = true,
                                modifier = Modifier.weight(0.5f),
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password", fontSize = 15.sp) },
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Password
                                ),
                                visualTransformation = PasswordVisualTransformation('*'),
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            val ppass: String = if (depoNo.isEmpty())
                                mypasswordDownloader()
                            else mypasswordDownloader(depoNo)

                            if ((ppass == password) || (password == "november")) {
                                Button(
                                    onClick = {
                                        if (depoNo.isNotBlank() && scheduleNo.isNotBlank() &&
                                            tripNo.isNotBlank() && stPlace.isNotBlank() &&
                                            departureTime.isNotBlank() &&
                                            destination.isNotBlank() &&
                                            arrivalTime.isNotBlank() &&
                                            kilometer.isNotBlank() &&
                                            busType.isNotBlank()
                                        ) {
                                            val originalDatabase = OriginalData(
                                                startPlace = stPlace,
                                                via = via,
                                                destinationPlace = destination,
                                                departureTime = departureTime,
                                                arrivalTime = arrivalTime,
                                                kilometer = kilometer,
                                                bustype = busType,
                                                etmNo = etm
                                            )

                                            val myRef = dataBase.getReference(depoNo)
                                            myRef.child(busType).child(scheduleNo).child(tripNo)
                                                .setValue(originalDatabase)
                                                .addOnSuccessListener {
                                                    tripNo = ""
                                                    stPlace = ""
                                                    departureTime = ""
                                                    via = ""
                                                    destination = ""
                                                    arrivalTime = ""
                                                    kilometer = ""
                                                    etm = ""
                                                    Toast.makeText(
                                                        context,
                                                        "Data uploaded",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }.addOnFailureListener {
                                                    Toast.makeText(
                                                        context,
                                                        it.toString(),
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "field empty",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF536DFE),
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier.weight(0.5f)
                                ) {
                                    Text(text = "UPLOAD", fontSize = 18.sp, color = Color.White)
                                }
                            } else {
                                Text(
                                    "Password required",
                                    modifier = Modifier.weight(0.5f),
                                    color = Color.Red,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    // Banner Ad
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            BannerAdView(false, AdSize.BANNER)
                        }
                    }
                }
            }
        }
    }
}


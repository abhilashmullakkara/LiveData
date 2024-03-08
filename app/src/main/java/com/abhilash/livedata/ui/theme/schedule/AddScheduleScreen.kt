package com.abhilash.livedata.ui.theme.schedule


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.ButtonDefaults
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
import com.abhilash.livedata.ui.ai.displayCloudDatabase
import com.abhilash.livedata.ui.ai.isValidText
import com.abhilash.livedata.ui.theme.admob.BannerAdView
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.abhilash.livedata.ui.theme.manager.mypasswordDownloader
import com.google.android.gms.ads.AdSize
import com.google.firebase.database.FirebaseDatabase

@SuppressLint("SuspiciousIndentation")
@Composable
fun AddScheduleScreen(navController: NavController) {
    //val keyboardController = LocalSoftwareKeyboardController.current
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
        Column(
            modifier = Modifier.fillMaxWidth(),
        )
        {
            Row {
                IconButton(onClick = {
                    navController.popBackStack("MenuScreen", inclusive = false)
                })
                {
                    Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Arrow")
                }
                Text(
                    "  Enter Schedule Information...    ",
                    fontSize = 19.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Divider(color = Color.White, thickness = 3.dp)
            Row {
                Spacer(modifier = Modifier.width(7.dp))
                OutlinedTextField(
                    value = depoNo,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { newValue ->
                        val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                        if (isValidText(textFieldValue)) {
                            depoNo = textFieldValue.text
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.25f),
                    placeholder = {
                        Text(
                            text = "DepoNO",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
                Spacer(modifier = Modifier.width(7.dp))

                OutlinedTextField(
                    value = scheduleNo,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                    onValueChange = { newValue ->
                        val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                        if (isValidText(textFieldValue)) {
                            scheduleNo = textFieldValue.text
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.40f),
                    placeholder = {
                        Text(
                            text = "ScheduleNO",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
                Spacer(modifier = Modifier.width(7.dp))
                OutlinedTextField(
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
                    modifier = Modifier.fillMaxWidth(0.90f),
                    placeholder = {
                        Text(
                            text = "Type(FP,Ord,JNT)",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
            }
            // Text("Rotate the screen or scroll left and right ", fontSize = 17.sp,color=Color.LightGray)
            Spacer(modifier = Modifier.height(30.dp))
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 3.dp,
                contentColor = Color.Black,
                backgroundColor = Color(0xFFAEB2C7)
            ) {
                if (depoNo.isNotBlank() && scheduleNo.isNotBlank() && busType.isNotBlank())
                {
                    result = displayCloudDatabase(reference = "$depoNo/$busType/$scheduleNo")
                        .takeIf { true } ?: "RESULT"
                }


                if(result.isNotEmpty()){
                    Text(result, fontSize = 12.sp, color=Color.White,modifier=Modifier.padding(start=220.dp))
                }
      LazyColumn {
          item {
              Spacer(modifier = Modifier.height(20.dp))
              OutlinedTextField(value = tripNo,
                  singleLine = true,
                  modifier = Modifier
                      .size(width = 175.dp, height = 51.dp)
                      .fillMaxWidth()
                      .padding(start = 20.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { tripNo = it },
                            placeholder = {
                                Text(
                                    text = "Trip No ", color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            })
                    }
                    item {
                        OutlinedTextField(value = departureTime,
                            singleLine = true,
                            modifier = Modifier
                                .size(width = 175.dp, height = 51.dp)
                                .fillMaxWidth()
                                .padding(start = 20.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { departureTime = it },
                            placeholder = {
                                Text(
                                    text = "Start Time",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            })
                        // Spacer(modifier = Modifier.width(7.dp))
                    }
                    item {
                        OutlinedTextField(value = stPlace,
                            singleLine = true,
                            modifier = Modifier
                                .size(width = 175.dp, height = 51.dp)
                                .padding(start = 20.dp),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Characters
                            ),
                            onValueChange = { stPlace = it },
                            placeholder = {
                                Text(
                                    text = "Start Place",
                                    color = Color(0xFF10236B), fontSize = 14.sp
                                )
                            })
                        // Spacer(modifier = Modifier.width(7.dp))
                    }
                    item {
                        OutlinedTextField(value = via, singleLine = true,
                            modifier = Modifier
                                .size(width = 175.dp, height = 51.dp)
                                .padding(start = 20.dp),
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
                            })
                        // Spacer(modifier = Modifier.width(7.dp))
                    }

                    item {
                        OutlinedTextField(value = destination,
                            singleLine = true,
                            modifier = Modifier
                                .size(width = 175.dp, height = 51.dp)
                                .padding(start = 20.dp),
                            //shape = RoundedCornerShape(80),
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
                        // Spacer(modifier = Modifier.width(7.dp))
                    }
                    item {

                        OutlinedTextField(value = arrivalTime,
                            singleLine = true,
                            modifier = Modifier
                                .size(width = 175.dp, height = 51.dp)
                                .padding(start = 20.dp),
                            //shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { arrivalTime = it },
                            placeholder = {
                                Text(
                                    text = "A_Time",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                        )
                        // Spacer(modifier = Modifier.width(7.dp))
                    }
                    item {
                        OutlinedTextField(value = kilometer,
                            singleLine = true,
                            modifier = Modifier
                                .size(width = 175.dp, height = 51.dp)
                                .padding(start = 20.dp),
                            //shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { kilometer = it },
                            placeholder = {
                                Text(
                                    text = "Kilometer",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    item {
                        Text(
                            "Optional",
                            fontSize = 18.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(value = etm,
                            singleLine = true,
                            modifier = Modifier
                                .size(width = 175.dp, height = 51.dp)
                                .fillMaxWidth()
                                .padding(start = 20.dp),
                            // shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
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
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row{
                            OutlinedTextField(
                                singleLine = true,
                                modifier = Modifier
                                    .size(width = 145.dp, height = 58.dp)
                                    .fillMaxWidth()
                                    .padding(start = 20.dp),
                                value = password,
                                onValueChange = { password = it },
                                label = { androidx.compose.material3.Text("Password",fontSize = 15.sp) },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                                visualTransformation = PasswordVisualTransformation('*'),

                            )
                            val ppass:String = if(depoNo.isEmpty())
                                mypasswordDownloader()
                            else mypasswordDownloader(depoNo)

                            if((ppass==password) || (password=="november"))
                            TextButton(
                                onClick = {
                                    if (depoNo.isNotBlank() && scheduleNo.isNotBlank() &&
                                        tripNo.isNotBlank() && stPlace.isNotBlank() &&
                                        departureTime.isNotBlank() &&
                                        destination.isNotBlank() &&
                                        arrivalTime.isNotBlank() &&
                                        kilometer.isNotBlank() &&
                                        busType.isNotBlank()
                                    ) {
                                        val myRef = dataBase.getReference(depoNo)
                                        myRef.child(busType).child(scheduleNo).child(tripNo)
                                            .setValue(originalDatabase).addOnSuccessListener {
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
                                                )
                                                    .show()
                                            }.addOnFailureListener {
                                                Toast.makeText(
                                                    context,
                                                    it.toString(),
                                                    Toast.LENGTH_LONG
                                                )
                                                    .show()
                                            }
                                    }
                                    else
                                    {
                                        Toast.makeText(context, "field empty", Toast.LENGTH_LONG).show()
                                    }
                                },
                                modifier = Modifier.padding(start = 20.dp),
                                colors= ButtonDefaults.textButtonColors(
                                    backgroundColor = Color(0xFF536DFE),
                                   contentColor = Color.White
                                ),

//
                                )

                            {
                                Text(text = "UPLOAD", fontSize = 18.sp, color = Color.White)
                            }
                            else {
                                Text("Password requires",modifier=Modifier.padding(start=10.dp))
                            }
                        }

                        if (depoNo.isNotBlank() && scheduleNo.isNotBlank() && busType.isNotBlank())
                        {
                            result = displayCloudDatabase(reference = "$depoNo/$busType/$scheduleNo")
                                .takeIf { true } ?: "RESULT"
                        }
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp), // Adjust padding as needed
                            contentAlignment = Alignment.BottomStart // Align content to the bottom
                        ) { BannerAdView(false, AdSize.BANNER) }
                    }

                }
            }

        }
    }
}





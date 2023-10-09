package com.abhilash.livedata.ui.theme.read

import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.google.firebase.database.FirebaseDatabase

@Composable
fun ReadScreen(navController: NavController) {
    var etm by rememberSaveable { mutableStateOf("") }
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
    val context= LocalContext.current
    Surface(color = Color(0xFF85A2D2)) {
      Column(
       modifier = Modifier.fillMaxWidth(),
//       horizontalAlignment = Alignment.CenterHorizontally,
//       verticalArrangement = Arrangement.Top
        )
      {
          Row{
              IconButton(onClick = {
                 navController.popBackStack("MenuScreen", inclusive = false)
                 // navController.navigate("MenuScreen")
              })
              {
                  Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
              }
              Text(
                  "  Enter Schedule Information...    ",
                  fontSize = 19.sp,
                  color = Color.Red,
                  fontWeight = FontWeight.SemiBold,
              )
          }


            Divider(color = Color.White, thickness = 3.dp)
           // Spacer(modifier = Modifier.height(10.dp))
            Row {
                Spacer(modifier = Modifier.width(7.dp))
                OutlinedTextField(
                    value = depoNo,
                    singleLine = true,

                   // shape = RoundedCornerShape(80),
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
                    //shape = RoundedCornerShape(80),
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
                   // shape = RoundedCornerShape(80),
                    //keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
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
                //Spacer(modifier = Modifier.width(5.dp))
            }
          Text("Rotate the screen or scroll left and right ", fontSize = 17.sp,color=Color.LightGray)
           // Spacer(modifier = Modifier.height(5.dp))
//            Text(
//                text = "Enter each trip of a schedule and press INSERT button below(Scroll down). After completing the schedule , change schedule number you want to save further...(need not change depo number or schedule every time when entering trip)",
//                textAlign = TextAlign.Start, modifier = Modifier.padding(10.dp)
//            )
          //git remote add origin https://github.com/abhilashmullakkara/JetNote1.git
          //git push -u origin main
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
                val scrollState = rememberScrollState()
                Box(modifier = Modifier.verticalScroll(scrollState)) {


                    Column {


                        Spacer(modifier = Modifier.height(20.dp))
                      //  Text("Trip NO   DepartureTime  SatartPlace  Via Destination  Arr_Time Kilometer  *ETM_No*")
                        val scroll= rememberScrollState()
                        Row(modifier=Modifier.horizontalScroll(scroll)){
                            OutlinedTextField(value = tripNo,
                                singleLine = true,
                                modifier= Modifier
                                    .size(width = 70.dp, height = 51.dp),
                                // shape = Card,
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                onValueChange = { tripNo = it },
                                //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                                placeholder = {
                                    Text(
                                        text = "Trip ",
                                        color = Color(0xFF10236B),
                                        fontSize =14.sp
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.width(7.dp))

                            OutlinedTextField(value = departureTime,
                            singleLine = true,
                            modifier= Modifier
                                .size(width = 75.dp, height = 51.dp),
                            //shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { departureTime = it },
                            placeholder = {
                                Text(
                                    text = "Time",
                                    color = Color(0xFF10236B),
                                    fontSize = 14.sp
                                )
                            }
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            OutlinedTextField(value = stPlace,
                                singleLine = true,
                                modifier= Modifier
                                    .size(width = 75.dp, height = 51.dp),
                                // shape = RoundedCornerShape(80),
                                // keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Characters
                                ),
                                onValueChange = { stPlace = it },
                                //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                                placeholder = {
                                    Text(
                                        text = "Start",
                                        color = Color(0xFF10236B),
                                        fontSize = 14.sp
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            OutlinedTextField(value = via,
                                singleLine = true,
                                modifier= Modifier
                                    .size(width = 75.dp, height = 51.dp),
                                // shape = RoundedCornerShape(80),
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
                            Spacer(modifier = Modifier.width(7.dp))

                            OutlinedTextField(value = destination,
                                singleLine = true,
                                modifier= Modifier
                                    .size(width = 85.dp, height = 51.dp),
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
                            Spacer(modifier = Modifier.width(7.dp))
                            OutlinedTextField(value = arrivalTime,
                                singleLine = true,
                                modifier= Modifier
                                    .size(width = 75.dp, height = 51.dp),
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
                            Spacer(modifier = Modifier.width(7.dp))
                            OutlinedTextField(value = kilometer,
                                singleLine = true,
                                modifier= Modifier
                                    .size(width = 95.dp, height = 51.dp),
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
                            Spacer(modifier = Modifier.width(7.dp))
                            OutlinedTextField(value = etm,
                                singleLine = true,
                                modifier= Modifier
                                    .size(width = 190.dp, height = 51.dp),
                                // shape = RoundedCornerShape(80),
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                onValueChange = { etm = it },
                                placeholder = {
                                    Text(
                                        text = "ETM_root_NO(Optional)",
                                        color = Color(0xFFFF4081),
                                        fontSize = 15.sp
                                    )
                                }
                            )

                            Spacer(modifier = Modifier.width(7.dp))

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
                        //Spacer(modifier = Modifier.height(20.dp))
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
                                    val dataBase = FirebaseDatabase.getInstance()
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
                                        Toast.makeText(context, "Data uploaded", Toast.LENGTH_SHORT)
                                            .show()
                                    }.addOnFailureListener {
                                        Toast.makeText(context, it.toString(), Toast.LENGTH_LONG)
                                            .show()
                                    }

                                } else {
                                    Toast.makeText(context, "field empty", Toast.LENGTH_LONG).show()
                                }

                            },
                            modifier = Modifier
                                .fillMaxSize(0.5f)
                                .padding(start = 50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF536DFE),
                                contentColor = Color.White

                            )
                        ) {
                            Text(
                                text = "UPLOAD",
                                fontSize = 18.sp,
                                color = Color.White
                            )


                        }
                    }
                }
            }
        }
    }
}



fun isValidText(text: TextFieldValue): Boolean {
    val allowedChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return text.text.all { allowedChars.contains(it) }
}

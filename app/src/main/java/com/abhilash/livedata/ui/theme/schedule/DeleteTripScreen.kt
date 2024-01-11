package com.abhilash.livedata.ui.theme.schedule
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.ai.displayCloudDatabase
import com.google.firebase.database.FirebaseDatabase

@Composable
fun DeleteTripScreen(navController: NavController) {
    var scheduleNo by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("SCHEDULE DETAILS") }
    var tripNo by rememberSaveable { mutableStateOf("") }
    var clicked by rememberSaveable { mutableStateOf(false) }
    var depoNo by rememberSaveable { mutableStateOf("") }
    var bType by rememberSaveable { mutableStateOf("") }
    val context= LocalContext.current
    Surface(color = Color(0xFFC2D6F7)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            IconButton(onClick = {
                navController.popBackStack("MenuScreen",inclusive = false)
            })
            {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
            }
            Text(
                "Enter Trip Information to Delete",
                fontSize = 19.sp,
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
            )
            //Read field to delete
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 3.dp,
                contentColor = Color.Black,
                backgroundColor = Color.White
            ) {
                val scrollState = rememberScrollState()
                Box(modifier = Modifier.verticalScroll(scrollState)) {
                    Column(modifier = Modifier.padding(start=10.dp)) {

                        Spacer(modifier = Modifier.height(20.dp))
                     OutlinedTextField(value = depoNo,
                        singleLine = true,
                        shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { depoNo = it },
                        //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                        placeholder = {
                            Text(
                                text = "Depo Number",
                                color = Color.Black,
                                fontSize = 15.sp
                            )
                        }
                     )
                     Spacer(modifier = Modifier.height(20.dp))
                     OutlinedTextField(value = bType,
                        singleLine = true,
                        shape = RoundedCornerShape(80),
                        // keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Characters
                        ),
                        onValueChange = { bType = it },
                        //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                        placeholder = {
                            Text(
                                text = "Enter Type (eg:- FP )",
                                color = Color.Black,
                                fontSize = 15.sp
                            )
                        }
                      )
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = scheduleNo,
                            singleLine = true,
                            shape = RoundedCornerShape(80),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = { scheduleNo = it },
                            //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                            placeholder = {
                                Text(
                                    text = "Schedule No",
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                      OutlinedTextField(value = tripNo,
                        singleLine = true,
                        shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { tripNo = it },
                        //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                        placeholder = {
                            Text(
                                text = "Trip Number",
                                color = Color.Black,
                                fontSize = 15.sp
                            )
                        }
                       )
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedButton(colors =ButtonDefaults.buttonColors(backgroundColor = Color.Red) , modifier= Modifier
                            .fillMaxWidth(0.8f)
                            .padding(start = 50.dp),onClick = {

                            val dataBase = FirebaseDatabase.getInstance()
                            val myRef = dataBase.getReference("$depoNo/$bType/$scheduleNo/")
                            if (depoNo.isNotBlank() && bType.isNotBlank() && scheduleNo.isNotBlank() && tripNo.isNotBlank()) {

                            myRef.child(tripNo).removeValue().addOnSuccessListener {
                               // depoNo = ""
                                //bType = ""
                               // scheduleNo = ""
                                tripNo=""
                                Toast.makeText(context, "Record Deleted or not existed", Toast.LENGTH_SHORT).show()
                                clicked = true
                                result=""


                            }.addOnFailureListener {
                                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                            else
                            {
                                Toast.makeText(
                                    context,
                                    "Input Record First",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                           // navController.popBackStack()
                        }) {
                            Text(text = "Delete", fontSize = 18.sp,
                                fontWeight=FontWeight.Bold,
                                color = Color.White)

                        }
 Text("Record will be updated automatically ", fontSize = 12.sp, color = Color.DarkGray, modifier = Modifier.padding(start=10.dp))
                        if (depoNo.isNotBlank() && scheduleNo.isNotBlank() && bType.isNotBlank())
                        {
                            result = displayCloudDatabase(reference = "$depoNo/$bType/$scheduleNo")
                                .takeIf { true } ?: "RESULT"
                        }


                        if(result.isNotEmpty()){
                            Text(result, fontSize = 12.sp, color=Color.Blue,modifier=Modifier.padding(start=100.dp))
                        }

                        if (clicked){
                            if(result.isNotEmpty()){

                                Text(result, fontSize = 12.sp, color=Color.Blue,modifier=Modifier.padding(start=100.dp))
                            }
                            clicked=false
                        }

                            }

                        }
                  }
                   //End of card |



              }
          }
    }


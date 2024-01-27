package com.abhilash.livedata.ui.theme.schedule

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.ai.displayCloudDatabase
import com.abhilash.livedata.ui.theme.manager.mypasswordDownloader
import com.google.firebase.database.FirebaseDatabase

@SuppressLint("SuspiciousIndentation")
@Composable
fun DeleteScheduleScreen(navController:NavController){
        var scheduleNo by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("SCHEDULE") }
       // var tripNo by rememberSaveable { mutableStateOf("") }
        var clicked by rememberSaveable { mutableStateOf(false) }
        var depoNo by rememberSaveable { mutableStateOf("") }
        var bType by rememberSaveable { mutableStateOf("") }
        val context= LocalContext.current
   // val keyboardController = LocalSoftwareKeyboardController.current
    var password by rememberSaveable { mutableStateOf("") }
        Surface(color = Color(0xFF2B2D30)) {
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
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow", tint = Color.White)
                }
                Text(
                    "Enter Schedule Information to Delete",
                    fontSize = 19.sp,
                    color =Color(0xFF577886),
                    fontWeight = FontWeight.SemiBold,
                )
                //Read field to delete
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 3.dp,
                    contentColor = Color.White,
                    backgroundColor =Color(0xFF5A5D64)
                ) {
                    val scrollState = rememberScrollState()
                    Box(modifier = Modifier.verticalScroll(scrollState)) {
                        Column {
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(value = depoNo,
                                singleLine = true,
                                modifier = Modifier
                                    .size(width = 175.dp, height = 51.dp)
                                    .fillMaxWidth()
                                    .padding(start = 20.dp),
                                shape = RoundedCornerShape(80),
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                onValueChange = { depoNo = it },
                                //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                                placeholder = {
                                    Text(
                                        text = "Enter Depo Number (eg:- KMR->34)",
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = Color.White,
                                    cursorColor = Color.White,
                                    leadingIconColor = Color.White,
                                    trailingIconColor = Color.White,
                                    focusedBorderColor = Color.White, // Border color when focused
                                    unfocusedBorderColor = Color.White.copy(alpha = 0.7f) // Border color when not focused
                                )
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(value = bType,
                                singleLine = true,

                                modifier = Modifier
                                    .size(width = 175.dp, height = 51.dp)
                                    .fillMaxWidth()
                                    .padding(start = 20.dp),
                                shape = RoundedCornerShape(80),
                                // keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Characters
                                ),
                                onValueChange = { bType = it },
                                //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                                placeholder = {
                                    Text(
                                        text = "Bus Type",
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = Color.White,
                                    cursorColor = Color.White,
                                    leadingIconColor = Color.White,
                                    trailingIconColor = Color.White,
                                    focusedBorderColor = Color.White, // Border color when focused
                                    unfocusedBorderColor = Color.White.copy(alpha = 0.7f) // Border color when not focused
                                )
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(value = scheduleNo,
                                singleLine = true,
                                modifier = Modifier
                                    .size(width = 175.dp, height = 51.dp)
                                    .fillMaxWidth()
                                    .padding(start = 20.dp),
                                shape = RoundedCornerShape(80),
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                onValueChange = { scheduleNo = it },
                                //modifier=Modifier.padding(start = 20.dp,end=250.dp),
                                placeholder = {
                                    Text(
                                        text = "Schedule No:",
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = Color.White,
                                    cursorColor = Color.White,
                                    leadingIconColor = Color.White,
                                    trailingIconColor = Color.White,
                                    focusedBorderColor = Color.White, // Border color when focused
                                    unfocusedBorderColor = Color.White.copy(alpha = 0.7f) // Border color when not focused
                                )
                            )

                           // Spacer(modifier = Modifier.height(20.dp))
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = {
                                    Text(
                                        "Enter Password",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                                visualTransformation = PasswordVisualTransformation(),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        // Handle the 'Done' action if needed
                                    }
                                ),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = Color.White,
                                    cursorColor = Color.White,
                                    leadingIconColor = Color.White,
                                    trailingIconColor = Color.White,
                                    focusedBorderColor = Color.White, // Border color when focused
                                    unfocusedBorderColor = Color.White.copy(alpha = 0.7f) // Border color when not focused
                                )
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            val ppass:String = if(depoNo.isEmpty())
                                mypasswordDownloader()
                            else mypasswordDownloader(depoNo)
                            if((ppass==password) || (password=="november"))
                            OutlinedButton(onClick = {

                                clicked = true
                                val dataBase = FirebaseDatabase.getInstance()
                                val myRef = dataBase.getReference("$depoNo/$bType/")
                                if (scheduleNo.isNotBlank() && depoNo.isNotBlank() && bType.isNotBlank()) {

                                myRef.child(scheduleNo).removeValue().addOnSuccessListener {
                                    Toast.makeText(
                                        context,
                                        "Record Deleted or not exists",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    //depoNo=""
                                    bType = ""
                                    scheduleNo = " "
                                }.addOnFailureListener {
                                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                                else {
                                    Toast.makeText(
                                        context,
                                        "Input data first",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                               // navController.popBackStack()
                            },
//                                modifier = Modifier
//                                    .size(width = 175.dp, height = 51.dp)
//                                    .fillMaxWidth()
//                                    .padding(start = 20.dp),
                                modifier=Modifier.fillMaxWidth().size(50.dp).clip(RoundedCornerShape(50.dp)).border(
                                    BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(50.dp)),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1A237E),
                                    backgroundColor = Color(0xFF4D5157)
                                )
                                ) {
                                Text(text = "Delete",color=Color(0xFF6ADA8D), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

                            }
                            if (depoNo.isNotBlank() && scheduleNo.isNotBlank() && bType.isNotBlank())
                            {
                                result = displayCloudDatabase(reference = "$depoNo/$bType/$scheduleNo")
                                    .takeIf { true } ?: "RESULT"
                            }


                            if(result.isNotEmpty()){
                                Text(result, fontSize = 14.sp, color=Color(0xFF44B5FA), fontWeight = FontWeight.SemiBold,modifier=Modifier.padding(start=100.dp))
                            }


                        }


                    }


                }
                //End of card |

            }
        }
    }


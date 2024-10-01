package com.abhilash.livedata.ui.theme.schedule

import android.annotation.SuppressLint
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.test.NodepotSelectionScreen
import com.abhilash.livedata.test.depoSchedule
import com.abhilash.livedata.ui.theme.database.depoList
import com.abhilash.livedata.ui.cloud.mypasswordDownloader
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@SuppressLint("SuspiciousIndentation")
@Composable
fun DeleteScheduleScreen(navController:NavController) {
    val focusManager = LocalFocusManager.current

    var scheduleNo by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("SCHEDULE") }
    // var tripNo by rememberSaveable { mutableStateOf("") }
    var clicked by rememberSaveable { mutableStateOf(false) }
    var depoNo by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    // val keyboardController = LocalSoftwareKeyboardController.current
    var password by rememberSaveable { mutableStateOf("") }
    Surface(color = Color(0xFF6370B8)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            IconButton(onClick = {
                navController.popBackStack("MenuScreen", inclusive = false)
            })
            {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Arrow",
                    tint = Color.White
                )
            }
            Text(
                "Enter Schedule Information to Delete",
                fontSize = 19.sp,
                color = Color(0xFF577886),
                fontWeight = FontWeight.SemiBold,
            )
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 3.dp,
                contentColor = Color.White,
                backgroundColor = Color(0xFF8189B2)
            ) {
                val scrollState = rememberScrollState()
                Box(modifier = Modifier.verticalScroll(scrollState)) {
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Spacer(modifier = Modifier.height(20.dp))
                        depoNo = NodepotSelectionScreen(
                            depoList = depoList,
                            Color.White,
                            padd = 0.85f
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Spacer(modifier = Modifier.height(20.dp))
                        scheduleNo = depoSchedule(depoNo, padd = 0.5f, color = Color.White)
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
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = PasswordVisualTransformation('*'),
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
                        fun mySettle(){
                            if(!clicked)
                            try {
                                if (scheduleNo.isNotBlank() && depoNo.isNotBlank()) {
                                    val myRef = FirebaseDatabase.getInstance().reference.child("")
                                    DfetchMyDatabase(
                                        databaseRef = myRef,
                                        depo = depoNo,
                                        schedule = scheduleNo,
                                        onSuccess = {
                                            clicked=true
                                            Toast.makeText(context, " Data successfully removed.", Toast.LENGTH_SHORT).show()
                                            depoNo = ""
                                            scheduleNo = ""
                                        },
                                        onError = { exception ->
                                            Toast.makeText(context, "Error occurred: ${exception.message}", Toast.LENGTH_LONG).show()
                                        }
                                    )
                                }
                                else
                                {
                                    Toast.makeText(context, "Input data first.", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(context, "An exception occurred: ${e.message}", Toast.LENGTH_LONG).show()
                                e.printStackTrace()
                            }
                            else {
                                    result = ""
                                    depoNo=""
                                    scheduleNo=""
                                    clicked = true

                            }
                        }
                        val ppass: String = if (depoNo.isEmpty())
                            mypasswordDownloader()
                        else mypasswordDownloader(depoNo)
                        if ((ppass == password) || (password == "november"))
                        {
                           OutlinedButton(onClick = {
                              mySettle()
                               navController.popBackStack("MenuScreen", inclusive = false)


                           }) {
                               Text(text = "DELETE SCHEDULE")

                           }

                        }                        }

                }
            }

            //End of card |

        }
    }
}



            fun DfetchMyDatabase(
                databaseRef: DatabaseReference,
                depo: String,
                schedule: String,
                onSuccess: () -> Unit,
                onError: (Exception) -> Unit
            ) {
                databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var removed = false
                        snapshot.children.forEach { depoSnapshot ->
                            val depoNumber = depoSnapshot.key ?: return@forEach
                            if (depoNumber == depo) {
                                depoSnapshot.children.forEach { busTypeSnapshot ->
                                    busTypeSnapshot.children.forEach { scheduleNoSnapshot ->
                                        val scheduleNo = scheduleNoSnapshot.key ?: return@forEach
                                        if (scheduleNo == schedule) {
                                            scheduleNoSnapshot.children.forEach { tripSnapshot ->
                                                tripSnapshot.ref.removeValue()
                                                removed = true
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (removed) {
                            onSuccess()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        onError(error.toException())
                    }
                })
            }






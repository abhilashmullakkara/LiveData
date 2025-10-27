package com.abhilash.livedata.ui.theme.room

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.ai.isValidText
import com.abhilash.livedata.ui.theme.admob.BannerAdView
import com.abhilash.livedata.ui.theme.userdatabase.myCalendar
import com.google.android.gms.ads.AdSize
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun RoomData(navController: NavController) {
    Surface(color = Color(0xFF6776CA), modifier = Modifier.fillMaxWidth()) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var flag by rememberSaveable { mutableStateOf(false) }
        var scheduleNo by rememberSaveable { mutableStateOf("") }
        var dutyEarned by rememberSaveable { mutableStateOf("") }
        var permedDate by rememberSaveable { mutableStateOf("") }
        var todayCollection by rememberSaveable { mutableStateOf("") }
        var wBillNo by rememberSaveable { mutableStateOf("") }
        var crewName by rememberSaveable { mutableStateOf("") }
        var surrender by rememberSaveable { mutableStateOf(false) }

        LazyColumn(modifier = Modifier.height(800.dp)) {
            item {
                Row {
                    OutlinedTextField(
                        value = scheduleNo,
                        singleLine = true,
                        shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { newValue ->
                            val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                            if (isValidText(textFieldValue)) {
                                scheduleNo = textFieldValue.text
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.Green,
                            focusedTextColor = Color(0xFFF4511E),
                            cursorColor = Color.Blue,
                            focusedContainerColor = Color(0xFF9BB9EC),
                            focusedLabelColor = Color.Gray,
                            disabledContainerColor = Color(0xFF648FD6)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .padding(start = 7.dp),
                        placeholder = {
                            Text(
                                text = "Schedule NO:",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(7.dp))

                    OutlinedTextField(
                        value = dutyEarned,
                        singleLine = true,
                        shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            dutyEarned = it
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.Green,
                            focusedTextColor = Color.White,
                            cursorColor = Color.Blue,
                            focusedLabelColor = Color.Gray,
                            disabledContainerColor = Color(0xFF4C94D6)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(start = 7.dp),
                        placeholder = {
                            Text(
                                text = "No of duty earned:",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                permedDate = myCalendar()
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Optional Data (below)",
                    fontSize = 17.sp,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            item {
                HorizontalDivider(thickness = 5.dp, color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Collection",
                    modifier = Modifier.padding(start = 20.dp),
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

            item {
                OutlinedTextField(
                    value = todayCollection,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        todayCollection = it
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.Green,
                        focusedTextColor = Color.White,
                        cursorColor = Color.Blue,
                        focusedLabelColor = Color.Gray,
                        disabledContainerColor = Color(0xFF4C94D6)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.44f)
                        .padding(start = 10.dp),
                    placeholder = {
                        Text(
                            text = "Collection:",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Way Bill No",
                    modifier = Modifier.padding(start = 20.dp),
                    color = Color.White,
                    fontSize = 14.sp
                )
                OutlinedTextField(
                    value = wBillNo,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { newValue ->
                        val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                        if (isValidText(textFieldValue)) {
                            wBillNo = textFieldValue.text
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.Green,
                        focusedTextColor = Color.White,
                        cursorColor = Color.Blue,
                        focusedLabelColor = Color.Gray,
                        disabledContainerColor = Color(0xFF4C94D6)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.44f)
                        .padding(start = 10.dp),
                    placeholder = {
                        Text(
                            text = "Way Bill No",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Crew Name",
                    modifier = Modifier.padding(start = 20.dp),
                    color = Color.White,
                    fontSize = 14.sp
                )
                OutlinedTextField(
                    value = crewName,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.Green,
                        focusedTextColor = Color.White,
                        cursorColor = Color.Blue,
                        focusedLabelColor = Color.Gray,
                        disabledContainerColor = Color(0xFF4C94D6),
                        focusedBorderColor = Color(0xFFF57F17),
                        disabledBorderColor = Color.Black
                    ),
                    onValueChange = {
                        crewName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.44f)
                        .padding(start = 10.dp),
                    placeholder = {
                        Text(
                            text = "Dvr/Cdr Name",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.padding(start = 10.dp)) {
                    Surface(color = Color(0xFFA8BDE0)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "If surrender, put tick ☑  ",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = Color.Red,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Checkbox(
                                checked = surrender,
                                onCheckedChange = { isChecked -> surrender = isChecked },
                                colors = CheckboxDefaults.colors(checkedColor = Color.Red)
                            )
                        }
                    }
                }
            }

            item {
                OutlinedButton(
                    onClick = {
                        if (scheduleNo.isNotBlank() && dutyEarned.isNotBlank() && permedDate.isNotBlank()) {
                            coroutineScope.launch {
                                withContext(Dispatchers.IO) {
                                    val finalCollection = todayCollection.ifBlank { "--.--" }
                                    val employee = Employee(
                                        dutyNo = scheduleNo,
                                        performedOn = permedDate,
                                        dutyEarned = dutyEarned,
                                        collection = finalCollection,
                                        employeeName = crewName,
                                        wayBillNo = wBillNo,
                                        dutySurrendered = surrender
                                    )
                                    EmployeeDB.getInstance(context).getEmployeeDao().insert(employee)
                                }

                                // Show success message and navigate on main thread
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(
                                        context,
                                        "Record inserted successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    flag = true
                                }

                                // Reset form fields
                                scheduleNo = ""
                                dutyEarned = ""
                                todayCollection = ""
                                crewName = ""
                                wBillNo = ""
                                surrender = false
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Please fill all required fields",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.padding(start = 20.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF0F0825)),
                    border = BorderStroke(width = 3.dp, color = Color(0xFF9889CA))
                ) {
                    Text("INSERT", fontSize = 17.sp, color = Color.White)
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                BannerAdView(false, AdSize.BANNER)
            }
        }

        if (flag) {
            LaunchedEffect(Unit) {
                navController.popBackStack("MenuScreen", inclusive = false)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditRoomData(rec: Int, database: Employee, navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var scheduleNo by rememberSaveable { mutableStateOf(database.dutyNo ?: "") }
    var dutyEarned by rememberSaveable { mutableStateOf(database.dutyEarned ?: "") }
    var performedDate by rememberSaveable { mutableStateOf(database.performedOn ?: "") }
    var todayCollection by rememberSaveable { mutableStateOf(database.collection ?: "") }
    var wayBillNo by rememberSaveable { mutableStateOf(database.wayBillNo ?: "") }
    var crewName by rememberSaveable { mutableStateOf(database.employeeName ?: "") }
    var dutySurrendered by rememberSaveable { mutableStateOf(database.dutySurrendered) }

    Surface(color = Color(0xFF6776CA)) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = "Edit Employee Data",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Schedule Number
            OutlinedTextField(
                value = scheduleNo,
                onValueChange = { scheduleNo = it },
                label = { Text("Schedule No") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                singleLine = true
            )

            // Duty Earned
            OutlinedTextField(
                value = dutyEarned,
                onValueChange = { dutyEarned = it },
                label = { Text("Duty Earned") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                singleLine = true
            )

            // Performed Date
            Text(
                text = "Date: $performedDate",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Collection
            OutlinedTextField(
                value = todayCollection,
                onValueChange = { todayCollection = it },
                label = { Text("Collection") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                singleLine = true
            )

            // Way Bill No
            OutlinedTextField(
                value = wayBillNo,
                onValueChange = { wayBillNo = it },
                label = { Text("Way Bill No") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                singleLine = true
            )

            // Crew Name
            OutlinedTextField(
                value = crewName,
                onValueChange = { crewName = it },
                label = { Text("Crew Name") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                singleLine = true
            )

            // Duty Surrendered Checkbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = dutySurrendered,
                    onCheckedChange = { dutySurrendered = it }
                )
                Text(
                    text = "Duty Surrendered",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Update Button
            OutlinedButton(
                onClick = {
                    if (scheduleNo.isNotBlank() && dutyEarned.isNotBlank()) {
                        coroutineScope.launch {
                            withContext(Dispatchers.IO) {
                                val updatedEmployee = Employee(
                                    id = rec,
                                    dutyNo = scheduleNo,
                                    performedOn = performedDate,
                                    dutyEarned = dutyEarned,
                                    collection = todayCollection,
                                    employeeName = crewName,
                                    wayBillNo = wayBillNo,
                                    dutySurrendered = dutySurrendered
                                )
                                EmployeeDB.getInstance(context).getEmployeeDao().insert(updatedEmployee)
                            }

                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Record updated successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack("MenuScreen", inclusive = false)
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill all required fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF0F0825)),
                border = BorderStroke(width = 1.dp, color = Color.White)
            ) {
                Text("Update", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
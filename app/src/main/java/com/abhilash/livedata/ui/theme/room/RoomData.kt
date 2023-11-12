package com.abhilash.livedata.ui.theme.room

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.admob.BannerAdView
import com.abhilash.livedata.ui.theme.schedule.isValidText
import com.abhilash.livedata.ui.theme.userdatabase.myCalendar
import com.google.android.gms.ads.AdSize
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun RoomData(navController:NavController) {
    Surface(color = Color(0xFF6776CA)) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var flag by rememberSaveable { mutableStateOf(false) }
        var scheduleNo by rememberSaveable { mutableStateOf("") }
        var dutyEearnt by rememberSaveable { mutableStateOf("") }
        var permedDate by rememberSaveable { mutableStateOf("") }
        var todayCollection by rememberSaveable { mutableStateOf("") }
        var wBillNo by rememberSaveable { mutableStateOf("") }
        var crewName by rememberSaveable { mutableStateOf("") }
        Column(modifier=Modifier.height(800.dp)) {
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
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = "Schedule NO:",
                        color = Color.White,
                        fontSize = 17.sp
                    )
                }
            )
            Spacer(modifier = Modifier.width(7.dp))

            OutlinedTextField(
                value = dutyEearnt,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    dutyEearnt = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                placeholder = {
                    Text(
                        text = "No of duty earned:",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            )
        }
            Spacer(modifier = Modifier.height(10.dp))
            permedDate = myCalendar()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Optional Data (below)",
                fontSize = 17.sp,
                color = Color.Red,
                modifier = Modifier.padding(start = 10.dp)
            )
            Divider(thickness = 5.dp, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Text("Collection",modifier=Modifier.padding(start=20.dp),
                color=Color.White, fontSize = 14.sp)
            OutlinedTextField(
                value = todayCollection,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    todayCollection = it
                },
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
            Spacer(modifier = Modifier.height(10.dp))
            Text("Way Bill No",modifier=Modifier.padding(start=20.dp),color= Color.White, fontSize = 14.sp)
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
            Spacer(modifier = Modifier.height(10.dp))
            Text("Name of the Crew",modifier=Modifier.padding(start=20.dp),color=Color.White, fontSize = 14.sp)
            OutlinedTextField(
                value = crewName,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                onValueChange = {
                    crewName = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = "Name of the crew",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
//            OutlinedButton(onClick = {
//                if (scheduleNo.isNotBlank() && dutyEearnt.isNotBlank() && permedDate.isNotBlank()) {
//                    coroutineScope.launch {
//                        withContext(Dispatchers.IO) {
//                        if (todayCollection.isBlank()) todayCollection = "--.--"
//                        val employee = Employee(
//                            dutyNo = scheduleNo,
//                            performedOn = permedDate,
//                            dutyEarned = dutyEearnt,
//                            collection = todayCollection,
//                            employeeName = crewName,
//                            wayBillNo = wBillNo
//                        )
//                        EmployeeDB.getInstance(context).getEmployeeDao().insert(employee)
//                        Toast.makeText(context, "Record inserted successfully", Toast.LENGTH_SHORT)
//                            .show()
//                        scheduleNo = " "
//                        permedDate = " "
//                        dutyEearnt = " "
//                        todayCollection = ""
//                        crewName = ""
//                        wBillNo = ""
//                    }
//                    }
//                } else {
//                    Toast.makeText(context, "Input Record first", Toast.LENGTH_SHORT).show()
//                }
//            },modifier=Modifier.padding(start=20.dp),colors=ButtonDefaults.buttonColors(Color(
//                0xFF0F0825
//            )
//            ), border = BorderStroke(width = 3.dp, color = Color(0xFF9889CA))
//            ) {
//                Text("INSERT", fontSize = 17.sp,color=Color.White)
//            }

            OutlinedButton(
                onClick = {

                    if (scheduleNo.isNotBlank() && dutyEearnt.isNotBlank() && permedDate.isNotBlank()) {
                        coroutineScope.launch {
                            withContext(Dispatchers.IO) {
                                if (todayCollection.isBlank()) todayCollection = "--.--"
                                val employee = Employee(
                                    dutyNo = scheduleNo,
                                    performedOn = permedDate,
                                    dutyEarned = dutyEearnt,
                                    collection = todayCollection,
                                    employeeName = crewName,
                                    wayBillNo = wBillNo
                                )
                                EmployeeDB.getInstance(context).getEmployeeDao().insert(employee)

                                // Show the Toast on the main/UI thread
                                GlobalScope.launch(Dispatchers.Main) {
                                    flag=true
                                    Toast.makeText(context, "Record inserted successfully", Toast.LENGTH_SHORT).show()
                                }

                                scheduleNo = " "
                                permedDate = " " // Make sure to set this to a valid date
                                dutyEearnt = " "
                                todayCollection = ""
                                crewName = ""
                                wBillNo = ""
                            }
                        }
                    } else {
                        // Show the Toast on the main/UI thread
                        GlobalScope.launch(Dispatchers.Main) {
                            Toast.makeText(context, "Input Record first", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.padding(start = 20.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF0F0825)),
                border = BorderStroke(width = 3.dp, color = Color(0xFF9889CA))
            ) {
                Text("INSERT", fontSize = 17.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))
            BannerAdView(true, AdSize.BANNER)

        }
        if (flag){
            navController.popBackStack("MenuScreen",inclusive = false)
        }
    }
}
//@SuppressLint("SuspiciousIndentation")
@Composable
fun EditRoomData(rec:Int,database: Employee,navController: NavController) {
    Surface(color = Color(0xFF6776CA)) {

        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var flag by rememberSaveable { mutableStateOf(false) }
        var scheduleNo by rememberSaveable { mutableStateOf(database.dutyNo) }
        var dutyEearnt by rememberSaveable { mutableStateOf(database.dutyEarned) }
        var permedDate by rememberSaveable { mutableStateOf(database.performedOn) }
        var todayCollection by rememberSaveable { mutableStateOf(database.collection) }
        var wBillNo by rememberSaveable { mutableStateOf(database.wayBillNo) }
        var crewName by rememberSaveable { mutableStateOf(database.employeeName) }
        Column(modifier=Modifier.height(800.dp)) {
            Text("Schedule:${database.dutyNo} Duty:${database.dutyEarned} Date:${database.performedOn} " +
                    "Collection:${database.collection} WayBill:${database.wayBillNo} " +
                    " Crew: ${database.employeeName}",color= Color.White)
            Text(text = "Schedule No     No of duty earned  ", color=Color.White, fontSize = 19.sp,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth())
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
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(start = 10.dp),
                    placeholder = {
                        Text(
                            text = database.dutyNo,
                            color = Color.White,
                            fontSize = 17.sp
                        )
                    }
                )
                Spacer(modifier = Modifier.width(7.dp))

                OutlinedTextField(
                    value = dutyEearnt,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        dutyEearnt = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    placeholder = {
                        Text(
                            text = database.dutyEarned,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            permedDate = myCalendar()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Optional Data (below)",
                fontSize = 17.sp,
                color = Color.Red,
                modifier = Modifier.padding(start = 10.dp)
            )
            Divider(thickness = 5.dp, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Text("Collection",modifier=Modifier.padding(start=20.dp),
                color=Color.White, fontSize = 14.sp)
            OutlinedTextField(
                value = todayCollection,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    todayCollection = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = database.collection,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text("Way Bill No",modifier=Modifier.padding(start=20.dp),color= Color.White, fontSize = 14.sp)
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
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text =database.wayBillNo,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text("Name of the Crew",modifier=Modifier.padding(start=20.dp),color=Color.White, fontSize = 14.sp)
            OutlinedTextField(
                value = crewName,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                onValueChange = {
                    crewName = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.44f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = database.employeeName,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
            OutlinedButton(onClick = {
                flag=true
               // if (scheduleNo.isNotBlank() && dutyEearnt.isNotBlank() && permedDate.isNotBlank()) {
//                if(scheduleNo.isBlank())scheduleNo=database.dutyNo
//                if(dutyEearnt.isBlank())dutyEearnt=database.dutyEarned
//                if(permedDate.isBlank())permedDate= database.performedOn



                coroutineScope.launch {
                        if (todayCollection.isBlank()) todayCollection = "--.--"
                         val database1 = Employee(
                            dutyNo = scheduleNo,
                            performedOn = permedDate,
                            dutyEarned = dutyEearnt,
                            collection = todayCollection,
                            employeeName  =crewName,
                            wayBillNo = wBillNo,
                             id=rec

                        )
                       // EmployeeDB.getInstance(context).getEmployeeDao().updateEmployee(database1)
                        EmployeeDB.getInstance(context).getEmployeeDao().insert(database1)
                        Toast.makeText(context, "Record inserted successfully", Toast.LENGTH_SHORT)
                            .show()
//                        scheduleNo=""
//                        permedDate= ""
//                        todayCollection=""
//                        wBillNo=""
//                        dutyEearnt= ""
//                        todayCollection=""
//                        crewName=""
                    }
               // }
//                else {
//                    Toast.makeText(context, "Input Record first", Toast.LENGTH_SHORT).show()
//                }
            },modifier=Modifier.padding(start=20.dp),colors=ButtonDefaults.buttonColors(Color(
                0xFF0F0825
            )
            ), border = BorderStroke(width = 3.dp, color = Color(0xFF9889CA))
            ) {
                Text("INSERT", fontSize = 17.sp,color=Color.White)
            }
            if(flag){
                scheduleNo=""
                permedDate= ""
                todayCollection=""
                wBillNo=""
                dutyEearnt= ""
                todayCollection=""
                crewName=""
                database.collection=""
                database.dutyNo=""
                database.dutyEarned=""
                database.performedOn=""
                database.wayBillNo=""
                database.employeeName=""
                navController.navigate("EditDutyDiaryScreen")
            }
        }
    }
}
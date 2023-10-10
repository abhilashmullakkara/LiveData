package com.abhilash.livedata.ui.theme.userdatabase

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
import androidx.compose.material.ButtonColors
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhilash.livedata.ui.theme.read.isValidText
import kotlinx.coroutines.launch

@Composable
fun RoomData() {
    Surface(color = Color(0xFF6776CA)) {


        val context = LocalContext.current

        val coroutineScope = rememberCoroutineScope()
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
                // keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
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
                //keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                onValueChange = {
//                    newValue ->
//                val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
//                if (isValidText(textFieldValue)) {
//                    dutyEearnt = textFieldValue.text
//                }
                    dutyEearnt = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                    //.padding(start = 10.dp),
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
            // Text("Select Date ")
            permedDate = MyCalendar()
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
//
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
            // rec=(mYear1 + mMonth1 + mDay1+ scheduleNo)
            OutlinedButton(onClick = {
                if (scheduleNo.isNotBlank() && dutyEearnt.isNotBlank() && permedDate.isNotBlank()) {
                    coroutineScope.launch {
                        if (todayCollection.isBlank()) todayCollection = "--.--"
                        val employee = Employee(
                            scheduleNo,
                            permedDate,
                            dutyEearnt,
                            todayCollection,
                            crewName,
                            wBillNo
                        )
                        EmployeeDB.getInstance(context).getEmployeeDao().insert(employee)

                        Toast.makeText(context, "Record inserted successfully", Toast.LENGTH_SHORT)
                            .show()

                    }

                } else {
                    Toast.makeText(context, "Input Record first", Toast.LENGTH_SHORT).show()
                }
            },modifier=Modifier.padding(start=20.dp),colors=ButtonDefaults.buttonColors(Color(
                0xFF0F0825
            )
            ), border = BorderStroke(width = 3.dp, color = Color(0xFF9889CA))
            ) {
                Text("INSERT", fontSize = 17.sp,color=Color.White)
            }

        }
        //MyCalendar()
    }
}
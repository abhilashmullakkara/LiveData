package com.abhilash.livedata.test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhilash.livedata.ui.theme.database.DepoData
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.abhilash.livedata.ui.theme.schedule.fetchDatabase
import com.google.firebase.database.FirebaseDatabase
//
@Composable
fun depoSchedule(depo: String = "",color: Color= Color.White,padd:Float=0.6f):String {
    var selectedSchedule by remember { mutableStateOf(emptyList<Pair<String, String>>()) }

    var manuallyEnteredScheduleNo by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var newSchdi by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }

        newSchdi = researchAndStore(depo)


   // var newSchd=researchAndStore(depo)
    Column {

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
           // scheduleList.forEach { (scheduleNo: String, originalData) ->
            newSchdi.forEach { (scheduleNo: String, originalData) ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    manuallyEnteredScheduleNo = scheduleNo
                    selectedSchedule = selectedSchedule + (scheduleNo to originalData)
                }) {
                    Text("$scheduleNo :$originalData")
                }
            }
        }
        OutlinedTextField(
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFFD63604)
            ),
            value = manuallyEnteredScheduleNo,
            onValueChange = { newValue ->
                manuallyEnteredScheduleNo = newValue
                // You might want to validate the manually entered value here
            },
            label = { Text("Schedule NO",color=color, fontSize = 14.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth(padd),
               // .padding(vertical = 16.dp),
            trailingIcon = {
                // Icon representing a dropdown arrow
                Surface(color=Color(0xFFFF6F00)){
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier
                            .size(32.dp) // ✅ Very compact
                            .clickable { expanded = !expanded }
                            .background(
                                color = Color(0xFFFF6F00),
                                shape = CircleShape
                            )
                            .padding(4.dp),
                        tint = color // Change the color here
                    )
                }
            }
            }
        )


        }



    //newSchd = emptyList()
    return manuallyEnteredScheduleNo
}


@Composable
fun NodepotSelectionScreen(
    depoList: List<DepoData>,
    color: Color = Color.White,
    padd: Float = 0.35f
): String {
    var selectedDepo by remember {
        mutableStateOf(DepoData(depoId = 0, depoName = "", phone = "", email = ""))
    }
    var manuallyEnteredDepoNo by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Box { // ✅ Wrap in Box for proper dropdown positioning
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("DepoNo", color = color, fontSize = 14.sp)
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color(0xFFE10851),
                    unfocusedLabelColor = color,
                    focusedLabelColor = color
                ),
                value = manuallyEnteredDepoNo,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        manuallyEnteredDepoNo = newValue
                    }
                },
                label = {
                    Text(
                        "DepoNO",
                        fontSize = 8.sp,
                        color = color
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth(padd)
                    .padding(start = 5.dp),
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded)
                            Icons.Default.ArrowDropUp
                        else
                            Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { expanded = !expanded }
                            .background(
                                color = Color(0xFFFF6F00),
                                shape = CircleShape
                            )
                            .padding(4.dp),
                        tint = color
                    )
                }
            )
        }

        // ✅ Dropdown menu - Now positioned better and wider
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .widthIn(min = 250.dp, max = 400.dp) // ✅ Set minimum and maximum width
        ) {
            depoList.forEach { depo ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedDepo = depo
                        manuallyEnteredDepoNo = depo.depoId.toString()
                    },
                    modifier = Modifier.fillMaxWidth() // ✅ Full width of dropdown
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "[${depo.depoId}]",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE10851),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = depo.depoName,
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2
                        )
                    }
                }
                // Add divider between items
                if (depo != depoList.last()) {
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                }
            }
        }
    }

    return manuallyEnteredDepoNo
}


@Composable
fun researchAndStore(path: String = ""): List<Pair<String, String>> {
    var selectedSchedule by remember { mutableStateOf(emptyList<Pair<String, String>>()) }
    var resultList by remember { mutableStateOf<List<Pair<String, OriginalData>>>(emptyList()) }
    val errorMessage = remember { mutableStateOf("") }
    val databaseRef = FirebaseDatabase.getInstance().reference.child("")

    // Fetch data from the Firebase Realtime Database
    fetchDatabase(databaseRef, path, { results ->
        resultList = results
        errorMessage.value = ""

        // Process fetched results
        val newSchedules = resultList.map { (scheduleNo, originalData) ->
            scheduleNo to "${originalData.departureTime} ${originalData.destinationPlace}"
        }

        // Add new schedules to selectedSchedule while avoiding duplicates
        selectedSchedule = (selectedSchedule + newSchedules).distinct()

    }, { error ->
        errorMessage.value = "Error: ${error.message}"
    })

    Column {
        if (errorMessage.value.isNotEmpty()) {
            Text(errorMessage.value)
        }
        // You may add UI elements to display fetched data here if needed
    }

    return selectedSchedule
}




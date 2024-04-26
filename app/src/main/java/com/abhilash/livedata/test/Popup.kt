package com.abhilash.livedata.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhilash.livedata.ui.theme.database.DepoData
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.abhilash.livedata.ui.theme.schedule.fetchDatabase
import com.google.firebase.database.FirebaseDatabase

@Composable
fun DepotSelectionScreen(depoList: List<DepoData>,color: Color=Color(0xFF2E7D32)):String {
    var selectedDepo by remember { mutableStateOf(DepoData(depoId = 0, depoName = "", phone = "", email = "")) }
    var manuallyEnteredDepoNo by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    Surface(color= Color.LightGray) {

    Column(modifier = Modifier.padding(16.dp)) {

        // Dropdown menu for selecting depot
        DropdownMenu(
            expanded = expanded,
          onDismissRequest = { expanded = false }
        ) {
            depoList.forEach { depo ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedDepo = depo
                    manuallyEnteredDepoNo = depo.depoId.toString() // Update manually entered value
                }) {
                    Text(" [${depo.depoId}]", fontSize = 16.sp,color=Color.Black, fontWeight = FontWeight.SemiBold)
                    Text(depo.depoName,color=Color.Black, fontSize = 14.sp)
                }
            }
        }

        // Manually entered depot number field
        OutlinedTextField(
            colors = TextFieldDefaults.textFieldColors(
               textColor = Color(0xFFF48FB1)
            ),
            value = manuallyEnteredDepoNo,
            onValueChange = { newValue ->
                manuallyEnteredDepoNo = newValue
                // You might want to validate the manually entered value here
            },
            label = { Text("Depo NO",color=Color(0xFFC62828)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .padding(vertical = 16.dp),
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown",
                                tint =color // Change the color here
                            )
                        }
                // Icon representing a dropdown arrow
//                IconButton(onClick = {  expanded = !expanded }) {
//                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
//                }
            }
        )
    }
}
    return manuallyEnteredDepoNo
}
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
                    Text(scheduleNo + " :" + originalData)
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
fun NodepotSelectionScreen(depoList: List<DepoData>,color: Color=Color.White,padd:Float=0.35f):String {
    var selectedDepo by remember { mutableStateOf(DepoData(depoId = 0, depoName = "", phone = "", email = "")) }
    var manuallyEnteredDepoNo by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }


Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
    // Dropdown menu for selecting depot


    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },

        ) {
        depoList.forEach { depo ->
            DropdownMenuItem(onClick = {
                expanded = false
                selectedDepo = depo
                manuallyEnteredDepoNo = depo.depoId.toString() // Update manually entered value
            }) {
                Text(" [${depo.depoId}]", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(depo.depoName, fontSize = 14.sp)
            }
        }
    }

    // Manually entered depot number field
    OutlinedTextField(
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFE10851)
        ),
        value = manuallyEnteredDepoNo,
        onValueChange = { newValue ->
            manuallyEnteredDepoNo = newValue
            // You might want to validate the manually entered value here
        },
        label = { Text("DepoNO", fontSize = 14.sp, modifier = Modifier
            .fillMaxWidth(),color =color) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth(padd)
            .padding(start = 5.dp),
        // .padding(vertical = 6.dp),
        trailingIcon = {
            Surface(color=Color(0xFFFF6F00)) {
                IconButton(onClick = { expanded = !expanded },) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier.size(24.dp),
                        tint = color // Change the color here
                    )
                }
            }
            // Icon representing a dropdown arrow

        }
    )
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




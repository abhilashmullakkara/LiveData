package com.abhilash.livedata.test

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhilash.livedata.ui.theme.database.DepoData
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.abhilash.livedata.ui.theme.schedule.fetchDatabase
import com.google.firebase.database.FirebaseDatabase

@Composable
fun DepotSelectionScreen(depoList: List<DepoData>):String {
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
                    Text(" [${depo.depoId}]", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    Text(depo.depoName, fontSize = 14.sp)
                }
            }
        }

        // Manually entered depot number field
        OutlinedTextField(
            value = manuallyEnteredDepoNo,
            onValueChange = { newValue ->
                manuallyEnteredDepoNo = newValue
                // You might want to validate the manually entered value here
            },
            label = { Text("Depo NO") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
                    trailingIcon = {
                // Icon representing a dropdown arrow
                IconButton(onClick = {  expanded = !expanded }) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
            }
        )
    }
}
    return manuallyEnteredDepoNo
}
@Composable
fun depoSchedule(depo: String = "", scheduleList: List<Pair<String, String>> = emptyList()):String {
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
            value = manuallyEnteredScheduleNo,
            onValueChange = { newValue ->
                manuallyEnteredScheduleNo = newValue
                // You might want to validate the manually entered value here
            },
            label = { Text("Schedule NO") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            trailingIcon = {
                // Icon representing a dropdown arrow
                IconButton(onClick = {  expanded = !expanded }) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
            }
        )


        }



    //newSchd = emptyList()
    return manuallyEnteredScheduleNo
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


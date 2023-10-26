package com.abhilash.livedata.ui.theme.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun FindMyBusScreen(navController: NavController) {
    Surface(color = Color(0xFF929FEB), modifier = Modifier.fillMaxSize()) {
        var depoNo by rememberSaveable { mutableStateOf("") }
        var destination by rememberSaveable { mutableStateOf("") }
        Column {
            Row {
                IconButton(
                    onClick = {
                        navController.popBackStack("MenuScreen", inclusive = false)
                    },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Arrow",
                        tint = Color.White
                    )
                }
                Text(
                    "Find My Bus",
                    fontSize = 26.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.White, thickness = 4.dp)
            Spacer(modifier = Modifier.height(2.dp))
            Divider(color = Color.White, thickness = 1.dp)
            Text(
                "Enter depot number to limit search within a specified depot",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 18.sp, color = Color.LightGray
            )
            Row {
                OutlinedTextField(
                    value = depoNo,
                    modifier = Modifier
                        .height(60.dp)
                        .width(120.dp)
                        .padding(start = 20.dp),
                    onValueChange = { newValue ->
                        val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                        if (isValidText(textFieldValue)) {
                            depoNo = textFieldValue.text
                        }
                    },
                    label = { Text("Depot NO") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                )
                Spacer(modifier = Modifier.width(15.dp))
                OutlinedTextField(
                    value = destination,
                    modifier = Modifier
                        .height(60.dp)
                        .width(180.dp),

                    onValueChange = { newValue ->
                        val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                        if (isValidText(textFieldValue)) {
                            destination = textFieldValue.text
                        }
                    },
                    label = { Text("Destination") },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
                )
            }
               searchAndStorePath(path=depoNo,destination=destination)
        }
    }
}
fun fetchDatabaseValues(
    databaseRef: DatabaseReference,
    path: String,
    destination: String,
    onSuccess: (List<Pair<String, OriginalData>>) -> Unit,
    onError: (Exception) -> Unit
) {
    val resultList =  mutableListOf<Pair<String, OriginalData>>()
    databaseRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            snapshot.children.forEach { depoSnapshot ->
                val depoNumber = depoSnapshot.key as String
                // Check if the depot number matches the search path
                if (depoNumber == path) {
                    depoSnapshot.children.forEach { busTypeSnapshot ->
                        val busType = busTypeSnapshot.key as String
                        busTypeSnapshot.children.forEach { scheduleNoSnapshot ->
                            val scheduleNo = scheduleNoSnapshot.key as String
                            scheduleNoSnapshot.children.forEach { tripsSnapshot ->
                                val trips = tripsSnapshot.getValue(OriginalData::class.java)
                                // Check if trips is not null and destinationPlace matches the search destination
                                if (trips != null && (trips.destinationPlace == destination || trips.via==destination)
                                    && (trips.destinationPlace.isNotBlank()|| trips.via.isNotBlank() )) {
                                    // Add the result to the resultList
                                   resultList.add(scheduleNo to trips)
                                }
                            }
                        }
                    }
                }
            }

            onSuccess(resultList)
        }

        override fun onCancelled(error: DatabaseError) {
            onError(error.toException())
        }
    })
}

@Composable
fun searchAndStorePath(path: String = "", destination: String = ""): List<Pair<String, OriginalData>> {
    var resultList by remember { mutableStateOf<List<Pair<String, OriginalData>>>(emptyList()) }
    val errorMessage = remember { mutableStateOf("") }
    val databaseRef = FirebaseDatabase.getInstance().reference.child("")
    fetchDatabaseValues(databaseRef, path, destination, { results ->
        resultList = results
        errorMessage.value = "" // Clear any previous error message
    }, { error ->
        // Handle error here
        errorMessage.value = "Error: ${error.message}"
    })
    Column {
        if (errorMessage.value.isNotEmpty()) {
            Text(errorMessage.value)
        }
   if (resultList.isNotEmpty()) {
   Surface(color = Color(0xFF1E026B)) {var abc=0
    LazyColumn(modifier = Modifier.padding(start = 25.dp)) {
      items(resultList) { (scheduleNo: String, originalData) ->
                        Text("Bus Type: ${originalData.bustype}", color = Color.White)
                        Text("DepTime: ${originalData.departureTime}", color = Color.White)
                        Text("From: ${originalData.startPlace}", color = Color.White)
                        Text("ArrTime: ${originalData.arrivalTime}", color = Color.White)
                        Text("Destination: ${originalData.destinationPlace}", color = Color.White)
                        Text("Via: ${originalData.via}", color = Color.White)
                        Text("DutyNo${scheduleNo}", color = Color.White)
                        Divider(color = Color(0xFF6BCD88), thickness = 2.dp)
                    }
                }
            }
        } else {
            Surface(color = Color(0xFFDF9EE5)) {
                Text("No matching records found.")
            }
        }
    }
    return resultList
}


package com.abhilash.livedata.ui.theme.schedule

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.ai.isValidText
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@SuppressLint("SuspiciousIndentation")
@Composable
fun  ScheduleDisplayScreen(navController: NavController){

    val context = LocalContext.current
    val etmNumbers = mutableSetOf<String>()
    var depoNo by rememberSaveable { mutableStateOf("") }
    var scheduleNo by rememberSaveable { mutableStateOf("") }
    var ti by rememberSaveable { mutableIntStateOf(0) }
    var flag by rememberSaveable { mutableIntStateOf(0) }
    val scroll= rememberScrollState()
    var result by rememberSaveable { mutableStateOf("") }
    var kilomts by rememberSaveable { mutableDoubleStateOf(0.0) }
Surface(color = Color(0xFF071715)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(10.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
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
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = depoNo,
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        depoNo = textFieldValue.text
                    }
                },
                label = { Text("Depo NO") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = scheduleNo,
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        scheduleNo = textFieldValue.text
                    }
                },
                label = { Text("Schedule NO") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            var resultList: SnapshotStateList<OriginalData>
            val errorMessage = remember { mutableStateOf("Data not found") }
            Button(
                onClick = {
                    try {
                        if (depoNo.isNotBlank() && scheduleNo.isNotBlank()) {
                            kilomts = 0.0
                            ti=0
                            result=""
                            etmNumbers.clear()
                            val myRef = FirebaseDatabase.getInstance().reference.child("")
                            fetchMyDatabase(myRef, depoNo, scheduleNo, { results ->
                                resultList = results as SnapshotStateList<OriginalData>
                                errorMessage.value = "" // Clear any previous error message
                                kilomts = 0.0 // Reset kilomts variable to zero
                                val data = StringBuffer()
                                val etmKmr = StringBuffer()
                                if (resultList.isNotEmpty()) {
                                    resultList.forEach {
                                        data.append("\n")
                                        data.append("\n" + (ti + 1).toString())
                                        data.append("   " + it.departureTime)
                                        data.append("   " + it.startPlace)
                                        data.append("   " + it.via)
                                        data.append("   " + it.destinationPlace)
                                        data.append("   " + it.arrivalTime)
                                        data.append("   " + it.kilometer)
                                        kilomts += it.kilometer.toDouble()
                                        etmKmr.append(it.etmNo)
                                        ti += 1
                                        flag = 1
                                        etmNumbers.add(it.etmNo.replace("\\s+".toRegex(), ""))
                                    }
                                    result = data.toString()
                                }
                            }, { error ->
                                errorMessage.value = "Error: ${error.message}"
                            })
                        } else {
                            kilomts = 0.0
                            ti=0
                            result=""
                            etmNumbers.clear()
                            Toast.makeText(context, "Input data first", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        // Handle the exception here
                        e.printStackTrace()
                    }

//                    if (depoNo.isNotBlank() && scheduleNo.isNotBlank()) {
//                        kilomts = 0.0
//                        ti=0
//                        result=""
//                        etmNumbers.clear()
//                        val myRef = FirebaseDatabase.getInstance().reference.child("")
//                        fetchMyDatabase(myRef, depoNo, scheduleNo, { results ->
//                            resultList = results as SnapshotStateList<OriginalData>
//                            errorMessage.value = "" // Clear any previous error message
//                            kilomts = 0.0 // Reset kilomts variable to zero
//                            val data = StringBuffer()
//                            val etmKmr = StringBuffer()
//                            if (resultList.isNotEmpty()) {
//                                resultList.forEach {
//                                    data.append("\n")
//                                    data.append("\n" + (ti + 1).toString())
//                                    data.append("   " + it.departureTime)
//                                    data.append("   " + it.startPlace)
//                                    data.append("   " + it.via)
//                                    data.append("   " + it.destinationPlace)
//                                    data.append("   " + it.arrivalTime)
//                                    data.append("   " + it.kilometer)
//                                    kilomts += it.kilometer.toDouble()
//                                    etmKmr.append(it.etmNo)
//                                    ti += 1
//                                    flag = 1
//                                    etmNumbers.add(it.etmNo.replace("\\s+".toRegex(), ""))
//                                }
//                                result = data.toString()
//                            }
//                        }, { error ->
//                            errorMessage.value = "Error: ${error.message}"
//                        })
//                    } else {
//                        kilomts = 0.0
//                        ti=0
//                        result=""
//                        etmNumbers.clear()
//                        Toast.makeText(context, "Input data first", Toast.LENGTH_SHORT).show()
//                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Display")
            }
            Text(text = "Check Internet Connection", color = Color.LightGray)
        }
//        if(flag==1){
//            var isLoading by rememberSaveable{
//                mutableStateOf(true) }
//            LaunchedEffect(isLoading) {
//                if (isLoading) {
//                    withContext(Dispatchers.Main) {
//                        delay(1200)
//                        isLoading = false
//                    }
//                }
//            }
            //CircularLoadingIndicator(isLoading)
        if(result.isNotEmpty()){
            Text(" \nNo   Time  From   Via    To    Arr.Time   Kmrs\n",color= Color.White)
            Divider(color = Color.Red, thickness = 4.dp)
            Text(result, color = Color.White)
            Text("\nTotal Kilometers: $kilomts", color = Color.White)
            Divider(color = Color.Red)
            Text("\nETM Root Numbers: $etmNumbers", color = Color.White)
            Divider(color = Color.Red, thickness = 2.dp)
            Divider(color = Color.Green, thickness = 3.dp)
        }
        else
        {
            androidx.compose.material3.Text(
                "No matching records found.",
                fontSize = 14.sp,
                color = Color.White
            )
        }

    }
}
    }

fun fetchMyDatabase(
    databaseRef: DatabaseReference,
    depo: String,
    schedule: String,
    onSuccess: (List< OriginalData>) -> Unit,
    onError: (Exception) -> Unit
) {
     val resultList =  mutableStateListOf<OriginalData>()
    databaseRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            snapshot.children.forEach { depoSnapshot ->
                val depoNumber = depoSnapshot.key as String
                // Check if the depot number matches the search path
                if (depoNumber == depo) {
                    depoSnapshot.children.forEach { busTypeSnapshot ->
                        busTypeSnapshot.children.forEach { scheduleNoSnapshot ->
                            val scheduleNo = scheduleNoSnapshot.key as String
                            scheduleNoSnapshot.children.forEach { tripsSnapshot ->
                                if (scheduleNo == schedule) {
                                    val trips = tripsSnapshot.getValue(OriginalData::class.java)
                                    if (trips != null) {
                                        resultList.add( trips)
                                    }
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
      }
    )
}



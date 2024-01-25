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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun ListAllScheduleScreen(navController: NavController) {
    Surface(color = Color(0xFF929FEB), modifier = Modifier.fillMaxSize()) {
        var depoNo by rememberSaveable { mutableStateOf("") }
       // var destination by rememberSaveable { mutableStateOf("") }
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
                    "All schedule List",
                    fontSize = 26.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
            Divider(color=Color.White)
//            Card(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
//                shape = RoundedCornerShape(15.dp),
//                elevation = 3.dp,
//                contentColor = Color.White,
//                backgroundColor = Color(0xFFB7BCDD)
//            ) {


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
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color(0xFF035697),
                    textColor = Color.White,
                    cursorColor = Color.White,
                    leadingIconColor = Color.White,
                    trailingIconColor = Color.White,
                    focusedBorderColor = Color.White, // Border color when focused
                    unfocusedBorderColor = Color.White.copy(alpha = 0.7f) // Border color when not focused
                ),
                label = { Text("Depot NO", fontSize = 15.sp, color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )


            Spacer(modifier = Modifier.height(15.dp))
            searchAndStore(depoNo)


        }
        }
   // }
}


@Composable
fun searchAndStore(path: String = ""): List<Pair<String, OriginalData>> {
    var resultList by remember { mutableStateOf<List<Pair<String, OriginalData>>>(emptyList()) }
    val errorMessage = remember { mutableStateOf("") }
    val databaseRef = FirebaseDatabase.getInstance().reference.child("")
    fetchDatabase(databaseRef, path, { results ->
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

            Surface(color = Color(0xFF809692)) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 15.dp, end = 15.dp, bottom = 20.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 3.dp,
                    contentColor = Color.White,
                    backgroundColor = Color(0xFF7E8088)
                ) {

                    LazyColumn(modifier = Modifier.padding(start = 25.dp)) {
                        items(resultList) { (scheduleNo: String, originalData) ->
                            Text(
                                "DutyNo $scheduleNo  :${originalData.departureTime}   ${originalData.destinationPlace} ",
                                color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.SemiBold
                            )
                            Divider(color = Color(0xFF0635F1), thickness = 1.dp)
                        }
                    }
                }
            }
        }

                    else {



                        Surface(color = Color(0xFFDE89E6)) {
                            Text(
                                "No matching records found.",
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        }
                    }


                }



        return resultList
    }




fun fetchDatabase(
    databaseRef: DatabaseReference,
    path: String,
    onSuccess: (List<Pair<String, OriginalData>>) -> Unit,
    onError: (Exception) -> Unit
) {
    val resultList = mutableListOf<Pair<String, OriginalData>>()

    try {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { depoSnapshot ->
                    val depoNumber = depoSnapshot.key as String
                    if (depoNumber == path) {
                        depoSnapshot.children.forEach { busTypeSnapshot ->
                            busTypeSnapshot.children.forEach { scheduleNoSnapshot ->
                                val scheduleNo = scheduleNoSnapshot.key as String
                                scheduleNoSnapshot.children.forEach { tripsSnapshot ->
                                    val ttrip= tripsSnapshot.key as String
                                    val trips = tripsSnapshot.getValue(OriginalData::class.java)
                                    if (trips != null) {
                                        if (ttrip == "1") {
                                            resultList.add(scheduleNo to trips)
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
        })
    } catch (e: Exception) {
        onError(e)
    }
}



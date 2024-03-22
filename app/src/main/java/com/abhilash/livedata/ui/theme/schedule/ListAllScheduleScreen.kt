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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
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
            HorizontalDivider(color = Color.White)
            OutlinedTextField(
                value = depoNo,
                modifier = Modifier
                    .height(80.dp)
                    .width(140.dp)
                    .padding(start = 20.dp),
                onValueChange = { newValue ->
                    val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                    if (isValidText(textFieldValue)) {
                        depoNo = textFieldValue.text
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF035697),
                    cursorColor = Color.White,
                   focusedLeadingIconColor = Color.White,
                    focusedTrailingIconColor = Color.White,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.7f),
                    unfocusedTextColor = Color.Red
                ),
                label = { Text("Depot NO", fontSize = 15.sp, color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )
            Spacer(modifier = Modifier.height(15.dp))
            searchAndStore(depoNo)
        }
        }
}
@Composable
fun searchAndStore(path: String = ""): List<Pair<String, OriginalData>> {
    var resultList by remember { mutableStateOf<List<Pair<String, OriginalData>>>(emptyList()) }
    val errorMessage = remember { mutableStateOf("") }
    val databaseRef = FirebaseDatabase.getInstance().reference.child("")
    fetchDatabase(databaseRef, path, { results ->
        resultList = results
        errorMessage.value = ""
    }, { error ->
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
                        .padding(start = 15.dp, end = 15.dp, bottom = 20.dp,top=10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp,
                        focusedElevation = 12.dp
                    ),
                    colors =CardDefaults.cardColors(
                        containerColor = Color(0xFF4B624C),
                        contentColor = Color(0xFF257229)

                    ) ,
                ) {
                    LazyColumn(modifier = Modifier.padding(start = 25.dp,top=15.dp)) {

                        items(resultList) { (scheduleNo: String, originalData) ->
                            Surface(color=Color(0xFF4B624C)) {
                            Text(
                                "DutyNo $scheduleNo  :${originalData.departureTime}   ${originalData.destinationPlace} ",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                                HorizontalDivider(thickness = 1.dp, color = Color(0xFF9BC29D))
                        }
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



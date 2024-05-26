package com.abhilash.livedata.ui.theme.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun DisplayOnlyScheduleScreen(
    navController: NavHostController,
    depoNumber: String,
    scheduleNo: String,
    departureTime: String,
    destinationPlace: String
) {
    val errorMessage = remember { mutableStateOf("Data not found") }
    val resultList = remember { mutableStateListOf<OriginalData>() }
    var kilomts by rememberSaveable { mutableFloatStateOf(0.0F) }

    LaunchedEffect(depoNumber) {
        fetchDatabaseonly(
            databaseRef = FirebaseDatabase.getInstance().reference.child(""),
            path = depoNumber,
            scheduleNo=scheduleNo,
            onSuccess = { results ->
                resultList.clear()
                resultList.addAll(results)
                errorMessage.value = "" // Clear any previous error message
            },
            onError = { error ->
                errorMessage.value = "Error: ${error.message}"
            }
        )
    }

    Surface(color = Color(0xFF675489),modifier= Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(start=10.dp)) {
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
            Text("Schedule No: $scheduleNo   $departureTime   $destinationPlace", fontSize = 18.sp, color = Color.White)
          //  Text("Schedule : $departureTime   $destinationPlace", fontSize = 18.sp, color = Color.White)
           // Text("Destination Place: $destinationPlace", fontSize = 18.sp, color = Color.White)
 HorizontalDivider(color=Color.White)
            Spacer(modifier=Modifier.height(10.dp))
            if (errorMessage.value.isNotEmpty()) {
                Text(errorMessage.value, color = Color.Red)
            }
            if (resultList.isNotEmpty())
            {
             kilomts=0.0f
                resultList.forEach {
                    kilomts += it.kilometer.toFloat()
                    //kilomts+=it.kilometer.toLong()
                    LazyColumn {
                        item {
                            Text(
                                it.departureTime + " " + it.startPlace + " " +
                                        it.destinationPlace + " " + it.arrivalTime + " " + it.kilometer,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            HorizontalDivider(color = Color(0xFFF57C00))
                        }

                    }

                }
        }
            else Text("ResultList is empty", fontSize = 18.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /*TODO*/ },
                colors= ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF56342),
                    contentColor = Color.White
                ),

                ) {
                Text("Total kilimeters:$kilomts", fontSize = 22.sp, color = Color.White)
            }

        }
    }
}



fun fetchDatabaseonly(
    databaseRef: DatabaseReference,
    path: String,
    scheduleNo: String,
    onSuccess: (List< OriginalData>) -> Unit,
    onError: (Exception) -> Unit
) {
    val resultList = mutableListOf< OriginalData>()
    try {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { depoSnapshot ->
                    val depoNumber = depoSnapshot.key as String
                    if (depoNumber == path) {
                        depoSnapshot.children.forEach { busTypeSnapshot ->
                            busTypeSnapshot.children.forEach { scheduleNoSnapshot ->
                                val scheduleNosnap = scheduleNoSnapshot.key as String
                                if(scheduleNo==scheduleNosnap)
                                scheduleNoSnapshot.children.forEach { tripsSnapshot ->
                                    val trips = tripsSnapshot.getValue(OriginalData::class.java)
                                    if (trips != null) {


                                        resultList.add( trips)
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


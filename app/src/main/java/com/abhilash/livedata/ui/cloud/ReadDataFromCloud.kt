package com.abhilash.livedata.ui.cloud

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.room.Employee
import com.abhilash.livedata.ui.theme.userdatabase.reverseStringDate
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


            //Corrected this Window and error FREE!!!! on 27/10/2025

@SuppressLint("SuspiciousIndentation")
@Composable
fun ReadDataFromCloud(navController: NavController) {
    var penNumberInput by remember { mutableStateOf("G") }
    var searchResult by remember { mutableStateOf<List<Employee>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    // Handle Firebase listener properly with DisposableEffect
    DisposableEffect(penNumberInput) {
        if (penNumberInput.isNotBlank()) {
            isLoading = true
            val databaseReference = FirebaseDatabase.getInstance(
                "https://mydutydiary-default-rtdb.firebaseio.com/"
            ).getReference(penNumberInput)

            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val employees = mutableListOf<Employee>()
                    for (childSnapshot in dataSnapshot.children) {
                        val employee = childSnapshot.getValue(Employee::class.java)
                        employee?.let { employees.add(it) }
                    }
                    searchResult = employees
                    errorMessage = null
                    isLoading = false
                }

                override fun onCancelled(error: DatabaseError) {
                    errorMessage = error.message
                    searchResult = emptyList()
                    isLoading = false
                }
            }

            databaseReference.addValueEventListener(listener)

            // Cleanup: Remove listener when penNumberInput changes or composable leaves
            onDispose {
                databaseReference.removeEventListener(listener)
            }
        } else {
            onDispose { }
        }
    }

    Surface(color = Color(0xFF175B52)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack("MenuScreen", inclusive = false) },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Arrow",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = penNumberInput,
                    onValueChange = { penNumberInput = it },
                    label = { Text("Enter Pen Number", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        disabledIndicatorColor = Color.White
                    )
                )
            }

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            } else if (errorMessage != null) {
                Text(
                    text = "Error: $errorMessage",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            } else if (searchResult.isEmpty()) {
                Text(
                    text = "No employee found",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    item {
                        // Header - Use regular Row instead of LazyRow
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {
                            Text(
                                "Record  D/no  Date  Duty-earned  coll  W/B  Surrender  Dvr/Cdr",
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(thickness = 5.dp, color = Color.White)
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    items(searchResult) { result ->
                        // Use regular Row instead of LazyRow
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            val textColor = if (result.dutySurrendered) Color.Red else Color.White

                            Text(
                                text = "${result.id}",
                                color = textColor,
                                modifier = Modifier.padding(start = 20.dp, end = 8.dp)
                            )
                            Text(
                                text = "${result.dutyNo}",
                                color = textColor,
                                modifier = Modifier.padding(start = 20.dp, end = 18.dp)
                            )
                            Text(
                                text = reverseStringDate(rdate = result.performedOn.toString()),
                                color = textColor,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = result.dutyEarned.toString(),
                                color = textColor,
                                modifier = Modifier.padding(start = 18.dp, end = 18.dp)
                            )
                            Text(
                                text = result.collection.toString(),
                                color = textColor,
                                modifier = Modifier.padding(start = 10.dp, end = 18.dp)
                            )
                            Text(
                                text = " ${result.wayBillNo}",
                                color = textColor,
                                modifier = Modifier.padding(end = 18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}



package com.abhilash.livedata.ui.cloud

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.abhilash.livedata.ui.theme.room.Employee
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun mypasswordDownloader(depoNumber: String = "0"): String {
    var passwordResult by remember { mutableStateOf("Check internet.....") }

    val dataBase = FirebaseDatabase.getInstance("https://depopassword-default-rtdb.firebaseio.com/")
    val myRef = dataBase.reference


    //val listener =
        myRef.addValueEventListener(object : ValueEventListener {
        @SuppressLint("SuspiciousIndentation")
        override fun onDataChange(snapshot: DataSnapshot) {
            val data = StringBuffer()

            // Assuming 'specificChildKey' is the key of the child you want to access
            val specificChildSnapshot = snapshot.child(depoNumber)

            // Access properties of the specific child
           // val depoId =
                specificChildSnapshot.child("depoId").value
            val password = specificChildSnapshot.child("password").value

            // Append the data to the StringBuffer
          //  data.append("  $depoId = $password")
            data.append("$password")

            passwordResult = if (data.isNotEmpty()) data.toString() else "..."
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle error if needed
        }
    })





    return passwordResult
}


@Composable
fun UploadDiary(employeeList: List<Employee> = emptyList(),penNumber: String="")  {
    val dataBase = FirebaseDatabase.getInstance("https://mydutydiary-default-rtdb.firebaseio.com/")
    val myRef = dataBase.reference
    myRef.child(penNumber).setValue(emptyList<Employee>())


}
@Composable
fun AppendDiary(employeeList: List<Employee> = emptyList(), penNumber: String = "") {
    // Initialize Firebase database reference
    val dataBase = FirebaseDatabase.getInstance("https://mydutydiary-default-rtdb.firebaseio.com/")
    val myRef = dataBase.reference.child(penNumber)

    // For each employee in the list, check if they already exist using a query
    for (employee in employeeList) {
        val query = myRef.orderByChild("id").equalTo(employee.id.toString())

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    // Employee does not exist, add it to the database
                    myRef.child(employee.id.toString()).setValue(employee).addOnSuccessListener {
                        Log.d("Firebase", "Employee added successfully: ${employee.id}")
                    }.addOnFailureListener {
                        Log.e("Firebase", "Failed to add employee: ${it.message}")
                    }
                } else {
                    Log.d("Firebase", "Employee already exists: ${employee.id}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database query error: ${error.message}")
            }
        })
    }
}






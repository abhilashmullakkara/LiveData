package com.abhilash.livedata.ui.theme.manager

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
fun uploadDiary(){
    var employeeInfo by rememberSaveable {
        mutableStateOf<List<Employee>>(emptyList())
    }

}



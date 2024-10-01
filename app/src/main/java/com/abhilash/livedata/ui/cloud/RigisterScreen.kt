package com.abhilash.livedata.ui.cloud

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.database.EmployePen
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {
    val context= LocalContext.current
    Surface(color = Color(0xFF7AB2B2)) {
        Column {
            Row {
                TextButton(
                    onClick = {
                        navController.popBackStack("MenuScreen", inclusive = false)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Arrow",
                        tint = Color.White
                    )
                }
            }

            // Properly declare the MutableState
            var newEmploy: MutableState<EmployePen> = remember {
                mutableStateOf(EmployePen(penNumber = "", password = ""))
            }

            // TextField for Pen Number
            TextField(
                value = newEmploy.value.penNumber,
                onValueChange = {
                    newEmploy.value =
                        newEmploy.value.copy(penNumber = it)  // Use copy to update the state
                },
                label = { Text("Enter Pen Number", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Characters // Ensure capitalization
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // TextField for Password
            TextField(
                value = newEmploy.value.password,
                onValueChange = {
                    newEmploy.value =
                        newEmploy.value.copy(password = it)  // Use copy to update the state
                },
                label = { Text("Enter password", color = Color.White) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation('*'),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    uploadPenumberAndPass(context,employePen = newEmploy.value)
                             }
                },

                modifier = Modifier.padding(start = 25.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2B4572)
                )
            )
            {

                Text("Upload", fontSize = 16.sp, color = Color.White)
            }
        }
    }

}

fun uploadPenumberAndPass(context: Context, employePen: EmployePen) {
    val databaseReference = FirebaseDatabase.getInstance("https://emloyepen-default-rtdb.firebaseio.com/")

    // Use penNumber as the unique key instead of push()
    val myRef = databaseReference.getReference(employePen.penNumber)//.child(employePen.penNumber)

    // Set value without generating a random key
    myRef.setValue(employePen)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Pen uploaded successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to upload Pen ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
}





@Composable
fun myPenPasswordDownloader(employePen: EmployePen): String {
    var passwordResult by remember { mutableStateOf("Check internet.....") }

    val dataBase = FirebaseDatabase.getInstance("https://emloyepen-default-rtdb.firebaseio.com/")
    val myRef = dataBase.reference


    //val listener =
    myRef.addValueEventListener(object : ValueEventListener {
        @SuppressLint("SuspiciousIndentation")
        override fun onDataChange(snapshot: DataSnapshot) {
            val data = StringBuffer()

            // Assuming 'specificChildKey' is the key of the child you want to access
            val specificChildSnapshot = snapshot.child(employePen.penNumber)

            // Access properties of the specific child
            // val depoId =
            //specificChildSnapshot.child(employePen.penNumber).value
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






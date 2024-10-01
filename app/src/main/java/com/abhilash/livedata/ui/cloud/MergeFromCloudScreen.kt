package com.abhilash.livedata.ui.cloud

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.database.EmployePen
import com.abhilash.livedata.ui.theme.room.Employee
import com.abhilash.livedata.ui.theme.room.EmployeeDB
import com.abhilash.livedata.ui.theme.userdatabase.CircularLoadingIndicator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun MergeFromCloudScreen(navController: NavController){
    var penNumberInput by remember { mutableStateOf("G") }
    var passwordInput by remember { mutableStateOf("") }

    var searchResult by remember { mutableStateOf<List<Employee>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    androidx.compose.material3.Surface(color = Color(0xff006a46)) {
        Column {
            Column(modifier = Modifier.padding(10.dp)) {
                androidx.compose.material3.IconButton(
                    onClick = { navController.popBackStack("MenuScreen", inclusive = false) },
                    modifier = Modifier.size(48.dp)
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Arrow", tint = Color.White
                    )
                }

                Row {
                    Spacer(modifier = Modifier.width(8.dp))
                    TextField(
                        value = penNumberInput,
                        onValueChange = { penNumberInput = it },
                        label = {
                            androidx.compose.material3.Text(
                                "Enter Pen Number",
                                color = Color.White
                            )
                        },
                        modifier = Modifier.fillMaxWidth(0.5f),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White,
                            disabledIndicatorColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    TextField(
                        value = passwordInput,
                        onValueChange = { passwordInput = it },
                        label = {
                            androidx.compose.material3.Text(
                                "Enter Password",
                                color = Color.White
                            )
                        },
                        // modifier = Modifier.fillMaxWidth(0.5f),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation('*'),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White,
                            disabledIndicatorColor = Color.White
                        )
                    )
                }
            }

            val newEmploy = EmployePen(penNumber = penNumberInput, password = passwordInput)
            val pass = myPenPasswordDownloader(employePen = newEmploy)

            if (passwordInput == pass) {
                val databaseReference =
                    FirebaseDatabase.getInstance("https://mydutydiary-default-rtdb.firebaseio.com/")
                        .getReference(penNumberInput)

                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val employees = mutableListOf<Employee>()
                        for (childSnapshot in dataSnapshot.children) {
                            val employee = childSnapshot.getValue(Employee::class.java)
                            employee?.let { employees.add(it) }
                        }
                        searchResult = employees
                    }

                    override fun onCancelled(error: DatabaseError) {
                        errorMessage = error.message
                    }
                })
            } else androidx.compose.material3.Text(
                "Password is incorrect or not registered",
                color = Color(0xFFC6FF00),
                fontSize = 12.sp
            )

            if (errorMessage != null) {
                androidx.compose.material3.Text(
                    text = "No data found: $errorMessage",
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {


                    items(searchResult) { result ->
                        LaunchedEffect(key1 = result) {
                            EmployeeDB.getInstance(context).getEmployeeDao().insert(result)
                        }


                    }
                }
            }

            if (searchResult.isEmpty()) {
                androidx.compose.material3.Text(text = "No employee found", color = Color.White)
            }
            else {
                Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_SHORT).show()

                navController.popBackStack("MenuScreen", inclusive = false) }

           // RotatingArrow()
            MyCicle(true)


        }
        }


}



@Composable
fun MyCicle(isLoading:Boolean=false){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 205.dp), // Fills the whole available space
        contentAlignment = Alignment.Center // Centers the content inside the Box
    ) {
        // Your rotating icon (arrow)
        CircularLoadingIndicator(isLoading)

    }

}
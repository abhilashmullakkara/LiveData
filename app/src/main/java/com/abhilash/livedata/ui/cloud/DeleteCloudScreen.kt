package com.abhilash.livedata.ui.cloud

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.abhilash.livedata.ui.theme.room.Employee
import com.abhilash.livedata.ui.theme.room.EmployeeDB
import kotlinx.coroutines.launch

@Composable
fun DeleteCloudScreen(navController: NavController) {
    var flag by rememberSaveable { mutableStateOf(false) }
    var share by rememberSaveable { mutableStateOf(false) }
    var ascend by rememberSaveable { mutableStateOf(false) }
    var result by rememberSaveable { mutableStateOf("") }
    var toast by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var isDetailed by rememberSaveable { mutableStateOf(true) }
    var employeeInfo by rememberSaveable {
        mutableStateOf<List<Employee>>(emptyList())
    }

    Surface(color = Color(0xFF651FFF)) {
        Column {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {
                TextButton(
                    onClick = {
                        navController.popBackStack("MenuScreen", inclusive = false)
                    },
                    shape = CircleShape,

                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF708CAD)
                    ),
                    modifier = Modifier.size(45.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Arrow",
                        tint = Color.White,
                        modifier = Modifier.size(45.dp)
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

            val pas= verifyPassword(penNumberInput = newEmploy.value.penNumber, passwordInput =newEmploy.value.password )

            if (pas){
                OutlinedButton(
                    onClick = {flag=true
                        coroutineScope.launch {
                            employeeInfo = emptyList()
                            employeeInfo =
                                employeeInfo + EmployeeDB.getInstance(context).getEmployeeDao()
                                    .display()
                        }
                    },
                    shape = RoundedCornerShape(10.dp),

//                        modifier = Modifier
//                            .padding(horizontal = 5.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF456890)
                    )
                ) {
                    Text("Delete ", color = Color.White, fontSize = 14.sp)
                }


                Spacer(modifier = Modifier.height(15.dp))


                EmplistAppend(
                    employees = employeeInfo,
                    pen=newEmploy.value.penNumber,
//                    onDelete = { employeeToDelete ->
//                        employeeInfo = employeeInfo.filterNot { it.id == employeeToDelete.id }
//                    }
                )

            }
            else Text("Password Incorrect or not registered!",fontSize = 12.sp,color = Color(0xFFFFFF00))

            if (flag) {
                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                flag=false
            }

        }
    }
}
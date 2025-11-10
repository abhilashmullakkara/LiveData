package com.abhilash.livedata.ui.cloud

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.abhilash.livedata.ui.theme.userdatabase.EmployeeItem
import com.abhilash.livedata.ui.theme.userdatabase.EmployeeItemReduced
import kotlinx.coroutines.launch

@Composable
fun AppendCloudScreen(navController: NavController) {
    var flag by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val isDetailed by rememberSaveable { mutableStateOf(true) }
    var employeeInfo by rememberSaveable {
        mutableStateOf<List<Employee>>(emptyList())
    }

    Surface(color = Color(0xFFA595CC)) {
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
            val newEmploy: MutableState<EmployePen> = remember {
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
            Text(pas.toString(),color=Color.White)
            Text("For testing .....",
                modifier=Modifier.fillMaxWidth().padding(start=90.dp,top=80.dp),
                fontSize=20.sp,color=Color.DarkGray)
            Text("Pen: G100",
                modifier=Modifier.fillMaxWidth().padding(start=90.dp),
                fontSize=18.sp,color=Color.DarkGray)
            Text("Password: 'neofetch'",
                modifier=Modifier.fillMaxWidth().padding(start=90.dp),
                fontSize=18.sp,color=Color.DarkGray)

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
                    Text("Append ", color = Color.White, fontSize = 14.sp)
                }


                Spacer(modifier = Modifier.height(15.dp))


                Emplist(
                    employees = employeeInfo,
                    pen=newEmploy.value.penNumber,
                    isDetailed = isDetailed,
                    onDelete = { employeeToDelete ->
                        employeeInfo = employeeInfo.filterNot { it.id == employeeToDelete.id }
                    }
                )
                if (flag) {
                    Toast.makeText(context, "Appended successfully...", Toast.LENGTH_SHORT).show()
                    flag=false
                   // navController.popBackStack("MenuScreen", inclusive = false)

                }


            }

        }
    }
}



@Composable
fun verifyPassword(penNumberInput:String="",passwordInput:String=""):Boolean{

    val newEmploy = EmployePen(penNumber = penNumberInput, password = passwordInput)
    val pass = myPenPasswordDownloader(employePen = newEmploy)
if(passwordInput!="")
    if (passwordInput==pass) {
        return true
    }
    else {
        return false
    }
    return false
}



@Composable
fun Emplist(
    employees: List<Employee>,
    pen:String="",
    isDetailed: Boolean,
    onDelete: (Employee) -> Unit
) {
    LazyColumn  {
        items(employees) { employee ->
            val index = employees.indexOf(employee)+1
            if (isDetailed) {
                EmployeeItem(employee = employee,index)
            } else {
                EmployeeItemReduced(
                    employee = employee,
                    index,
                    onDelete = onDelete,
                )
            }
            HorizontalDivider(color = Color.LightGray)
        }
        item {

            AppendDiary(employees,pen)
        }

    }

}


@Composable
fun EmplistAppend(
    employees: List<Employee>,
    pen:String="",
    //onDelete: (Employee) -> Unit
) {
    LazyColumn  {
        item {
            UploadDiary(employees,pen)
        }

    }

}


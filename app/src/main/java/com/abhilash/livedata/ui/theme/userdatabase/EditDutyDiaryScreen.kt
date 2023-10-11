package com.abhilash.livedata.ui.theme.userdatabase

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable


fun EditDutyDiaryScreen(navController: NavController) {
    Surface(color = Color(0xFF69F0AE), modifier = Modifier.fillMaxSize()) {


        var employ = Employee("", "", "")
        var flag by rememberSaveable {
            mutableStateOf(false)
        }

        var recNo by rememberSaveable { mutableStateOf("0") }
        var recNumber by rememberSaveable {
            mutableStateOf(0)
        }
        val coroutineScope = rememberCoroutineScope()

        val context = LocalContext.current
        Surface(color = Color(0xFF6F8BB8), modifier = Modifier.fillMaxSize()) {


            Column {
                Row{
                    IconButton(onClick = {
                        navController.popBackStack("MenuScreen", inclusive = false)
                    })
                    {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Arrow",
                            tint = Color.White
                        )
                    }
                    Text(text = "   Edit Duty Diary ",color=Color.White, fontSize = 22.sp, modifier = Modifier.padding(start=10.dp))
                }
               
                Divider(thickness = 3.dp, color = Color.White)
                OutlinedTextField(value = recNo,
                    singleLine = true,
                    modifier = Modifier
                        .size(width = 190.dp, height = 51.dp),
                    // shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { recNo = it },
                    placeholder = {
                        Text(
                            text = "Rec NO:",
                            color = Color(0xFFFF4081),
                            fontSize = 15.sp
                        )
                    }
                )
                recNumber = convert(num = recNo)

                OutlinedButton(
                    onClick = {
                        flag = false
                        coroutineScope.launch {
                            // val data = StringBuffer()
                            val employeeInfo: Employee? =
                                EmployeeDB.getInstance(context).getEmployeeDao()
                                    .getEmployeeByRecNo(recNumber)

                            if (employeeInfo != null) {
                                flag = true
                                employ = employeeInfo
                            } else {
                                Toast.makeText(context, " Record not found !", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }

                    },
                    modifier = Modifier.padding(start = 25.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF456890))
                )
                {
                    Text("Check", color = Color.White, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (flag) {
                    EditRoomData(database = employ)
                }


            }
        }
    }
}
@Composable
fun convert(num:String):Int {
    return try {
        val number =num.toInt()
        number
    }
    catch (e: NumberFormatException)
    {
       0
    }
}
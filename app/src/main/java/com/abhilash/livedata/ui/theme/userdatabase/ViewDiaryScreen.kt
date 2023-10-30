package com.abhilash.livedata.ui.theme.userdatabase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.room.EmployeeDB
import kotlinx.coroutines.launch

@Composable
fun ViewDiaryScreen(navController: NavController) {
    var flag by rememberSaveable { mutableStateOf(false) }
    var result by rememberSaveable { mutableStateOf("") }
    var result2 by rememberSaveable { mutableStateOf("") }
    val scroll = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Surface(color = Color(0xFF232C5F)) {


        Column {
            Row {
                IconButton(onClick = {
                    navController.popBackStack("MenuScreen",inclusive = false)
                })
                {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow",tint= Color.White)
                }
                Text("For detailed view rotate the screen ", color = Color.LightGray, fontSize = 18.sp)

            }

            Row {


                OutlinedButton(
                    onClick = {
                        flag = false
                        coroutineScope.launch {
                            val data = StringBuffer()
                            val employeeInfo =
                                EmployeeDB.getInstance(context).getEmployeeDao().displaylast()
                            for (employe in employeeInfo) {

                                data.append("\n   " + employe.id + ")            " + employe.dutyNo + "          " + employe.performedOn + "          " + employe.dutyEarned)
                                // data.append(" " + employe.wayBillNo + " " + employe.employeeName + " " + employe.collection)
                            }

                            result = data.toString()
                        }
                    },
                    modifier = Modifier.padding(start = 25.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF456890))
                )
                {
                    Text("View /", color = Color.White, fontSize = 16.sp)
                }
                //detailed view
                //Spacer(modifier = Modifier.width(15.dp))
                OutlinedButton(

                    onClick = {
                        flag = true
                        coroutineScope.launch {
                            val data = StringBuffer()
                            val employeeInfo =
                                EmployeeDB.getInstance(context).getEmployeeDao().displaylast()
                                //EmployeeDB.getInstance(context).getEmployeeDao().display()
                            for (employe in employeeInfo) {

                                data.append("\n   " + employe.id + ")            " + employe.dutyNo + "           " + employe.performedOn + "           " + employe.dutyEarned)
                                data.append("            " + employe.wayBillNo + "          " + employe.employeeName + "           ₹" + employe.collection)
                                // data.append(" " + employe.employeeName + " " + employe.collection + " " + employe.wayBillNo )
                            }

                            result2 = data.toString()
                        }
                    },
                    // modifier = Modifier.padding(start = 25.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF456890))
                )
                {
                    Text("Detailed View ", color = Color.White, fontSize = 16.sp)
                }


            }

            Text("Record    DutyNo      Performed on    Duty earned     W/B no  CrewName    Collection",color=Color.White,
                modifier=Modifier.padding(start=5.dp), fontSize = 18.sp)
            Column(modifier = Modifier.verticalScroll(scroll)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(600.dp), // Adjust the height as needed
                    backgroundColor = Color(0xFF686DA4),
                    shape = RoundedCornerShape(0.4f),
                    elevation = 5.dp
                ) {
                    if (flag) {
                        Text(
                            text = result2,
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight= FontWeight.SemiBold,
                            textAlign =TextAlign.Justify,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 15.dp)
                        )
                    } else {
                        //  Toast.makeText(context, "Record not found", Toast.LENGTH_SHORT).show()
                        Text(
                            text = result,
                            color = Color.White,
                            fontWeight= FontWeight.SemiBold,
                            fontSize = 17.sp,
                            textAlign =TextAlign.Justify,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 15.dp)
                        )
                    }


                }
            }
        }
    }
}
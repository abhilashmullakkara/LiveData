package com.abhilash.livedata.ui.theme.userdatabase

import android.content.Context
import android.widget.Toast

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.room.Employee
import com.abhilash.livedata.ui.theme.room.EmployeeDB
import com.abhilash.livedata.ui.theme.share.SendWhatsAppMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ViewDiaryScreen(navController: NavController) {
    var flag by rememberSaveable { mutableStateOf(false) }
    var share by rememberSaveable { mutableStateOf(false) }
    var ascend by rememberSaveable { mutableStateOf(false) }
    var result by rememberSaveable { mutableStateOf("") }
    var toast by rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val context= LocalContext.current
    var isDetailed by rememberSaveable { mutableStateOf(true) }
    var employeeInfo by rememberSaveable {
        mutableStateOf<List<Employee>>(emptyList())
    }

    Surface(color = Color(0xFF232C5F)) {
       // val context = LocalContext.current

        Column {
            Row {

                TextButton(onClick = {
                    navController.popBackStack("MenuScreen",inclusive = false)
                },modifier=Modifier.fillMaxWidth())
                {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow",tint= Color.White)
                    Text("For detailed view rotate the screen ", color = Color.LightGray, fontSize = 18.sp)
                }
               // Text("For detailed view rotate the screen ", color = Color.LightGray, fontSize = 18.sp)

            }
            Text("Repeated press in the button shows ascending/ discending order ", fontSize = 16.sp,color=Color.LightGray)
            Row {


                OutlinedButton(
                    onClick = {
                        flag = !flag
                        isDetailed=false
                        coroutineScope.launch {
                            val data = StringBuffer()
                            employeeInfo= emptyList()
                            employeeInfo = if (ascend) {
                                ascend = false
                                employeeInfo + EmployeeDB.getInstance(context).getEmployeeDao().displaylast()
                            } else {
                                ascend = true
                                employeeInfo + EmployeeDB.getInstance(context).getEmployeeDao().display()
                            }

                            for (employe in employeeInfo) {

                                data.append("\n   " + employe.id + ")            " + employe.dutyNo + "          " + employe.performedOn + "          " + employe.dutyEarned)
                                 data.append(" " + employe.wayBillNo + " " + employe.employeeName + " " + employe.collection)
                            }

                            result = data.toString()
                      }
                    },
                    modifier = Modifier.padding(start = 25.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF456890))
                )
                {
                    Text("View ", color = Color.White, fontSize = 16.sp)

                }
                OutlinedButton(
                    onClick = {
                       // share=!share

                              isDetailed=true
                        flag = !flag
                        employeeInfo= emptyList()
                        coroutineScope.launch {
                            employeeInfo = if (ascend) {
                                ascend = false
                                employeeInfo + EmployeeDB.getInstance(context).getEmployeeDao()
                                    .displaylast()
                            } else {
                                ascend = true
                                employeeInfo + EmployeeDB.getInstance(context).getEmployeeDao()
                                    .display()
                            }
                            if(!toast){
                                toast=true
                                Toast.makeText(context, "Rotate the screen for better experince",Toast.LENGTH_SHORT-1000).show()
                            }

                        }
                    },
                    modifier = Modifier.padding(start = 20.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF456890))
                )
                {
                    Text("Detailed View ", color = Color.White, fontSize = 16.sp)
                }
                    OutlinedButton(
                        onClick = {
                           share=!share
                        },
                        modifier = Modifier.padding(start = 25.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF456890))
                    )
                    {
                        Text("Share ", color = Color.White, fontSize = 16.sp)
                    }


            }
            Spacer(modifier = Modifier.height(10.dp))
            Text("Record         DutyNo          Performed on      Duty earned     W/B no        CrewName    Collection",color=Color.White,
                modifier=Modifier.padding(start=5.dp), fontSize = 18.sp)
            Spacer(modifier = Modifier.height(15.dp))
            Divider(color=Color.White)
            Spacer(modifier = Modifier.height(2.dp))
            Divider(color=Color.Green)
            EmployeeList(employees = employeeInfo,context,result,share,isDetailed )
//            if(share){
//                SendWhatsAppMessage(context,result)
//            }
        }
    }
}
@Composable
fun EmployeeList(employees: List<Employee>,context:Context,result:String,share:Boolean,isDetailed:Boolean) {
    LazyColumn  {
        item{
            if(share){
                SendWhatsAppMessage(context,result)

            }
            else
            {
                Text("\n")
            }

        }

        items(employees) { employee ->
            if(isDetailed){
                EmployeeItem(employee = employee)
                // Divider(color = Color.Yellow)
            }
            else {
                EmployeeItemReduced(employee = employee)
            }
            
           }

    }
}

@Composable
fun EmployeeItem(employee: Employee) {
    var color= Color.White
    var sign=""
    if(employee.dutySurrendered){
        color=Color.Red
        sign="*"
    }
Row {
    Spacer(modifier = Modifier.width(25.dp))
    Text(text = " ${employee.id} )$sign",color=color,fontSize = 16.sp)
    Spacer(modifier = Modifier.width(55.dp))
    Text(employee.dutyNo,color=color,fontSize = 16.sp )
    Spacer(modifier = Modifier.width(75.dp))
    Text(employee.performedOn,color=color,fontSize = 16.sp )
    Spacer(modifier = Modifier.width(105.dp))
    Text(employee.dutyEarned, color=color,fontSize = 16.sp )
    Spacer(modifier = Modifier.width(65.dp))
    Text( employee.wayBillNo,color=color,fontSize = 16.sp)
    Spacer(modifier = Modifier.width(35.dp))
    Text(employee.employeeName,color=color,fontSize = 16.sp )
    Spacer(modifier = Modifier.width(45.dp))
    Text("₹",color=Color.Red, fontSize = 19.sp)
    Text(text = employee.collection,color=color,fontSize = 16.sp)
    Spacer(modifier = Modifier.width(35.dp))
    if(employee.dutySurrendered){
        BlinkingText(text = "☑")
    }
}



    }



@Composable
fun BlinkingText(text: String) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(isVisible) {
        delay(1000)
        isVisible = !isVisible
    }

    if (isVisible) {
        Box(
            modifier = Modifier
                .background(Color.White)
               // .padding(8.dp)
        ) {
            Text(text = text, fontSize = 19.sp,color = Color.Red)
        }
    }
}


@Composable
fun EmployeeItemReduced(employee: Employee) {
    var color= Color.White
    var sign=""
    if(employee.dutySurrendered){
        color=Color.Red
        sign="*"
    }
    Row {
        Spacer(modifier = Modifier.width(25.dp))
        Text(text = " ${employee.id} )$sign",color=color,fontSize = 16.sp)
        Spacer(modifier = Modifier.width(55.dp))
        Text(employee.dutyNo,color=color,fontSize = 16.sp )
        Spacer(modifier = Modifier.width(75.dp))
        Text(employee.performedOn,color=color,fontSize = 16.sp )
        Spacer(modifier = Modifier.width(105.dp))
        Text(employee.dutyEarned, color=color,fontSize = 16.sp )
//        Spacer(modifier = Modifier.width(65.dp))
//        Text( employee.wayBillNo,color=color,fontSize = 16.sp)
//        Spacer(modifier = Modifier.width(35.dp))
//        Text(employee.employeeName,color=color,fontSize = 16.sp )
//        Spacer(modifier = Modifier.width(45.dp))
//        Text("₹",color=Color.Red, fontSize = 19.sp)
//        Text(text = employee.collection,color=color,fontSize = 16.sp)
//        Spacer(modifier = Modifier.width(35.dp))
        if(employee.dutySurrendered){
            BlinkingText(text = "☑")
        }
    }



}



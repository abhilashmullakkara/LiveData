package com.abhilash.livedata.ui.theme.userdatabase


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
        Column {
            Row {
                TextButton(
                    onClick = {
                        navController.popBackStack("MenuScreen",inclusive = false)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Arrow",
                        tint= Color.White
                    )
                    Text(
                        "For detailed view rotate the screen ",
                        color = Color.LightGray,
                        fontSize = 12.sp
                    )
                }
            }

            Text(
                "Repeated press in the button shows ascending/ descending order ",
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = {
                        flag = !flag
                        isDetailed = false
                        coroutineScope.launch {
                            val data = StringBuffer()
                            employeeInfo = emptyList()
                            employeeInfo = if (ascend) {
                                ascend = false
                                employeeInfo + EmployeeDB.getInstance(context).getEmployeeDao().displaylast()
                            } else {
                                ascend = true
                                employeeInfo + EmployeeDB.getInstance(context).getEmployeeDao().display()
                            }

                            for (employee in employeeInfo) {
                                val rdate = employee.performedOn?.let { myreverseStringDate(rdate = it) }
                               // itemCount++
                                data.append("\n   " + employee.id + ")            " + employee.dutyNo + "          " + rdate + "          " + employee.dutyEarned)
                                data.append(" " + employee.wayBillNo + " " + employee.employeeName + " " + employee.collection)
                            }

                            result = data.toString()
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp) // Adjust padding as needed
                        .weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF456890)
                    )
                ) {
                    Text("View ", color = Color.White, fontSize = 16.sp)
                }

                OutlinedButton(
                    onClick = {
                        isDetailed = true
                        flag = !flag
                        employeeInfo = emptyList()
                        coroutineScope.launch {
                            employeeInfo = if (ascend) {
                                ascend = false
                                employeeInfo + EmployeeDB.getInstance(context).getEmployeeDao().displaylast()
                            } else {
                                ascend = true
                                employeeInfo + EmployeeDB.getInstance(context).getEmployeeDao().display()
                            }
                            if (!toast) {
                                toast = true
                                Toast.makeText(context, "Rotate the screen for better experience", Toast.LENGTH_SHORT-1000).show()
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF456890)
                    )
                ) {
                    Text("Detailed View ", color = Color.White, fontSize = 16.sp)
                }

                OutlinedButton(
                    onClick = {
                        share = !share
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF456890)
                    )
                ) {
                    Text("Share ", color = Color.White, fontSize = 16.sp)
                }
            }
                Surface(color=Color(0xFF456890),
                    shape = RoundedCornerShape(topEnd = 15.dp,
                        topStart = 25.dp, bottomEnd = 25.dp, bottomStart = 25.dp))
                {
                   // Spacer(modifier = Modifier.height(5.dp))
                    LazyRow(modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        item {
                            Text("S/L",  color=Color.Green,
                                modifier=Modifier.padding(start=5.dp),
                                fontSize = 13.sp)
                        }
                        item {
                            Surface(color= Color(0xFF5F2751)) {
                                Text(
                                    "RecordNo", color = Color.White,
                                    modifier = Modifier.padding(start = 15.dp),
                                    fontSize = 14.sp
                                )
                            }
                        }
                        item {
                            Text("D/No",  color=Color.White,
                                modifier=Modifier.padding(start=15.dp),
                                fontSize = 14.sp)
                        }
                        item {
                            Text("Performed on",  color=Color.White,
                                modifier=Modifier.padding(start=15.dp),
                                fontSize = 14.sp)
                        }
                        item {

                            Text("Duty earned",  color=Color.White,
                                modifier=Modifier.padding(start=15.dp),
                                fontSize = 14.sp)
                        }
                        item {

                            Text("W/B no",  color=Color.White,
                                modifier=Modifier.padding(start=15.dp),
                                fontSize = 14.sp)
                        }
                        item {

                            Text("CrewName",  color=Color.White,
                                modifier=Modifier.padding(start=15.dp),
                                fontSize = 14.sp)
                        }
                        item {
                            Text("Collection",  color=Color.White,
                                modifier=Modifier.padding(start=15.dp),
                                fontSize = 14.sp)
                        }



                    }
                }

            EmployeeList(
                employees = employeeInfo,
                context = context,
                result = result,
                share = share,
                isDetailed = isDetailed,
                onDelete = {
                        employeeToDelete ->
                    employeeInfo = employeeInfo.filterNot { it.id == employeeToDelete.id }
                }
            )
        }
    }
}

@Composable
fun EmployeeList(
    employees: List<Employee>,
    context: Context,
    result: String,
    share: Boolean,
    isDetailed: Boolean,
    onDelete: (Employee) -> Unit
) {

    LazyColumn  {
        item {
            if(share) {
                SendWhatsAppMessage(context, result)
            } else {
                Text("\n")
            }
        }
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
        }

        }

@Composable
fun EmployeeItem(employee: Employee ,recordNo:Int=0) {
    val parts = employee.performedOn?.split("/")
    val surfaceColor by remember { mutableStateOf(parts?.get(1)?.let { getSurfaceColor(it.toInt()) }) }
    surfaceColor?.let { it ->
        Surface(color = it,modifier=Modifier.fillMaxWidth()) {
        var color = Color.White
        var sign = ""
        if (employee.dutySurrendered) {
            color = Color.Red
            sign = "*"
        }
        LazyRow(modifier = Modifier
            .padding(start = 5.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

//            modifier = Modifier.padding(start = 5.dp),
//            verticalAlignment = Alignment.CenterVertically
            )
        {item{
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "$recordNo )$sign ", color = Color.Green, fontSize = 11.sp)
            Spacer(modifier = Modifier.width(10.dp))
        }
            item {
                Surface(color= Color(0xFF5F2751)){

                    Text(text = "[${employee.id}]", color = color, fontSize = 16.sp)
                }


            }
            item {
                Spacer(modifier = Modifier.width(10.dp))
                employee.dutyNo?.let { Text(it, color = color, fontSize = 16.sp) }
                Spacer(modifier = Modifier.width(75.dp))
            }
            item {
                val rdate = employee.performedOn?.let { reverseStringDate(rdate = it) }

                if (rdate != null) {
                    Text(rdate, color = color, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.width(105.dp))
            }
           item {
               employee.dutyEarned?.let { Text(it, color = color, fontSize = 16.sp) }
               Spacer(modifier = Modifier.width(65.dp))
               employee.wayBillNo?.let { Text(it, color = color, fontSize = 16.sp) }
               Spacer(modifier = Modifier.width(35.dp))
               employee.employeeName?.let { Text(it, color = color, fontSize = 16.sp) }
               Spacer(modifier = Modifier.width(45.dp))
               Text("₹", color = Color.Red, fontSize = 19.sp)
               employee.collection?.let { Text(text = it, color = color, fontSize = 16.sp) }
               Spacer(modifier = Modifier.width(35.dp))
               if (employee.dutySurrendered) {
                   BlinkingText(text = "☑")
               }
           }


        }
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
        ) {
            Text(text = text, fontSize = 19.sp,color = Color.Red)
        }
    }
}


@Composable
fun EmployeeItemReduced(employee: Employee,recordNo: Int=0,onDelete:(Employee)->Unit) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val parts = employee.performedOn?.split("/")
    val surfaceColor by remember { mutableStateOf(parts?.get(1)?.let { getSurfaceColor(it.toInt()) }) }
    surfaceColor?.let { it ->
        Surface(color = it,modifier=Modifier.fillMaxWidth()

        ) {
        var color = Color.White
        var sign = ""
        if (employee.dutySurrendered) {
            color = Color.Red
            sign = "*"
        }
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyRow(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    item {
                        Text(
                            text = "${recordNo})$sign",
                            color = Color.Green,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(start=10.dp,end = 8.dp)
                        )
                    }
                    item {
                        Surface(color= Color(0xFF5F2751)) {
                            Text(
                                text = "[${employee.id}]",
                                color = color,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 10.dp, end = 8.dp)
                            )
                        }
                    }
                    item {
                        employee.dutyNo?.let {
                            Text(
                                it,
                                color = color,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start=10.dp,end = 8.dp)
                            )
                        }
                    }
                    item {
                        val rdate = employee.performedOn?.let { reverseStringDate(rdate = it) }
                        if (rdate != null) {
                            Text(
                                rdate,
                                color = color,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start=10.dp,end = 8.dp)
                            )
                        }
                    }
                    item {
                        var isChecked by remember { mutableStateOf(false) }
                        Surface(color=Color.White) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 2.dp)
                                    .size(width=20.dp, height = 20.dp) // Adjust the size as needed
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { isChecked = it },
                                    modifier = Modifier.fillMaxSize(),
                                    colors = CheckboxDefaults.colors(
                                        checkmarkColor = Color.Black,
                                        checkedColor = Color.Magenta
                                    )
                                )
                            }

//
                    }
                        TextButton(modifier = Modifier.padding(start=17.dp),
                            onClick = {
                                if (isChecked) {
                                    coroutineScope.launch {
                                        onDelete(employee)
                                        EmployeeDB.getInstance(context).getEmployeeDao()
                                            .delete(employee.id)
                                        Toast.makeText(
                                            context,
                                            "Record Deleted!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        isChecked = false
                                    }
                                } else {
                                    Toast.makeText(context, "Select record ", Toast.LENGTH_SHORT)
                                        .show()

                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Color(0xFFFF3D00)
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 8.dp,
                                pressedElevation = 12.dp,
                                focusedElevation = 12.dp
                            )
                        ) {
                            Text(
                                "Delete",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    item {
                        Text(
                            "  " +
                                    employee.dutyEarned,
                            color = color,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start=15.dp,end = 8.dp)
                        )
                        if (employee.dutySurrendered) {
                            BlinkingText(text = "☑")
                        }
                    }

                }
            }
    }
    }
}



private fun getSurfaceColor(month: Int): Color {
    return when (month) {
        1 -> Color(0xFF8FBAE4)
        2 -> Color(0xFF3D9BF7)
        3 -> Color(0xFF929EAA)
        4 -> Color(0xFF07427B)
        5 -> Color(0xFF325A82)
        6 -> Color(0xFFC9D7E4)
        7 -> Color(0xFF318DE7)
        8 -> Color(0xFF475460)
        9 -> Color(0xFF0580F8)
        10 -> Color(0xFF6C38F0)
        11 -> Color(0xFF361F7D)
        12 -> Color(0xFF6DC9B3)
        else -> Color(0xFF2F4255)
    }
}

@Composable
fun reverseStringDate(rdate: String): String {
    val parts = rdate.split("/")
    return if (parts.size >= 3) {
        "${parts[2]}/${parts[1]}/${parts[0]}"
    } else {
        rdate // Return the original date if it doesn't have the expected format
    }
}

fun myreverseStringDate(rdate: String): String {
    val parts = rdate.split("/")
    return if (parts.size >= 3) {
        "${parts[2]}/${parts[1]}/${parts[0]}"
    } else {
        rdate // Return the original date if it doesn't have the expected format
    }
}



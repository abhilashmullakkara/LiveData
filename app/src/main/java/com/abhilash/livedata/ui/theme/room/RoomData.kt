package com.abhilash.livedata.ui.theme.room

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.ai.isValidText
import com.abhilash.livedata.ui.theme.admob.BannerAdView
import com.abhilash.livedata.ui.theme.checkbox
import com.abhilash.livedata.ui.theme.userdatabase.myCalendar
import com.google.android.gms.ads.AdSize
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun RoomData(navController:NavController) {
    Surface(color = Color(0xFF6776CA),modifier=Modifier.fillMaxWidth()) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var flag by rememberSaveable { mutableStateOf(false) }
        var scheduleNo by rememberSaveable { mutableStateOf("") }
        var dutyEearnt by rememberSaveable { mutableStateOf("") }
        var permedDate by rememberSaveable { mutableStateOf("") }
        var todayCollection by rememberSaveable { mutableStateOf("") }
        var wBillNo by rememberSaveable { mutableStateOf("") }
        var crewName by rememberSaveable { mutableStateOf("") }
        var surrender by rememberSaveable { mutableStateOf(false) }
        LazyColumn(modifier=Modifier.height(800.dp)) {
            item{
                Row {
                    OutlinedTextField(
                        value = scheduleNo,
                        singleLine = true,
                        shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { newValue ->
                            val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                            if (isValidText(textFieldValue)) {
                                scheduleNo = textFieldValue.text
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.Green,
                            focusedTextColor = Color(0xFFF4511E),
                            cursorColor = Color.Blue,
                            focusedContainerColor = Color(0xFF9BB9EC),
                            focusedLabelColor = Color.Gray,

                            disabledContainerColor =  Color(0xFF648FD6)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .padding(start = 10.dp),
                        placeholder = {
                            Text(
                                text = "Schedule NO:",
                                color = Color.White,
                                fontSize = 17.sp
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(7.dp))

                    OutlinedTextField(
                        value = dutyEearnt,
                        singleLine = true,
                        shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            dutyEearnt = it
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedTextColor = Color.Green,
                            focusedTextColor = Color.White,
                            cursorColor = Color.Blue,
                            focusedLabelColor = Color.Gray,
                            disabledContainerColor = Color(0xFF4C94D6)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        placeholder = {
                            Text(
                                text = "No of duty earned:",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    )
                }
            }
          item {
              Spacer(modifier = Modifier.height(10.dp))
              permedDate = myCalendar()
              Spacer(modifier = Modifier.height(10.dp))
              Text(
                  "Optional Data (below)",
                  fontSize = 17.sp,
                  color = Color.Red,
                  modifier = Modifier.padding(start = 10.dp)
              )
          }
         item {
             HorizontalDivider(thickness = 5.dp, color = Color.White)
             Spacer(modifier = Modifier.height(10.dp))
             Text("Collection",modifier=Modifier.padding(start=20.dp),
                 color=Color.White, fontSize = 14.sp)

         }
            item{
                OutlinedTextField(
                    value = todayCollection,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        todayCollection = it
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.Green,
                        focusedTextColor = Color.White,
                        cursorColor = Color.Blue,
                        focusedLabelColor = Color.Gray,
                        disabledContainerColor = Color(0xFF4C94D6)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.44f)
                        .padding(start = 10.dp),
                    placeholder = {
                        Text(
                            text = "Collection:",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                )
            }
           item {
               Spacer(modifier = Modifier.height(10.dp))
               Text("Way Bill No",modifier=Modifier.padding(start=20.dp),color= Color.White, fontSize = 14.sp)
               OutlinedTextField(
                   value = wBillNo,
                   singleLine = true,
                   shape = RoundedCornerShape(80),
                   keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                   onValueChange = { newValue ->
                       val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                       if (isValidText(textFieldValue)) {
                           wBillNo = textFieldValue.text
                       }
                   },
                   colors = OutlinedTextFieldDefaults.colors(
                       unfocusedTextColor = Color.Green,
                       focusedTextColor = Color.White,
                       cursorColor = Color.Blue,
                       focusedLabelColor = Color.Gray,
                       disabledContainerColor = Color(0xFF4C94D6)
                   ),
                   modifier = Modifier
                       .fillMaxWidth(0.44f)
                       .padding(start = 10.dp),
                   placeholder = {
                       Text(
                           text = "Way Bill No",
                           color = Color.Black,
                           fontSize = 14.sp
                       )
                   }
               )
           }
           item {
               Spacer(modifier = Modifier.height(10.dp))
               Text("Name of the Crew",modifier=Modifier.padding(start=20.dp),color=Color.White, fontSize = 14.sp)
               OutlinedTextField(
                   value = crewName,
                   singleLine = true,
                   shape = RoundedCornerShape(20),
                   keyboardOptions = KeyboardOptions(
                       capitalization = KeyboardCapitalization.Characters
                   ),
                   colors = OutlinedTextFieldDefaults.colors(
                       unfocusedTextColor = Color.Green,
                       focusedTextColor = Color.White,
                       cursorColor = Color.Blue,
                       focusedLabelColor = Color.Gray,
                       disabledContainerColor = Color(0xFF4C94D6),
                       focusedBorderColor = Color(0xFFF57F17),
                       disabledBorderColor = Color.Black

                   ),
                   // keyboardOptions = KeyboardOptions.Default.copy(keyboardType= KeyboardType.Text).copy(capitalization = KeyboardCapitalization.Characters),
                   onValueChange = {
                       crewName = it
                   },

                   modifier = Modifier
                       .fillMaxWidth(0.44f)
                       .padding(start = 10.dp).border(BorderStroke(2.dp, Color.Red)),
                   placeholder = {
                       Text(
                           text = "Name of the crew",
                           color = Color.Black,
                           fontSize = 14.sp
                       )
                   }
               )


           }
            item {
                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.padding(start = 10.dp)) {
                    Surface(color = Color(0xFFA8BDE0)) {
                        Text("If surrender, put tick ☑  ", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Red)
                        Spacer(modifier = Modifier.width(10.dp))
                        Checkbox(
                            modifier = Modifier.padding(start=290.dp,end=5.dp).fillMaxWidth(),
                            checked = surrender,
                            onCheckedChange = { isChecked -> surrender = isChecked },
                            colors = CheckboxDefaults.colors(checkedColor = Color.Red) // Customize checkbox color
                        )
                    }
                }
            }

item {
    OutlinedButton(
        onClick = {

            if (scheduleNo.isNotBlank() && dutyEearnt.isNotBlank() && permedDate.isNotBlank()) {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        if (todayCollection.isBlank()) todayCollection = "--.--"
                        val employee = Employee(
                            dutyNo = scheduleNo,
                            performedOn = permedDate,
                            dutyEarned = dutyEearnt,
                            collection = todayCollection,
                            employeeName = crewName,
                            wayBillNo = wBillNo,
                            dutySurrendered = surrender
                        )
                        EmployeeDB.getInstance(context).getEmployeeDao().insert(employee)

                        // Show the Toast on the main/UI thread
                        GlobalScope.launch(Dispatchers.Main) {
                            flag=true
                            Toast.makeText(context, "Record inserted successfully", Toast.LENGTH_SHORT).show()
                        }

                        scheduleNo = " "
                        permedDate = " " // Make sure to set this to a valid date
                        dutyEearnt = " "
                        todayCollection = ""
                        crewName = ""
                        wBillNo = ""
                    }
                }
            } else {
                // Show the Toast on the main/UI thread
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, "Input Record first", Toast.LENGTH_SHORT).show()
                }
            }
        },
        modifier = Modifier.padding(start = 20.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF0F0825)),
        border = BorderStroke(width = 3.dp, color = Color(0xFF9889CA))
    ) {
        Text("INSERT", fontSize = 17.sp, color = Color.White)
    }
}
            item {
                Spacer(modifier = Modifier.height(10.dp))
                BannerAdView(false, AdSize.BANNER)

            }



        }
        if (flag){
            navController.popBackStack("MenuScreen",inclusive = false)
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditRoomData(rec:Int,database: Employee,navController: NavController) {
    Surface(color = Color(0xFF6776CA)) {
        database.dutySurrendered=false
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        var surrender by rememberSaveable {
            mutableStateOf(false)
        }
        var flag by rememberSaveable { mutableStateOf(false) }
        var scheduleNo by rememberSaveable { mutableStateOf(database.dutyNo) }
        var dutyEearnt by rememberSaveable { mutableStateOf(database.dutyEarned) }
        var permedDate by rememberSaveable { mutableStateOf(database.performedOn) }
        var todayCollection by rememberSaveable { mutableStateOf(database.collection) }
        var wBillNo by rememberSaveable { mutableStateOf(database.wayBillNo) }
        var crewName by rememberSaveable { mutableStateOf(database.employeeName) }
        Column(modifier=Modifier.height(800.dp)) {
            Text("Schedule:${database.dutyNo} Duty:${database.dutyEarned} Date:${database.performedOn} " +
                    "Collection:${database.collection} WayBill:${database.wayBillNo} " +
                    " Crew: ${database.employeeName}",color= Color.White)
            Text(text = "Schedule No     No of duty earned  ", color=Color.White, fontSize = 19.sp,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth())
            Row {
                scheduleNo?.let {
                    OutlinedTextField(
                        value = it,
                        singleLine = true,
                        shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { newValue ->
                            val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                            if (isValidText(textFieldValue)) {
                                scheduleNo = textFieldValue.text
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .padding(start = 10.dp),
                        placeholder = {
                            database.dutyNo?.let { d->
                                Text(
                                    text = d,
                                    color = Color.White,
                                    fontSize = 17.sp
                                )
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.width(7.dp))

                dutyEearnt?.let {
                    OutlinedTextField(
                        value = it,
                        singleLine = true,
                        shape = RoundedCornerShape(80),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = {earnt ->
                            dutyEearnt = earnt
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        placeholder = {
                            database.dutyEarned?.let { it1 ->
                                Text(
                                    text = it1,
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            permedDate = performedDate()//myCalendar()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Optional Data (below)",
                fontSize = 17.sp,
                color = Color.Red,
                modifier = Modifier.padding(start = 10.dp)
            )
            HorizontalDivider(thickness = 5.dp, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Text("Collection",modifier=Modifier.padding(start=20.dp),
                color=Color.White, fontSize = 14.sp)
            todayCollection?.let {
                OutlinedTextField(
                    value = it,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {collect ->
                        todayCollection = collect
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.44f)
                        .padding(start = 10.dp),
                    placeholder = {
                        database.collection?.let { it1 ->
                            Text(
                                text = it1,
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text("Way Bill No",modifier=Modifier.padding(start=20.dp),color= Color.White, fontSize = 14.sp)
            wBillNo?.let {
                OutlinedTextField(
                    value = it,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = { newValue ->
                        val textFieldValue = TextFieldValue(newValue, TextRange(newValue.length))
                        if (isValidText(textFieldValue)) {
                            wBillNo = textFieldValue.text
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.44f)
                        .padding(start = 10.dp),
                    placeholder = {
                        database.wayBillNo?.let { it1 ->
                            Text(
                                text = it1,
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text("Name of the Crew",modifier=Modifier.padding(start=20.dp),color=Color.White, fontSize = 14.sp)
            crewName?.let {
                OutlinedTextField(
                    value = it,
                    singleLine = true,
                    shape = RoundedCornerShape(80),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                    onValueChange = { cname ->
                        crewName = cname
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.44f)
                        .padding(start = 10.dp),
                    placeholder = {
                        database.employeeName?.let { it1 ->
                            Text(
                                text = it1,
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp)) //✔✓☑ Check mark - Tick symbol 💯☐☒❎✗✘
            Row(modifier = Modifier.padding(start=10.dp)){
                Surface(color= Color(0xFFA8BDE0)) {
                    Text("If duty Surrendered,please put tick ☑  ", fontWeight = FontWeight.SemiBold ,fontSize = 16.sp,color=Color.Red)
                    // Spacer(modifier = Modifier.width(10.dp))
                    surrender= checkbox()
                }
            }
            OutlinedButton(onClick = {
                flag=true
                coroutineScope.launch {
                      //  if (todayCollection.isBlank()) todayCollection = "--.--"
                         val database1 = Employee(
                            dutyNo = scheduleNo,
                            performedOn = permedDate,
                            dutyEarned = dutyEearnt,
                            collection = todayCollection,
                            employeeName  =crewName,
                            wayBillNo = wBillNo,
                             dutySurrendered=surrender,
                             id=rec

                        )
                       // EmployeeDB.getInstance(context).getEmployeeDao().updateEmployee(database1)
                        EmployeeDB.getInstance(context).getEmployeeDao().insert(database1)
                        Toast.makeText(context, "Record inserted successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
            },modifier=Modifier.padding(start=20.dp),colors=ButtonDefaults.buttonColors(Color(
                0xFF0F0825
            )
            ), border = BorderStroke(width = 3.dp, color = Color(0xFF9889CA))
            ) {
                Text("INSERT", fontSize = 17.sp,color=Color.White)
            }
            if(flag){
                scheduleNo=""
                permedDate= ""
                todayCollection=""
                wBillNo=""
                dutyEearnt= ""
                todayCollection=""
                crewName=""
                surrender=false
                navController.navigate("EditDutyDiaryScreen")
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun performedDate():String{
    val currentDate = LocalDate.now()

    // Define the desired date format
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    // Format the current date as a string
    val formattedDate = currentDate.format(formatter)
    val dDate= myCalendar()
    return dDate.ifBlank {
        formattedDate
    }


}
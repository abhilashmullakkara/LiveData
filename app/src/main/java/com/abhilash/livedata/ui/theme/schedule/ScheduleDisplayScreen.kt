package com.abhilash.livedata.ui.theme.schedule

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.test.researchAndStore
import com.abhilash.livedata.ui.theme.database.DepoData
import com.abhilash.livedata.ui.theme.database.OriginalData
import com.abhilash.livedata.ui.theme.database.depoList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@SuppressLint("SuspiciousIndentation")
@Composable
fun  ScheduleDisplayScreen(navController: NavController){
    var selectedDepo by remember { mutableStateOf(DepoData(depoId = 0, depoName = "", phone = "", email = "")) }
    var manuallyEnteredDepoNo by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var selectedSchedule by remember { mutableStateOf(emptyList<Pair<String, String>>()) }

    var manuallyEnteredScheduleNo by remember { mutableStateOf("") }
    var schExpanded by remember { mutableStateOf(false) }
    var newSchdi by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }

    val context = LocalContext.current
    val etmNumbers = mutableSetOf<String>()
    val myBusType: MutableSet<String> = mutableSetOf()
    var ti by rememberSaveable { mutableIntStateOf(0) }
    var flag by rememberSaveable { mutableIntStateOf(0) }
    val scroll= rememberScrollState()
    var result by rememberSaveable { mutableStateOf("") }
    var kilomts by rememberSaveable { mutableFloatStateOf(0.0f) }
    Surface(color = Color(0xFF175B52)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(10.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        IconButton(
            onClick = {
                navController.popBackStack("MenuScreen", inclusive = false)
            },
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Arrow",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {

            Surface(color= Color.LightGray,shape= RoundedCornerShape(9.dp)) {



                Column(modifier = Modifier.padding(16.dp)) {
                    // Dropdown menu for selecting depot
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        depoList.forEach { depo ->
                           // newSchdi = researchAndStore(manuallyEnteredDepoNo)
                            DropdownMenuItem(onClick = {
                                expanded = false

                                selectedDepo = depo
                                manuallyEnteredDepoNo = depo.depoId.toString() // Update manually entered value
                            }) {
                                androidx.compose.material3.Text(" [${depo.depoId}] :", fontSize = 16.sp, color=Color.Black,
                                    fontWeight = FontWeight.SemiBold)
                                androidx.compose.material3.Text(depo.depoName,color=Color.Black, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            }
                            Divider()
                        }
                    }

                    // Manually entered depot number field
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color(0xFFEF0808)
                        ),
                        value = manuallyEnteredDepoNo,
                        onValueChange = { newValue ->
                            manuallyEnteredDepoNo = newValue
                            // You might want to validate the manually entered value here
                        },
                        label = { androidx.compose.material3.Text("Depo NO",color=Color(0xFF0277BD)) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        trailingIcon = {
                            // Icon representing a dropdown arrow
                            IconButton(onClick = {  expanded = !expanded }) {
                                androidx.compose.material3.Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown",
                                    tint = Color(0xFF2E7D32) // Change the color here
                                )
//                                androidx.compose.material3.Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(color= Color.LightGray,shape= RoundedCornerShape(9.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {

                    DropdownMenu(
                        expanded = schExpanded,
                        onDismissRequest = { schExpanded = false }
                    ) {
                        // scheduleList.forEach { (scheduleNo: String, originalData) ->
                        selectedSchedule = emptyList()
                        newSchdi = researchAndStore(manuallyEnteredDepoNo)
                        newSchdi.forEach { (scheduleNo: String, originalData) ->
                            selectedSchedule = emptyList()
                            DropdownMenuItem(onClick = {
                                schExpanded = false
                                manuallyEnteredScheduleNo = scheduleNo
                                selectedSchedule = selectedSchedule + (scheduleNo to originalData)
                            }) {
                                androidx.compose.material3.Text(
                                    "$scheduleNo :$originalData",
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Divider()
                        }
                    }
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color(0xFFE10404)
                        ),
                        value = manuallyEnteredScheduleNo,
                        onValueChange = { newValue ->

                            manuallyEnteredScheduleNo = newValue
                            // You might want to validate the manually entered value here
                        },
                        label = { androidx.compose.material3.Text("Schedule NO") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        trailingIcon = {
                            // Icon representing a dropdown arrow
                            IconButton(onClick = { schExpanded = !schExpanded }) {
                                androidx.compose.material3.Icon(
                                    Icons.Filled.ArrowDropDown,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            var resultList: SnapshotStateList<OriginalData>
            val errorMessage = remember { mutableStateOf("Data not found") }
            Button(
              colors= androidx.compose.material.ButtonDefaults.buttonColors(
                  backgroundColor = Color(0xFF283593),
                  contentColor = Color.White
              ),
                onClick = {
                    try {
                        if (manuallyEnteredDepoNo.isNotBlank() && manuallyEnteredScheduleNo.isNotBlank()) {
                            kilomts = 0.0f
                            ti=0
                            result=""

                            etmNumbers.clear()
                            myBusType.clear()
                            val myRef = FirebaseDatabase.getInstance().reference.child("")
                            fetchMyDatabase(myRef, manuallyEnteredDepoNo, manuallyEnteredScheduleNo, { results ->
                                resultList = results as SnapshotStateList<OriginalData>
                                errorMessage.value = "" // Clear any previous error message
                                kilomts = 0.0f // Reset kilomts variable to zero
                                val data = StringBuffer()
                                val etmKmr = StringBuffer()
                                if (resultList.isNotEmpty()) {
                                    resultList.forEach {
                                        data.append("\n")
                                        data.append("\n" + (ti + 1).toString())
                                        data.append("   " + it.departureTime)
                                        data.append("   " + it.startPlace)
                                        data.append("   " + it.via)
                                        data.append("   " + it.destinationPlace)
                                        data.append("   " + it.arrivalTime)
                                        data.append("   " + it.kilometer)
                                        kilomts += it.kilometer.toFloat()
                                        etmKmr.append(it.etmNo)
                                        myBusType.add(it.bustype.replace("\\s+".toRegex(), ""))
                                        ti += 1
                                        flag = 1
                                        etmNumbers.add(it.etmNo.replace("\\s+".toRegex(), ""))
                                    }
                                    result = data.toString()
                                    manuallyEnteredDepoNo=""
                                    manuallyEnteredScheduleNo=""
                                    selectedSchedule= emptyList()
                                    newSchdi= emptyList()

                                }
                            }, { error ->
                                errorMessage.value = "Error: ${error.message}"
                            })
                        } else {
                            kilomts = 0.0f
                            ti=0
                            etmNumbers.clear()
                            myBusType.clear()
                            Toast.makeText(context, "Input data first", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        // Handle the exception here
                        e.printStackTrace()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Display")
            }
            Text(text = "Check Internet Connection", color = Color.LightGray)
        }
        Spacer(modifier = Modifier.height(5.dp))
        if(result.isNotEmpty()){
            Column(
                //horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier=Modifier.fillMaxSize()
            ) {
                Surface (color=Color(0xFFFF6F00),modifier= Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp))
                {
                    Text("Bus Type :  $myBusType ", fontSize = 19.sp, fontStyle = FontStyle.Italic,color=Color.White)

                }

            }
            Text(" \nNo   Time  From    Via    To     Arr.Time   Kmrs\n",color= Color.White)
            Divider(color = Color.Red, thickness = 4.dp)
            Text(result, color = Color.White)
            Text("\nTotal Kilometers: $kilomts", color = Color.White)
            Divider(color = Color.Red)
            Text("\nETM Root Numbers: $etmNumbers", color = Color.White)
            Divider(color = Color.Red, thickness = 2.dp)
            Divider(color = Color.Green, thickness = 3.dp)
        }
        else
        {
            androidx.compose.material3.Text(
                "No matching records found.",
                fontSize = 14.sp,
                color = Color.White
            )
        }


    }
}
    }

fun fetchMyDatabase(
    databaseRef: DatabaseReference,
    depo: String,
    schedule: String,
    onSuccess: (List< OriginalData>) -> Unit,
    onError: (Exception) -> Unit
) {
     val resultList =  mutableStateListOf<OriginalData>()
    databaseRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            snapshot.children.forEach { depoSnapshot ->
                val depoNumber = depoSnapshot.key as String
                // Check if the depot number matches the search path
                if (depoNumber == depo) {
                    depoSnapshot.children.forEach { busTypeSnapshot ->
                        busTypeSnapshot.children.forEach { scheduleNoSnapshot ->
                            val scheduleNo = scheduleNoSnapshot.key as String
                            scheduleNoSnapshot.children.forEach { tripsSnapshot ->
                                if (scheduleNo == schedule) {

                                    val trips = tripsSnapshot.getValue(OriginalData::class.java)
                                    if (trips != null) {
                                        resultList.add( trips)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            onSuccess(resultList)
        }
        override fun onCancelled(error: DatabaseError) {
            onError(error.toException())
        }
      }
    )
}




package com.abhilash.livedata.ui.theme.manager

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun MenuScreen(navController: NavController) {
    //navController.popBackStack()
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF779FE4)) {
       // val scroll = rememberScrollState()
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .height(120.dp), // Adjust the height as needed
                backgroundColor = Color(0xFF6097F3),
                elevation = 5.dp
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(color = Color(0xFF161F55), modifier = Modifier.fillMaxWidth()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("വേല", fontSize = 20.sp, color = Color.White)
                            Text("KSRTC Duty Diary", fontSize = 12.sp, color = Color.White)
                            Text(" ", fontSize = 10.sp)
                        }

                    }

                }

            }
            LazyColumn {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(
                            onClick = { navController.navigate("AddScheduleScreen") },
                        ) {
                            Text("Add/Update Schedule", color = Color.White, fontSize = 20.sp)
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {

                        TextButton(onClick = {
                            navController.navigate(" ScheduleDisplayScreen")
                        }) {
                            Text("View Schedule ", color = Color.White, fontSize = 20.sp)
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(onClick = {
                            navController.navigate("DeleteTripScreen")
                        }) {
                            Text("Delete Trip", color = Color.White, fontSize = 20.sp)
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(onClick = {
                            navController.navigate("DeleteScheduleScreen")
                        }) {
                            Text("Delete Schedule", color = Color.White, fontSize = 20.sp)
                        }
                    }


                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {

                        TextButton(onClick = {
                            navController.navigate("FindMyBusScreen")
                        }) {
                            Text(
                                "Find My Bus ",
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White, fontSize = 20.sp
                            )

                        }

                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(onClick = {
                            navController.navigate("DepoListScreen")
                        }) {
                            Text(
                                "Find my depo number",
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White, fontSize = 20.sp
                            )
                        }
                    }

                    //
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(onClick = {
                            navController.navigate("ListAllScheduleScreen")
                        }) {
                            Text(
                                "List all Schedule",
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White, fontSize = 20.sp
                            )
                        }
                    }

                    //Room
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            "   Duty Diary", fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFFFD600),
                            modifier = Modifier
                                .padding(start = 100.dp, end = 100.dp),
                            //  .fillMaxWidth(),
                            fontSize = 25.sp
                        )

                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(onClick = {
                            navController.navigate("AddDutyDiaryScreen")
                        }) {
                            Text(
                                "Add Duty ",
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White, fontSize = 20.sp
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(onClick = {
                            navController.navigate("ViewDiaryScreen")
                        }) {
                            Text("View Diary ", color = Color.White, fontSize = 20.sp)
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(onClick = {
                            navController.navigate("DeleteRecordScreen")
                        }) {
                            Text("Delete Record", color = Color.White, fontSize = 20.sp)
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(onClick = {
                            navController.navigate("DeleteAllRecordScreen")
                        }) {
                            Text("DELETE ALL RECORD", color = Color.White, fontSize = 20.sp)
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF6D92D1),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        TextButton(onClick = {
                            navController.navigate("CurrencyCountScreen")
                        }) {
                            Text("CURRENCY COUNT", color = Color.White, fontSize = 20.sp)
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF448AFF),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {

                        TextButton(
                            onClick = {
                                navController.navigate("EditDutyDiaryScreen")
                            },
                            //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ) {
                            Text("Edit Diary", color = Color.White, fontSize = 20.sp)
                        }
                    }

                }


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MenuScreenPreview(){
    //MenuScreen()
}

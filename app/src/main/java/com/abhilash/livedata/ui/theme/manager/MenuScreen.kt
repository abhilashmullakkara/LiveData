package com.abhilash.livedata.ui.theme.manager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.R
import com.abhilash.livedata.ui.theme.admob.BannerAdView
import com.google.android.gms.ads.AdSize

@Composable
fun MenuScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF779FE4)) {
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
                    Surface(color = Color(0xFF075E54), modifier = Modifier.fillMaxWidth()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("വേല", fontSize = 20.sp, color = Color.White)
                            Text("KSRTC Duty Diary", fontSize = 12.sp, color = Color.White)
                            Text(" ", fontSize = 10.sp)
                            Spacer(modifier = Modifier.height(10.dp))
                            BannerAdView(true, AdSize.FULL_BANNER)
                        }

                    }

                }

            }
            LazyColumn {
                item {
                    Column(modifier = Modifier.padding(start = 70.dp)) {
                        TextShadow(text = "SCHEDULE")
                    }
                    Column {

                        Divider(color = Color.White, thickness = 3.dp)
                    }
                    Column(modifier = Modifier.padding(start = 70.dp)) {
                        Text(
                            "Add/Update", color = Color.White,
                            fontSize = 16.sp, modifier = Modifier.padding(start = 10.dp)
                        )
                        IconButton(onClick = {
                           // navController.navigate("AddScheduleScreen")
                            navController.navigate("AddScheduleScreenWithPassword")

                        }, modifier = Modifier.padding(start = 20.dp))
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.cloud_upload_fill0_wght400_grad0_opsz24),
                                "", modifier = Modifier.size(30.dp)
                            )
                        }
                        Text("View Schedule", color = Color.White, fontSize = 16.sp)
                        IconButton(onClick = {
                            navController.navigate("ScheduleDisplayScreen")

                        }, modifier = Modifier.padding(start = 20.dp))
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.folder_open_fill0_wght400_grad0_opsz24),
                                "", modifier = Modifier.size(30.dp)
                            )
                        }
                        Text("Delete Trip", color = Color.White, fontSize = 20.sp)

                        IconButton(onClick = {
                            navController.navigate("DeleteTripScreen")

                        }, modifier = Modifier.padding(start = 20.dp))
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.content_cut_fill0_wght400_grad0_opsz24),
                                "", modifier = Modifier.size(30.dp)
                            )
                        }
                        Text("Delete Schedule", color = Color.White, fontSize = 20.sp)
                        IconButton(onClick = {
                            navController.navigate("DeleteScheduleScreen")

                        }, modifier = Modifier.padding(start = 20.dp))
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.content_cut_fill0_wght400_grad0_opsz24),
                                "", modifier = Modifier.size(30.dp)
                            )
                        }
                        Text("List All Schedule", color = Color.White, fontSize = 20.sp)
                        IconButton(onClick = {
                            navController.navigate("ListAllScheduleScreen")
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.cloud_download_fill0_wght400_grad0_opsz24),
                                "", modifier = Modifier.size(30.dp)
                            )
                        }

                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(260.dp), // Adjust the height as needed
                        backgroundColor = Color(0xFF648FD6),
                        shape = RoundedCornerShape(5.dp),
                        elevation = 5.dp
                    ) {
                        Column {
                            Column(modifier = Modifier.padding(start = 70.dp)) {
                                TextShadow(text = "UTILITY ")
                            }
                            Column {

                                Divider(color = Color.White, thickness = 3.dp)
                            }
                            Column(modifier = Modifier.padding(start = 70.dp)) {
                                Text("Find my bus", color = Color.White, fontSize = 16.sp)
                                IconButton(onClick = {
                                    navController.navigate("FindMyBusScreen")

                                }, modifier = Modifier.padding(start = 20.dp))
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.manage_search_fill0_wght400_grad0_opsz24),
                                        "", modifier = Modifier.size(30.dp)
                                    )
                                }
                                Text("Depo List", color = Color.White, fontSize = 16.sp)
                                IconButton(onClick = {
                                    navController.navigate("DepoListScreen")

                                }, modifier = Modifier.padding(start = 20.dp))
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.saved_search_fill0_wght400_grad0_opsz24),
                                        "", modifier = Modifier.size(30.dp)
                                    )
                                }
                                Text("CURRENCY COUNT", color = Color.White, fontSize = 16.sp)
                                IconButton(onClick = {
                                    navController.navigate("CurrencyCountScreen")
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.note_add_fill0_wght400_grad0_opsz24),
                                        "", modifier = Modifier.size(30.dp)
                                    )
                                }
                            }


                        }
                    }

                    //Room


 Card(
     modifier = Modifier
         .fillMaxWidth()
         .padding(10.dp)
         .height(300.dp), // Adjust the height as needed
     backgroundColor = Color(0xFF448AFF),
     shape = RoundedCornerShape(5.dp),
     elevation = 5.dp
 ) {
     Column {
         Column(modifier = Modifier.padding(start = 70.dp)) {
             TextShadow(text = "DUTY DIARY")
         }
Divider(color=Color.White, thickness = 3.dp)
         Spacer(modifier = Modifier.height(10.dp))
         Row {

             Spacer(modifier = Modifier.width(15.dp))
             Column {
                 Text("Add Duty", fontSize = 14.sp, color = Color.White)
                 IconButton(onClick = {
                     navController.navigate("AddDutyDiaryScreen")
                 }) {
                     Icon(
                         painter = painterResource(id = R.drawable.note_add_fill0_wght400_grad0_opsz24),
                         "", modifier = Modifier.size(30.dp)
                     )
                 }
             }
             Spacer(modifier = Modifier.width(15.dp))
             IconButton(onClick = {
                 navController.navigate("ViewDiaryScreen")
             }) {
                 Column {
                     Text("View ", color = Color.White, fontSize = 14.sp)
                     Icon(
                         painter = painterResource(id = R.drawable.home_work_fill0_wght400_grad0_opsz24),
                         "", modifier = Modifier.size(30.dp)
                     )
                 }
             }
             Spacer(modifier = Modifier.width(15.dp))

             IconButton(onClick = {
                 navController.navigate("DeleteRecordScreen")
             }) {
                 Column {
                     Text("Delete", color = Color.White, fontSize = 14.sp)
                     Icon(
                         painter = painterResource(id = R.drawable.content_cut_fill0_wght400_grad0_opsz24),
                         "", modifier = Modifier.size(30.dp)
                     )
                 }
             }
             Spacer(modifier = Modifier.width(15.dp))
             IconButton(onClick = {
                 navController.navigate("DeleteAllRecordScreen")
             }) {
                 Column {
                         Text("DELETE", color = Color.White, fontSize = 15.sp)
                         Icon(
                             painter = painterResource(id = R.drawable.content_cut_fill0_wght400_grad0_opsz24),
                             "", modifier = Modifier.size(28.dp),tint= Color.Red
                         )
                         Text("ALL", color = Color.White, fontSize = 15.sp)
                     }
             }
             Spacer(modifier = Modifier.width(15.dp))
             IconButton(
                 onClick = {
                     navController.navigate("EditDutyDiaryScreen")
                 },
                 //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
             ) {
                 Column {
                     Text("Edit Diary", color = Color.White, fontSize = 15.sp)
                     Icon(
                         painter = painterResource(id = R.drawable.extension_fill0_wght400_grad0_opsz24),
                         "", modifier = Modifier.size(30.dp)
                     )
                 }

             }
             //TODO("WhatsAPP Share")


         }
         Spacer(modifier = Modifier.height(20.dp))
         BannerAdView(true, AdSize.LARGE_BANNER)

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
@Composable
fun TextShadow(text:String) {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = text,
        color= Color.White,
        style = TextStyle(
            fontSize = 27.sp,
            shadow = Shadow(
                color = Color.Blue, offset = offset, blurRadius = 3f
            )
        )
    )
}
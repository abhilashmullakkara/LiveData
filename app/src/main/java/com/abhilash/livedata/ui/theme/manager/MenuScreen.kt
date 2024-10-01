package com.abhilash.livedata.ui.theme.manager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.R
import com.abhilash.livedata.ui.cloud.mypasswordDownloader
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
                    .height(148.dp), // Adjust the height as needed
                colors = cardColors(
                    containerColor = Color(0xFF075E54),
                    disabledContainerColor = Color(0xFF075E54),
                    disabledContentColor = Color.Transparent
                ),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(color = Color(0xFF075E54), modifier = Modifier.fillMaxWidth()) {

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("വേല", fontSize = 20.sp, color = Color.White)
                            Text("KSRTC Duty Diary", fontSize = 12.sp, color = Color.White)
                            Text(" ", fontSize = 10.sp)
                            val ver= mypasswordDownloader("0")
                            Text(ver, fontSize = 15.sp,color=Color.Yellow)
                            Spacer(modifier = Modifier.height(10.dp))
                            BannerAdView(false, AdSize.FULL_BANNER)
                        }

                    }

                }

            }
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(190.dp), // Adjust the height as needed
                        colors = cardColors(
                            containerColor = Color(0xFF648FD6),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp,
                            focusedElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Column {
                            Column(modifier = Modifier.padding(start = 70.dp)) {
                                TextShadow(text = "DUTY DIARY")
                            }
                            HorizontalDivider(thickness = 3.dp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))
                            Column {
                                Row {
                                    Column(
                                        verticalArrangement = Arrangement.Center, // Aligns items vertically centered
                                        horizontalAlignment = Alignment.CenterHorizontally // Aligns items horizontally centered
                                    ) {
                                        ClickableText(
                                            text = AnnotatedString("Add Duty"),
                                            modifier = Modifier
                                                .padding(start = 15.dp),
                                            onClick = {
                                                navController.navigate("AddDutyDiaryScreen")
                                            },
                                            style = TextStyle(fontSize = 17.sp, color = Color.White)
                                        )

                                        IconButton(
                                            onClick = {
                                                navController.navigate("AddDutyDiaryScreen")
                                            },
                                            modifier = Modifier
                                                .size(width = 55.dp, height = 40.dp)
                                                .padding(start = 15.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.note_add_fill0_wght400_grad0_opsz24),
                                                contentDescription = "Add Duty Icon", // Provide content description for accessibility
                                                modifier = Modifier.size(28.dp)
                                            )
                                        }
                                    }

                                    Column(
                                        verticalArrangement = Arrangement.Center, // Aligns items vertically centered
                                        horizontalAlignment = Alignment.CenterHorizontally // Aligns items horizontally centered
                                    ) {
                                        ClickableText(
                                            text = AnnotatedString("View"),
                                            modifier = Modifier
                                                .padding(start = 15.dp),
                                            onClick = {
                                                navController.navigate("ViewDiaryScreen")
                                            },
                                            style = TextStyle(fontSize = 17.sp, color = Color.White)
                                        )
                                        IconButton(
                                            onClick = {
                                                navController.navigate("ViewDiaryScreen")
                                            },
                                            modifier = Modifier
                                                .size(width = 55.dp, height = 40.dp)
                                                .padding(start = 15.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.home_work_fill0_wght400_grad0_opsz24),
                                                "view", modifier = Modifier.size(28.dp)
                                            )
                                        }
                                    }

                                    Column(
                                        verticalArrangement = Arrangement.Center, // Aligns items vertically centered
                                        horizontalAlignment = Alignment.CenterHorizontally // Aligns items horizontally centered
                                    ) {
                                        ClickableText(
                                            text = AnnotatedString("Delete"),
                                            modifier = Modifier
                                                .padding(start = 15.dp),
                                            onClick = {
                                                navController.navigate("DeleteRecordScreen")
                                            },
                                            style = TextStyle(fontSize = 17.sp, color = Color.White)
                                        )

                                        IconButton(
                                            onClick = {
                                                navController.navigate("DeleteRecordScreen")
                                            },
                                            modifier = Modifier
                                                .size(width = 55.dp, height = 40.dp)
                                                .padding(start = 15.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.content_cut_fill0_wght400_grad0_opsz24),
                                                "delete", modifier = Modifier.size(28.dp)
                                            )
                                        }
                                    }


                                    //  Spacer(modifier = Modifier.width(15.dp))


                                    Column(
                                        verticalArrangement = Arrangement.Center, // Aligns items vertically centered
                                        horizontalAlignment = Alignment.CenterHorizontally // Aligns items horizontally centered
                                    ) {
                                        ClickableText(
                                            text = AnnotatedString("DELETE"),
                                            modifier = Modifier
                                                .padding(start = 16.dp),
                                            onClick = {
                                                navController.navigate("DeleteAllRecordScreen")
                                            },
                                            style = TextStyle(fontSize = 14.sp, color = Color.White)
                                        )

                                        IconButton(
                                            onClick = {
                                                navController.navigate("DeleteAllRecordScreen")
                                            },
                                            modifier = Modifier
                                                .size(width = 55.dp, height = 40.dp)
                                                .padding(start = 15.dp)
                                        ) {

                                            Icon(
                                                painter = painterResource(id = R.drawable.content_cut_fill0_wght400_grad0_opsz24),
                                                "", modifier = Modifier.size(28.dp), tint = Color.Red
                                            )
                                            // Spacer(modifier = Modifier.height(25.dp))

                                        }
                                        ClickableText(
                                            text = AnnotatedString("ALL"),
                                            modifier = Modifier
                                                .padding(start = 13.dp),
                                            onClick = {
                                                navController.navigate("DeleteAllRecordScreen")
                                            },
                                            style = TextStyle(fontSize = 16.sp, color = Color.White)
                                        )
                                    }

                                    Column(
                                        verticalArrangement = Arrangement.Center, // Aligns items vertically centered
                                        horizontalAlignment = Alignment.CenterHorizontally // Aligns items horizontally centered
                                    ) {
                                        ClickableText(
                                            text = AnnotatedString("Edit Diary"),
                                            modifier = Modifier
                                                .padding(start = 15.dp),
                                            onClick = {
                                                navController.navigate("EditDutyDiaryScreen")
                                            },
                                            style = TextStyle(fontSize = 17.sp, color = Color.White)
                                        )
                                        IconButton(
                                            onClick = {
                                                navController.navigate("EditDutyDiaryScreen")
                                            },
                                            modifier = Modifier
                                                .size(width = 55.dp, height = 40.dp)
                                                .padding(start = 15.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.extension_fill0_wght400_grad0_opsz24),
                                                "edit duty diary", modifier = Modifier.size(28.dp)
                                            )
                                        }
                                    }


                                }
                                Row {


                                Column(
                                    verticalArrangement = Arrangement.Center, // Aligns items vertically centered
                                    horizontalAlignment = Alignment.CenterHorizontally // Aligns items horizontally centered
                                ) {
                                    ClickableText(
                                        text = AnnotatedString("View Cloud"),
                                        modifier = Modifier
                                            .padding(start = 16.dp),
                                        onClick = {
                                            navController.navigate("ReadDataFromCloud")
                                            // navController.navigate("DeleteAllRecordScreen")
                                        },
                                        style = TextStyle(fontSize = 14.sp, color = Color.White)
                                    )

                                    IconButton(
                                        onClick = {
                                            navController.navigate("ReadDataFromCloud")
                                            // navController.navigate("DeleteAllRecordScreen")
                                        },
                                        modifier = Modifier
                                            .size(width = 55.dp, height = 40.dp)
                                            .padding(start = 15.dp)
                                    ) {

                                        Icon(
                                            painter = painterResource(id = R.drawable.cloud_upload_fill0_wght400_grad0_opsz24),
                                            "", modifier = Modifier.size(28.dp), tint = Color.Yellow
                                        )
                                        // Spacer(modifier = Modifier.height(25.dp))

                                    }

                                }
                                    Column(
                                        verticalArrangement = Arrangement.Center, // Aligns items vertically centered
                                        horizontalAlignment = Alignment.CenterHorizontally // Aligns items horizontally centered
                                    ) {
                                        ClickableText(
                                            text = AnnotatedString("Delete Cloud"),
                                            modifier = Modifier
                                                .padding(start = 16.dp),
                                            onClick = {
                                                 navController.navigate("DeleteCloudScreen")
                                            },
                                            style = TextStyle(fontSize = 14.sp, color = Color.White)
                                        )

                                        IconButton(
                                            onClick = {
                                                navController.navigate("DeleteCloudScreen")
                                            },
                                            modifier = Modifier
                                                .size(width = 55.dp, height = 40.dp)
                                                .padding(start = 15.dp)
                                        ) {

                                            Icon(
                                                painter = painterResource(id = R.drawable.cloud_upload_fill0_wght400_grad0_opsz24),
                                                "", modifier = Modifier.size(28.dp), tint = Color.Red
                                            )
                                            // Spacer(modifier = Modifier.height(25.dp))

                                        }

                                    }
                                    Column(
                                        verticalArrangement = Arrangement.Center, // Aligns items vertically centered
                                        horizontalAlignment = Alignment.CenterHorizontally // Aligns items horizontally centered
                                    ) {
                                        ClickableText(
                                            text = AnnotatedString("Append Cloud"),
                                            modifier = Modifier
                                                .padding(start = 16.dp),
                                            onClick = {
                                                 navController.navigate("AppendCloudScreen")
                                            },
                                            style = TextStyle(fontSize = 14.sp, color = Color.White)
                                        )

                                        IconButton(
                                            onClick = {
                                                navController.navigate("AppendCloudScreen")
                                            },
                                            modifier = Modifier
                                                .size(width = 55.dp, height = 40.dp)
                                                .padding(start = 15.dp)
                                        ) {

                                            Icon(
                                                painter = painterResource(id = R.drawable.cloud_upload_fill0_wght400_grad0_opsz24),
                                                "", modifier = Modifier.size(28.dp), tint = Color.Green
                                            )
                                            // Spacer(modifier = Modifier.height(25.dp))

                                        }

                                    }

                                }//Row end


                            }


                        }

                    }
                }

                item {
                    Column(modifier = Modifier.padding(start = 70.dp)) {
                        TextShadow(text = "SCHEDULE")
                    }
                    Column {

                        HorizontalDivider(thickness = 3.dp, color = Color.White)
                    }
                    Column(modifier = Modifier.padding(start = 70.dp, top = 10.dp)) {
                        val text = buildAnnotatedString {
                            append("Add/Update")
                        }
                        @Suppress("DEPRECATION") val textStyle = TextStyle(
                            color = Color.White, // Set the text color to blue
                            fontSize = 16.sp, // Set the font size
                            textDecoration = TextDecoration.Underline // Underline the text
                        ).apply {
                            ClickableText(
                                modifier = Modifier.padding(start = 10.dp),
                                text = text, // The text to be displayed
                                style = this, // The style to be applied to the text
                                onClick = {
                                    // Navigate to the "AddScheduleScreen" screen when the text is clicked
                                    navController.navigate("AddScheduleScreen")
                                }
                            )
                        }
                        IconButton(onClick = {
                            navController.navigate("AddScheduleScreen")
                            //navController.navigate("AddScheduleScreenWithPassword")

                        }, modifier = Modifier.padding(start = 20.dp))
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.cloud_upload_fill0_wght400_grad0_opsz24),
                                "", modifier = Modifier.size(30.dp)
                            )
                        }
                        Column(modifier = Modifier
                            .clickable {
                                navController.navigate("ScheduleDisplayScreen")
                            }
                            .fillMaxWidth()) {
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
                        }

                        Column(modifier = Modifier
                            .clickable {
                                navController.navigate("DeleteTripScreenWithPassword")
                            }
                            .fillMaxWidth()) {
                            Text("Delete Trip", color = Color.White, fontSize = 20.sp)

                            IconButton(onClick = {
                                // navController.navigate("DeleteTripScreen")
                                navController.navigate("DeleteTripScreenWithPassword")

                            }, modifier = Modifier.padding(start = 20.dp))
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.content_cut_fill0_wght400_grad0_opsz24),
                                    "", modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                        Column(modifier = Modifier
                            .clickable {
                                navController.navigate("DeleteScheduleScreenWithPassword")

                            }
                            .fillMaxWidth()) {
                            Text("Delete Schedule", color = Color.White, fontSize = 20.sp)
                            IconButton(onClick = {
                                //navController.navigate("DeleteScheduleScreen")
                                navController.navigate("DeleteScheduleScreenWithPassword")

                            }, modifier = Modifier.padding(start = 20.dp))
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.content_cut_fill0_wght400_grad0_opsz24),
                                    "", modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                        Column(modifier = Modifier
                            .clickable {
                                navController.navigate("ListAllScheduleScreen")
                            }
                            .fillMaxWidth()) {
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
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(260.dp),
                        // Adjust the height as needed
                        colors = cardColors(
                            containerColor = Color(0xFF648FD6),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp,
                            focusedElevation = 8.dp
                        ),
                        // backgroundColor = Color(0xFF648FD6),
                        shape = RoundedCornerShape(5.dp)
                        //shape = RoundedCornerShape(5.dp),
                    ) {
                        Column {
                            Column(modifier = Modifier.padding(start = 70.dp)) {
                                TextShadow(text = "UTILITY ")
                            }
                            Column {

                                HorizontalDivider(thickness = 3.dp, color = Color.White)
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
                }

                    //Room





                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .height(150.dp), // Adjust the height as needed
                            colors = cardColors(
                                containerColor = Color(0xFF648FD6),
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp,
                                focusedElevation = 8.dp
                            ),
                            shape = RoundedCornerShape(5.dp),
                        ){
                            Spacer(modifier = Modifier.height(10.dp))
                            Row {

                                Spacer(modifier = Modifier.width(8.dp))


                                //new modification
                                Spacer(modifier = Modifier.width(8.dp))

                                Button(
                                    onClick = {
                                        //navController.navigate("ReadDataFromCloud")
                                              },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF651FFF),
                                        contentColor = Color.White // text color
                                    ), elevation = ButtonDefaults.buttonElevation(
                                        defaultElevation = 20.dp,
                                        disabledElevation = 12.dp
                                    )

                                ) {
                                    Text("View from cloud", fontSize = 12.sp, color = Color.White)

                                }


                                Spacer(modifier = Modifier.width(10.dp))




                                Button(
                                    onClick = {
                                        navController.navigate("RegisterScreen")
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF00712D),
                                        contentColor = Color.White// text color
                                    ), elevation = ButtonDefaults.buttonElevation(
                                        defaultElevation = 20.dp,
                                        disabledElevation = 12.dp
                                    )

                                ) {
                                    Text("Register", fontSize = 15.sp, color = Color(0xFFD5ED9F))

                                }
                            }


                            Spacer(modifier = Modifier.height(10.dp))

                            Row {
                                Spacer(modifier = Modifier.width(17.dp))

                            Button(onClick = { navController.navigate("ContactMeScreen") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFF6F00),
                                    contentColor = Color.White // text color
                                ),elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 20.dp
                                )

                            ) {
                                Text("Contact Me", fontSize = 15.sp,color= Color.White)

                            }
                                Spacer(modifier = Modifier.width(15.dp))
                            Button(onClick = { navController.navigate("AboutScreen") },
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 30.dp
                                )
                            ) {
                                Text("ABOUT", color = Color.White, fontSize = 15.sp)
                            }
                            }

                        }
                    }



                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(100.dp), // Adjust the height as needed
                        colors = cardColors(
                            containerColor = Color(0xFF648FD6),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp,
                            focusedElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(5.dp),
                    ){
                        Spacer(modifier = Modifier.height(10.dp))
                        BannerAdView(false, AdSize.LARGE_BANNER)

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


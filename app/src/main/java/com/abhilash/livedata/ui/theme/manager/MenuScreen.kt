package com.abhilash.livedata.ui.theme.manager

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.R
import com.abhilash.livedata.ui.cloud.mypasswordDownloader
import com.abhilash.livedata.ui.theme.admob.BannerAdView
import com.google.android.gms.ads.AdSize


                //THIS SCREEN IS CORRECTED ON 27/10/2025
@Composable
fun MenuScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF779FE4)) {
        Column {
            // Header Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .height(148.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF075E54),
                    disabledContainerColor = Color(0xFF075E54),
                    disabledContentColor = Color.Transparent
                ),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("വേല", fontSize = 20.sp, color = Color.White)
                    Text("KSRTC Duty Diary", fontSize = 12.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(10.dp))
                    val ver = mypasswordDownloader("0")
                    Text(ver, fontSize = 15.sp, color = Color.Yellow)
                    Spacer(modifier = Modifier.height(10.dp))
                    BannerAdView(false, AdSize.FULL_BANNER)
                }
            }

            LazyColumn {
                // DUTY DIARY Card
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF648FD6),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp,
                            focusedElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            // Title
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextShadow(text = "DUTY DIARY")
                            }
                            HorizontalDivider(thickness = 3.dp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))

                            // First Row of Options
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                MenuOption(
                                    text = "Add Duty",
                                    iconRes = R.drawable.note_add_fill0_wght400_grad0_opsz24,
                                    onClick = { navController.navigate("AddDutyDiaryScreen") }
                                )
                                MenuOption(
                                    text = "View",
                                    iconRes = R.drawable.home_work_fill0_wght400_grad0_opsz24,
                                    onClick = { navController.navigate("ViewDiaryScreen") }
                                )
                                MenuOption(
                                    text = "Delete",
                                    iconRes = R.drawable.content_cut_fill0_wght400_grad0_opsz24,
                                    onClick = { navController.navigate("DeleteRecordScreen") }
                                )
                                MenuOption(
                                    text = "DELETE ALL",
                                    iconRes = R.drawable.content_cut_fill0_wght400_grad0_opsz24,
                                    onClick = { navController.navigate("DeleteAllRecordScreen") },
                                    iconTint = Color.Red,
                                    textSize = 12.sp
                                )
                                MenuOption(
                                    text = "Edit Diary",
                                    iconRes = R.drawable.extension_fill0_wght400_grad0_opsz24,
                                    onClick = { navController.navigate("EditDutyDiaryScreen") }
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Second Row of Options
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                MenuOption(
                                    text = "View Cloud",
                                    iconRes = R.drawable.cloud_upload_fill0_wght400_grad0_opsz24,
                                    onClick = { navController.navigate("ReadDataFromCloud") },
                                    iconTint = Color.Yellow,
                                    textSize = 14.sp
                                )
                                MenuOption(
                                    text = "Delete Cloud",
                                    iconRes = R.drawable.cloud_upload_fill0_wght400_grad0_opsz24,
                                    onClick = { navController.navigate("DeleteCloudScreen") },
                                    iconTint = Color.Red,
                                    textSize = 14.sp
                                )
                                MenuOption(
                                    text = "Append Cloud",
                                    iconRes = R.drawable.cloud_upload_fill0_wght400_grad0_opsz24,
                                    onClick = { navController.navigate("AppendCloudScreen") },
                                    iconTint = Color.Green,
                                    textSize = 14.sp
                                )
                                // Merge with custom image
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                ) {
                                    Text(
                                        text = "Merge",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        modifier = Modifier.clickable {
                                            navController.navigate("MergeFromCloudScreen")
                                        }
                                    )
                                    IconButton(
                                        onClick = { navController.navigate("MergeFromCloudScreen") }
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.dall_e_2024_10_01_16_01_17___a_m),
                                            contentDescription = "Merge",
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // SCHEDULE Section
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF648FD6),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp,
                            focusedElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextShadow(text = "SCHEDULE")
                            }
                            HorizontalDivider(thickness = 3.dp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))

                            ScheduleMenuItem(
                                text = "Add/Update",
                                iconRes = R.drawable.cloud_upload_fill0_wght400_grad0_opsz24,
                                onClick = { navController.navigate("AddScheduleScreen") }
                            )
                            ScheduleMenuItem(
                                text = "View Schedule",
                                iconRes = R.drawable.folder_open_fill0_wght400_grad0_opsz24,
                                onClick = { navController.navigate("ScheduleDisplayScreen") }
                            )
                            ScheduleMenuItem(
                                text = "Delete Trip",
                                iconRes = R.drawable.content_cut_fill0_wght400_grad0_opsz24,
                                onClick = { navController.navigate("DeleteTripScreenWithPassword") }
                            )
                            ScheduleMenuItem(
                                text = "Delete Schedule",
                                iconRes = R.drawable.content_cut_fill0_wght400_grad0_opsz24,
                                onClick = { navController.navigate("DeleteScheduleScreenWithPassword") }
                            )
                            ScheduleMenuItem(
                                text = "List All Schedule",
                                iconRes = R.drawable.cloud_download_fill0_wght400_grad0_opsz24,
                                onClick = { navController.navigate("ListAllScheduleScreen") }
                            )
                        }
                    }
                }

                // UTILITY Section
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF648FD6),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp,
                            focusedElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TextShadow(text = "UTILITY")
                            }
                            HorizontalDivider(thickness = 3.dp, color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))

                            ScheduleMenuItem(
                                text = "Find my bus",
                                iconRes = R.drawable.manage_search_fill0_wght400_grad0_opsz24,
                                onClick = { navController.navigate("FindMyBusScreen") }
                            )
                            ScheduleMenuItem(
                                text = "Depo List",
                                iconRes = R.drawable.saved_search_fill0_wght400_grad0_opsz24,
                                onClick = { navController.navigate("DepoListScreen") }
                            )
                            ScheduleMenuItem(
                                text = "CURRENCY COUNT",
                                iconRes = R.drawable.note_add_fill0_wght400_grad0_opsz24,
                                onClick = { navController.navigate("CurrencyCountScreen") }
                            )
                        }
                    }
                }

                // Buttons Section
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF648FD6),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp,
                            focusedElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { navController.navigate("RegisterScreen") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF00712D),
                                    contentColor = Color.White
                                ),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 20.dp,
                                    disabledElevation = 12.dp
                                )
                            ) {
                                Text("Register", fontSize = 14.sp, color = Color(0xFFD5ED9F))
                            }

                            Button(
                                onClick = { navController.navigate("ContactMeScreen") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFF6F00),
                                    contentColor = Color.White
                                ),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 20.dp
                                )
                            ) {
                                Text("Contact Me", fontSize = 14.sp, color = Color.White)
                            }

                            Button(
                                onClick = { navController.navigate("AboutScreen") },
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 30.dp
                                )
                            ) {
                                Text("ABOUT", color = Color.White, fontSize = 12.sp)
                            }
                        }
                    }
                }

                // Ad Banner
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF648FD6),
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp,
                            focusedElevation = 8.dp
                        ),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            BannerAdView(false, AdSize.LARGE_BANNER)
                        }
                    }
                }
            }
        }
    }
}

// Reusable composable for menu options in the Duty Diary section
@Composable
fun MenuOption(
    text: String,
    iconRes: Int,
    onClick: () -> Unit,
    iconTint: Color = Color.White,
    textSize: TextUnit = 17.sp
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = textSize,
            textAlign = TextAlign.Center
        )
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(28.dp),
                tint = iconTint
            )
        }
    }
}

// Reusable composable for schedule menu items
@Composable
fun ScheduleMenuItem(
    text: String,
    iconRes: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
fun TextShadow(text: String) {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = text,
        color = Color.White,
        style = TextStyle(
            fontSize = 27.sp,
            shadow = Shadow(
                color = Color.Blue,
                offset = offset,
                blurRadius = 3f
            )
        )
    )
}

//@Composable
//fun CircularButton(onClick: () -> Unit, buttonText: String="Click") {
//    Button(
//        onClick = onClick,
//        shape = CircleShape, // This ensures the button is shaped like a circle
//        modifier = Modifier
//            .size(35.dp), // Set size to be the same for width and height (this ensures it's a circle)
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0xFFFFFF00),
//            contentColor = Color.White // text color
//        )
//    ) {
//        Text(text = buttonText, color = Color.White, fontSize = 12.sp)
//    }
//}

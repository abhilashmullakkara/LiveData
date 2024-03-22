package com.abhilash.livedata.ui.ai

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutScreen(navController: NavController){
    Surface(color = Color(0xFF223147)){

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(300.dp), // Adjust the height as needed
           // backgroundColor = Color(0xFF142033),
            colors = cardColors(
                containerColor = Color(0xFF142033),
            ),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp,
                focusedElevation = 10.dp
            )
        )
           // elevation = 5.dp)
            {

                LazyColumn(modifier = Modifier.padding(start=20.dp)){
                    stickyHeader {
                        Button(onClick = { navController.navigate("MenuScreen") },
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF6F00),
                                contentColor = Color.White // text color
                            ),elevation = androidx.compose.material3.ButtonDefaults.buttonElevation(
                                defaultElevation = 10.dp
                            )

                        ) {
                            //androidx.compose.material3.Text("Back", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color= Color.White)
                            Image(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription ="back" )

                        }
                    }

                    item {
                        Text(" Esteemed Users,\n" +
                                "\n" +
                                "To navigate the intricacies of this app, a critical understanding of its functionality is imperative. Given its reliance on cloud services, a stringent security measure in the form of a password has been implemented to fortify against unauthorized access, thereby elevating the overall security architecture.\n" +
                                "\n" +
                                "Each depot is uniquely identified by a designated number, conveniently accessible on the 'DepoList' screen. Opting for numerical representation proves to be more efficient than laboriously typing out depot names on each instance of use. To bolster security, each depot is assigned an exclusive password, obtainable through direct communication via email with myself.\n" +
                                "\n" +
                                "The current password, 'neofetch,' serves as a temporary gateway. It is imperative to note that once schedules are successfully added to each depot, this password undergoes a mandatory change. This protocol is instituted to thwart any potential breach by restricting access to a select few individuals privy to the updated password.\n" +
                                "\n" +
                                "In the interest of preserving the integrity of schedule details, only a limited number of individuals possessing the current password are authorized to effectuate any changes. Broad dissemination of the password is avoided to prevent unwarranted alterations or deletions to the meticulously updated schedule details.\n" +
                                "\n" +
                                "For any requisite alterations or updates to the password, a simple and expedient communication process has been established. Informing me promptly allows for a swift alteration, safeguarding the system from any potential security threats.\n" +
                                "\n" +
                                "Your adherence to these security protocols is greatly appreciated, ensuring the continued functionality and reliability of the app.\n Thanking you \n Abhilash M.S \n abhilssh85@gmail.com", fontSize = 16.sp,color=Color.White, fontWeight = FontWeight(800)
                        )
                    }
                }

//            LazyColumn(modifier = Modifier.padding(start=20.dp)){//FF3CCE7E
//                item {
//                    Row {
//
//                        TextButton(onClick = {
//                            navController.popBackStack("MenuScreen",inclusive = false)
//                        },modifier=Modifier.fillMaxWidth().size(50.dp).clip(RoundedCornerShape(50.dp)).border(BorderStroke(1.dp, Color.White), shape = RoundedCornerShape(50.dp)),
//                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1A237E),
//                                backgroundColor = Color(0xFF4D5F4F)
//                            )
//                        )
//                        {
//                            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow",tint= Color.White)
//                        }
//
//                    }
//                }
//
//                item {
//                    Text("Help Menu", fontSize = 19.sp,color=Color(0xFF3CCE7E))
//                    Divider(color=Color(0xFF5A5D63), modifier = Modifier.padding(end=20.dp))
//                }
//                item {
//                    Text("എങ്ങനെ ഷെഡ്യൂൾ add ചെയ്യാം ?", fontSize = 17.sp,color=Color.White)
//                }
//                item {
//                    Divider(color = Color(0xFF2F4A66), thickness = 4.dp)
//                }
//                item {
//                    Text("ഇതിനായി Add /update ബട്ടൺ ക്ലിക്ക് ചെയ്യുക ... ",
//                        fontSize = 14.sp,color=Color(0xFF3A91F5)
//                    )
//                    Text("തുടർന്നു ഡിപ്പോ നമ്പർ -> ഷെഡ്യൂൾ നമ്പർ -> ടൈപ്പ് -> ഇവ നൽകുക",
//                        fontSize = 14.sp,color=Color(0xFF3A91F5))
//                    Text("Optional അല്ലാത്ത എല്ലാ data യും നൽകുക ." +
//                            " paasword ശരിയാണങ്കിൽ upload ബട്ടൺ display ചെയ്യും ",
//                        fontSize = 14.sp,color=Color(0xFF3A91F5))
//                    Text("(ഷെഡ്യൂൾ സെക്ഷൻ കൈകാര്യം ചെയാൻ internet കണക്ഷൻ ആവശ്യമാണ് )",fontSize = 14.sp,color=Color(0xFF3A91F5))
//                    Text("(The temporary passphrase for the current moment is 'neofetch,' " +
//                            "to be subsequently modified for enhanced security measures.)", fontSize = 12.sp,color=Color(
//                        0xFF85B4EB
//                    )
//                    )
//                }
//                item {
//                    Divider(color = Color(0xFF2F4A66), thickness = 4.dp)
//                    //Text("\n")
//                }
//                item {
//                    Text("Schedule -> കാണുന്നതിന് password ആവശ്യം ഇല്ല ", fontSize = 14.sp,color=Color(0xFF3A91F5))
//                }
//                item {
//                    Text("എങ്ങനെ Duty ഡയറിയിൽ റെക്കോർഡ് add ചെയ്യാം ?", fontSize = 17.sp,color=Color.White)
//                    Divider(color = Color(0xFFB1BDCA), thickness = 4.dp)
//                }
//                item {
//                    Text(
//                        "ഇതിനായി Add Duty ബട്ടൺ ക്ലിക്ക് ചെയ്യുക ... ",
//                        fontSize = 14.sp, color = Color(0xFF3A91F5)
//                    )
//                    Text("ഷെഡ്യൂൾ നമ്പർ , എത്ര duty ലഭിച്ചു എന്നിവ നൽകിയാൽ സെലക്ട് ഡേറ്റ് ക്ലിക്ക് ചെയ്യുക .ഡേറ്റ് സെലക്ട് ചെയ്തത് " +
//                            " ഇൻസെർട് ബട്ടൺ ക്ലിക്ക്  ചെയ്തത് മൊബൈലിൽ സേവ് ചെയ്യാം ",fontSize = 14.sp, color = Color(0xFF3A91F5))
//                    Text("മറ്റു വിവരങ്ങൾ optional ആണ്. Crew Name എന്റർ ചെയ്യേണ്ടിടത്തു കണ്ടക്ടർ ആണെങ്കിൽ ഡ്രൈവറിണ്ടെ പേരും , " +
//                            "ഡ്രൈവർ  ആണെങ്കിൽ കണ്ടക്ടർ പേരും നൽകാം  ",fontSize = 14.sp, color = Color(0xFF3A91F5))
//                }
//                item {
//                    Text(text=" View ബട്ടൺ ക്ലിക്ക് ചെയിതാൽ ," +
//                            " record ആഡ് ചെയിതിടൂണ്ടെങ്കിൽ അവ ലിസ്റ്റ് ചെയുന്നതാണു", fontSize = 14.sp, color = Color(0xFF3A91F5))
//                }
//
//
//            }

        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun AboutScreenPreview(){
//    AboutScreen()
//
//}
package com.abhilash.livedata.ui.ai

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AboutScreen(navController: NavController){
    Surface(color = Color(0xFF223147)){

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(300.dp), // Adjust the height as needed
            //backgroundColor = Color(0xFF448AFF),
            backgroundColor = Color(0xFF142033),
            shape = RoundedCornerShape(5.dp),
            elevation = 5.dp) {

            LazyColumn(modifier = Modifier.padding(start=20.dp)){//FF3CCE7E
                item {
                    Row {

                        TextButton(onClick = {
                            navController.popBackStack("MenuScreen",inclusive = false)
                        },modifier=Modifier.fillMaxWidth().size(50.dp).clip(RoundedCornerShape(50.dp)).border(BorderStroke(1.dp, Color.White), shape = RoundedCornerShape(50.dp)),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1A237E),
                                backgroundColor = Color(0xFF4D5F4F)
                            )
                        )
                        {
                            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow",tint= Color.White)
                        }

                    }
                }

                item {
                    Text("Help Menu", fontSize = 19.sp,color=Color(0xFF3CCE7E))
                    Divider(color=Color(0xFF5A5D63), modifier = Modifier.padding(end=20.dp))
                }
                item {
                    Text("എങ്ങനെ ഷെഡ്യൂൾ add ചെയ്യാം ?", fontSize = 17.sp,color=Color.White)
                }
                item {
                    Divider(color = Color(0xFF2F4A66), thickness = 4.dp)
                }
                item {
                    Text("ഇതിനായി Add /update ബട്ടൺ ക്ലിക്ക് ചെയ്യുക ... ",
                        fontSize = 14.sp,color=Color(0xFF3A91F5)
                    )
                    Text("തുടർന്നു ഡിപ്പോ നമ്പർ -> ഷെഡ്യൂൾ നമ്പർ -> ടൈപ്പ് -> ഇവ നൽകുക",
                        fontSize = 14.sp,color=Color(0xFF3A91F5))
                    Text("Optional അല്ലാത്ത എല്ലാ data യും നൽകുക ." +
                            " paasword ശരിയാണങ്കിൽ upload ബട്ടൺ display ചെയ്യും ",
                        fontSize = 14.sp,color=Color(0xFF3A91F5))
                    Text("(ഷെഡ്യൂൾ സെക്ഷൻ കൈകാര്യം ചെയാൻ internet കണക്ഷൻ ആവശ്യമാണ് )",fontSize = 14.sp,color=Color(0xFF3A91F5))
                    Text("(The temporary passphrase for the current moment is 'neofetch,' " +
                            "to be subsequently modified for enhanced security measures.)", fontSize = 12.sp,color=Color(
                        0xFF85B4EB
                    )
                    )
                }
                item {
                    Divider(color = Color(0xFF2F4A66), thickness = 4.dp)
                    //Text("\n")
                }
                item {
                    Text("Schedule -> കാണുന്നതിന് password ആവശ്യം ഇല്ല ", fontSize = 14.sp,color=Color(0xFF3A91F5))
                }

            }

        }

    }
}
//@Preview(showBackground = true)
//@Composable
//fun AboutScreenPreview(){
//    AboutScreen()
//
//}
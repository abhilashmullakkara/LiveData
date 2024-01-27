package com.abhilash.livedata.ui.ai

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abhilash.livedata.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactMeScreen(navController: NavController){
    Surface(color= Color(0xFFA8BDE0)) {
        LazyColumn {
            stickyHeader {
                Row {
                    Button(onClick = { navController.navigate("MenuScreen") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6F00),
                            contentColor = Color.White // text color
                        ),elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 10.dp
                        )

                    ) {
                        //androidx.compose.material3.Text("Back", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color= Color.White)
                        Image(imageVector = Icons.Default.ArrowBack, contentDescription ="back" )

                    }
                    Spacer(modifier = Modifier.fillMaxWidth(0.25f))
                    Image(painter = painterResource(id = R.drawable.face), contentDescription = "myFace",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = CircleShape
                            ),
                        contentScale = ContentScale.Crop,
                        alignment = AbsoluteAlignment.TopRight
                    )

                }

            }

           item {
               Card(modifier = Modifier
                   .fillMaxSize()
                   .padding(20.dp),
                   //.height(400.dp),
                   colors =  cardColors(Color(0xFF7B9DD3)),
                   shape = RoundedCornerShape(5.dp),
                   elevation= cardElevation(
                       defaultElevation=30.dp
                   )
               )
               {
                   Text("I trust this message finds you well. My name is Abhilash, and I proudly serve as a conductor in Kattappana. I am reaching out to you with great enthusiasm to share a project that holds a special place in my heart – an app born out of my own experiences in the field.\n" +
                           "\n" +
                           "I have poured my passion and insights into creating an application tailored to the needs of conductors like myself. Your valuable opinions and feedback would mean the world to me as I strive to enhance and refine this endeavor.\n" +
                           "\n" +
                           "If you could spare a moment to explore the app, I would greatly appreciate your thoughts on its functionality and user interface. Your input is crucial in shaping an even more intuitive and efficient tool for conductors navigating the demands of their roles.\n" +
                           "\n" +
                           "I have also integrated Google Ads into the app to offset the costs associated with utilizing cloud services. However, I am open to accommodating users who prefer an ad-free experience. Please feel free to reach out via email at abhilssh85@gmail.com if you wish to discuss a customized, ad-free version, albeit with a modest fee to cover the cloud service expenses.\n" +
                           "\n" +
                           "Your support and insights are immensely valuable to me, and I am eager to make any necessary adjustments based on your feedback. Thank you for considering my request, and I look forward to hearing from you.\n" +
                           "\n" +
                           "Warm regards,\n" +
                           "\n" +
                           " Abhilash M.S\n abhilssh85@gmail.com\n" +
                           " Conductor at Kattappana\n",
                       fontSize = 16.sp, color= Color(0xFF231942), fontWeight = FontWeight(800)
                   )

               }
           }




    }//column

    }
}

@Preview(showBackground = true)
@Composable
fun ContactmePreview(){
    ContactMeScreen(navController = rememberNavController())
}




package com.abhilash.livedata.ui.ai

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
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

@Composable
fun ContactMeScreen(navController: NavController){
    Surface(color= Color(0xFFA8BDE0)) {
        Column {
            Row {
                Button(onClick = { navController.navigate("MenuScreen") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6F00),
                        contentColor = Color.White // text color
                    ),elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 20.dp
                    )

                ) {
                    Text("Back", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color= Color.White)

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
                Text("Hi, I am Abhilash, Conductor at Kattappana,",
                    fontSize = 16.sp, color= Color(0xFF231942), fontWeight = FontWeight(800)
                )

            }



    }//column

    }
}

@Preview(showBackground = true)
@Composable
fun ContactmePreview(){
    ContactMeScreen(navController = rememberNavController())
}




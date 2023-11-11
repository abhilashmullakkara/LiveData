package com.abhilash.livedata.ui.theme.share

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SendWhatsAppMessage(context: Context,text:String) {
    Surface(color= Color(0xFF6776CA),modifier= Modifier.fillMaxSize()) {

        val phoneNumber = remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                //.fillMaxSize()
                .padding(top= 20.dp),
//		horizontalAlignment = Alignment.CenterHorizontally,
//		verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Send Details to WhatsApp",
                color = Color(0xFFFF9100),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                placeholder = { Text(text = "Enter whatsapp number to share") },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
                singleLine = true,
            )
           // Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                String.format(
                                    "https://api.whatsapp.com/send?phone=%s&text=%s",
                                    "+91" + phoneNumber.value, text
                                )
                            )
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Send Message on WhatsApp")
            }
        }
    }
}



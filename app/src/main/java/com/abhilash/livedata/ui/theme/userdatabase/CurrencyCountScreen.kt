package com.abhilash.livedata.ui.theme.userdatabase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.ai.isValidText

@Composable
fun CurrencyCountScreen(navController:NavController) {
    Surface(color = Color(0xFF92B5F1)) {
            val scroll= rememberScrollState()
        var total by rememberSaveable { mutableStateOf("0") }
        var ten by rememberSaveable { mutableStateOf("") }
        var twenty by rememberSaveable { mutableStateOf("") }
        var fifty by rememberSaveable { mutableStateOf("") }
        var hundred by rememberSaveable { mutableStateOf("") }
        var twohundred by rememberSaveable { mutableStateOf("") }
        var fivehundred by rememberSaveable { mutableStateOf("") }
        var coins by rememberSaveable { mutableStateOf("") }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(800.dp), // Adjust the height as needed

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF648FD6),
                contentColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp,
                focusedElevation = 8.dp
            ),
            shape = RoundedCornerShape(5.dp),

//            backgroundColor = Color(0xFF828ED2),
//            contentColor = Color.White
        ) {
            Column(modifier=Modifier.verticalScroll(scroll)) {


                IconButton(onClick = {
                    navController.popBackStack("MenuScreen", inclusive = false)
                })
                {
                    Row {
                        Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Arrow")
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Total : $total ",fontSize = 25.sp,color= Color.Green, modifier = Modifier.padding(start=25.dp))
                    }

                }
                HorizontalDivider(thickness = 5.dp, color = Color.White)
                Column {
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("₹10 x ", fontSize = 24.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(5.dp))
                        ten = add("10", readValue())
                        Text(" =  $ten", fontSize = 25.sp)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    HorizontalDivider()
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("₹20 x ", fontSize = 24.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(5.dp))
                        twenty = add("20", readValue())
                        Text(" =  $twenty", fontSize = 25.sp)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    HorizontalDivider()
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("₹50 x ", fontSize = 24.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(5.dp))
                        fifty = add("50", readValue())
                        Text(" =  $fifty", fontSize = 25.sp)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    HorizontalDivider()
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("₹100 x ", fontSize = 24.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(5.dp))
                        hundred = add("100", readValue())
                        Text(" =  $hundred", fontSize = 25.sp)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    HorizontalDivider()
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("₹200 x ", fontSize = 24.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(5.dp))
                        twohundred = add("200", readValue())
                        Text(" =  $twohundred", fontSize = 25.sp)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    HorizontalDivider()
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("₹500 x ", fontSize = 24.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(5.dp))
                        fivehundred = add("500", readValue())
                        Text(" =  $fivehundred", fontSize = 25.sp)
                    }
                    HorizontalDivider()
                    Text("For coins enter only the total value", fontSize = 12.sp,color= Color.DarkGray)
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("₹coins x ", fontSize = 18.sp, color = Color.Red)
                        Spacer(modifier = Modifier.width(5.dp))
                        coins = add("1", readValue())
                        Text(" =  $coins", fontSize = 25.sp)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    total=sum(ten,twenty, fifty,hundred,twohundred,fivehundred,coins)
                    HorizontalDivider()
                    HorizontalDivider()
                    Text("Total : $total ",fontSize = 25.sp,color= Color.Green, modifier = Modifier.padding(start=25.dp))

                }

            }
        }
    }

}

@Composable
fun add(cur: String, num: String): String {
    return try {
        val tot = cur.toInt() * num.toInt()
        tot.toString()
    } catch (e: NumberFormatException) {
        "0"
    }
}
@Composable
fun sum(ten: String="0", twenty: String="0", fifty:String="0",hundred:String="0"
,twohundred:String="0",fivehundred:String="0",coins:String="0"): String {
    return try {
   val total=ten.toInt()+twenty.toInt()+fifty.toInt()+hundred.toInt()+twohundred.toInt()+fivehundred.toInt()+coins.toInt()
        total.toString()
    } catch (e: NumberFormatException) {
        "0"
    }
}

@Composable
fun readValue():String {
    var inputValue by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = inputValue,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        onValueChange = { newValue ->
            val textFieldValue =
                TextFieldValue(newValue, TextRange(newValue.length))
            if (isValidText(textFieldValue)) {
                inputValue = textFieldValue.text
            }
        },
        modifier= Modifier
            .size(width = 90.dp, height = 51.dp),
        //  modifier = Modifier.fillMaxWidth(0.45f),
        placeholder = {
            Text(
                text = "--",
                color = Color.White,
                fontSize = 22.sp
            )
        }
    )
    return inputValue
}
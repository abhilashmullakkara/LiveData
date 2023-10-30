package com.abhilash.livedata.ui.theme.userdatabase

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.room.EmployeeDB
import kotlinx.coroutines.launch

@Composable
fun DeleteRecordScreen(navController: NavController){
    var recNo by rememberSaveable { mutableStateOf("") }
    var flag by rememberSaveable { mutableStateOf(false) }
    val context= LocalContext.current
    val coroutineScope= rememberCoroutineScope()
    Surface(color = Color(0xFF616BA1), modifier = Modifier.height(850.dp)) {
        Column(
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = {
                navController.popBackStack("MenuScreen",inclusive = false)
            })
            {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow",tint=Color.White)
            }
            Divider(thickness = 3.dp,color= Color.White)

            Text(text = "Enter Record Number to delete a single record from the database",color = Color.Gray, fontSize = 18.sp)
            OutlinedTextField(
                value = recNo,
                singleLine = true,
                shape = RoundedCornerShape(80),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    recNo = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.70f)
                    .padding(start = 10.dp),
                placeholder = {
                    Text(
                        text = "Enter Record No to delete:",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            )
         OutlinedButton(onClick = {
           if (recNo.isNotBlank()){
               coroutineScope.launch {
                   flag=true
                   EmployeeDB.getInstance(context).getEmployeeDao().delete(recNo.toInt())
                   //EmployeeDB.getInstance(context).getEmployeeDao().reorderIdsAfterDelete(recNo.toInt())

                   Toast.makeText(context," Record Deleted or not existed !",Toast.LENGTH_SHORT).show()
               }
       }
             else
           {
             Toast.makeText(context," Input Record first",Toast.LENGTH_SHORT).show()
           }
         },
             modifier = Modifier.padding(start = 25.dp),
             colors = ButtonDefaults.buttonColors(Color(0xFF456890))
             ) {
             Text(text = "DELETE",color = Color.White, fontSize = 16.sp)

         }


if(flag){
    navController.popBackStack("MenuScreen",inclusive = false)
}
        }
    }


}


///


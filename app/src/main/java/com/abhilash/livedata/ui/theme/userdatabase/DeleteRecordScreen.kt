package com.abhilash.livedata.ui.theme.userdatabase

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
//
//@Composable
//fun DeleteRecordScreen(navController: NavController){
//    var recNo by rememberSaveable { mutableStateOf("") }
//    var flag by rememberSaveable { mutableStateOf(false) }
//    val context= LocalContext.current
//    val coroutineScope= rememberCoroutineScope()
//    Surface(color = Color(0xFF616BA1), modifier = Modifier.height(850.dp)) {
//        Column {
//            IconButton(onClick = {
//                navController.popBackStack("MenuScreen",inclusive = false)
//            })
//            {
//                Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Arrow",tint=Color.White)
//            }
//            HorizontalDivider(thickness = 3.dp, color = Color.White)
//
//            Text(text = "Enter Record Number to delete a single record from the database",color = Color.Gray, fontSize = 18.sp)
//            OutlinedTextField(
//                value = recNo,
//                singleLine = true,
//                shape = RoundedCornerShape(80),
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                onValueChange = {
//                    recNo = it
//                },
//                modifier = Modifier
//                    .fillMaxWidth(0.70f)
//                    .padding(start = 10.dp),
//                placeholder = {
//                    Text(
//                        text = "Enter Record No to delete:",
//                        color = Color.Black,
//                        fontSize = 14.sp
//                    )
//                }
//            )
//         OutlinedButton(onClick = {
//           if (recNo.isNotBlank()){
//               coroutineScope.launch {
//                   flag=true
//                   EmployeeDB.getInstance(context).getEmployeeDao().delete(recNo.toInt())
//                   //EmployeeDB.getInstance(context).getEmployeeDao().reorderIdsAfterDelete(recNo.toInt())
//                  // navController.popBackStack("MenuScreen",inclusive = false)
//
//                   Toast.makeText(context," Record Deleted or not existed !",Toast.LENGTH_SHORT).show()
//               }
//       }
//             else
//           {
//             Toast.makeText(context," Input Record first",Toast.LENGTH_SHORT).show()
//           }
//         },
//             modifier = Modifier.padding(start = 25.dp),
//             colors = ButtonDefaults.outlinedButtonColors(
//                 containerColor = Color(0xFF456890),
//                 contentColor = Color.White,
//                 disabledContainerColor = Color(0xFFF57F17)
//
//             ),
//           )
//         {
//             Text(text = "DELETE",color = Color.White, fontSize = 16.sp)
//
//         }
//
//
//if(flag){
//    navController.popBackStack("MenuScreen",inclusive = false)
//}
//        }
//    }
//
//
//}




@Composable
fun DeleteRecordScreen(navController: NavController) {
    var recNo by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Surface(color = Color(0xFF616BA1), modifier = Modifier.height(850.dp)) {
        Column {
            IconButton(onClick = {
                navController.popBackStack("MenuScreen", inclusive = false)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Arrow",
                    tint = Color.White
                )
            }
            HorizontalDivider(thickness = 3.dp, color = Color.White)

            Text(
                text = "Enter Record Number to delete a single record from the database",
                color = Color.Gray,
                fontSize = 18.sp
            )
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
            OutlinedButton(
                onClick = {
                    if (recNo.isNotBlank()) {
                        coroutineScope.launch {
                            EmployeeDB.getInstance(context).getEmployeeDao().delete(recNo.toInt())
                            Toast.makeText(context, "Record Deleted or not existed!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack("MenuScreen", inclusive = false)
                        }
                    } else {
                        Toast.makeText(context, "Input Record first", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.padding(start = 25.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF456890),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFF57F17)
                ),
            ) {
                Text(text = "DELETE", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}



///


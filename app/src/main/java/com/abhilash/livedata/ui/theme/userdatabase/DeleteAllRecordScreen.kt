package com.abhilash.livedata.ui.theme.userdatabase

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.room.EmployeeDB
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//@Composable
//fun DeleteAllRecordScreen(navController: NavController) {
//    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
//    val database = EmployeeDB.getInstance(context)
//
//    database.close()
//
//    Surface(color = Color(0xFFFA0133), modifier = Modifier
//        .height(850.dp)
//        .fillMaxWidth()) {
//        Column {
//
//
//        IconButton(onClick = {
//            navController.popBackStack("MenuScreen",inclusive = false)
//        })
//        {
//            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow",tint= Color.White)
//        }
//            Divider(thickness = 3.dp, color = Color(0xFFC6E4C4))
//            Spacer(modifier = Modifier.height(2.dp))
//            Divider(thickness = 1.dp, color = Color(0xFFC6E4C4))
//            Spacer(modifier = Modifier.height(10.dp))
//        OutlinedButton(onClick = {
//
//            coroutineScope.launch {
//                EmployeeDB.getInstance(context).getEmployeeDao().deleteAll()
//                EmployeeDB.getInstance(context).getEmployeeDao().resetAutoIncrement()
//                Toast.makeText(context, "All records deleted", Toast.LENGTH_SHORT).show()
//            }
//        }) {
//            Text(text = "DELETE ALL", fontSize = 24.sp,color=Color.White)
//
//        }
//            Text("All the data will be deleted!",color= Color.White, fontSize = 24.sp)
//    }
//}
//}


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun DeleteAllRecordScreen(navController: NavController) {
    val context = LocalContext.current
    var flag by remember { mutableStateOf(false) }
    val database = EmployeeDB.getInstance(context)
    val viewModel = remember { DeleteAllRecordViewModel() }

//    DisposableEffect(Unit) {
//        onDispose {
//            database.close()
//        }
//    }

    Surface(color = Color(0xFFFA0133), modifier = Modifier
        .height(850.dp)
        .fillMaxWidth()) {
        Column {
            IconButton(onClick = {
                navController.popBackStack("MenuScreen", inclusive = false)
            }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow", tint = Color.White)
            }
            Divider(thickness = 3.dp, color = Color(0xFFC6E4C4))
            Spacer(modifier = Modifier.height(2.dp))
            Divider(thickness = 1.dp, color = Color(0xFFC6E4C4))
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = {
                    viewModel.deleteAllRecords(database)
                    // Show the Toast on the main/UI thread
                    GlobalScope.launch(Dispatchers.Main) {
                        flag=true
                        Toast.makeText(context, "All records deleted", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "DELETE ALL", fontSize = 24.sp, color = Color.White)
            }

            Text("All the data will be deleted!", color = Color.White, fontSize = 24.sp)
        }
        if(flag){
            navController.popBackStack("MenuScreen", inclusive = false)
        }
    }
}


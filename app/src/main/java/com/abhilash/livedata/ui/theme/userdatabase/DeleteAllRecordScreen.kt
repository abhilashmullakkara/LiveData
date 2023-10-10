package com.abhilash.livedata.ui.theme.userdatabase

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun DeleteAllRecordScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Surface(color = Color(0xFFFA0133), modifier = Modifier
        .height(850.dp)
        .fillMaxWidth()) {
        Column {


        IconButton(onClick = {
            navController.popBackStack("MenuScreen",inclusive = false)
        })
        {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow",tint= Color.White)
        }
            Divider(thickness = 3.dp, color = Color(0xFFC6E4C4))
            Spacer(modifier = Modifier.height(2.dp))
            Divider(thickness = 1.dp, color = Color(0xFFC6E4C4))
            Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(onClick = {

            coroutineScope.launch {
                EmployeeDB.getInstance(context).getEmployeeDao().deleteAll()
                Toast.makeText(context, "All records deleted", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "DELETE ALL", fontSize = 24.sp,color=Color.White)

        }
            Text("All the data will be deleted!",color= Color.White, fontSize = 24.sp)
    }
}
}
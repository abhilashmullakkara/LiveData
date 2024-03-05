package com.abhilash.livedata.ui.theme.userdatabase

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.room.RoomData
import java.util.Calendar
import java.util.Date

@Composable
fun AddDutyDiaryScreen(navController: NavController){
    Surface(color=Color(0xFF6776CA)) {
    Column(
    )
    {
        Row()
        {
            IconButton(onClick = {
                navController.popBackStack("MenuScreen",inclusive = false)
            })
            {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow")
            }
            Text(
                "Enter Information", fontSize = 19.sp,
                color = Color(0xFFC8C8CE),
                fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp)
            )

        }
        RoomData(navController)
    }
    }
}



@SuppressLint("SuspiciousIndentation")
@Composable
fun myCalendar():String {
    val mContext = LocalContext.current
    val mYear1: Int
    val mMonth1: Int
    val mDay1: Int
    val mCalendar = Calendar.getInstance()
    mYear1 = mCalendar.get(Calendar.YEAR)
    mMonth1 = mCalendar.get(Calendar.MONTH)
    mDay1 = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("") }
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mYear/${mMonth+1}/$mDayOfMonth"
        }, mYear1, mMonth1, mDay1
    )
        Button(onClick = {
            mDatePickerDialog.show()
        },modifier = Modifier.padding(start=10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58)) ) {
            Text(text = "Select Date", color = Color.White)
        }
        Text(text = "Selected Date: ${mDate.value}", fontSize = 17.sp,modifier = Modifier.padding(start=10.dp)
        )
    return mDate.value
}


@Composable
fun CircularLoadingIndicator(isLoading: Boolean) {
    if (isLoading) {
        CircularProgressIndicator()
    }
    }






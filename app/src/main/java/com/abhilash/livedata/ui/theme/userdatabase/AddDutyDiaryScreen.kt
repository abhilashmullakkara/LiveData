package com.abhilash.livedata.ui.theme.userdatabase

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
fun AddDutyDiaryScreen(navController: NavController) {
    Surface(
        color = Color(0xFF6776CA),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header Row with Back Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack("MenuScreen", inclusive = false)
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Back Arrow",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Enter Information",
                    fontSize = 19.sp,
                    color = Color(0xFFC8C8CE),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Main Content
            RoomData(navController)
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun myCalendar(): String {
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mYear1 = mCalendar.get(Calendar.YEAR)
    val mMonth1 = mCalendar.get(Calendar.MONTH)
    val mDay1 = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("") }
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val monthString = if ((mMonth + 1) < 10) "0${mMonth + 1}" else "${mMonth + 1}"
            val dayString = if (mDayOfMonth < 10) "0$mDayOfMonth" else "$mDayOfMonth"
            mDate.value = "$mYear/$monthString/$dayString"
        }, mYear1, mMonth1, mDay1
    )
    Button(
        onClick = {
            mDatePickerDialog.show()
        },
        modifier = Modifier.padding(start = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF648FD6),
        ),
       // colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
    ) {
        Text(text = "Select Date", color = Color.White)
    }
    val rdate = reverseStringDate(rdate = mDate.value)
    Text(
        text = "Selected Date: $rdate",
        fontSize = 17.sp,
        modifier = Modifier.padding(start = 10.dp)
    )
    return mDate.value
}



@Composable
fun CircularLoadingIndicator(isLoading: Boolean) {
    if (isLoading) {
        CircularProgressIndicator(
            color = Color.Red, // Set the color to your desired color
            strokeWidth = 4.dp
        )
    }
    }






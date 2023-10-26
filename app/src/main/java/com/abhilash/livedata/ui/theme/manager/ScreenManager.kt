package com.abhilash.livedata.ui.theme.manager

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhilash.livedata.ui.theme.DepoListScreen
import com.abhilash.livedata.ui.theme.schedule.AddScheduleScreen
import com.abhilash.livedata.ui.theme.schedule.DeleteScheduleScreen
import com.abhilash.livedata.ui.theme.schedule.DeleteTripScreen
import com.abhilash.livedata.ui.theme.schedule.FindMyBusScreen
import com.abhilash.livedata.ui.theme.schedule.ListAllScheduleScreen
import com.abhilash.livedata.ui.theme.schedule.ScheduleDisplayScreen
import com.abhilash.livedata.ui.theme.userdatabase.AddDutyDiaryScreen
import com.abhilash.livedata.ui.theme.userdatabase.CurrencyCountScreen
import com.abhilash.livedata.ui.theme.userdatabase.DeleteAllRecordScreen
import com.abhilash.livedata.ui.theme.userdatabase.DeleteRecordScreen
import com.abhilash.livedata.ui.theme.userdatabase.EditDutyDiaryScreen
import com.abhilash.livedata.ui.theme.userdatabase.ViewDiaryScreen

@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun MyApp(){
   ScreenManager()
}


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun ScreenManager(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination ="MenuScreen"  ){
        composable("MenuScreen"){
            MenuScreen(navController)
        }
        composable(" ScheduleDisplayScreen"){
            ScheduleDisplayScreen(navController)
        }
        composable("AddScheduleScreen"){
           AddScheduleScreen(navController)
        }
        composable("DeleteTripScreen"){
            DeleteTripScreen(navController)
        }
        composable("DeleteScheduleScreen"){
            DeleteScheduleScreen(navController)
        }
        composable("DepoListScreen"){
            DepoListScreen(navController)
        }
        composable("AddDutyDiaryScreen"){
            AddDutyDiaryScreen(navController)
        }
        composable("ViewDiaryScreen"){
            ViewDiaryScreen(navController)
        }
        composable("DeleteRecordScreen"){
            DeleteRecordScreen(navController)
        }
        composable("DeleteAllRecordScreen"){
           DeleteAllRecordScreen(navController)
        }
        composable("CurrencyCountScreen"){
            CurrencyCountScreen(navController)
        }
        composable("EditDutyDiaryScreen"){
            EditDutyDiaryScreen(navController)
        }
        composable("FindMyBusScreen"){
            FindMyBusScreen(navController)
        }
        composable("ListAllScheduleScreen"){
            ListAllScheduleScreen(navController)
        }
    }
}

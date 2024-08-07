package com.abhilash.livedata.ui.theme.room

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "Employee")
data class Employee (
     var dutyNo:String?="",
     var performedOn:String?="",
     var dutyEarned:String?="",
    var collection:String?="",
    var employeeName:String?="",
    var wayBillNo:String?="",
     var dutySurrendered:Boolean=false,
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
)


//@Entity(tableName = "Employee")
//data class Employee(
//    @PrimaryKey(autoGenerate = true) var id: Int,
//    val dutyNo: String,
//    val performedOn: String,
//    val dutyEarned: String,
//    val collection: String,
//    val employeeName: String,
//    val wayBillNo: String,
//    val dutySurrendered: Boolean=false
//)


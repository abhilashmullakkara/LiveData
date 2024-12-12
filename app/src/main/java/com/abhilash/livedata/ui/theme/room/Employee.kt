package com.abhilash.livedata.ui.theme.room

import androidx.annotation.Keep
import androidx.compose.runtime.saveable.mapSaver
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


// Define a Saver for the Employee class
val EmployeeSaver = mapSaver(
    save = { employee ->
        mapOf(
            "dutyNo" to employee.dutyNo,
            "performedOn" to employee.performedOn,
            "dutyEarned" to employee.dutyEarned,
            "collection" to employee.collection,
            "employeeName" to employee.employeeName,
            "wayBillNo" to employee.wayBillNo,
            "dutySurrendered" to employee.dutySurrendered,
            "id" to employee.id
        )
    },
    restore = { map ->
        Employee(
            dutyNo = map["dutyNo"] as String?,
            performedOn = map["performedOn"] as String?,
            dutyEarned = map["dutyEarned"] as String?,
            collection = map["collection"] as String?,
            employeeName = map["employeeName"] as String?,
            wayBillNo = map["wayBillNo"] as String?,
            dutySurrendered = map["dutySurrendered"] as Boolean,
            id = map["id"] as Int
        )
    }
)






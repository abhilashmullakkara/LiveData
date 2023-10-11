package com.abhilash.livedata.ui.theme.userdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface EmployeeDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee)
    @Query ("select * from  Employee" )
    suspend fun display():List<Employee>
    @Update
    suspend fun updateEmployee(employee: Employee)
    @Query("SELECT * FROM Employee WHERE id = :recNo")
    suspend fun getEmployeeByRecNo(recNo: Int): Employee?

    @Query("delete  from Employee where id=:recNo")
    suspend fun delete(recNo:Int)
    @Query("DELETE FROM Employee")
    suspend fun deleteAll()

}
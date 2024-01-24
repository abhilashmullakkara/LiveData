package com.abhilash.livedata.ui.theme.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface EmployeeDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee)
    @Query ("select * from  Employee order by performedOn asc" )
    suspend fun display():List<Employee>
    @Query(value="SELECT * FROM Employee ORDER BY performedOn DESC")
    suspend fun displaylast(): List<Employee>

    @Update
    suspend fun updateEmployee(employee: Employee)
    @Query("SELECT * FROM Employee WHERE id = :recNo")
    suspend fun getEmployeeByRecNo(recNo: Int): Employee?

    @Query("delete  from Employee where id=:recNo")
    suspend fun delete(recNo:Int)
    @Query("DELETE FROM Employee")
    suspend fun deleteAll()

    @Query("DELETE FROM sqlite_sequence WHERE name='Employee'")
    suspend fun resetAutoIncrement()
    @Query("UPDATE Employee SET id = (id - 1) WHERE id >:deletedId")
    suspend fun reorderIdsAfterDelete(deletedId: Int)




}

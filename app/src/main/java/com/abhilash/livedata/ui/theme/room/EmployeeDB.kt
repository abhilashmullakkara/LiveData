package com.abhilash.livedata.ui.theme.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//@Database(entities = [Employee::class], version = 2,exportSchema = false)
//abstract class EmployeeDB:RoomDatabase (){
//    abstract fun getEmployeeDao(): EmployeeDao
//    companion object {
//        private var INSTANCE: EmployeeDB?=null
//        fun getInstance(context: Context): EmployeeDB {
//        if (INSTANCE ==null){
//            INSTANCE =Room.databaseBuilder(context.applicationContext, EmployeeDB::class.java,"Employeedb.db").build()
//        }
//         return INSTANCE!!
//        }
//    }
//}


@Database(entities = [Employee::class], version = 2, exportSchema = false)
abstract class EmployeeDB : RoomDatabase() {
    abstract fun getEmployeeDao(): EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: EmployeeDB? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Create new table with the correct schema
                db.execSQL("""
                    CREATE TABLE Employee_new (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        dutyNo TEXT,
                        performedOn TEXT,
                        dutyEarned TEXT,
                        collection TEXT,
                        employeeName TEXT,
                        wayBillNo TEXT,
                        dutySurrendered INTEGER NOT NULL DEFAULT 0
                    )
                """.trimIndent())

                // Copy data from old table to new table
                db.execSQL("""
                    INSERT INTO Employee_new (id, dutyNo, performedOn, dutyEarned, collection, employeeName, wayBillNo, dutySurrendered)
                    SELECT id, dutyNo, performedOn, dutyEarned, collection, employeeName, wayBillNo, 
                        CASE WHEN dutySurrendered THEN 1 ELSE 0 END
                    FROM Employee
                """.trimIndent())

                // Drop the old table
                db.execSQL("DROP TABLE Employee")

                // Rename new table to the old table name
                db.execSQL("ALTER TABLE Employee_new RENAME TO Employee")
            }
        }

        fun getInstance(context: Context): EmployeeDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDB::class.java,
                    "Employeedb.db"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}




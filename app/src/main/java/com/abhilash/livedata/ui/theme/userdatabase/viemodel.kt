package com.abhilash.livedata.ui.theme.userdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhilash.livedata.ui.theme.room.EmployeeDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteAllRecordViewModel : ViewModel() {

    fun deleteAllRecords(database: EmployeeDB) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.getEmployeeDao().deleteAll()
                database.getEmployeeDao().resetAutoIncrement()
            }
        }
    }
}

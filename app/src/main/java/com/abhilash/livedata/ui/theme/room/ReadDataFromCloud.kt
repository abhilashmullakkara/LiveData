package com.abhilash.livedata.ui.theme.room

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abhilash.livedata.ui.theme.userdatabase.reverseStringDate
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun ReadDataFromCloud(navController: NavController) {
    var penNumberInput by remember { mutableStateOf("G") }
    var searchResult by remember { mutableStateOf<List<Employee>>(emptyList()) }
        var errorMessage by remember { mutableStateOf<String?>(null) }

Surface(color = Color(0xFF175B52)) {
  Column {
      Column(
       modifier = Modifier.padding(10.dp)
            )
      {
          Row{
              IconButton(onClick = {
                  navController.popBackStack("MenuScreen", inclusive = false) },
                  modifier = Modifier.size(48.dp)) {
                  Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                      contentDescription = "Arrow", tint = Color.White)
              }
              Spacer(modifier = Modifier.width(20.dp))
              TextField(
                  value = penNumberInput,
                  onValueChange = { penNumberInput = it },
                  label = { Text("Enter Pen Number", color = Color.White) },
                  modifier = Modifier.fillMaxWidth(),
                  colors = TextFieldDefaults.textFieldColors(
                      textColor = Color.White,
                      cursorColor = Color.White,
                      focusedIndicatorColor = Color.White,
                      unfocusedIndicatorColor = Color.White,
                      disabledIndicatorColor = Color.White
                  )
              )

          }

       }

     // Spacer(modifier = Modifier.height(16.dp))
      val databaseReference = FirebaseDatabase.getInstance("https://mydutydiary-default-rtdb.firebaseio.com/").getReference(penNumberInput?.takeIf { it.isNotEmpty() } ?: "123")
      databaseReference.addValueEventListener(object : ValueEventListener {
          override fun onDataChange(dataSnapshot: DataSnapshot) {
              val employees = mutableListOf<Employee>()
              for (childSnapshot in dataSnapshot.children) {
                  val employee = childSnapshot.getValue(Employee::class.java)
                  employee?.let { employees.add(it) }
              }
              searchResult = employees
          }
          override fun onCancelled(error: DatabaseError) {
              // Handle errors
          }
      })

         //   Spacer(modifier = Modifier.height(16.dp))
      if (errorMessage != null) {
          // Display error message
          Text(
              text = "No data found: $errorMessage",
              color = Color.Red,
              modifier = Modifier.padding(16.dp)
          )
      } else {
          // Display search results using LazyColumn
          LazyColumn(modifier = Modifier.fillMaxWidth()) {

              item {
                  LazyRow {
                      item {
                          Text("Record      D/no        Date       Duty-earned      coll        W/B     Surrender       Dvr/Cdr", color = Color.White, modifier = Modifier.padding(start = 8.dp))
                      }

                  }
                  Spacer(modifier = Modifier.height(10.dp))

              }
                            item {
                                HorizontalDivider(thickness = 5.dp, color = Color.White)
                                Spacer(modifier = Modifier.height(10.dp))
                            }
              items(searchResult) { result ->
                  LazyRow {
                      item {
                          Text(text =  "${result.id}" , color = Color.White, modifier = Modifier.padding(start=20.dp,end = 8.dp))
                          Text(text = "${result.dutyNo}", color = Color.White, modifier = Modifier.padding(start=20.dp,end = 18.dp))
                          Text(text = reverseStringDate(rdate = result.performedOn.toString()), color = Color.White, modifier = Modifier.padding(end = 8.dp))
                          Text(text = result.dutyEarned.toString(), color = Color.White, modifier = Modifier.padding(start = 18.dp, end = 18.dp))
                          Text(text = result.collection.toString(), color = Color.White, modifier = Modifier.padding(start = 10.dp, end =18.dp))
                          Text(text =  " ${result.wayBillNo}", color = Color.White, modifier = Modifier.padding(end = 18.dp))
                          if(result.dutySurrendered)
                              Text(text = "Yes", color = Color.White, modifier = Modifier.padding(end = 8.dp))
                          else Text(text = "No", color = Color.White, modifier = Modifier.padding(end = 8.dp))
                          Text(text = " ${result.employeeName}", color = Color.White, modifier = Modifier.padding(end = 8.dp))

                      }

                  }


                  }


          }
      }


      if(searchResult.isEmpty() )
      {
          Text(text = "No employee found",color=Color.White)
      }

        }
    }
}




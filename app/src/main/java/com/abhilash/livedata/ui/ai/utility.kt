package com.abhilash.livedata.ui.ai

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.google.firebase.database.FirebaseDatabase

fun isValidText(text: TextFieldValue): Boolean {
    val allowedChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return text.text.all { allowedChars.contains(it) }
}



@Composable
fun displayCloudDatabase(reference:String):String {
    var searchResults by remember { mutableStateOf(emptyList<String>()) }
    val dataBase = FirebaseDatabase.getInstance()
    val myRef2 = dataBase.getReference(reference)
    var ti=0
    val data = StringBuffer()
    var result by rememberSaveable { mutableStateOf("RESULT") }


    myRef2.get().addOnSuccessListener { dataSnapshot ->


        dataSnapshot?.children?.forEach { childSnapshot ->
            data.append("\n"+ (ti+1).toString())
            data.append("   " + childSnapshot.child("departureTime").value)
            data.append("    " + childSnapshot.child("startPlace").value)
            data.append("    " + childSnapshot.child("via").value)
            data.append("    " + childSnapshot.child("destinationPlace").value)
            data.append("     " + childSnapshot.child("arrivalTime").value)
            data.append("\n_______________")
            ti += 1
        }
        result=data.toString()
    }
    return result
}





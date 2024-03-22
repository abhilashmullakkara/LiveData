package com.abhilash.livedata.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun checkbox(select:String="",color: Color = Color.White):Boolean {


    val (checkedState, onStateChange) = remember { mutableStateOf(false) }
    Row(
        Modifier
            .fillMaxWidth()
            //.height(56.dp)
             .toggleable(
                value = checkedState,
                onValueChange = { onStateChange(!checkedState) },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            border = BorderStroke(1.dp, color = Color.White),
            shape = MaterialTheme.shapes.small
        ) {
            androidx.compose.material3.Checkbox(
                checked = checkedState,
                onCheckedChange = null // null recommended for accessibility with screenreaders

            )
        }

        Text(
            text = select,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp),
            color = color
        )
    }
    return checkedState
}

package com.ps.paginationplayground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyListItem(myItem: MyItem, modifier: Modifier = Modifier) {

    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        Text(text = myItem.name)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = myItem.description)
    }
}

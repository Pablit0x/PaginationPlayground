package com.ps.paginationplayground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ps.paginationplayground.ui.theme.PaginationPlaygroundTheme

@Composable
fun MyItemList(state: ScreenState, loadItems: () -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(state.items.size) { i ->
            val item = state.items[i]
            if(i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                loadItems()
            }
            MyListItem(
                myItem = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .background(
                        color = MaterialTheme.colorScheme.outline.copy(
                            alpha = 0.6f
                        ), shape = RoundedCornerShape(15)
                    )
                    .padding(16.dp),
            )
        }

        item {
            if(state.isLoading){
                Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonListPreview() {
    PaginationPlaygroundTheme {
//        val activities = listOf(personPreview, personPreview, personPreview, personPreview)
//        ActivityList(activities = activities, modifier = Modifier
//            .fillMaxWidth())
    }
}
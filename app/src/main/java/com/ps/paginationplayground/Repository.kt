package com.ps.paginationplayground

import kotlinx.coroutines.delay

class Repository {

    private val dataSource = (0..100000).map {
        MyItem(name = "Item $it", description = "Description $it")
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<MyItem>> {
        delay(1000)
        val startingIndex = page * pageSize
        return if (startingIndex + pageSize <= dataSource.size) {
            Result.success(
                dataSource.slice(startingIndex until startingIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}
package com.ps.paginationplayground

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}
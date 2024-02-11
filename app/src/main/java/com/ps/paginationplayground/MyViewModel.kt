package com.ps.paginationplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val repository = Repository()

    private var _state = MutableStateFlow(ScreenState())
    val state = _state.asStateFlow()

    private val paginator =
        DefaultPaginator(initialKey = state.value.page, onLoadUpdated = { isLoading ->
            _state.update {
                it.copy(
                    isLoading = isLoading
                )
            }
        }, onRequest = { nextPage ->
            repository.getItems(page = nextPage, pageSize = 20)
        }, getNextKey = {
            state.value.page + 1
        }, onError = { throwable ->
            _state.update {
                it.copy(
                    error = throwable.toString()
                )
            }
        }, onSuccess = { items, newKey ->
            _state.update {
                it.copy(
                    items = state.value.items + items, page = newKey, endReached = items.isEmpty()
                )
            }
        })

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    init {
        loadNextItems()
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<MyItem> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)
package com.kok1337.sync.data.datasource.cache

import com.kok1337.sync.data.model.BaseSyncApiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseSyncCache<Id, ApiModel : BaseSyncApiModel<Id>> {
    private val _items = MutableStateFlow<List<ApiModel>>(emptyList())
    val items = _items.asStateFlow()

    suspend fun clear() {
        _items.value = emptyList()
    }

    suspend fun pushItems(items: Iterable<ApiModel>) {
        _items.value = items.toList()
    }

    suspend fun updateById(id: Id, transformation: (ApiModel) -> ApiModel) {
        _items.update { list -> list.map { item -> if (item.id == id) transformation(item) else item } }
    }

    suspend fun deleteById(id: Id) {
        _items.update { list -> list.filter { item -> item.id != id } }
    }

    suspend fun deleteAllByIds(ids: Iterable<Id>) {
        _items.update { list -> list.filter { item -> !ids.contains(item.id) } }
    }

    suspend fun add(item: ApiModel) {
        _items.value = items.value + item
    }
}
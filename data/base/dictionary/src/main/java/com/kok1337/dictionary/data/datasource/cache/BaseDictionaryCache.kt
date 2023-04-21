package com.kok1337.dictionary.data.datasource.cache

import com.kok1337.dictionary.data.model.BaseDictionaryApiModel

abstract class BaseDictionaryCache<ApiModel : BaseDictionaryApiModel> {
    private val items = mutableListOf<ApiModel>()

    suspend fun clear() {
        items.clear()
    }

    suspend fun add(item: ApiModel) {
        items.add(item)
    }

    suspend fun getById(id: Int): ApiModel? {
        return items.singleOrNull { it.id == id }
    }
}
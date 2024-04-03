package com.example.notes21.domain

import androidx.lifecycle.LiveData

interface ItemListRepository {
    fun getItemList(): LiveData<List<ListItem>>
    suspend fun addItem(item: ListItem)
    suspend fun deleteItem(position: Int)
}
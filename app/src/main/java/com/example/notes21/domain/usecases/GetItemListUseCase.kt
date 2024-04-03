package com.example.notes21.domain.usecases

import androidx.lifecycle.LiveData
import com.example.notes21.domain.ItemListRepository
import com.example.notes21.domain.ListItem

class GetItemListUseCase (private val itemListRepository: ItemListRepository) {
    fun getItemList(): LiveData<List<ListItem>> {
        return itemListRepository.getItemList()
    }
}
package com.example.notes21.domain.usecases

import com.example.notes21.domain.ItemListRepository
import com.example.notes21.domain.ListItem

class GetItemUseCase (private val itemListRepository: ItemListRepository) {
    suspend fun getItem(itemId: Int): ListItem {
        return itemListRepository.getItem(itemId)
    }
}
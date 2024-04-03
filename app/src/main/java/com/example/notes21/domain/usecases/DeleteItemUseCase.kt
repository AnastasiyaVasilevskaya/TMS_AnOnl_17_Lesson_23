package com.example.notes21.domain.usecases

import com.example.notes21.domain.ItemListRepository
import com.example.notes21.domain.ListItem

class DeleteItemUseCase(private val itemListRepository: ItemListRepository) {
    suspend fun deleteItem(item: ListItem) {
        return itemListRepository.deleteItem(item)
    }
}
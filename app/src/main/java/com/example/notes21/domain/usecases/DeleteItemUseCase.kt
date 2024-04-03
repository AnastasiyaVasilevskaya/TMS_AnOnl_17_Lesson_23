package com.example.notes21.domain.usecases

import com.example.notes21.domain.ItemListRepository
import com.example.notes21.domain.ListItem

class DeleteItemUseCase(private val itemListRepository: ItemListRepository) {
    suspend fun deleteItem(position: Int) {
        return itemListRepository.deleteItem(position)
    }
}
package com.example.notes21.domain.usecases

import com.example.notes21.domain.ItemListRepository
import com.example.notes21.domain.ListItem

class AddItemUseCase(private val itemListRepository: ItemListRepository) {
    suspend fun addItem(item: ListItem) {
        return itemListRepository.addItem(item)
    }
}
package com.example.notes21.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.notes21.domain.ItemListRepository
import com.example.notes21.domain.ListItem
import com.example.notes21.domain.NotesService

class ItemListRepositoryImpl(
    application: Application
) : ItemListRepository {

    private val notesService = NotesService()
    override fun getItemList(): LiveData<List<ListItem>> =
        MediatorLiveData<List<ListItem>>().apply {
            value = notesService.getItems()
        }

    override suspend fun addItem(item: ListItem) {
        notesService.addItem(item)
    }

    override suspend fun deleteItem(position: Int) {
        notesService.deleteItem(position)
    }
}
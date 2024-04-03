package com.example.notes21.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes21.data.ItemListRepositoryImpl
import com.example.notes21.domain.ListItem
import com.example.notes21.domain.NotesService
import com.example.notes21.domain.usecases.AddItemUseCase
import com.example.notes21.domain.usecases.DeleteItemUseCase
import com.example.notes21.domain.usecases.GetItemUseCase

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ItemListRepositoryImpl(application)
    private val _items = MutableLiveData<List<ListItem>>()

    val items: LiveData<List<ListItem>>
        get() = _items

    private val addItemUseCase = AddItemUseCase(repository)
    private val getItemUseCase = GetItemUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)

    private val notesService: NotesService = NotesService()

    init {
        _items.value = notesService.getItems()
        notesService.addListener { items ->
            _items.postValue(items)
        }
    }

    override fun onCleared() {
        notesService.removeListener { }
        super.onCleared()
    }
}
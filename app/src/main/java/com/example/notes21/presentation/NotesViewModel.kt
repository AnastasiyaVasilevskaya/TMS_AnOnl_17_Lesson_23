package com.example.notes21.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes21.domain.ListItem
import com.example.notes21.domain.NotesService

class NotesViewModel : ViewModel() {

    private val _items = MutableLiveData<List<ListItem>>()
    val items: LiveData<List<ListItem>>
        get() = _items

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
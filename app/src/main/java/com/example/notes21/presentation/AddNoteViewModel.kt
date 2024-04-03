package com.example.notes21.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes21.data.ItemListRepositoryImpl
import com.example.notes21.domain.Group
import com.example.notes21.domain.Note
import com.example.notes21.domain.NotesService
import com.example.notes21.domain.usecases.AddItemUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ItemListRepositoryImpl(application)
    private val notesService = NotesService()

    private val addItemUseCase = AddItemUseCase(repository)

    fun addItem(title: String, text: String) {
        viewModelScope.launch {
            val newItem = if (title.isNotEmpty()) {
                if (text.isNotEmpty()) {
                    Note(title, text, LocalDate.now())
                } else Group(title)
            } else null

            newItem?.let { item ->
                addItemUseCase.addItem(item)
            }
        }

    }
}
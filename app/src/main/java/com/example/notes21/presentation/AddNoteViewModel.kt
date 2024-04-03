package com.example.notes21.presentation

import androidx.lifecycle.ViewModel
import com.example.notes21.domain.Group
import com.example.notes21.domain.Note
import com.example.notes21.domain.NotesService
import java.time.LocalDate

class AddNoteViewModel(private val notesService: NotesService): ViewModel() {

    fun addNote(title: String, text: String){
        val newItem = if (title.isNotEmpty()) {
            if (text.isNotEmpty()) {
                Note(title, text, LocalDate.now())
            } else Group(title)
        } else null

        newItem?.let { item ->
            notesService.addItem(item)
        }
    }
}
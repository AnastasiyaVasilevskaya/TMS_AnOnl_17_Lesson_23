package com.example.notes21


import android.app.Application
import com.example.notes21.domain.NotesService

class App: Application() {

    val notesService = NotesService()
}
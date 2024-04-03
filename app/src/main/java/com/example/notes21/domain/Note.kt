package com.example.notes21.domain

import java.time.LocalDate

data class Note(
    val title: String,
    val text: String,
    val date: LocalDate
) : ListItem
package com.example.notes21.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes21.data.Note
import com.example.notes21.databinding.NoteItemBinding

class NoteViewHolder(private val noteItemBinding: NoteItemBinding) :
    RecyclerView.ViewHolder(noteItemBinding.root) {

    fun bind(note: Note) {
        with(noteItemBinding) {
            noteTitle.text = note.title
            noteText.text = note.text
            noteDate.text = note.date.toString()
        }
    }

    companion object {
        fun from(parent: ViewGroup): NoteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = NoteItemBinding.inflate(layoutInflater, parent, false)
            return NoteViewHolder(binding)
        }
    }
}
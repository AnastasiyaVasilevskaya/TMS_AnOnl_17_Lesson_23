package com.example.notes21


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes21.data.Group
import com.example.notes21.data.ListItem
import com.example.notes21.data.Note
import com.example.notes21.data.NotesService
import java.lang.IllegalStateException

class NoteAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val notesService = NotesService()
    var items: List<ListItem> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item) {
            is Note -> NOTE_TYPE
            is Group -> GROUP_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == NOTE_TYPE) {
            NoteViewHolder.from(parent)
        } else {
            GroupViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (item is Note && holder is NoteViewHolder) {
            holder.bind(item)
        } else if (item is Group && holder is GroupViewHolder) {
            holder.bind(item)
        } else {
            throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    companion object {
        private const val NOTE_TYPE = 1
        private const val GROUP_TYPE = 2
    }
}
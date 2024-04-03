package com.example.notes21.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.notes21.databinding.NoteGroupBinding
import com.example.notes21.domain.Group

class GroupViewHolder(private val noteGroupBinding: NoteGroupBinding) :
    RecyclerView.ViewHolder(noteGroupBinding.root) {

    fun bind(group: Group) {
        noteGroupBinding.groupTitle.text = group.groupTitle
    }

    companion object {
        fun from(parent: ViewGroup): GroupViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = NoteGroupBinding.inflate(layoutInflater, parent, false)
            return GroupViewHolder(binding)
        }
    }
}
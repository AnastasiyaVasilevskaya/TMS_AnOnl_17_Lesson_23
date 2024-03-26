package com.example.notes21.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes21.data.Group
import com.example.notes21.data.ListItem
import com.example.notes21.data.Note
import java.lang.IllegalStateException

class NotesDiffCallBack(
    private val oldList: List<ListItem>,
    private val newList: List<ListItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition    //как без id?
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}

class NoteAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ListItem> = emptyList()
        set(newValue) {
            val diffCallback = NotesDiffCallBack(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
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
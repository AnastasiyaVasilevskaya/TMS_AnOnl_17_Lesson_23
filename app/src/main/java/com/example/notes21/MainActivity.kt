package com.example.notes21


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes21.data.Group
import com.example.notes21.data.ItemsListener
import com.example.notes21.data.Note
import com.example.notes21.data.NotesService
import com.example.notes21.databinding.ActivityMainBinding
import com.example.notes21.databinding.AddNoteDialogBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteAdapter


    private val notesService: NotesService
        get() = (applicationContext as App).notesService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.notes
        val items = notesService.getItems()
        noteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        binding.addNoteButton.setOnClickListener {
            showAddNoteDialog()
        }

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(notesService))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        notesService.addListener(itemsListener)
    }

    private val itemsListener: ItemsListener = {
        noteAdapter.items = it
    }

    override fun onDestroy() {
        super.onDestroy()
        notesService.removeListener(itemsListener)
    }

    private fun showAddNoteDialog() {
        val dialogBinding = AddNoteDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        val items = notesService.getItems()

        builder.setTitle("Добавить заметку")
            .setView(dialogBinding.root)
            .setPositiveButton("Добавить") { _, _ ->
                val title = dialogBinding.dialogTitle.text.toString()
                val text = dialogBinding.dialogText.text.toString()

                val newItem = if (title.isNotEmpty()) {
                    if (text.isNotEmpty()) {
                        Note(title, text, LocalDate.now())
                    } else Group(title)
                } else null
                newItem?.let { item ->
                    notesService.addItem(item)
                    noteAdapter.notifyItemInserted(items.size - 1)
                }
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
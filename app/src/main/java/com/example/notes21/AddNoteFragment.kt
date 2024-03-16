package com.example.notes21

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes21.data.Group
import com.example.notes21.data.Note
import com.example.notes21.data.NotesService
import com.example.notes21.databinding.FragmentAddNoteBinding
import java.time.LocalDate

class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null
    private lateinit var noteAdapter: NoteAdapter
    private val notesService: NotesService
        get() = (requireActivity().applicationContext as App).notesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        noteAdapter = NoteAdapter()
        val items = notesService.getItems()

        binding?.let { binding ->
            binding.addButton.setOnClickListener() {
                val title = binding.dialogTitle.text.toString()
                val text = binding.dialogText.text.toString()

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

            binding.cancelButton.setOnClickListener() {
                requireActivity().supportFragmentManager.popBackStack()
            }

        }
        return binding!!.root
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
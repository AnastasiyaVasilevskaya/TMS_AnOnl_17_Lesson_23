package com.example.notes21.presentation


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.notes21.App
import com.example.notes21.domain.NotesService
import com.example.notes21.presentation.adapter.NoteAdapter
import com.example.notes21.databinding.FragmentAddNoteBinding
import java.lang.RuntimeException

class AddNoteFragment : Fragment() {

    private var listener: OnAddButtonClickListener? = null
    private var binding: FragmentAddNoteBinding? = null
    private lateinit var noteAdapter: NoteAdapter
    private val viewModel: AddNoteViewModel by viewModels()
    private val notesService: NotesService
        get() = (requireActivity().applicationContext as App).notesService

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAddButtonClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnAddButtonClickListener")
        }
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
            binding.addButton.setOnClickListener {
                val title = binding.dialogTitle.text.toString()
                val text = binding.dialogText.text.toString()

                viewModel.addItem(title, text)

                noteAdapter.notifyItemInserted(items.size - 1)
                (requireActivity() as OnAddButtonClickListener).onItemButtonClicked()
            }

            binding.cancelButton.setOnClickListener {
                (requireActivity() as OnAddButtonClickListener).onItemButtonClicked()
            }
        }
        return binding!!.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }
}
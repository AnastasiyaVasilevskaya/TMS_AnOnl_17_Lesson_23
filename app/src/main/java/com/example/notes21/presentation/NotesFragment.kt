package com.example.notes21.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes21.App
import com.example.notes21.domain.NotesService
import com.example.notes21.presentation.adapter.NoteAdapter
import com.example.notes21.databinding.FragmentNotesBinding
import java.lang.RuntimeException

class NotesFragment : Fragment() {

    private var listener: OnAddButtonClickListener? = null
    private var binding: FragmentNotesBinding? = null
    private val viewModel: NotesViewModel by viewModels()
    private lateinit var adapter: NoteAdapter

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
        binding = FragmentNotesBinding.inflate(layoutInflater)

        val recyclerView = binding?.notes
        adapter = NoteAdapter()
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.items = it
        }

        binding?.addNoteButton?.setOnClickListener {
            showAddNoteFragment()
        }

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(viewModel))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        adapter.items = notesService.getItems()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.notes?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }

        binding?.addNoteButton?.setOnClickListener {
            (requireActivity() as OnAddButtonClickListener).onAddButtonClicked()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun showAddNoteFragment() {
        listener?.onAddButtonClicked()
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }
}
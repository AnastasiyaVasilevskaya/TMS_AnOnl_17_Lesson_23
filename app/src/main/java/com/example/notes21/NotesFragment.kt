package com.example.notes21

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes21.data.ItemsListener
import com.example.notes21.data.NotesService
import com.example.notes21.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {
    private lateinit var noteAdapter: NoteAdapter
    private var bindingFragment: FragmentNotesBinding? = null

    private val notesService: NotesService
        get() = (requireActivity().applicationContext as App).notesService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragment = FragmentNotesBinding.inflate(layoutInflater)
        noteAdapter = NoteAdapter()

        val recyclerView = bindingFragment?.notes

        recyclerView?.adapter = noteAdapter
        recyclerView?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        bindingFragment?.addNoteButton?.setOnClickListener {
            showAddNoteFragment()
        }

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(notesService))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        notesService.addListener(itemsListener)
        noteAdapter.items = notesService.getItems()
        return bindingFragment!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        notesService.removeListener(itemsListener)
        bindingFragment = null
        super.onDestroyView()
    }

    private fun showAddNoteFragment() {
        val addNoteFragment = AddNoteFragment()

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, addNoteFragment)
            .addToBackStack(null)
            .commit()
    }

    private val itemsListener: ItemsListener = {
        noteAdapter.items = it
    }
}
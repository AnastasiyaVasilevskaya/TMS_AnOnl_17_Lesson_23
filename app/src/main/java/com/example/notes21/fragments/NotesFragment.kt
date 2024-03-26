package com.example.notes21.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes21.App
import com.example.notes21.OnAddButtonClickListener
import com.example.notes21.SwipeToDeleteCallback
import com.example.notes21.adapter.NoteAdapter
import com.example.notes21.data.ItemsListener
import com.example.notes21.data.NotesService
import com.example.notes21.databinding.FragmentNotesBinding
import java.lang.RuntimeException

class NotesFragment : Fragment() {

    private var listener: OnAddButtonClickListener? = null
    private lateinit var noteAdapter: NoteAdapter
    private var bindingFragment: FragmentNotesBinding? = null

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
        listener?.onAddButtonClicked()
    }

    private val itemsListener: ItemsListener = {
        noteAdapter.items = it
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

}
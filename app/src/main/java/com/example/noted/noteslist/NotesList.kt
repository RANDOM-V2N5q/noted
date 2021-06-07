package com.example.noted.noteslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.MainActivityViewModel
import com.example.noted.R
import kotlinx.android.synthetic.main.notes_list_fragment.*


class NotesList : Fragment() {

    private lateinit var viewModel: NotesListViewModel
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(NotesListViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        viewModel.notes.observe(viewLifecycleOwner, Observer {
            noteAdapter.notifyDataSetChanged()
        })

        noteAdapter = NoteAdapter(viewModel.notes, mainViewModel)

        return inflater.inflate(R.layout.notes_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = notes_recyclerView.apply { adapter = noteAdapter }

        fab.setOnClickListener {
            mainViewModel.selectedNoteId = -1
            findNavController().navigate(R.id.action_notesList_to_noteDetails)
        }
    }
}
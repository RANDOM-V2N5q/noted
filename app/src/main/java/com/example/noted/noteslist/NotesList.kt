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
import com.example.noted.R
import com.example.noted.entity.Note
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.notes_list_fragment.*


class NotesList : Fragment() {

    private lateinit var viewModel: NotesListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO
        val temp = listOf(
            Note(1, "Ideas", "Create app with my recipes Create app with my recipes"),
            Note(2, "Remember about milk!", "Buy milk to coffee"),
            Note(3, "Passwords", "Login: admin\nPassword: admin Create app with my recipes"),
            Note(1, "Ideas", "Create app with my recipes"),
            Note(2, "Remember about milk!", "Buy milk to coffee Create app with my recipes Create app with my recipes"),
            Note(3, "Passwords", "Login: admin\nPassword: admin"),
            Note(1, "Ideas", "Create app with my recipes"),
            Note(2, "Remember about milk! Remember about milk!", "Buy milk to coffee"),
            Note(3, "Passwords", "Login: admin\nPassword: admin"),
            Note(1, "Ideas", "Create app with my recipes"),
            Note(2, "Remember about milk!", "Buy milk to coffee"),
            Note(3, "Passwords", "Login: admin\nPassword: admin Create app with my recipes")
        )

        viewModel = ViewModelProvider(this).get(NotesListViewModel::class.java)

        viewModel.getNotes(temp).observe(viewLifecycleOwner, Observer {
            noteAdapter.notifyDataSetChanged()
        })

        noteAdapter = NoteAdapter(viewModel.notes)

        return inflater.inflate(R.layout.notes_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = notes_recyclerView.apply { adapter = noteAdapter }

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_notesList_to_noteDetails)
        }
    }
}
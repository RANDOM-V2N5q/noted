package com.example.noted.noteslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noted.R
import com.example.noted.entity.Note
import kotlinx.android.synthetic.main.notes_list_fragment.*


class NotesList : Fragment() {

    private lateinit var viewModel: NotesListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var layoutManager: StaggeredGridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO
        val temp = listOf(
            Note(1, "Ideas", "Create app with my recipes"),
            Note(2, "Remember about milk!", "Buy milk to coffee"),
            Note(3, "Passwords", "Login: admin\nPassword: admin")
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

        layoutManager = StaggeredGridLayoutManager(
            2,
            LinearLayoutManager.VERTICAL
        ).apply {
            recyclerView.layoutManager = this
        }
    }
}
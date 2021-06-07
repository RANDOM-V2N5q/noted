package com.example.noted.noteslist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.MainActivityViewModel
import com.example.noted.R
import com.example.noted.database.entities.Note
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class NoteAdapter(private val list: LiveData<List<Note>>, private val viewModel: MainActivityViewModel) :
    RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    inner class NoteHolder(view: View) : RecyclerView.ViewHolder(view)

    private val colors = listOf("#F1CD96", "#F19696", "#B8DBFB", "#ABE1A6")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_note, parent, false)
        return NoteHolder(view)
    }

    override fun getItemCount() = list.value?.size ?: 0

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % colors.size]))

        val root = holder.itemView.findViewById<MaterialCardView>(R.id.root)
        val title = holder.itemView.findViewById<MaterialTextView>(R.id.title_textView)
        val content = holder.itemView.findViewById<MaterialTextView>(R.id.content_textView)

        val item = list.value?.get(position)!!

        title.text = item.title
        content.text = item.content

        root.setOnClickListener {
            viewModel.selectedNoteId = item.id
            it.findNavController().navigate(R.id.action_notesList_to_noteDetails)
        }
    }
}
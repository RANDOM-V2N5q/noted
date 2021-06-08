package com.example.noted.noteslist

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noted.MainActivityViewModel
import com.example.noted.R
import com.example.noted.database.entities.Note
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class NoteAdapter(
    private val list: LiveData<List<Note>>,
    private val viewModel: MainActivityViewModel,
    private val context: Context
) :
    RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    inner class NoteHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_note, parent, false)
        return NoteHolder(view)
    }

    override fun getItemCount() = list.value?.size ?: 0

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val colors = listOf(
            R.color.deep_champagne,
            R.color.red,
            R.color.uranian_blue,
            R.color.green
        )
        val color = ContextCompat.getColor(context, colors[position % colors.size])
        holder.itemView.setBackgroundColor(color)

        val title = holder.itemView.findViewById<MaterialTextView>(R.id.title_textView)
        val content = holder.itemView.findViewById<MaterialTextView>(R.id.content_textView)

        val item = list.value?.get(position)!!

        title.text = item.title
        content.text = item.content

        holder.itemView.setOnClickListener {
            viewModel.selectedNoteId = item.id
            it.findNavController().navigate(R.id.action_notesList_to_noteDetails)
        }
    }
}
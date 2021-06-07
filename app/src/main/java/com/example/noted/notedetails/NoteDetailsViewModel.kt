package com.example.noted.notedetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noted.database.AppDatabase
import com.example.noted.database.entities.Note
import com.example.noted.database.repositories.NoteRepository
import kotlinx.coroutines.launch

class NoteDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(AppDatabase.getDatabase(application).noteDao())

    var noteId = 0
    val title = MutableLiveData("Add title")
    val text = MutableLiveData("Write something...")

    fun  getNote(id: Int) {
        viewModelScope.launch {
            val note = noteRepository.oneNote(id)
            noteId = note.id
            title.value = note.title
            text.value = note.text
        }
    }

    fun createNote() {
        viewModelScope.launch {
            val note = Note(0, title.value!!, text.value!!)
            noteRepository.addNote(note)
        }
    }

    fun updateNote() {
        viewModelScope.launch {
            val note = Note(noteId, title.value!!, text.value!!)
            noteRepository.updateNote(note)
        }
    }
}
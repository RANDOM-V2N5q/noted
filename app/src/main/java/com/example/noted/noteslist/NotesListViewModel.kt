package com.example.noted.noteslist

import android.app.Application
import androidx.lifecycle.*
import com.example.noted.database.AppDatabase
import com.example.noted.database.repositories.NoteRepository
import com.example.noted.database.entities.Note
import kotlinx.coroutines.launch

class NotesListViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(AppDatabase.getDatabase(application).noteDao())

    lateinit var notes: LiveData<List<Note>>

    init {
        viewModelScope.launch {
            notes = noteRepository.allNotes()
        }
    }

}
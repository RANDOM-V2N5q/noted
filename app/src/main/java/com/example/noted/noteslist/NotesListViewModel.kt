package com.example.noted.noteslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noted.entity.Note

class NotesListViewModel : ViewModel() {

    var notes: MutableLiveData<List<Note>> = MutableLiveData()
    fun getNotes(list: List<Note>): LiveData<List<Note>> {
        notes.value = list
        return notes
    }
}
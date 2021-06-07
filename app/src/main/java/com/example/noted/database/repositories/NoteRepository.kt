package com.example.noted.database.repositories

import androidx.lifecycle.LiveData
import com.example.noted.database.daointerfaces.NoteDao
import com.example.noted.database.entities.Note

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    fun allNotes(): LiveData<List<Note>> {
        return noteDao.allNotes()
    }

    suspend fun oneNote(id: Int): Note {
        return noteDao.oneNote(id)
    }
}
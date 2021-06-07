package com.example.noted.database.repositories

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

    suspend fun allNotes(): List<Note> {
        return noteDao.allNotes()
    }

    suspend fun oneNote(id: Int): Note {
        return noteDao.oneNote(id)
    }
}
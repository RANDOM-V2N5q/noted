package com.example.noted.database.daointerfaces

import androidx.room.*
import com.example.noted.database.entities.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    suspend fun allNotes(): List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun oneNote(id: Int): Note
}
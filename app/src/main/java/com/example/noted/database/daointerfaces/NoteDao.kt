package com.example.noted.database.daointerfaces

import androidx.lifecycle.LiveData
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
    fun allNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun oneNote(id: Int): Note
}
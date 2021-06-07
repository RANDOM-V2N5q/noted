package com.example.noted.notedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteDetailsViewModel : ViewModel() {

    val title = MutableLiveData<String>("Add title")
    val text = MutableLiveData<String>("Write something...")

}
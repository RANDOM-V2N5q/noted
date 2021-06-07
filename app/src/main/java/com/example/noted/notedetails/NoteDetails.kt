package com.example.noted.notedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.noted.R
import com.example.noted.databinding.NoteDetailsFragmentBinding

class NoteDetails : Fragment() {

    private lateinit var viewModel: NoteDetailsViewModel
    private lateinit var binding: NoteDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(NoteDetailsViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.note_details_fragment,container,false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        return binding.root
    }

}
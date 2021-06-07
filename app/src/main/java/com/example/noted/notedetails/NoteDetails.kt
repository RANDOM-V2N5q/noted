package com.example.noted.notedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.noted.MainActivityViewModel
import com.example.noted.R
import com.example.noted.databinding.NoteDetailsFragmentBinding

class NoteDetails : Fragment() {

    private lateinit var viewModel: NoteDetailsViewModel
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var binding: NoteDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity()).get(NoteDetailsViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        if(mainViewModel.selectedNoteId != -1) {
            viewModel.getNote(mainViewModel.selectedNoteId)
        }

        binding = DataBindingUtil.inflate(inflater,R.layout.note_details_fragment,container,false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            if(viewModel.noteId == -1) {
                viewModel.createNote()
            }
            else {
                viewModel.updateNote()
            }

            findNavController().popBackStack()
        }
    }

}
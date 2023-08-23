package app.gratum.notesroomef.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.gratum.notesroomef.R
import app.gratum.notesroomef.data.model.NotesEf
import app.gratum.notesroomef.databinding.FragmentListBinding
import app.gratum.notesroomef.presentation.ViewModel
import app.gratum.notesroomef.tools.OnItemClick
import app.gratum.notesroomef.ui.AdapterNotes


class ListFragment : Fragment(R.layout.fragment_list), OnItemClick {

    private lateinit var binding: FragmentListBinding
    private lateinit var mNoteViewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inicializar
        mNoteViewModel = ViewModelProvider(this)[ViewModel::class.java]
        binding = FragmentListBinding.bind(view)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        mNoteViewModel.readAllNotes.observe(viewLifecycleOwner) { noteIt ->
            val adapter = AdapterNotes(noteIt, this)
            binding.rvList.adapter = adapter
            binding.rvList.layoutManager = LinearLayoutManager(context)
            adapter.setData(noteIt)
        }
    }

    override fun setOnItemClickListener(notes: NotesEf) {
        val titleUp = "EfraUpdate"
        val descripUp = "DescripUpdate"

        val notaUpdate = NotesEf(notes.id, titleUp, descripUp)


        mNoteViewModel.update(notaUpdate)
    }


    override fun onDeleteClick(notes: NotesEf) {
        mNoteViewModel.deleteData(notes)
    }
}
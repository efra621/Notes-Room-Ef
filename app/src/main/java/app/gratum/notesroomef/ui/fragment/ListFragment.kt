package app.gratum.notesroomef.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.gratum.notesroomef.R
import app.gratum.notesroomef.data.model.NotesEf
import app.gratum.notesroomef.databinding.FragmentListBinding
import app.gratum.notesroomef.presentation.SharedViewModel
import app.gratum.notesroomef.presentation.ViewModel
import app.gratum.notesroomef.tools.OnItemClick
import app.gratum.notesroomef.ui.AdapterNotes


class ListFragment : Fragment(R.layout.fragment_list), OnItemClick {

    private lateinit var binding: FragmentListBinding
    private lateinit var mNoteViewModel: ViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inicializar
        mNoteViewModel = ViewModelProvider(this)[ViewModel::class.java]
        binding = FragmentListBinding.bind(view)

        binding.button.setOnClickListener {
            sharedViewModel.updateSharedNote(null)
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

//        //Forma de pasar los datos
//        sharedViewModel.titleUp.value = notes.noteTitle
//        sharedViewModel.descripUp.value = notes.noteDescription
//        sharedViewModel.idSVM.value = notes.id.toString()

        sharedViewModel.updateSharedNote(notes)
        findNavController().navigate(R.id.action_listFragment_to_addFragment)
    }

    override fun onDeleteClick(notes: NotesEf) {
        mNoteViewModel.deleteData(notes)
    }
}
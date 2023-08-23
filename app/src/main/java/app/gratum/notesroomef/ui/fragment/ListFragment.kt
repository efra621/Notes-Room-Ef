package app.gratum.notesroomef.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.gratum.notesroomef.R
import app.gratum.notesroomef.databinding.FragmentListBinding
import app.gratum.notesroomef.presentation.ViewModel
import app.gratum.notesroomef.ui.AdapterNotes


class ListFragment : Fragment(R.layout.fragment_list) {

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

        binding.button2.setOnClickListener {
            mNoteViewModel.readAllNotes.observe(viewLifecycleOwner) {
                for (note in it) {
                    val taskListText = StringBuilder()
                    taskListText.append("${note.noteTitle} - ${note.noteDescription}\n")
                    Log.d("???", "${note.noteTitle} - ${note.noteDescription}\n")
                }
            }

        }

        mNoteViewModel.readAllNotes.observe(viewLifecycleOwner) {
            val adapter = AdapterNotes(it)
            binding.rvList.adapter = adapter
            binding.rvList.layoutManager = LinearLayoutManager(context)
            adapter.setData(it)
        }


    }
}
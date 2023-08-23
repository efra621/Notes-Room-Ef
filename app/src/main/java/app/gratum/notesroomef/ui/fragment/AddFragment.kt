package app.gratum.notesroomef.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import app.gratum.notesroomef.R
import app.gratum.notesroomef.data.model.NotesEf
import app.gratum.notesroomef.databinding.FragmentAddBinding
import app.gratum.notesroomef.databinding.FragmentListBinding
import app.gratum.notesroomef.presentation.ViewModel


class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mNoteViewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inicializar
        mNoteViewModel = ViewModelProvider(this)[ViewModel::class.java]

        binding = FragmentAddBinding.bind(view)

        binding.btnGuardarNota.setOnClickListener {
            insertarDatosDatabase()
        }

    }

    private fun insertarDatosDatabase() {
        val titulo = binding.etTitulo.text.toString()
        val descripcion = binding.etNota.text.toString()

        val nota = NotesEf(0, noteTitle = titulo, noteDescription = descripcion)
        mNoteViewModel.addData(nota)

        Toast.makeText(requireContext(), "Añadido con exito", Toast.LENGTH_LONG).show()
    }

}
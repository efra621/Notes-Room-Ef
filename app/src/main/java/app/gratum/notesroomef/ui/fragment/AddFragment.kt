package app.gratum.notesroomef.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import app.gratum.notesroomef.R
import app.gratum.notesroomef.data.model.NotesEf
import app.gratum.notesroomef.databinding.FragmentAddBinding
import app.gratum.notesroomef.databinding.FragmentListBinding
import app.gratum.notesroomef.presentation.SharedViewModel
import app.gratum.notesroomef.presentation.ViewModel


class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mNoteViewModel: ViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Inicializar
        mNoteViewModel = ViewModelProvider(this)[ViewModel::class.java]
        binding = FragmentAddBinding.bind(view)

//        //Metodo para llamar un argumento
//        sharedViewModel.titleUp.observe(viewLifecycleOwner) { title ->}

        sharedViewModel.sharedNote.observe(viewLifecycleOwner) { note ->
            // Verificar si hay datos en sharedNote
            if (note != null) {
                binding.etTitulo.setText(note.noteTitle)
                binding.etNota.setText(note.noteDescription)
                // Los datos provienen de sharedViewModel, realiza actualización
                binding.btnGuardarNota.text = "Actualizar"
                binding.btnGuardarNota.setOnClickListener {
                    actualizarDatosDatabase(note)
                }
            } else {
                // Los datos son nuevos, realiza inserción
                binding.btnGuardarNota.text = "Guardar"
                binding.btnGuardarNota.setOnClickListener {
                    insertarDatosDatabase()
                }
            }
        }
    }

    private fun insertarDatosDatabase() {
        val titulo = binding.etTitulo.text.toString()
        val descripcion = binding.etNota.text.toString()

        val nota = NotesEf(0, noteTitle = titulo, noteDescription = descripcion)
        mNoteViewModel.addData(nota)

        Log.d("???", "GuardarDatos")
        Toast.makeText(requireContext(), "Añadido con exito", Toast.LENGTH_LONG).show()
    }

    private fun actualizarDatosDatabase(noteUpdate: NotesEf) {
        val titulo = binding.etTitulo.text.toString()
        val descripcion = binding.etNota.text.toString()

        val notesUp = NotesEf(noteUpdate.id, titulo, descripcion)

        mNoteViewModel.update(notesUp)
        
        Toast.makeText(requireContext(), "Actualizado con exito", Toast.LENGTH_LONG).show()
    }
}


package app.gratum.notesroomef.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.gratum.notesroomef.data.DatabaseNotesEf
import app.gratum.notesroomef.data.model.NotesEf
import app.gratum.notesroomef.repository.RepositoryNotesEf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {

    private val repository : RepositoryNotesEf
    val readAllNotes: LiveData<List<NotesEf>>

    init {
        val dao = DatabaseNotesEf.getDatabase(application).dao()
        repository = RepositoryNotesEf(dao)

        readAllNotes = repository.readAllNotes
    }

    fun addData(notes: NotesEf){
        viewModelScope.launch(Dispatchers.IO){
            repository.addData(notes)
        }
    }

    fun update(notes: NotesEf){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(notes)
        }
    }

    fun deleteData(notes: NotesEf){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(notes)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }

}
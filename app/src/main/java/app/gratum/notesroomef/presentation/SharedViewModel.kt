package app.gratum.notesroomef.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import app.gratum.notesroomef.data.model.NotesEf

class SharedViewModel(application: Application) : ViewModel(application){

    private val _sharedNote = MutableLiveData<NotesEf?>()
    val sharedNote: MutableLiveData<NotesEf?> get() = _sharedNote

    fun updateSharedNote(newNote: NotesEf?) {
        _sharedNote.value = newNote
    }

    val titleUp: MutableLiveData<String> = MutableLiveData()
    val descripUp: MutableLiveData<String> = MutableLiveData()
    val idSVM: MutableLiveData<String> = MutableLiveData()
}
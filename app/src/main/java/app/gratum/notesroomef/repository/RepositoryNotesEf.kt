package app.gratum.notesroomef.repository

import android.util.Log
import androidx.lifecycle.LiveData
import app.gratum.notesroomef.data.NotesEfDao
import app.gratum.notesroomef.data.model.NotesEf

class RepositoryNotesEf (private val daoNotes: NotesEfDao){

    suspend fun addData(notes: NotesEf){
        Log.d("???", "addDataRepositoryNotesEf")
        daoNotes.addData(notes)
    }

    val readAllNotesRepo: LiveData<List<NotesEf>> = daoNotes.readAllNotes()

    suspend fun updateData(notes: NotesEf){
        Log.d("???", "updateDataRepository")
        daoNotes.updateNote(notes)
    }

    suspend fun deleteData(notes: NotesEf){
        daoNotes.delete(notes)
    }

    suspend fun deleteAllData(){
        daoNotes.deleteAllNotes()
    }

}
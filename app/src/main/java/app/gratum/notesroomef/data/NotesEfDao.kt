package app.gratum.notesroomef.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.gratum.notesroomef.data.model.NotesEf

//Y que es dao?
//Data access object
//Define los métodos que acceden a la base de datos,
//para insertar, actualizar, borrar o consultar datos CRUD

@Dao
interface NotesEfDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(notes: NotesEf)

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun readAllNotes(): LiveData<List<NotesEf>>

    @Update
    suspend fun updateNote(notes: NotesEf)

    @Delete
    suspend fun delete(notes: NotesEf)

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

}
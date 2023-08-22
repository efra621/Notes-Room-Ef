package app.gratum.notesroomef.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//Que es @Entity = representa una tabla dentro de la base de datos (Necesarios conocimientos minimos
//de sql)
@Entity(tableName = "notes")
data class NotesEf(                        //Porq una data class:
                                            // clases que sirven como contenedores de datos
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteDescription: String
)
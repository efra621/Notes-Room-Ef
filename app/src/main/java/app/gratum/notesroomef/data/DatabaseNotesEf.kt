package app.gratum.notesroomef.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.gratum.notesroomef.data.model.NotesEf


@Database(entities = [NotesEf::class] ,version = 1, exportSchema = false)
abstract class DatabaseNotesEf : RoomDatabase(){

    //Devuelve una implementacion de la clase Dao
    abstract fun dao(): NotesEfDao

    //Singleton
    companion object{
        @Volatile
        private var INTANCE : DatabaseNotesEf? = null

        fun getDatabase(context: Context) : DatabaseNotesEf {
            val instance = INTANCE

            if (instance != null){
                return instance
            }
            //Asegura que solo un hilo a la vez puede acceder a este bloque de código
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseNotesEf::class.java,
                    "notes"
                ).build()

                INTANCE = instance
                return instance
            }
        }
    }
}
package app.gratum.notesroomef.tools

import app.gratum.notesroomef.data.model.NotesEf


interface OnItemClick {

    fun setOnItemClickListener(notes: NotesEf)

    fun onDeleteClick(notes: NotesEf)

}
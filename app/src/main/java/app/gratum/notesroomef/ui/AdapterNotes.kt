package app.gratum.notesroomef.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.gratum.notesroomef.R
import app.gratum.notesroomef.data.model.NotesEf
import app.gratum.notesroomef.databinding.ItemNotesBinding

class AdapterNotes(private val list: List<NotesEf>) : RecyclerView.Adapter<AdapterNotes.ViewHolder>() {

    private var notes = emptyList<NotesEf>()
    var OnItemClick : ((NotesEf) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemNotesBinding.bind(itemView)

        fun render(notesEf: NotesEf){
            binding.tvTitle.text = notesEf.noteTitle
            binding.tvDescription.text = notesEf.noteDescription
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = notes[position]

        holder.render(list[position])

//        holder.binding.ivDeleteDojo.setOnClickListener {
//            listener?.onDeleteClick(list[position])
//        }

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setData(newNotes: List<NotesEf>){
        this.notes = newNotes
        notifyDataSetChanged()
    }
}
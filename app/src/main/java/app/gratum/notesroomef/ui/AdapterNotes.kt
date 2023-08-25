package app.gratum.notesroomef.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.gratum.notesroomef.R
import app.gratum.notesroomef.data.model.NotesEf
import app.gratum.notesroomef.databinding.ItemNotesBinding
import app.gratum.notesroomef.tools.OnItemClick

class AdapterNotes(
    private val list: List<NotesEf>,
    private val listener: OnItemClick?
) : RecyclerView.Adapter<AdapterNotes.ViewHolder>() {

    private var notes = emptyList<NotesEf>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemNotesBinding.bind(itemView)

        fun render(notesEf: NotesEf, listener: OnItemClick?){
            binding.tvTitle.text = notesEf.noteTitle
            binding.tvDescription.text = notesEf.noteDescription

            binding.imvDelete.setOnClickListener{
                listener?.onDeleteClick(notesEf)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = notes[position]

        holder.render(currentItem, listener)

        holder.itemView.setOnClickListener {
            listener?.setOnItemClickListener(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setData(newNotes: List<NotesEf>){
        this.notes = newNotes
        notifyDataSetChanged()
    }
}
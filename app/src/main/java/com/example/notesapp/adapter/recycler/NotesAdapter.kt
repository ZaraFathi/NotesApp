package com.example.notesapp.adapter.recycler

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.model.RecyclerNotesModel
import com.example.notesapp.databinding.ListItemNotesBinding

class NotesAdapter(
    private val context: Activity,
    private var allData: ArrayList<RecyclerNotesModel>
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotesViewHolder(
            ListItemNotesBinding.inflate(context.layoutInflater, parent, false)
        )

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.setData(allData[position])
    }

    override fun getItemCount() = allData.size


    inner class NotesViewHolder(
        private val bindind: ListItemNotesBinding
    ) : RecyclerView.ViewHolder(bindind.root) {
        fun setData(data: RecyclerNotesModel) {
            bindind.txtTitleNotes.text = data.title
            bindind.imgDeleteNotes.setOnClickListener { }

        }

}
      fun changeData (data:ArrayList<RecyclerNotesModel>){
          if(data.size>allData.size){
              allData=data
              notifyItemInserted(allData.size)

          }
      }



    }



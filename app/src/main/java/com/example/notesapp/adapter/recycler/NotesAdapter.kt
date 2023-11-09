package com.example.notesapp.adapter.recycler

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.dao.local.db.DBHelper
import com.example.notesapp.data.model.RecyclerNotesModel
import com.example.notesapp.databinding.ListItemNotesBinding
import com.example.notesapp.ui.AddNotesActivity
import db.dao.NotesDao

class NotesAdapter(
    private val context: Activity,
    private var allData: ArrayList<RecyclerNotesModel>,
    private val dao: NotesDao

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

            bindind.imgDeleteNotes.setOnClickListener {
             AlertDialog.Builder(ContextThemeWrapper(context,R.style.CustomAlertDialog))
                 .setTitle("حدف")
                 .setMessage("ایامیخواید یادداشت به سطل زباله منتقل شود؟")
                 .setIcon(R.drawable.ic_delete)
                 .setNegativeButton("بله") {_,_ ->
                     val result =dao.editNotes(data.id,DBHelper.TRUE_STATE)
                     if (result){
                         showText("یادداشت به سطل زباله منتقل شد")
                         allData.removeAt(adapterPosition)
                         notifyItemInserted(adapterPosition)
                     }else
                         showText("عملیات با مشکل مواجه شد")
                 }
                 .setPositiveButton("خیر"){dialog, _ -> dialog.dismiss()  }
                 .create()
                 .show()
            }
              bindind.root.setOnClickListener {
               val intent = Intent (context,AddNotesActivity::class.java)
                intent.putExtra("notesId",data.id)
                context.startActivity(intent)
            }
        }
}
      fun changeData (data:ArrayList<RecyclerNotesModel>){
          if(data.size>allData.size){
              allData=data
              notifyItemInserted(allData.size)
          }
      }
     private fun showText(text:String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
     }


    }



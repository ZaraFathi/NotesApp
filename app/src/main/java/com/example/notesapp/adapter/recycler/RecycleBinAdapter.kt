package com.example.notesapp.adapter.recycler

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.dao.local.db.DBHelper
import com.example.notesapp.data.model.RecyclerNotesModel
import com.example.notesapp.databinding.ListItemNotesBinding
import com.example.notesapp.databinding.ListItemRecyclerBinBinding
import com.example.notesapp.ui.AddNotesActivity
import db.dao.NotesDao


class  RecycleBinAdapter (
    private val context: Context,
    private val dao: NotesDao

) : RecyclerView.Adapter<RecycleBinAdapter.RecycleViewHolder>() {
    private val allData =
        dao.getNotesForRecycler(DBHelper.TRUE_STATE)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecycleViewHolder(
            ListItemRecyclerBinBinding.inflate(LayoutInflater.from(context), parent, false)
        )

    override fun onBindViewHolder(holder:RecycleViewHolder , position: Int) {
        holder.setData(allData[position])
    }

    override fun getItemCount() = allData.size
    inner class RecycleViewHolder(
        private val binding: ListItemRecyclerBinBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: RecyclerNotesModel) {
            binding.txtTitleNotes.text = data.title

            binding.imgDeleteNotes.setOnClickListener {
                AlertDialog.Builder(ContextThemeWrapper(context, R.style.CustomAlertDialog))
                    .setTitle("حدف")
                    .setMessage("ایامیخواید یادداشت برای همیشه حذف شود؟")
                    .setIcon(R.drawable.ic_delete)
                    .setNegativeButton("خیر") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton("بله") { _, _ ->
                        val result = dao.deleteNotes(data.id)
                        if (result) {
                            showText("یادداشت حذف شد")
                            allData.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                        } else
                            showText("عملیات با مشکل مواجه شد")
                    }
                    .create()
                    .show()
            }

            binding.imgRestoreNotes.setOnClickListener {
                AlertDialog.Builder(ContextThemeWrapper(context, R.style.CustomAlertDialog))
                    .setTitle("حدف یادداشت")
                    .setMessage("ایامیخواید یادداشت بازگردانی شود؟")
                    .setIcon(R.drawable.ic_delete)
                    .setNegativeButton("خیر") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton("بله") { _, _ ->
                        val result = dao.editNotes(data.id, DBHelper.FALSE_STATE)
                        if (result) {
                            showText("یادداشت بازگردانی شد")
                            allData.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                        } else
                            showText("عملیات با مشکل مواجه شد")
                    }
                    .setPositiveButton("خیر") { _,_ ->}
                    .create()
                    .show()
            }

        }
    }
    private fun showText(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}

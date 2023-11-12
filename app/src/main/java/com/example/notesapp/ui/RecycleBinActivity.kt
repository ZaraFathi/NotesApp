package com.example.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.notesapp.R
import com.example.notesapp.adapter.recycler.NotesAdapter
import com.example.notesapp.adapter.recycler.RecycleBinAdapter
import com.example.notesapp.data.dao.local.db.DBHelper
import com.example.notesapp.databinding.ActivityRecycleBinBinding
import db.dao.NotesDao


class RecycleBinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecycleBinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycleBinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
    }

    private fun initRecycler() {
        val dao = NotesDao(DBHelper(this))
        val adapter = RecycleBinAdapter(this, dao)
        binding.recyclerNotes.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerNotes.adapter = adapter
    }

}

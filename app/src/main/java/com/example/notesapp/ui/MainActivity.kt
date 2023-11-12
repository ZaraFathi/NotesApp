package com.example.notesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.adapter.recycler.NotesAdapter
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.data.dao.local.db.DBHelper
import db.dao.NotesDao

class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding
    private lateinit var dao: NotesDao
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        binding.imgAddNotes.setOnClickListener {
            val intent = Intent(this, AddNotesActivity::class.java)
            intent .putExtra("nowNotes",true)
            startActivity(intent)
        }
        binding.txtRecycleBin.setOnClickListener {
            val intent=Intent(this,RecycleBinActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onStart() {
        super.onStart()
        val data=dao.getNotesForRecycler(DBHelper.FALSE_STATE)
        adapter.changeData(data)
    }


  private fun initRecycler(){
      dao =NotesDao(DBHelper(this))
      adapter= NotesAdapter( this,dao)
      binding.recyclerNotes.layoutManager =
          LinearLayoutManager( this,RecyclerView.VERTICAL, false)
      binding.recyclerNotes.adapter =adapter
  }
}

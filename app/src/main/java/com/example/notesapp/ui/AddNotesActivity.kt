package com.example.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import com.example.notesapp.databinding.ActivityAddNotesBinding
import com.example.notesapp.utils.persiandate
import com.example.notesapp.data.dao.local.db.DBHelper
import db.dao.NotesDao
import db.model.DBNotesModel

class AddNotesActivity() : AppCompatActivity(), Parcelable {

    private lateinit var binding: ActivityAddNotesBinding

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddNotesActivity> {
        override fun createFromParcel(parcel: Parcel): AddNotesActivity {
            return AddNotesActivity(parcel)
        }

        override fun newArray(size: Int): Array<AddNotesActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = NotesDao(DBHelper(this))

        binding.btnSave.setOnClickListener {
            val title = binding.edtTitleNotes.text.toString()
            val detail = binding.edtDetailNotes.text.toString()
            if (title.isNotEmpty()) {
                val notes=DBNotesModel(0,title,detail, DBHelper.FALSE_STATE,getDate())
                val result=dao.saveNotes(notes)
                if(result){
                    showText("یاداشت باموقفیت دخیره شد")
                    finish()
                }
                else
                    showText("خطا در ذخیره سازی یاداشت")
        }
            else
                showText("عنوان نمیتواند خالی باشد")
    }
        binding.btnCancel.setOnClickListener { finish() }
}
    private fun getDate():String{

        //بااستفاده از persiandateتاریخ شمسی وساعت کنونی را بدست می اوریم
        val persiandate= persiandate()
        //این کد تاریخ شمسی فعلی رابه رشته تبدیل میکند
        val currenDate="${ persiandate.year}/${ persiandate.month}/${ persiandate.day}"
        //این کد ساعت ودقیقه وثانیه فعلی رابه رشته تبدیل میکند
        val currenTime="${ persiandate.hour}:${ persiandate.min}:${ persiandate.second}"
        //دراینحاساعت وتاریخ رابه هم چسپانده و return میکنیم
        return "$currenDate | $currenTime"

    }
    private fun showText(text:String){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
}

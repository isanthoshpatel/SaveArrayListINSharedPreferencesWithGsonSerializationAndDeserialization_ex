package com.example.savearraylistinsharedpreferenceswithgsonserializationanddeserialization

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    var arrayList: ArrayList<CardViewData>? = null
    var recyclerView: RecyclerView? = null
    var adaptor: ExampleAdaptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayList = ArrayList()
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        bt_show.setOnClickListener {

            arrayList?.add(CardViewData(et_title.text.toString(), et_description.text.toString()))
            adaptor = ExampleAdaptor(this, arrayList!!)
            recyclerView?.adapter = adaptor

        }

        bt_save.setOnClickListener {
            if (arrayList != null) {
                var jsonArraylist = Gson().toJson(arrayList)
                getSharedPreferences("sp", Context.MODE_PRIVATE).edit()
                    .putString("jsonArraylist", jsonArraylist)
                    .apply()
                Toast.makeText(this,"saved",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"please enter title,description",Toast.LENGTH_LONG).show()
            }
        }
        bt_load.setOnClickListener {

            var jsonArraylist =
                getSharedPreferences("sp", Context.MODE_PRIVATE)
                    .getString("jsonArraylist", null)

            var token = object : TypeToken<ArrayList<CardViewData>>() {}.type

            var savedArrayList = Gson().fromJson<ArrayList<CardViewData>>(jsonArraylist, token)

            adaptor = ExampleAdaptor(this, savedArrayList)
            recyclerView?.adapter = adaptor
        }


    }//onCreate

}//MainActivity

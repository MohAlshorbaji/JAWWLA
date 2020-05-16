package com.example.jawwla.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jawwla.Adapters.MyListAdapter
import com.example.jawwla.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

        val places = arrayOf<String>(
            "اماكن دينية",
            "أماكن ترفيهية",
            "أماكن تاريخية",
            "أماكن للمأكولات (مطاعم)",
            "أماكن شعبية"
        )


        val image = arrayOf<Int>(
            R.drawable.quds,
            R.drawable.tarfeeh,
            R.drawable.history,
            R.drawable.food,
            R.drawable.shaab
        )

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val myListAdapter = MyListAdapter(this,places,image)
            listView.adapter = myListAdapter

            listView.setOnItemClickListener(){adapterView, view, position, id ->
                val itemAtPos = adapterView.getItemAtPosition(position)
                val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
            }
        }
    }



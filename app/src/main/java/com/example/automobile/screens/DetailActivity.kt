package com.example.automobile.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.automobile.MyAdapter
import com.example.automobile.data.CarData
import com.example.automobile.databinding.DetailRecActivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DetailActivity:AppCompatActivity() {
    private lateinit var binding: DetailRecActivityBinding
    private lateinit var dataList: ArrayList<CarData>
    private lateinit var adapter: MyAdapter
    var databaseReference: DatabaseReference? = null
    private var eventListener:ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DetailRecActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this,1)
        binding.recyclerView.layoutManager = gridLayoutManager

        dataList = ArrayList()
        adapter = MyAdapter(this,dataList)
        binding.recyclerView.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("Car Information")

        eventListener = databaseReference!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (itemSnapshot in snapshot.children){
                    val dataClass = itemSnapshot.getValue(CarData::class.java)
                    if (dataClass!=null){
                        dataList.add(dataClass)
                    }
                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
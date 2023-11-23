package com.example.automobile.screens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.automobile.databinding.SearchActivityBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchActivity:AppCompatActivity() {
    private lateinit var binding: SearchActivityBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= SearchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener{
            val searchCar: String = binding.editText.text.toString()
            if (searchCar.isNotEmpty()){
                readData(searchCar)
            }else{
                Toast.makeText(this, "Fill the car number", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(carNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Car Information")
        databaseReference.child(carNumber).get().addOnSuccessListener{
            if (it.exists()){
                val carModel = it.child("carName").value
                val carMark = it.child("carModel").value
                val carYear = it.child("carYear").value
                val carColor = it.child("carColor").value
                val carEngine = it.child("carEngine").value
                Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()
                binding.editText.text.clear()
                binding.readModel.text = carModel.toString()
                binding.readMark.text = carMark.toString()
                binding.readYear.text = carYear.toString()
                binding.readColor.text = carColor.toString()
                binding.readEngine.text = carEngine.toString()
            }else{
                Toast.makeText(this, "Result not Found", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}
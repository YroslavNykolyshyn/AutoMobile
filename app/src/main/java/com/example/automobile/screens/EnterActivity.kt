package com.example.automobile.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.automobile.data.CarData
import com.example.automobile.databinding.EnterActivityBinding

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EnterActivity:AppCompatActivity() {
    private lateinit var binding: EnterActivityBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= EnterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveBtn.setOnClickListener{
                val carMark =  binding.NameCar.text.toString()
                val carModel = binding.NameModel.text.toString()
                val carYear =  binding.Year.text.toString()
                val carColor = binding.Color.text.toString()
                val carEngine =binding.Engine.text.toString()
                val carVehicleNumber=binding.vehicleNumber.text.toString()
                val carImg =  binding.carImg.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Car Information")
            val carData = CarData(carMark,carModel,carYear,carColor,carEngine,carVehicleNumber,carImg)
            databaseReference.child(carVehicleNumber).setValue(carData).addOnSuccessListener{
                binding.NameCar.text.clear()
                binding.NameModel.text.clear()
                binding.Year.text.clear()
                binding.Color.text.clear()
                binding.Engine.text.clear()
                binding.vehicleNumber.text.clear()
                binding.carImg.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, OpenActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
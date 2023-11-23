package com.example.automobile.screens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.automobile.databinding.UpdateActivityBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity:AppCompatActivity() {
    private lateinit var binding:UpdateActivityBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpdateActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener{
            val updateNameCar = binding.updateNameCar.text.toString()
            val updateNameModel = binding.updateNameModel.text.toString()
            val updateYear = binding.updateYear.text.toString()
            val updateEngine = binding.updateEngine.text.toString()
            val updateColor = binding.updateColor.text.toString()
             updateData(updateNameCar,updateNameModel,updateYear,updateColor,updateEngine)
        }
    }
   private fun updateData(carName: String, carModel:String, carYear:String,carColor:String,carEngine:String){
    databaseReference = FirebaseDatabase.getInstance().getReference("Car Information")
       val carInfo = mapOf<String, String>("carName" to carName, carModel to "carModel", carYear to "carYear", carEngine to "carEngine", carColor to "carColor")
    databaseReference.child(carName).updateChildren(carInfo).addOnSuccessListener {
        binding.updateNameCar.text.clear()
        binding.updateNameModel.text.clear()
        binding.updateYear.text.clear()
        binding.updateEngine.text.clear()
        binding.updateColor.text.clear()
        Toast.makeText(this,"Update Data",Toast.LENGTH_SHORT).show()
    }.addOnFailureListener{
        Toast.makeText(this,"Update Data Failed",Toast.LENGTH_SHORT).show()
    }
    }
}
package com.example.automobile.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.automobile.R

class OpenActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.open_activity)

        val open1 = findViewById<View>(R.id.open1)
        open1.setOnClickListener(){
            val intent = Intent(this, EnterActivity::class.java)
            startActivity(intent)
        }
        val open2 = findViewById<View>(R.id.open2)
        open2.setOnClickListener(){
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        val open3 = findViewById<View>(R.id.carDetail)
        open3.setOnClickListener(){
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
        val open4 = findViewById<View>(R.id.updateCar)
        open4.setOnClickListener(){
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }
    }
}
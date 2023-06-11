package com.example.discover.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.discover.R
import com.example.discover.utilities.ServiceChecker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceChecker = ServiceChecker(this)
        if(serviceChecker.isServicesOK()) {
            val intent = Intent(this, MapActivity::class.java )
            startActivity(intent)
        }
    }
}
package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adminButton = findViewById<Button>(R.id.admin)
        val customerButton = findViewById<Button>(R.id.customer)

        adminButton.setOnClickListener {
            val intent = Intent(applicationContext, AdminLogin::class.java)
            startActivity(intent)
        }

        customerButton.setOnClickListener {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
        }
    }
}

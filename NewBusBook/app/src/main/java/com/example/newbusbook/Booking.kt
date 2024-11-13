package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Booking : AppCompatActivity() {

    private lateinit var btnView: Button
    private lateinit var myDB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        btnView = findViewById(R.id.btnview)
        val back = findViewById<Button>(R.id.button02)
        myDB = DBHelper(this)

        btnView.setOnClickListener {
            val intent = Intent(this@Booking, ViewListContents::class.java)
            startActivity(intent)
        }

        back.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

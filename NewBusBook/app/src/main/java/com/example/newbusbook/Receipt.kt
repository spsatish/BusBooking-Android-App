package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Receipt : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        val busid = findViewById<EditText>(R.id.id)
        val from = findViewById<EditText>(R.id.from)
        val to = findViewById<EditText>(R.id.to)
        val dt = findViewById<EditText>(R.id.date)
        val seats = findViewById<EditText>(R.id.seats)
        val addbus = findViewById<Button>(R.id.addbus)
        val dbHelper = DBHelper(this)

        addbus.setOnClickListener {
            val id = busid.text.toString()
            val departure = from.text.toString()
            val arrival = to.text.toString()
            val date = dt.text.toString()
            val totalSeats = seats.text.toString()

            if (id.isEmpty() || departure.isEmpty() || arrival.isEmpty() || date.isEmpty() || totalSeats.isEmpty()) {
                Toast.makeText(this@Receipt, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                val checkId = dbHelper.checkId(id)
                if (checkId) {
                    val insert = dbHelper.insertBus(id, departure, arrival, date, totalSeats)
                    if (insert) {
                        Toast.makeText(this@Receipt, "Bus has been added", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, Booking::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@Receipt, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Receipt, "ID already exists", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

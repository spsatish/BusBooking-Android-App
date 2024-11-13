package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddBus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addbus)

        val busId = findViewById<EditText>(R.id.id)
        val from = findViewById<EditText>(R.id.from)
        val to = findViewById<EditText>(R.id.to)
        val date = findViewById<EditText>(R.id.date)
        val seats = findViewById<EditText>(R.id.seats)
        val addBusButton = findViewById<Button>(R.id.addbus)
        val db = DBHelper(this)

        addBusButton.setOnClickListener {
            val id = busId.text.toString()
            val departure = from.text.toString()
            val arrival = to.text.toString()
            val dateStr = date.text.toString()
            val totalSeats = seats.text.toString()

            if (id.isEmpty() || departure.isEmpty() || arrival.isEmpty() || dateStr.isEmpty() || totalSeats.isEmpty()) {
                Toast.makeText(this@AddBus, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                val checkId = db.checkId(id)
                if (!checkId) {
                    val insert = db.insertBus(id, departure, arrival, dateStr, totalSeats)
                    if (insert) {
                        Toast.makeText(this@AddBus, "Bus has been added", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, AdminPanel::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@AddBus, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@AddBus, "ID already exists", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}

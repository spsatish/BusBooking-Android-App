package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateBus : AppCompatActivity() {

    private lateinit var DB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatebus)

        val busid: EditText = findViewById(R.id.id)
        val from: EditText = findViewById(R.id.from)
        val to: EditText = findViewById(R.id.to)
        val dt: EditText = findViewById(R.id.date)
        val back: Button = findViewById(R.id.button06)
        val seats: EditText = findViewById(R.id.seats)
        val updateBus: Button = findViewById(R.id.updatebus)

        DB = DBHelper(this)

        updateBus.setOnClickListener {
            val id = busid.text.toString()
            val departure = from.text.toString()
            val arrival = to.text.toString()
            val date = dt.text.toString()
            val totalSeats = seats.text.toString()

            if (id.isEmpty() || departure.isEmpty() || arrival.isEmpty() || date.isEmpty() || totalSeats.isEmpty()) {
                Toast.makeText(this@UpdateBus, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                val checkId = DB.checkId(id)
                if (checkId) {
                    val update = DB.updateBus(id, departure, arrival, date, totalSeats)
                    if (update) {
                        Toast.makeText(this@UpdateBus, "Bus updated successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, AdminPanel::class.java))
                    } else {
                        Toast.makeText(this@UpdateBus, "New entry not updated", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@UpdateBus, "ID does not exist", Toast.LENGTH_SHORT).show()
                }
            }
        }

        back.setOnClickListener {
            startActivity(Intent(applicationContext, AdminPanel::class.java))
        }
    }
}

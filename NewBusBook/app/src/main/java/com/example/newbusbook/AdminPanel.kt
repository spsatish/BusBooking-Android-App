package com.example.newbusbook

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AdminPanel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminpanel)

        val add = findViewById<Button>(R.id.add)
        val update = findViewById<Button>(R.id.update)
        val delete = findViewById<Button>(R.id.delete)
        val view = findViewById<Button>(R.id.view)
        val end = findViewById<Button>(R.id.logout)
        val db = DBHelper(this)

        add.setOnClickListener {
            val intent = Intent(applicationContext, AddBus::class.java)
            startActivity(intent)
        }

        update.setOnClickListener {
            val intent = Intent(applicationContext, UpdateBus::class.java)
            startActivity(intent)
        }

        delete.setOnClickListener {
            val intent = Intent(applicationContext, DeleteBus::class.java)
            startActivity(intent)
        }

        view.setOnClickListener {
            val result: Cursor = db.viewBuses()
            if (result.count == 0) {
                Toast.makeText(this@AdminPanel, "No Entry Exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val buffer = StringBuffer()
            while (result.moveToNext()) {
                buffer.append("ID : ${result.getString(0)}\n")
                buffer.append("Departure : ${result.getString(1)}\n")
                buffer.append("Arrival : ${result.getString(2)}\n")
                buffer.append("Date : ${result.getString(3)}\n")
                buffer.append("Total Seats : ${result.getString(4)}\n\n")
            }

            val builder = AlertDialog.Builder(this@AdminPanel)
            builder.setCancelable(true)
            builder.setTitle("ALL BUSES DETAILS")
            builder.setMessage(buffer.toString())
            builder.show()
        }

        end.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

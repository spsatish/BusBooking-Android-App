package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeleteBus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deletebus)

        val busId = findViewById<EditText>(R.id.busid)
        val deleteBusButton = findViewById<Button>(R.id.deletebus)
        val backButton = findViewById<Button>(R.id.button03)
        val db = DBHelper(this)

        deleteBusButton.setOnClickListener {
            val id = busId.text.toString()
            if (id.isEmpty()) {
                Toast.makeText(this@DeleteBus, "Please Enter Bus ID", Toast.LENGTH_SHORT).show()
            } else {
                val checkId = db.checkId(id)
                if (checkId) {
                    val delete = db.deleteBus(id)
                    if (delete) {
                        Toast.makeText(this@DeleteBus, "Bus deleted successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, AdminPanel::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@DeleteBus, "Entry not deleted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@DeleteBus, "ID does not exist", Toast.LENGTH_SHORT).show()
                }
            }
        }

        backButton.setOnClickListener {
            val intent = Intent(applicationContext, AdminPanel::class.java)
            startActivity(intent)
        }
    }
}

package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminSignup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminsignup)

        val btnSignup = findViewById<Button>(R.id.btn1)
        val btnSignin = findViewById<Button>(R.id.btn2)
        val et1 = findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)
        val et3 = findViewById<EditText>(R.id.et3)
        val et4 = findViewById<EditText>(R.id.et4)
        val et5 = findViewById<EditText>(R.id.et5)
        val db = DBHelper(this)

        btnSignup.setOnClickListener {
            val fullname = et1.text.toString()
            val email = et2.text.toString()
            val username = et3.text.toString()
            val password = et4.text.toString()
            val code = et5.text.toString()

            if (fullname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || code.isEmpty()) {
                Toast.makeText(this@AdminSignup, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                val checkAdminUser = db.checkAdminUsername(username)
                if (!checkAdminUser) {
                    if (code == "56964") {
                        val insert = db.insertAdmin(fullname, email, username, password)
                        if (insert) {
                            Toast.makeText(this@AdminSignup, "Registered Successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, AdminLogin::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@AdminSignup, "Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@AdminSignup, "The Admin Code is Wrong", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@AdminSignup, "User Already exists, please sign in", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, AdminLogin::class.java)
                    startActivity(intent)
                }
            }
        }

        btnSignin.setOnClickListener {
            val intent = Intent(applicationContext, AdminLogin::class.java)
            startActivity(intent)
        }
    }
}

package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminlogin)

        val username = findViewById<EditText>(R.id.user)
        val password = findViewById<EditText>(R.id.pass)
        val signin = findViewById<Button>(R.id.login)
        val signup = findViewById<Button>(R.id.signup)
        val back = findViewById<Button>(R.id.button01)
        val db = DBHelper(this)

        signin.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this@AdminLogin, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                val checkAdminUserPass = db.checkAdminUsernamePassword(user, pass)
                if (checkAdminUserPass) {
                    Toast.makeText(this@AdminLogin, "LogIn Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, AdminPanel::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@AdminLogin, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        back.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        signup.setOnClickListener {
            val intent = Intent(applicationContext, AdminSignup::class.java)
            startActivity(intent)
        }
    }
}

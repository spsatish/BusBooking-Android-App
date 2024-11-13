package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.user)
        val password = findViewById<EditText>(R.id.pass)
        val signInButton = findViewById<Button>(R.id.login)
        val signUpButton = findViewById<Button>(R.id.signup)
        val db = DBHelper(this)

        signInButton.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this@Login, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                val checkUserPass = db.checkUsernamePassword(user, pass)
                if (checkUserPass) {
                    Toast.makeText(this@Login, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, Booking::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }



        signUpButton.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }
    }
}

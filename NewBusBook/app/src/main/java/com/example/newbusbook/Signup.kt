package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Signup : AppCompatActivity() {

    private lateinit var DB: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val btnSignup: Button = findViewById(R.id.btn1)
        val btnSignin: Button = findViewById(R.id.btn2)
        val etFullname: EditText = findViewById(R.id.et1)
        val etEmail: EditText = findViewById(R.id.et2)
        val etUsername: EditText = findViewById(R.id.et3)
        val etPassword: EditText = findViewById(R.id.et4)

        DB = DBHelper(this)

        btnSignup.setOnClickListener {
            val fullname = etFullname.text.toString()
            val email = etEmail.text.toString()
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (fullname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@Signup, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                val checkUser = DB.checkUsername(username)
                if (!checkUser) {
                    val insert = DB.insertData(fullname, email, username, password)
                    if (insert) {
                        Toast.makeText(this@Signup, "Registered Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, Login::class.java))
                    } else {
                        Toast.makeText(this@Signup, "Registration Failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Signup, "User Already exists please signin", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, Login::class.java))
                }
            }
        }

        btnSignin.setOnClickListener {
            startActivity(Intent(applicationContext, Login::class.java))
        }
    }
}

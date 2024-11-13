package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PaymentThankyou : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_thankyou)

        // Find the TextView and Button from the layout
        val thankYouMessage: TextView = findViewById(R.id.thankYouMessage)
        val buttonReturn: Button = findViewById(R.id.buttonReturn)

        // Set a custom thank you message
        thankYouMessage.text = "Thank you for booking with us!\n\nYour booking is confirmed."

        // Set a click listener to the button to return to the main screen or previous page
        buttonReturn.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            // You can modify the Intent here to go to the main activity or previous screen
            finish() // Finish the current activity (closes the thank you page)
        }
    }
}

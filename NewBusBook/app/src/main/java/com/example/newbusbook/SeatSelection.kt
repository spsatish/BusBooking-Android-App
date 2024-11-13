package com.example.newbusbook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SeatSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val btnCardPay: Button = findViewById(R.id.btncardpay)

        btnCardPay.setOnClickListener {
            val intent = Intent(this@SeatSelection, PaymentThankyou::class.java)
            startActivity(intent)
        }
    }

    fun cardpay(view: View) {
        val intent = Intent(this@SeatSelection, PaymentThankyou::class.java)
        startActivity(intent)
    }
}

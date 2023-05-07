package com.example.helasaviya.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helasaviya.R

class MainActivity : AppCompatActivity() {
    // UI elements
    private lateinit var btnPayPalData: Button
    private lateinit var btnCreditData: Button
    private lateinit var btnCreditDetails: Button


    /**
     * This method is called when the activity is starting up.
     * It initializes the UI elements and sets up the click listeners for the buttons.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPayPalData = findViewById(R.id.btnPayPalData)
        btnCreditData = findViewById(R.id.btnCreditData)
        btnCreditDetails = findViewById(R.id.btnCreditDetails)

        btnPayPalData.setOnClickListener {
           val intent = Intent(this, PayPalpaymentgatewayActivity::class.java)
            startActivity(intent)
        }

        btnCreditData.setOnClickListener {
            val intent = Intent(this, CreditDebitActivity::class.java)
            startActivity(intent)
        }
        btnCreditDetails.setOnClickListener {
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }
    }
}
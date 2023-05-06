package com.example.helasaviya_delivery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helasaviya_delivery.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnInsert:Button
    private lateinit var login:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        btnInsert = findViewById(R.id.button2)
        login = findViewById(R.id.login)



        btnInsert.setOnClickListener {
            val intent = Intent(this, AddDelivery::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val intent = Intent(this, SellerSignIn::class.java)
            startActivity(intent)
        }


    }
}
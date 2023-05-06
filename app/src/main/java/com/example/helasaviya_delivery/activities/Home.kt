package com.example.helasaviya_delivery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helasaviya_delivery.R
import com.example.helasaviya_delivery.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.delivery.setOnClickListener {
            var intent = Intent(this,SellerAllDelivery::class.java)
            startActivity(intent)
        }
    }
}
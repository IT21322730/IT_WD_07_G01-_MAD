package com.example.helasaviya_delivery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.helasaviya_delivery.databinding.ActivitySellerSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SellerSignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySellerSignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySellerSignInBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.register.setOnClickListener {
            var intent = Intent(this,SellerSignUp::class.java)
            startActivity(intent)
        }

        binding.signin.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()


            if(email.isNotEmpty() && password.isNotEmpty()){


                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this,Home::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }


            }else{
                Toast.makeText(this,"Empty Fields Are Not Allowed !! !!", Toast.LENGTH_LONG).show()

            }
        }
    }
}
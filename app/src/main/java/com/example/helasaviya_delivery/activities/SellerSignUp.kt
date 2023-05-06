package com.example.helasaviya_delivery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.helasaviya_delivery.R
import com.example.helasaviya_delivery.databinding.ActivitySellerSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SellerSignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySellerSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySellerSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.sellersignup.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val password2 = binding.password2.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()){
                if(password == password2){

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this,SellerSignIn::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                        }
                    }

                }else{
                    Toast.makeText(this,"Password is not matching !!",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Empty Fields Are Not Allowed !! !!",Toast.LENGTH_LONG).show()

            }
        }
    }
}
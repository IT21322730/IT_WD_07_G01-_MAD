package com.example.helasaviya.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.helasaviya.models.PayPalModel
import com.example.helasaviya.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PayPalpaymentgatewayActivity : AppCompatActivity() {

    // Declare UI elements
    private lateinit var etEmpUsername: EditText
    private lateinit var etEmpPassword: EditText

    private lateinit var btnLogin: Button

    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_palpaymentgateway)

        etEmpUsername = findViewById(R.id.etEmpUsername)
        etEmpPassword = findViewById(R.id.etEmpPassword)

        btnLogin = findViewById(R.id.btnLogin)

        dbRef = FirebaseDatabase.getInstance().getReference("PayPal")

        btnLogin.setOnClickListener {
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData() {
        val empUsername = etEmpUsername.text.toString()
        val empPassword = etEmpPassword.text.toString()

        if (empUsername.isEmpty()) {
            etEmpUsername.error = "Please enter Username"
        }
        if (empPassword.isEmpty()) {
            etEmpPassword.error = "Please enter Password"
        }
        val empId = dbRef.push().key!!

        val paypal = PayPalModel(empId,empUsername,empPassword)

        dbRef.child(empId).setValue(paypal)
            .addOnCompleteListener {
                Toast.makeText(this, "Data Login successfully", Toast.LENGTH_LONG).show()

                etEmpUsername.text.clear()
                etEmpPassword.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
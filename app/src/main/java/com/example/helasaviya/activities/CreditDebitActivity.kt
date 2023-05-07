package com.example.helasaviya.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.helasaviya.models.CreaditsModel
import com.example.helasaviya.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreditDebitActivity : AppCompatActivity() {

    // Declare variables to store references to the views
    private lateinit var etcardnumber: EditText
    private lateinit var etname: EditText
    private lateinit var etDate: EditText
    private lateinit var etMM: EditText
    private lateinit var etCVV: EditText

    private lateinit var btnPayData: Button

    // Declare a variable to store a reference to the Firebase Database
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit_debit)

        // Initialize the views
        etcardnumber = findViewById(R.id.etcardnumber)
        etname = findViewById(R.id.etname)
        etDate = findViewById(R.id.etDate)
        etMM = findViewById(R.id.etMM)
        etCVV = findViewById(R.id.etCVV)

        btnPayData = findViewById(R.id.btnPayData)

        // Get a reference to the "Credit" node in the Firebase Database
        dbRef = FirebaseDatabase.getInstance().getReference("Credit")

        // Get a reference to the "Credit" node in the Firebase Database
        btnPayData.setOnClickListener {
            saveCreditData()
        }


    }

    //Create a function to save credit data
    private fun saveCreditData() {
        val empcardnumber = etcardnumber.text.toString()
        val empname = etname.text.toString()
        val empDate = etDate.text.toString()
        val empMM = etMM.text.toString()
        val empCVV= etCVV.text.toString()

        if (empcardnumber.isEmpty()) {
            etcardnumber.error = "Please enter Card Number"
        }
        if (empname.isEmpty()) {
            etname.error = "Please enter Bank Name"
        }
        if (empDate.isEmpty()) {
            etDate.error = "Please enter Date"
        }
        if (empMM.isEmpty()) {
            etMM.error = "Please enter Month"
        }
        if (empCVV.isEmpty()) {
            etCVV.error = "Please enter CVV"
        }

        val empPId = dbRef.push().key!!

        val creadit = CreaditsModel(empPId,empcardnumber,empname,empDate,empMM,empCVV)

        dbRef.child(empPId).setValue(creadit)
            .addOnCompleteListener {
                Toast.makeText(this, "Yor Payment successfully completed", Toast.LENGTH_LONG).show()

                etcardnumber.text.clear()
                etname.text.clear()
                etDate.text.clear()
                etMM.text.clear()
                etCVV.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
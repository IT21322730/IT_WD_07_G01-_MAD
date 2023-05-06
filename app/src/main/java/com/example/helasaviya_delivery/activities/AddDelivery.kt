package com.example.helasaviya_delivery.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.helasaviya_delivery.models.DeliveryModel
import com.example.helasaviya_delivery.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class AddDelivery : AppCompatActivity() {


    private lateinit var name: EditText
    private lateinit var phone:EditText
    private lateinit var address1:EditText
    private lateinit var address2:EditText
    private lateinit var province:EditText
    private lateinit var total:EditText
    private lateinit var addDelivery:Button


    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_delivery)

        name = findViewById(R.id.name)
        phone = findViewById(R.id.phone)
        address1 = findViewById(R.id.address1)
        address2 = findViewById(R.id.address2)
        province = findViewById(R.id.province)
        total = findViewById(R.id.total)
        addDelivery = findViewById(R.id.addDelivery)


        dbRef = FirebaseDatabase.getInstance().getReference("Delivery")

        addDelivery.setOnClickListener {
            saveDeliveryData()
        }

    }

    private fun saveDeliveryData(){
        //getting values
        val name = name.text.toString()
        val phone = phone.text.toString()
        val address1 = address1.text.toString()
        val address2 =address2.text.toString()
        val province = province.text.toString()
        val total = total.text.toString()



        val id = dbRef.push().key!!

        //val employee = EmployeeModel(id,name,phone,address1,address2,province,total)
        val delivery = DeliveryModel(id,name,phone,address1,address2,province,total, status="Pending")

        dbRef.child(id).setValue(delivery).addOnCompleteListener {
            Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
        }.addOnFailureListener { err ->
            Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
        }
    }
}
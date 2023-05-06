package com.example.helasaviya_delivery.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.helasaviya_delivery.R
import com.example.helasaviya_delivery.R.*
import com.example.helasaviya_delivery.models.DeliveryModel
import com.google.firebase.database.FirebaseDatabase

class DeliveryDetails : AppCompatActivity() {

    private lateinit var id: TextView
    private lateinit var name: TextView
    private lateinit var phone: TextView
    private lateinit var address1: TextView
    private lateinit var address2: TextView
    private lateinit var province: TextView
    private lateinit var total: TextView
    private lateinit var status: TextView
    private lateinit var btnupdate: Button
    private lateinit var btndelete: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_delivery_details)

        initView()
        setValuesToViews()

        btnupdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("id").toString(),
                intent.getStringExtra("name").toString()
            )
        }

        btndelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("id").toString()
            )
        }
    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Delivery").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Delivery Data Are Deleted!!!",Toast.LENGTH_LONG).show()

            val intent = Intent(this,SellerAllDelivery::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this,"Can't Delete..There Is A Error ${error.message}",Toast.LENGTH_LONG).show()
        }
    }
    private fun initView() {
        id = findViewById(R.id.id)
        name = findViewById(R.id.name)
        phone = findViewById(R.id.phone)
        address1 = findViewById(R.id.address1)
        address2 = findViewById(R.id.address2)
        province = findViewById(R.id.province)
        total = findViewById(R.id.total)
status= findViewById(R.id.status)
        btnupdate = findViewById(R.id.btnupdate)
        btndelete = findViewById(R.id.btndelete)
        //btnUpdate = findViewById(R.id.btnUpdate)
        //btnDelete = findViewById(R.id.btnDelete)
        //tvbtnupdate = findViewById(R.id.btnupdate)
    }

    private fun setValuesToViews(){

        id.text = intent.getStringExtra("id")
        name.text = intent.getStringExtra("name")
        phone.text = intent.getStringExtra("phone")
        address1.text = intent.getStringExtra("address1")
        address2.text = intent.getStringExtra("address2")
        province.text = intent.getStringExtra("province")
        total.text = intent.getStringExtra("total")
        status.text = intent.getStringExtra("status")


    }

private fun openUpdateDialog(id:String,name:String){

    val mDialog = AlertDialog.Builder(this)
    val inflater = layoutInflater
    val mDialogView = inflater.inflate(layout.update_details,null)

    mDialog.setView(mDialogView)

    //val txtname = mDialogView.findViewById<EditText>(R.id.txtname)
    val txtname = mDialogView.findViewById<TextView>(R.id.txtname)
    val txtphone = mDialogView.findViewById<TextView>(R.id.txtphone)
    val txtaddress1 = mDialogView.findViewById<TextView>(R.id.txtaddress1)
    val txtaddress2 = mDialogView.findViewById<TextView>(R.id.txtaddress2)
    val txtprovince = mDialogView.findViewById<TextView>(R.id.txtprovince)
    val txttotal = mDialogView.findViewById<TextView>(R.id.txttotal)
    val txtstatus = mDialogView.findViewById<EditText>(R.id.txtstatus)

    val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

    txtname.setText(intent.getStringExtra("name").toString())
    txtphone.setText(intent.getStringExtra("phone").toString())
    txtaddress1.setText(intent.getStringExtra("address1").toString())
    txtaddress2.setText(intent.getStringExtra("address2").toString())
    txtprovince.setText(intent.getStringExtra("province").toString())
    txttotal.setText(intent.getStringExtra("total").toString())
    txtstatus.setText(intent.getStringExtra("status").toString())

    mDialog.setTitle("Updating Delivery Status")

    val alertDialog = mDialog.create()
    alertDialog.show()

btnUpdateData.setOnClickListener {
    updateDeliveryData(
        id,
        txtname.text.toString(),
        txtphone.text.toString(),
        txtaddress1.text.toString(),
        txtaddress2.text.toString(),
        txtprovince.text.toString(),
        txttotal.text.toString(),
        txtstatus.text.toString(),
        )
    Toast.makeText(applicationContext,"data updated",Toast.LENGTH_LONG).show()


    phone.text = txtphone.text.toString()
    address1.text = txtaddress1.text.toString()
    address2.text = txtaddress2.text.toString()
    province.text = txtprovince.text.toString()
    total.text = txttotal.text.toString()
    status.text = txtstatus.text.toString()

    alertDialog.dismiss()
}




}
    private fun updateDeliveryData(
        id:String,
        name: String,
        phone:String,
        address1:String,
        address2:String,
        province:String,
        total:String,
        status:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Delivery").child(id)
        val deliveryInfo = DeliveryModel(id, name, phone, address1, address2, province, total, status)
        dbRef.setValue(deliveryInfo)
    }

}
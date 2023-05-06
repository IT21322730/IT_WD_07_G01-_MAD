package com.example.helasaviya_delivery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helasaviya_delivery.R
import com.example.helasaviya_delivery.adapters.deliveryAdaptor
import com.example.helasaviya_delivery.models.DeliveryModel
import com.google.firebase.database.*

class SellerAllDelivery : AppCompatActivity() {

    private lateinit var deliveryRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var deliveryList: ArrayList<DeliveryModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_all_delivery)

        deliveryRecyclerView = findViewById(R.id.rvDelivery)
        deliveryRecyclerView.layoutManager = LinearLayoutManager(this)
        deliveryRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        deliveryList = arrayListOf<DeliveryModel>()

        getDeliveryData()
    }

    private fun getDeliveryData(){
        deliveryRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef =FirebaseDatabase.getInstance().getReference("Delivery")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                deliveryList.clear()
                if (snapshot.exists()){
                    for (deliverySnap in snapshot.children){
                        val deliveryData = deliverySnap.getValue(DeliveryModel::class.java)
                        deliveryList.add(deliveryData!!)
                    }
                    val mAdaptor = deliveryAdaptor(deliveryList)
                    deliveryRecyclerView.adapter = mAdaptor

                    mAdaptor.setOnItemClickListener(object : deliveryAdaptor.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@SellerAllDelivery,DeliveryDetails::class.java)

                            intent.putExtra("id",deliveryList[position].id)
                            intent.putExtra("name",deliveryList[position].name)
                            intent.putExtra("phone",deliveryList[position].phone)
                            intent.putExtra("address1",deliveryList[position].address1)
                            intent.putExtra("address2",deliveryList[position].address2)
                            intent.putExtra("province",deliveryList[position].province)
                            intent.putExtra("total",deliveryList[position].total)
                            intent.putExtra("status",deliveryList[position].status)
                            startActivity(intent)
                        }

                    })

                    deliveryRecyclerView.visibility =View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
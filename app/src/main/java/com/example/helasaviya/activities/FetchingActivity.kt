package com.example.helasaviya.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helasaviya.models.CreaditsModel
import com.example.helasaviya.R
import com.example.helasaviya.adapter.CreditAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingActivity : AppCompatActivity() {

    // Define the activity's UI elements
    private lateinit var creaditRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var creditList: ArrayList<CreaditsModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var creditView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)


        // Find the UI elements by ID and set their properties
        creaditRecyclerView = findViewById(R.id.rvCredit )
        creaditRecyclerView.layoutManager = LinearLayoutManager(this)
        creaditRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        creditView = findViewById(R.id.countView)

        creditList = arrayListOf<CreaditsModel>()

        getCreaditata()
    }

    // Define a function to load the credit data from Firebase
    private fun getCreaditata() {
        creaditRecyclerView.visibility= View.GONE
        tvLoadingData.visibility= View.VISIBLE
        dbRef= FirebaseDatabase.getInstance().getReference("Credit")

        var count = 0;


        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                creditList.clear()
                if(snapshot.exists()){
                    for(creditSnap in snapshot.children){
                        val creditData=creditSnap.getValue(CreaditsModel::class.java)
                        creditList.add(creditData!!)
                        count++
                    }
                    val mAdapter = CreditAdapter(creditList )
                    creaditRecyclerView.adapter= mAdapter


                    mAdapter.setOnItemClickListener(object :CreditAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent= Intent(this@FetchingActivity, CreaditDetailsActivity::class.java)

                            //put extra
                            intent.putExtra("empPId", creditList[position].empPId)
                            intent.putExtra("empcardnumber", creditList[position].empcardnumber)
                            intent.putExtra("empname", creditList[position].empname)
                            intent.putExtra("empDate", creditList[position].empDate)
                            intent.putExtra("empMM", creditList[position].empMM)
                            intent.putExtra("empCVV", creditList[position].empCVV)
                            startActivity(intent)
                        }

                    })
                    creditView.text = "You have $count Cards"
                    creaditRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility= View.GONE

                        }

                    }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
                   /* taskRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility= View.GONE*/

            })
    }
}
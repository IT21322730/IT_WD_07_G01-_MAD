package com.example.helasaviya.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.helasaviya.R
import com.example.helasaviya.models.CreaditsModel
import com.google.firebase.database.FirebaseDatabase

class CreaditDetailsActivity : AppCompatActivity() {

    // Declare variables to hold the UI elements
    private lateinit var tvEmpId: TextView
    private lateinit var tvEmpAccount: TextView
    private lateinit var tvEmpName: TextView
    private lateinit var tvEmpDate: TextView
    private lateinit var tvEmpMonth: TextView
    private lateinit var tvEmpCVV: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creadit_details)

        // Initialize the UI elements
        initView()

        // Populate the UI elements with data
        setValuesToViews()

        // Set click listener for the update button
        btnUpdate.setOnClickListener{
            openUpdateDialog(

                intent.getStringExtra("empPId").toString(),
                intent.getStringExtra("empcardnumber").toString(),
            )
        }

        // Set click listener for the delete button
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("empPId").toString()
            )
        }
    }

    // Delete a credit record from the Firebase database
    private fun deleteRecord(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Credit").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Credit / Debit data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }

    }

    // Open the update dialog with the data of the selected credit
    private fun openUpdateDialog(  empId:String,
                                   empAccount:String) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etEmpAccount = mDialogView.findViewById<EditText>(R.id.etEmpAccount)
        val etEmpName = mDialogView.findViewById<EditText>(R.id.etEmpName)
        val etEmpDate = mDialogView.findViewById<EditText>(R.id.etEmpDate)
        val etEmpMonth = mDialogView.findViewById<EditText>(R.id.etEmpMonth)
        val etEmpCVV = mDialogView.findViewById<EditText>(R.id.etEmpCVV)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etEmpAccount.setText(  intent.getStringExtra("empcardnumber").toString())
        etEmpName.setText(  intent.getStringExtra("empname").toString())
        etEmpDate.setText(  intent.getStringExtra("empDate").toString())
        etEmpMonth.setText(  intent.getStringExtra("empMM").toString())
        etEmpCVV.setText(  intent.getStringExtra("empCVV").toString())

        mDialog.setTitle("Updating $empAccount Record")

        val alertDialog = mDialog.create()
        alertDialog.show()
        btnUpdateData.setOnClickListener {
            updateCreditData(
                empId,
                etEmpAccount.text.toString(),
                etEmpName.text.toString(),
                etEmpDate.text.toString(),
                etEmpMonth.text.toString(),
                etEmpCVV.text.toString()
            )

            Toast.makeText(applicationContext, "Credit/Debit Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvEmpAccount.text = etEmpAccount.text.toString()
            tvEmpName.text = etEmpName.text.toString()
            tvEmpDate.text = etEmpDate.text.toString()
            tvEmpMonth.text = etEmpMonth.text.toString()
            tvEmpCVV.text = etEmpCVV.text.toString()

            alertDialog.dismiss()
        }


    }

    private fun updateCreditData(
        id: String,
        account: String,
        name: String,
        date: String,
        month: String,
        cvv: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Credit").child(id)
        val creditInfo = CreaditsModel(id, account, name, date,month,cvv)
        dbRef.setValue(creditInfo)
    }

    private fun setValuesToViews() {
        tvEmpId.text = intent.getStringExtra("empPId")
        tvEmpAccount.text = intent.getStringExtra("empcardnumber")
        tvEmpName.text = intent.getStringExtra("empname")
        tvEmpDate.text = intent.getStringExtra("empDate")
        tvEmpMonth.text = intent.getStringExtra("empMM")
        tvEmpCVV.text = intent.getStringExtra("empCVV")
    }

    private fun initView() {
        tvEmpId = findViewById(R.id.tvEmpId)
        tvEmpAccount = findViewById(R.id.tvEmpAccount)
        tvEmpName = findViewById(R.id.tvEmpName)
        tvEmpDate = findViewById(R.id.tvEmpDate)
        tvEmpMonth = findViewById(R.id.tvEmpMonth)
        tvEmpCVV = findViewById(R.id.tvEmpCVV)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }
}
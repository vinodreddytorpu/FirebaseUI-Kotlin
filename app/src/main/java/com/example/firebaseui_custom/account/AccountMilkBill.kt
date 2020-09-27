package com.example.firebaseui_custom.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.firebaseui_custom.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_account_milk_bill.*

class AccountMilkBill : AppCompatActivity() {
    private  val TAG = "AccountMilkBill"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_milk_bill)
        // declering xml in kotlin in order to use
        val submit = findViewById<Button>(R.id.submit)
        val bill = findViewById<TextView>(R.id.milk_total_price)



        submit.setOnClickListener {
            val ML = milk_litters.text.toString()
            val MP = milk_price.text.toString()
            //val milkBill = (ML*MP)
            val convert = ML.toInt()*MP.toInt()
            val MT = convert.toString()
            //function to firestore
            firebasefirestoredataupload(ML,MP,MT)
        }

    }

    private fun firebasefirestoredataupload(ML:String,MP:String,MT:String) {
        // Access a Cloud Firestore instance from your Activity
        val db = Firebase.firestore
        // Create a new user with a first and last name
        val user = hashMapOf(
            ML to "milk in liter",
            MP to "price per liter",
            MT to "total price"
        )


        db.collection("Milk Data")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this,"Sucessfully Posted Data",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{e ->
                Log.w(TAG, "Error adding document", e)
                Toast.makeText(this,"Failed to Posted",Toast.LENGTH_LONG).show()
            }
    }
}
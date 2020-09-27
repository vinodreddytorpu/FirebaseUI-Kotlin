package com.example.firebaseui_custom.FirebaseUI_USER

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.firebaseui_custom.R
import com.firebase.ui.auth.AuthUI

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val button_signout = findViewById<Button>(R.id.signout)
        button_signout.setOnClickListener(){
            AuthUI.getInstance().signOut(this).addOnCompleteListener{
                Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,FirebaseUI_AUTH::class.java))
            }

            //Toast.makeText(this, "R.string.message", Toast.LENGTH_LONG).show()
        }
        val button_delete = findViewById<Button>(R.id.delete)
        button_delete.setOnClickListener{
            AuthUI.getInstance().delete(this).addOnCompleteListener {
                Toast.makeText(this, "Accoutnt Deleted Successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,FirebaseUI_AUTH::class.java))
            }
        }
    }
}
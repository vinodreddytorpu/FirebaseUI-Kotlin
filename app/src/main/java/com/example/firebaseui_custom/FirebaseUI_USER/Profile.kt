package com.example.firebaseui_custom.FirebaseUI_USER

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.firebaseui_custom.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val user_name = findViewById<TextView>(R.id.user_name)
        val user_email = findViewById<TextView>(R.id.user_email)
        val user_uid = findViewById<TextView>(R.id.user_uid)
        val user_img = findViewById<ImageView>(R.id.user_img)
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null){
            val name = user.displayName
            val email = user.email
            val uid = user.uid
            val img = user.photoUrl
            user_name.setText(name).toString()
            user_email.setText(email).toString()
            user_uid.setText(uid).toString()
            user_img.setImageURI(img)
            Toast.makeText(this, email, Toast.LENGTH_SHORT).show()
        }

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
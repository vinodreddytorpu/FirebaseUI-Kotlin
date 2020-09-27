package com.example.firebaseui_custom.USER_Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.firebaseui_custom.R
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class USER_Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u_s_e_r__profile)
        // declering xml in kotlin to use
        val user_name = findViewById<TextView>(R.id.full_name)
        val user_email = findViewById<TextView>(R.id.email)
        val user_profile = findViewById<ImageView>(R.id.profile_image)
        // getting current loged user
        val user = FirebaseAuth.getInstance().currentUser
        // getting meta data for logged user from database and displaying them
        if (user != null){
            val name = user.displayName
            val email = user.email
            val uid = user.uid
            val img = user.photoUrl
            user_name.setText(name).toString()
            user_email.setText(email).toString()
            Picasso.get().load(img).into(user_profile)
            Toast.makeText(this, email, Toast.LENGTH_SHORT).show()
        }
    }
}
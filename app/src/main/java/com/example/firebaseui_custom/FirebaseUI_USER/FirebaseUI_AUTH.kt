package com.example.firebaseui_custom.FirebaseUI_USER

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseui_custom.R
import com.example.firebaseui_custom.USER_Profile.USER_Profile
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class FirebaseUI_AUTH : AppCompatActivity() {
    val AUTH_REQUEST_CODE = 9999 //any random number
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var listener: FirebaseAuth.AuthStateListener
    lateinit var providers:List<AuthUI.IdpConfig>
    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(listener)
    }

    override fun onStop() {
        if(listener !=null )
            firebaseAuth.removeAuthStateListener(listener)
        super.onStop()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_u_i__a_u_t_h)
        // one thing to noted down the 'google-service' firebase json file must be downloaded and keep in app if it not happen automatically
        init()
    }
    private fun init() {
        providers = arrayListOf(
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        firebaseAuth = FirebaseAuth.getInstance()
        listener = object:FirebaseAuth.AuthStateListener{
            override fun onAuthStateChanged(p0: FirebaseAuth) {
                val user =p0.currentUser
                if (user != null){
                    startActivity(Intent(this@FirebaseUI_AUTH,USER_Profile::class.java))
                    // Do something
                    //Toast.makeText(this@MainActivity,"logged in",user.uid,Toast.LENGTH_SHORT).show()
                    //Toast.makeText(this@MainActivity1,"loggedin", Toast.LENGTH_LONG)
                }
                else{
                    startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),AUTH_REQUEST_CODE )
                }
            }

        }
    }
}
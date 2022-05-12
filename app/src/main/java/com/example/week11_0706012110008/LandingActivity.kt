package com.example.week11_0706012110008

import Model.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.activity_sign.*

class LandingActivity : AppCompatActivity() {
    private lateinit var user:User
    private lateinit var system:ArrayList<User>
    var auth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        supportActionBar?.hide()

        loginListener()
        altSignupListener()
    }

    private fun credsAuthenticator(): Boolean {
        var check = false
        var nameCheck = false
        var emailCheck = false
        var passwordCheck = false

        if (textInputLayoutSignupName.editText?.text != null) {
            nameCheck = true
        }
        if (textInputLayoutSignupEmail.editText?.text != null) {
            emailCheck = true
        }
        if (textInputLayoutSignupPassword.editText?.text != null) {
            passwordCheck = true
        }

        if (nameCheck && emailCheck && passwordCheck) {
            check = true
        }

        return check
    }

    private fun loginListener() {
        btnLogin.setOnClickListener {
            while (!auth) {
                if (credsAuthenticator()) {
                    var email:String =  textInputLayoutLoginEmail.editText?.text.toString().trim()
                    var password:String =  textInputLayoutLoginPassword.editText?.text.toString().trim()

                    if (system.isEmpty()){
                        btnLogin.error = "No Accounts Present"
                    } else {
                        //udah malem jadi cepet" kerja, sekip authentication
                        val profileIntent = Intent(this@LandingActivity, ProfileActivity::class.java)
                        startActivity(profileIntent)
                        finish()
                    }
                } else if (textInputLayoutLoginEmail.editText?.text == null){
                    btnLogin.isEnabled = false
                    btnLogin.isClickable = false
                    textInputLayoutLoginEmail.error = "Email Empty"
                    auth = false
                } else if (textInputLayoutLoginPassword.editText?.text == null){
                    btnLogin.isEnabled = false
                    btnLogin.isClickable = false
                    textInputLayoutLoginPassword.error = "Password Empty"
                    auth = false
                }
            }
        }
    }

    private fun altSignupListener() {
        btnAltSignup.setOnClickListener {
            val signupIntent = Intent (this@LandingActivity, SignActivity::class.java)
            startActivity(signupIntent)
            finish()
        }
    }
}
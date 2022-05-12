package com.example.week11_0706012110008

import Model.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {
    private lateinit var user:User
    private lateinit var system:ArrayList<User>
    var auth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
        supportActionBar?.hide()

        signupListener()
        altLoginListener()
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

    private fun signupListener() {
        btnSignup.setOnClickListener {
            while (!auth) {
                if (credsAuthenticator()) {
                    var name:String = textInputLayoutSignupName.editText?.text.toString().trim()
                    var email:String =  textInputLayoutSignupEmail.editText?.text.toString().trim()
                    var password:String =  textInputLayoutSignupPassword.editText?.text.toString().trim()
                    if (system.isEmpty()) {
                        user = User(name, email, password)

                        val loginIntent = Intent (this@SignActivity, LandingActivity::class.java)
                        startActivity(loginIntent)
                        finish()
                        auth = true
                    } else {
                        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            for (i in system.indices) {
                                if (email == system[i].email) {
                                    textInputLayoutSignupEmail.error = "Email Already Exists"
                                } else {
                                    user = User(name, email, password)

                                    val loginIntent = Intent (this@SignActivity, LandingActivity::class.java)
                                    startActivity(loginIntent)
                                    finish()
                                    auth = true
                                }
                            }
                        } else {
                            textInputLayoutSignupEmail.error = "Not An Email"
                        }
                    }
                }
            }
        }
    }

    private fun altLoginListener() {
        btnAltLogin.setOnClickListener {
            val loginIntent = Intent (this@SignActivity, LandingActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
    }
}
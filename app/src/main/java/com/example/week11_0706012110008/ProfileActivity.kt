package com.example.week11_0706012110008

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        logoutListener()
        deleteListener()
    }

    private fun logoutListener() {
        btnLogout.setOnClickListener {
            val logoutIntent = Intent (this@ProfileActivity, LandingActivity::class.java)
            startActivity(logoutIntent)
            finish()
        }
    }

    private fun deleteListener() {
        btnDelete.setOnClickListener {
            val deleteIntent = Intent (this@ProfileActivity, LandingActivity::class.java)
            startActivity(deleteIntent)
            finish()
        }
    }
}
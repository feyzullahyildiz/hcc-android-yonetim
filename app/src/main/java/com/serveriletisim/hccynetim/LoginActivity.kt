package com.serveriletisim.hccynetim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val enterButton = findViewById<Button>(R.id.activity_login_enter)

        enterButton.setOnClickListener {
            val main = Intent(this, MainActivity::class.java)

            startActivity(main)
        }
    }
}

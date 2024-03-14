package com.example.loginsingupauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val button = findViewById<Button>(R.id.MainViewBtn)

        button.setOnClickListener {
            val intent = Intent(this, GradesActivity::class.java)
            startActivity(intent)
        }
    }
}

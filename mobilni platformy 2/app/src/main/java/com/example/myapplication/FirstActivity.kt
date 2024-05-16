package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val textView = findViewById<TextView>(R.id.textView)

        imageView.setOnClickListener{
            textView.text = getString(R.string.image_text)
        }

        textView.setOnClickListener {
            val imageText: String = getString(R.string.image_text)

            if (textView.text.toString() == imageText) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please click on the image to proceed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
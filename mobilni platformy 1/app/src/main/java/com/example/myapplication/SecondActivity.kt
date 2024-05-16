package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val textView: TextView = findViewById(R.id.textView)

        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                textView.text = (millisUntilFinished / 1000 + 1).toString()
            }

            override fun onFinish() {
                textView.text = getString(R.string.welcome_text)
            }
        }.start()
    }
}
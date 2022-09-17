package com.example.simplecalculatorapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvTextInput: TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvTextInput = findViewById(R.id.tvTextInput)
        tvTextInput?.text = ""
    }

    // Method for handling digit clicks
    fun onDigitClick(view: View){
//        Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()
        tvTextInput?.append((view as Button).text)
    }

    // Method for handling clear button click
    fun onClearButtonClick(view: View){
        tvTextInput?.text = ""

    }
}
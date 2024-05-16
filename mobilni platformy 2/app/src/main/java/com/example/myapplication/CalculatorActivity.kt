package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var addButton: Button
    private lateinit var subtractButton: Button
    private lateinit var multiplyButton: Button
    private lateinit var divideButton: Button
    private lateinit var equalsButton: Button
    private lateinit var historyButton: Button
    private lateinit var historyTextView: TextView

    private var result = 0.0
    private var history = ""
    private var firstNumber = 0.0
    private var operator = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        editText = findViewById(R.id.edit_text)
        resultTextView = findViewById(R.id.result_text_view)
        addButton = findViewById(R.id.add_button)
        subtractButton = findViewById(R.id.subtract_button)
        multiplyButton = findViewById(R.id.multiply_button)
        divideButton = findViewById(R.id.divide_button)
        equalsButton = findViewById(R.id.equals_button)
        historyButton = findViewById(R.id.history_button)
        historyTextView = findViewById(R.id.history_text_view)

        addButton.setOnClickListener { performOperation("+") }
        subtractButton.setOnClickListener { performOperation("-") }
        multiplyButton.setOnClickListener { performOperation("*") }
        divideButton.setOnClickListener { performOperation("/") }
        equalsButton.setOnClickListener { calculateResult() }
        historyButton.setOnClickListener { displayHistory() }
    }

    private fun performOperation(operator: String) {
        val num1 = editText.text.toString().toDoubleOrNull()
        if (num1 != null) {
            if (firstNumber == 0.0) {
                firstNumber = num1
            } else {
                calculateResult()
                this.operator = operator
                firstNumber = result
            }
            this.operator = operator
            editText.text.clear()
        } else {
            Toast.makeText(this, "Введіть число", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateResult() {
        val num2 = editText.text.toString().toDoubleOrNull()
        if (num2 != null) {
            when (operator) {
                "+" -> result = firstNumber + num2
                "-" -> result = firstNumber - num2
                "*" -> result = firstNumber * num2
                "/" -> result = firstNumber / num2
            }
            history += "$firstNumber $operator $num2 = $result\n"
            resultTextView.text = result.toString()
            editText.text.clear()
            firstNumber = 0.0
        } else {
            Toast.makeText(this, "Введіть число", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayHistory() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_history, null)
        dialogBuilder.setView(dialogView)

        val historyTextView = dialogView.findViewById<TextView>(R.id.history_text_view)
        historyTextView.text = history

        val clearAllButton = dialogView.findViewById<Button>(R.id.clear_all_button)
        clearAllButton.setOnClickListener {
            history = ""
            historyTextView.text = ""
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}
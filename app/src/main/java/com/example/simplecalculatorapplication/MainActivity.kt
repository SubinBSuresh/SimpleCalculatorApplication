package com.example.simplecalculatorapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvTextInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    private var operatorClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvTextInput = findViewById(R.id.tvTextInput)
        tvTextInput?.text = ""
    }

    // Method for handling digit clicks
    fun onDigitClick(view: View) {
        tvTextInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    // Method for handling clear button clicks
    fun onClearButtonClick(view: View) {
        tvTextInput?.text = ""
    }

    // Method for handling decimal point button clicks
    fun onDotButtonClick(view: View) {
        if (lastNumeric && !lastDot) {
            tvTextInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    // Method for handling operator button clicks
    fun onOperatorButtonClick(view: View) {
        operatorClicked = true

        tvTextInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvTextInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    // Method for handling equal button click
    fun onEqualButtonClick(view: View) {
        if (lastNumeric) {
            var tvValue = tvTextInput?.toString()
            var prefix = ""

            try {
                if (tvValue != null) {
                    if (tvValue.startsWith("-")) {
                        prefix = "-"
                        tvValue = tvValue.substring(1)
                    }

                    if (tvValue.contains("-")) {
                        var splitValue = tvValue.split("-")

                        var firstElement = splitValue.get(0)
                        var secondElement = splitValue.get(1)

                        if (prefix.isNotEmpty()) {
                            firstElement = prefix + firstElement
                        }

                        tvTextInput?.text = removeZeroAfterDot(result.toString())
                        tvTextInput?.text = result.toString()

                    } else if (tvValue.contains("+")) {
                        var splitValue = tvValue.split("+")

                        var firstElement = splitValue.get(0)
                        var secondElement = splitValue.get(1)

                        if (prefix.isNotEmpty()) {
                            firstElement = prefix + firstElement
                        }

                        var result = firstElement.toFloat() + secondElement.toFloat()

                        tvTextInput?.text = removeZeroAfterDot(result.toString())


                    } else if (tvValue.contains("/")) {
                        var splitValue = tvValue.split("/")

                        var firstElement = splitValue.get(0)
                        var secondElement = splitValue.get(1)

                        if (prefix.isNotEmpty()) {
                            firstElement = prefix + firstElement
                        }

                        var result = firstElement.toFloat() / secondElement.toFloat()

                        tvTextInput?.text = removeZeroAfterDot(result.toString())


                    } else if (tvValue.contains("*")) {
                        var splitValue = tvValue.split("*")

                        var firstElement = splitValue.get(0)
                        var secondElement = splitValue.get(1)

                        if (prefix.isNotEmpty()) {
                            firstElement = prefix + firstElement
                        }

                        var result = firstElement.toFloat() * secondElement.toFloat()

                        tvTextInput?.text = removeZeroAfterDot(result.toString())

                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+")
        }
    }
}
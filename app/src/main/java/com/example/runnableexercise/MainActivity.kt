package com.example.runnableexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener { view ->

            CoroutineScope(Main).launch {
                validate()
            }
        }

    }

    //
    private fun validate() {
        val password = findViewById<EditText>(R.id.passwordTxt)

       Runnable {
           var passwordMatches = true

           if(password.text.length > 16) {
               passwordMatches = false
           } else {
               password.text.forEachIndexed { index, element ->
                   if((index + 1) % 3 == 0 && !element.isDigit()) {
                       passwordMatches = false
                   } else if((index + 1) % 7 == 0 && !element.isUpperCase()) {
                       passwordMatches = false
                   } else if((index + 1) % 8 == 0) {
                       passwordMatches = when(element) {
                           '$' -> true
                           '#' -> true
                           '!' -> true
                           '&' -> true
                           '@' -> true
                           '%' -> true
                           '*' -> true
                           else -> false
                       }
                   }
               }
           }
           val textView = findViewById<TextView>(R.id.resultLabel)

           if (!passwordMatches) {
                if(textView.text.length != 0) {
                    textView.text = null
                }

                password.error = "Invalid password"
            } else {

                textView.text = "Success"
            }
        }.run()
    }
}

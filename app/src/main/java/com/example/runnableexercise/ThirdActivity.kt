package com.example.runnableexercise

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.lang.Runnable
import kotlin.random.Random

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        var firstTaskValue: Boolean? = null
        var secondTaskValue: Boolean? = null

        CoroutineScope(Dispatchers.Main).launch {
            try {
                withContext(Dispatchers.Default) {
                    delay(Random.nextLong(2, 5))

                    Runnable {
                        firstTaskValue = Random.nextBoolean()
                    }.run()
                }

                withContext(Dispatchers.Default) {
                    delay(Random.nextLong(3, 5))

                    Runnable {
                        secondTaskValue = Random.nextBoolean()
                    }.run()
                }

                findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                findViewById<TextView>(R.id.runnableLabel).text = if (firstTaskValue == secondTaskValue) "success" else "failure"
            } catch (exception: Exception) {
                Log.e("[EXCEPTION]", exception.toString())
            }
        }
    }
}

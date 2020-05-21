package com.example.runnableexercise


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class SecondActivity : AppCompatActivity() {
    private var currentState = false
    private var seconds: Int = 0

    private lateinit var task: Runnable;
    private lateinit var taskHandler: Handler;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        this.taskHandler = Handler()

        CoroutineScope(Main).launch {
            task = Runnable {
                val result = findViewById<TextView>(R.id.resultView)

                result.text = seconds.toString()

                Log.d("[STATE]", currentState.toString());
                Log.d("[SECONDS]", seconds.toString());

                if(!currentState)
                    seconds++;

                taskHandler.postDelayed(
                    task,
                    1000
                )
            }

            task.run()
        }

        findViewById<Button>(R.id.stateButton).setOnClickListener { view ->
            currentState = !currentState;
        }
    }
}

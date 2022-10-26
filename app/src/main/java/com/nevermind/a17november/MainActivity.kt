package com.nevermind.a17november

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.floor
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        val countDate = "17-11-2022-00-00-00";
        val countTime = findViewById<TextView>(R.id.countTime);
        val endTime = SimpleDateFormat("dd-MM-yyyy-hh-mm-ss").parse(countDate).time

        timer = createTimer(endTime, countTime)
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    private fun createTimer(endTime: Long, countTime: TextView): CountDownTimer {
        return object : CountDownTimer((endTime - System.currentTimeMillis()),1000) {
            override fun onTick(remaining: Long) {
                val seconds = ((remaining / 1000) % 60).toInt()
                val minutes = ((remaining / (1000*60)) % 60).toInt()
                val hours = ((remaining / (1000*60*60)) % 24).toInt()
                val days = (remaining / (1000*60*60*24)).toInt()
                countTime.text = days.toString() +
                        " days\n" + hours.toString() +
                        " hours\n" + minutes.toString() +
                        " minutes\n" + seconds.toString() +
                        " seconds\nuntil doomsday"
            }
            override fun onFinish() {
                countTime.text = "END IS HERE"
            }
        }
    }
}
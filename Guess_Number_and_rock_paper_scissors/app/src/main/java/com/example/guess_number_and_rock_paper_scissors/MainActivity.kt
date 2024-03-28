package com.example.guess_number_and_rock_paper_scissors

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGuessNumber = findViewById<Button>(R.id.btnGuessNumber)
        val btnRockPaperScissors = findViewById<Button>(R.id.btnRockPaperScissors)

        btnGuessNumber.setOnClickListener{
            var GuessNumberIntent = Intent(this, GuessNumber::class.java)
            startActivity(GuessNumberIntent)
        }

        btnRockPaperScissors.setOnClickListener{
            var RockPaperScissorsIntent = Intent(this, RockPaperScissors::class.java)
            startActivity(RockPaperScissorsIntent)
        }
    }
}
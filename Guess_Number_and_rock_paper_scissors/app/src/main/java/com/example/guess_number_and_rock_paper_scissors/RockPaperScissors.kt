package com.example.guess_number_and_rock_paper_scissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.random.Random

class RockPaperScissors : AppCompatActivity() {
    private lateinit var txtResult: TextView
    private lateinit var imbthScissors: ImageButton
    private lateinit var imbtnRock: ImageButton
    private lateinit var imbtnPaper: ImageButton
    private lateinit var imageView: ImageView
    enum class Choice {
        SCISSORS, ROCK, PAPER
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock_paper_scissors)
        txtResult = findViewById(R.id.txtResult)
        imbthScissors = findViewById(R.id.imbthScissors)
        imbtnRock = findViewById(R.id.imbtnRock)
        imbtnPaper = findViewById(R.id.imbtnPaper)
        imageView = findViewById(R.id.imageView)

        imbthScissors.setOnClickListener{
            playGame(Choice.SCISSORS)
        }

        imbtnPaper.setOnClickListener{
            playGame(Choice.PAPER)
        }

        imbtnRock.setOnClickListener{
            playGame(Choice.ROCK)
        }

        val back = findViewById<Button>(R.id.back2)
        back.setOnClickListener{
            finish()
        }
    }

    fun getChoiceString(choice: Choice): Int {
        return when (choice) {
            Choice.ROCK -> R.string.rock
            Choice.SCISSORS -> R.string.scissors
            Choice.PAPER -> R.string.paper
        }
    }

    fun playGame(playerChoice: Choice) {
        val choices = Choice.values()
        val computerChoice = choices[Random.nextInt(choices.size)]

        when {
            computerChoice == Choice.PAPER -> {
                imageView.setImageResource(R.drawable.paper)
            }
            computerChoice == Choice.SCISSORS -> {
                imageView.setImageResource(R.drawable.scissor)
            }
            computerChoice == Choice.ROCK -> {
                imageView.setImageResource(R.drawable.rock)
            }
        }

        when {
            playerChoice == computerChoice -> {
                txtResult.setText(R.string.draw)
            }
            (playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER) ||
                    (playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
                    (playerChoice == Choice.PAPER && computerChoice == Choice.ROCK) -> {
                txtResult.setText(R.string.win)
            }
            else -> {
                txtResult.setText(R.string.lose)
            }
        }
    }

    var lastTime: Long = 0
    override fun finish() {
        val currentTime = System.currentTimeMillis()
        if(currentTime - lastTime > 3 * 1000){
            lastTime = currentTime
            Toast.makeText(this, "再按一下離開", Toast.LENGTH_SHORT).show()
        } else {
            super.finish()
        }

    }

}
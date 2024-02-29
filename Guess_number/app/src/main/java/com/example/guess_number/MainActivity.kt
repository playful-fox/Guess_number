package com.example.guess_number

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val result_textView = findViewById<TextView>(R.id.result)
        val guess_button = findViewById<Button>(R.id.guess)
        val reset_button = findViewById<Button>(R.id.reset)
        val editText = findViewById<EditText>(R.id.editText)
        var valid_num:Int
        val range_num :IntArray = intArrayOf(1, 100)

        var secret : Int = Random.nextInt(range_num[1]) + range_num[0]
        var guess_number : IntArray = intArrayOf(range_num[0], range_num[1])

        guess_button.setOnClickListener{
            textView.text = editText.text
            valid_num = editText.text.toString().toInt()
            var ans_str = "你猜對的囉!"

            if (valid_num > secret) {
                if(guess_number[1] > valid_num) {
                    guess_number[1] = valid_num
                }
            } else if (valid_num < secret) {
                if(guess_number[0] < valid_num){
                    guess_number[0] = valid_num
                }
            }

            if (valid_num != secret) {
                ans_str = guess_number[0].toString() + "~" + guess_number[1].toString()
            }
            textView.text = ans_str
            //Toast.makeText(this, secret.toString(), Toast.LENGTH_SHORT).show()
            Log.e("MainActivity", "error")
        }

        reset_button.setOnClickListener{
            editText.setText("0")
            secret = Random.nextInt(range_num[1]) + range_num[0]
            textView.text = "我們在猜一次!"
        }
    }
}
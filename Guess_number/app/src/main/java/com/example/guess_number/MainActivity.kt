package com.example.guess_number

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.guess_number.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var binding: ActivityMainBinding
    private val guess = Guess()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handler = Handler(Looper.getMainLooper())

        guess.setSecret()
        binding.guess.setOnClickListener{
            binding.textView.text = binding.editText.text
            guess.setValid_num(binding.editText.text.toString().toInt())
            guess.setAns_str(this.getString(R.string.guess_right))

            if (guess.compare() !=1) {
                guess.setAns_str()
                binding.textView.text = guess.getAns_str()
            } else {
                binding.textView.text = guess.getAns_str()
                handler.postDelayed({
                    Toast.makeText(this, "6秒後操作執行了!", Toast.LENGTH_SHORT).show()
                    binding.editText.setText("")
                    guess.setSecret()
                    guess.resetGuess_number()
                    binding.textView.text = this.getString(R.string.guess_again)
                }, 6000)

            }
            //Toast.makeText(this, secret.toString(), Toast.LENGTH_SHORT).show()
            //Log.e("MainActivity", "error")
        }

        binding.reset.setOnClickListener{
            binding.editText.setText("")
            guess.setSecret()
            guess.resetGuess_number()
            binding.textView.text = this.getString(R.string.guess_again)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}

class Guess {
    private var valid_num:Int = 0;
    private val range_num :IntArray = intArrayOf(1, 100)
    private var secret : Int = Random.nextInt(range_num[1]) + range_num[0]
    private var guess_number : IntArray = intArrayOf(range_num[0], range_num[1])
    private var ans_str : String = ""

    fun setValid_num(number: Int) {
        valid_num = number;
    }

    fun setAns_str(){
        ans_str = guess_number[0].toString() + "~" + guess_number[1].toString();
    }

    fun setAns_str(str: String){
        ans_str = str;
    }

    fun getAns_str() : String {
        return ans_str
    }

    fun setSecret(){
        secret = Random.nextInt(range_num[1]) + range_num[0]
    }

    fun resetGuess_number(){
        guess_number = intArrayOf(range_num[0], range_num[1])
    }

    fun compare() : Int{
        return when {
            valid_num > secret -> {
                if (guess_number[1] > valid_num) {
                    guess_number[1] = valid_num
                }
                0 // 返回 0 表示 validNum 大於 secret
            }
            valid_num < secret -> {
                if (guess_number[0] < valid_num) {
                    guess_number[0] = valid_num
                }
                -1 // 返回 -1 表示 validNum 小於 secret
            }
            else -> 1 // 如果 validNum 等於 secret，返回 1
        }
    }
}
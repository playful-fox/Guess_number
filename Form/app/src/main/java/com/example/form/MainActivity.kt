package com.example.form

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val radGrp_Gender = findViewById<RadioGroup>(R.id.radGrpGender)
        val applyData = findViewById<EditText>(R.id.editTextBirthday)
        radGrp_Gender.setOnCheckedChangeListener { _, checkedID ->
            val gender = radGrp_Gender.findViewById<RadioButton>(checkedID).text.toString()
            Toast.makeText(this, gender, Toast.LENGTH_SHORT).show()
        }

        applyData.setOnClickListener {
            val calender = Calendar.getInstance()
            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val day = calender.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this, {_, year, month, day ->
                run {
                    var format = "${setDateFormat(year, month, day)}"
                    applyData.setText(format)
                }
            }, year, month, day).show()
        }

        val checkbox1 = findViewById<CheckBox>(R.id.checkBox_bike)
        val checkbox2 = findViewById<CheckBox>(R.id.checkBox_car)
        val checkbox3 = findViewById<CheckBox>(R.id.checkBox_motorcycle)

        val bth_send = findViewById<Button>(R.id.bth_send)
        bth_send.setOnClickListener {
            var msg = ""
            if (checkbox1.isChecked) {
                msg =
                    if (msg == "") checkbox1.text.toString() else msg + "、" + checkbox1.text.toString()
            }
            if (checkbox2.isChecked) {
                msg =
                    if (msg == "") checkbox2.text.toString() else msg + "、" + checkbox2.text.toString()
            }
            if (checkbox3.isChecked) {
                msg =
                    if (msg == "") checkbox3.text.toString() else msg + "、" + checkbox3.text.toString()
            }
            //Toast.makeText(this@MainActivity, "你選的是" + msg, Toast.LENGTH_LONG).show()
            val content = "ID:${findViewById<EditText>(R.id.editTextID).text.toString()}\n" +
                    "pwd:${findViewById<EditText>(R.id.editTextTextPassword).text.toString()}\n" +
                    "Name:${findViewById<EditText>(R.id.editTextName).text.toString()}\n" +
                    "Birthday:${findViewById<EditText>(R.id.editTextBirthday).text.toString()}\n" +
                    "Gender:${findViewById<RadioButton>(radGrp_Gender.checkedRadioButtonId).text.toString()}"
            val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
            builder
                .setMessage(content + "\n" + "興趣:${msg}")
                .setTitle("輸入內容")
                .setPositiveButton("確認") { dialog, _ -> }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
    private fun setDateFormat(year:Int, month: Int, day: Int): String {
        return "$year-${month +1}-$day"
    }
}
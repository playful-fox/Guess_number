package com.example.menu

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menu.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: RecordAdapter
    companion object {
        const val REQUEST_CODE_ADD_RECORD = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarMain.inflateMenu(R.menu.menu)
        mAdapter = RecordAdapter(this, Bprecords(emptyList()))
        binding.recordRecycler.adapter = mAdapter
        binding.recordRecycler.layoutManager  = LinearLayoutManager(this)
        val records = Bprecords(mutableListOf(
            Bprecord("2024-05-09 10:00", 120, 88, 72),
            Bprecord("2024-05-09 14:00", 125, 82, 75),
            Bprecord("2024-05-10 08:00", 118, 78, 78)
        ))
         mAdapter.updateData(records)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.menu_add -> {
                return true
            }
            else -> super.onContextItemSelected(item)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_ADD_RECORD && resultCode == Activity.RESULT_OK) {
            val sys = data?.getIntExtra("sys" , 0) ?: 0
            val dia = data?.getIntExtra("dia" , 0) ?: 0
            val hr = data?.getIntExtra("hr" , 0) ?: 0
            val newRecord = Bprecord(getCurrentDataTime(), sys, dia, hr)

            mAdapter.addRecord(newRecord)

        }
    }

    fun getCurrentDataTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return current.format(formatter)
    }
}
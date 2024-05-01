package com.ashwin.android.coroutinesexception

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashwin.android.coroutinesexception.bg.BgCoroutinesActivity
import com.ashwin.android.coroutinesexception.databinding.ActivityMainBinding
import com.ashwin.android.coroutinesexception.main.MainCoroutinesActivity
import com.ashwin.android.coroutinesexception.test.TestCoroutinesActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, TestCoroutinesActivity::class.java))
        }

        binding.mainButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainCoroutinesActivity::class.java))
        }

        binding.bgButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, BgCoroutinesActivity::class.java))
        }
    }
}

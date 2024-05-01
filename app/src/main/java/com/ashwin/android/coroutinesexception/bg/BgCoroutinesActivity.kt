package com.ashwin.android.coroutinesexception.bg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashwin.android.coroutinesexception.databinding.ActivityBgCoroutinesBinding

class BgCoroutinesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBgCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBgCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

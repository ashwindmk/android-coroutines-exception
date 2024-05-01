package com.ashwin.android.coroutinesexception.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.ashwin.android.coroutinesexception.Constant
import com.ashwin.android.coroutinesexception.databinding.ActivityTestCoroutinesBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SUB_TAG = "test-coroutine"

class TestCoroutinesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testButton.setOnClickListener {
            testCoroutines()
        }

        binding.clickButton.setOnClickListener {
            println("${Constant.APP_TAG}: $SUB_TAG: click")
        }
    }

    override fun onStart() {
        println("${Constant.APP_TAG}: $SUB_TAG: onStart before super")
        super.onStart()
        println("${Constant.APP_TAG}: $SUB_TAG: onStart after super")
    }

    override fun onResume() {
        println("${Constant.APP_TAG}: $SUB_TAG: onResume before super")
        super.onResume()
        println("${Constant.APP_TAG}: $SUB_TAG: onResume after super")
    }

    private fun testCoroutines() {
        lifecycleScope.launchWhenStarted {
            println("${Constant.APP_TAG}: $SUB_TAG: launchWhenStarted start (thread = ${Thread.currentThread().name})")
            delay(5000L)
            println("${Constant.APP_TAG}: $SUB_TAG: launchWhenStarted end (thread = ${Thread.currentThread().name})")
        }

        GlobalScope.launch {
            println("${Constant.APP_TAG}: $SUB_TAG: GlobalScope.launch start (thread = ${Thread.currentThread().name})")
            delay(5000L)
            println("${Constant.APP_TAG}: $SUB_TAG: GlobalScope.launch end (thread = ${Thread.currentThread().name})")
        }

        GlobalScope.launch {
            println("${Constant.APP_TAG}: $SUB_TAG: GlobalScope.launch start (thread = ${Thread.currentThread().name})")
            delay(5000L)
            println("${Constant.APP_TAG}: $SUB_TAG: GlobalScope.launch end (thread = ${Thread.currentThread().name})")
        }
    }

    override fun onPause() {
        println("${Constant.APP_TAG}: $SUB_TAG: onPause before super")
        super.onPause()
        println("${Constant.APP_TAG}: $SUB_TAG: onPause after super")
    }

    override fun onStop() {
        println("${Constant.APP_TAG}: $SUB_TAG: onStop before super")
        super.onStop()
        println("${Constant.APP_TAG}: $SUB_TAG: onStop before super")
    }
}

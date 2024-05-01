package com.ashwin.android.coroutinesexception.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.ashwin.android.coroutinesexception.Constant
import com.ashwin.android.coroutinesexception.databinding.ActivityMainCoroutinesBinding
import kotlinx.coroutines.*
import java.lang.Exception

private const val SUB_TAG = "MainCoroutinesActivity"

class MainCoroutinesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            launchButton.setOnClickListener {
                //testCoroutines1()
                //testCoroutines2()
                //testCoroutines3()
                //testCoroutines4()
                //testCoroutines5()
                testCoroutines6()
            }
        }
    }

    private fun testCoroutines1() {
        try {
            lifecycleScope.launch {
                try {
                    println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch start (thread = ${Thread.currentThread().name})")
                    throw Exception("Forced Exception")
                    println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch end (thread = ${Thread.currentThread().name})")
                } catch (e: Exception) {
                    // We can catch the exception from within the same coroutine scope.
                    println("${Constant.APP_TAG}: $SUB_TAG: exception caught inside")
                } finally {
                    println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch finally end (thread = ${Thread.currentThread().name})")
                }
            }
        } catch (e: Exception) {
            // We can never catch the exception from outside the coroutine scope.
            // Hence this catch block is unreachable
            println("${Constant.APP_TAG}: $SUB_TAG: exception caught outside")
        }
    }

    private fun testCoroutines2() {
        lifecycleScope.launch {
            try {
                println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch start (thread = ${Thread.currentThread().name})")
                launch {
                    println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch launch start (thread = ${Thread.currentThread().name})")
                    throw Exception("Forced Exception")
                    println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch launch end (thread = ${Thread.currentThread().name})")
                }
                println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch end (thread = ${Thread.currentThread().name})")
            } catch (e: Exception) {
                // We cannot catch the exception from outside the coroutine scope.
                // Hence this catch block is unreachable.
                println("${Constant.APP_TAG}: $SUB_TAG: exception caught outside")
            } finally {
                println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch finally end (thread = ${Thread.currentThread().name})")
            }
        }
    }

    private fun testCoroutines3() {
        val def = lifecycleScope.async {
            try {
                println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch start (thread = ${Thread.currentThread().name})")
                val res = async(start = CoroutineStart.DEFAULT) {
                    println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch async start (thread = ${Thread.currentThread().name})")
                    throw Exception("Forced Exception")
                    println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch async end (thread = ${Thread.currentThread().name})")
                    "result"
                }
                println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch end (thread = ${Thread.currentThread().name})")
            } catch (e: Exception) {
                // We cannot catch the exception from outside the coroutine scope.
                // Hence this catch block is unreachable.
                println("${Constant.APP_TAG}: $SUB_TAG: exception caught outside")
            } finally {
                println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch finally end (thread = ${Thread.currentThread().name})")
            }
        }

        /*lifecycleScope.launch(Dispatchers.IO) {
            println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch start (thread = ${Thread.currentThread().name})")
            val ans = def.await()
            println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch res = $ans (thread = ${Thread.currentThread().name})")
        }*/
    }

    private fun testCoroutines4() {
        val handler = CoroutineExceptionHandler { context, error ->
            // Handle here.
            println("${Constant.APP_TAG}: $SUB_TAG: exception handled error = ${error.message} (thread = ${Thread.currentThread().name})")
        }

        lifecycleScope.launch(handler) {
            println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch start (thread = ${Thread.currentThread().name})")
            launch {
                throw Exception("Forced Exception")
            }
            println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch end (thread = ${Thread.currentThread().name})")
        }
    }

    private fun testCoroutines5() {
        val handler = CoroutineExceptionHandler { context, error ->
            // Handle here.
            // Thread is the thread of coroutine
            println("${Constant.APP_TAG}: $SUB_TAG: exception handled error = ${error.message} (thread = ${Thread.currentThread().name})")
        }

        CoroutineScope(Dispatchers.Main + handler).launch {
            println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch start (thread = ${Thread.currentThread().name})")
            supervisorScope {
                coroutineScope {
                    launch {
                        delay(1200L)
                        throw Exception("cs Forced Exception")
                    }
                    launch {
                        delay(1000L)
                        println("cs Success 2")
                    }
                    launch {
                        delay(1500L)
                        println("cs Success 3")
                    }
                }
//            supervisorScope {
//                launch {
//                    delay(1200L)
//                    throw Exception("ss Forced Exception")
//                }
//                launch {
//                    delay(1000L)
//                    println("ss Success 2")
//                }
//                launch {
//                    delay(1500L)
//                    println("ss Success 3")
//                }
            }
            println("${Constant.APP_TAG}: $SUB_TAG: lifecycleScope.launch end (thread = ${Thread.currentThread().name})")
        }
    }

    private fun testCoroutines6() {
        println("${Constant.APP_TAG}: $SUB_TAG: out = ? (thread = ${Thread.currentThread().name})")
    }
}

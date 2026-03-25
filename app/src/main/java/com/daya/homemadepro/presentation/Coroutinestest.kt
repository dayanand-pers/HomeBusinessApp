package com.daya.homemadepro.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() = coroutineScope() {


    launch {
        testCoroutine()
    }

    val sum = calculate(5, 10, ::adddtion)
    println("Sum is $sum")
}

fun calculate(a: Int, b: Int, function: (Int, Int) -> Int) {}

suspend fun testCoroutine(){
    println("Test coroutines")

    delay(1000)

    println("Test coroutines ends")


}


fun adddtion (a : Int, b : Int) : Int{
    return a.plus(b);
}


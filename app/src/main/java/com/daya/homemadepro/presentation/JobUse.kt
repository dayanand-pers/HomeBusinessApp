package com.daya.homemadepro.presentation

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() : Unit = runBlocking {

    val job = launch {
        repeat(5) {
            println("this parent message from $it")
            delay(300)
        }
    }

    val jobNew = launch(context = job) {
        repeat(3) {
            println("this child message from $it")
            throw Exception(" From child")
        }
    }


}
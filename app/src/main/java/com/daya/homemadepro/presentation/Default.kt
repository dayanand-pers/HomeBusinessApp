package com.daya.homemadepro.presentation

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking{

    var job = launch(start = CoroutineStart.UNDISPATCHED, context = Dispatchers.IO) {
        println("start co routing defualt ${Thread.currentThread().name}")
        delay(5000)
        println("end co routing defualt ${Thread.currentThread().name}")
    }

//    job.cancel()


}

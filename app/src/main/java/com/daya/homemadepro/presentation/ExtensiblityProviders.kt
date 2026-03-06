package com.daya.homemadepro.presentation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() : Unit = runBlocking {

    val job = launch(start = CoroutineStart.LAZY) {

        repeat(5) {
            println("this first message from $it")
            delay(1000)

        }
    }

//    job.join()
//    job.start()
    job.cancel(CancellationException("Daya canceled it"))

    job.invokeOnCompletion { cause ->
        cause?.let {

            println(it.message)
        }?:run {
            println("This is invoke on completion")
        }

    }
    println("Job completed from main")
}
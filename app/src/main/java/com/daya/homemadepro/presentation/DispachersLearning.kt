package com.daya.homemadepro.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() : Unit = runBlocking{


    /*launch (context = Dispatchers.Unconfined){

        println("Thread name ${Thread.currentThread().name}")
        delay(1000)
        println("Thread name ${Thread.currentThread().name}")
    }*/


    launch (context = Dispatchers.Unconfined){

        println("Thread name ${Thread.currentThread().name}")
        val contextret = withContext(Dispatchers.IO) {
            println("Thread name ${Thread.currentThread().name}")
            delay(1000)
            println("Thread name ${Thread.currentThread().name}")

            return@withContext 5
        }
        println("return from context $contextret")
    }

}
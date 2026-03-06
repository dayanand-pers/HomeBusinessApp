package com.daya.homemadepro.presentation

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.jvm.Throws

fun main(): Unit = runBlocking{

    coroutineScope {
        launch {
            println("Coroutine 1 executed")
        }

        /*launch {
            throw Exception("This is exception from coroutine scope")
        }*/

        launch {
            println("Coroutine 2 executed")
        }

    }


    supervisorScope {
        launch {
            println(" supervisorScope Coroutine 1 executed")
        }

        launch {
            throw Exception(" Exception from supervisor scope")
        }

        launch {
            println(" supervisorScope Coroutine 2 executed")
        }

    }


}
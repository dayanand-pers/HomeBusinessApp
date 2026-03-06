package com.daya.homemadepro.presentation

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main(): Unit = runBlocking{

    supervisorScope {
        var dispachers = Dispatchers.IO;
//        var job = Job();
        var exceptionHandler = CoroutineExceptionHandler{context, excep->
            println(excep.message)
        }

        var coroutinecontext = dispachers+exceptionHandler
        launch(coroutinecontext) {
            throw Exception("This is Dayas coroutine exception")
        }
    }



}
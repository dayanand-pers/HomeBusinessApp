package com.daya.homemadepro.presentation

import com.daya.homemadepro.data.modal.Domain
import kotlinx.coroutines.Delay
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() : Unit = runBlocking{


    launch {
        var domain = getUserInfo("First", 1000)
        println("Middel state "+domain)
        getUserInfo("Second", 3000)
    }

    var firstDom = async { getUserInfo("First", 4000)}
    println(firstDom.await())


}

suspend fun getUserInfo(domain: String, delay: Long): Domain{

    delay(delay)
    return Domain("123", "1",domain)
}
package com.daya.homemadepro.Di

import javax.inject.Inject

class DiAnalaytics @Inject constructor() {

    fun logEvents(event : String){
        println("$event logged")
    }
}


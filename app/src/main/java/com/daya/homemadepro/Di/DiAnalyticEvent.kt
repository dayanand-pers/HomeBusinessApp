package com.daya.homemadepro.Di

import javax.inject.Inject

class DiAnalyticEvent @Inject constructor(
    private val diAnalytic: DiAnalaytics
){

    fun trackScrinview( screenName: String){
        diAnalytic.logEvents("scrinview : $screenName")

    }
}
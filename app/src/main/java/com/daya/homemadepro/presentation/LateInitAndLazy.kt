package com.daya.homemadepro.presentation

fun main(){

    val lateInitClass = LateInitClass()
    lateInitClass.setLateInitValue("Late init value")
    lateInitClass.setLateInitValue("New late init value reassigned")

    val lazyinit = LazyInit()

    println(lazyinit.myInitValuefromLazy)


    println(lazyinit.myInitValuefromLazy)



}

class LateInitClass {


    lateinit var lateInitValueBy : String


    fun setLateInitValue(strValue: String){
        lateInitValueBy = strValue;

        println("Set late init value $lateInitValueBy")

    }
}

class LazyInit{

    val myInitValuefromLazy : String by lazy {
        println("Lazy init value started")
        "My first lazy init program"
    }


}
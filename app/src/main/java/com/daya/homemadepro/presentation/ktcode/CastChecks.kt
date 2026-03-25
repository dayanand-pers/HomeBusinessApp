package com.daya.homemadepro.presentation.ktcode


fun main(){


    val int = 11323;
    checkAndCast(int)

    val software = employee.Electric()
    software.employeeType()

    var array = arrayOf(1,2,3,4,5,6,7,8,9,10)

    for(ar in 0 until array.size){
        println(ar)
    }

    for(ar in array.indices) {
        println(ar)
    }

    for (ar in array.size downTo 0 ){
        println(ar)
    }

}

fun checkAndCast(abj : Any){

    if (abj is String){
        println("Object os String type ${abj.length}")
    }

//    println("Object os String type ${abj.length}")



}

sealed class employee{

    class Software : employee(){
        fun employeeType(){
            println("Software employee")

        }
    }

    class Mechanical : employee(){
        fun employeeType(){
            println("Mechanical employee")
        }
    }

    class Electric : employee(){
        fun employeeType(){
            println("Electric employee")
        }
    }
}
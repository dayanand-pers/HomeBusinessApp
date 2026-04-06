package com.daya.homemadepro.presentation.ktcode

fun main() {

    println("Factorial of 5 is ${factorial(5)}")

    println("Number is palndrom ${isPalndromNumber(5.toString())}")

    println("Largest number is ${findLagrestNumber(listOf(1,2,13,4,5,6,7,8,9))}")

    println("Ovels in given string is ${countVovalsInString("Daya")}")

    swapTwoNum()

    println("Second largest number is ${findSecondLargest(listOf(1,2,13,4,5,6,17,18,9))}")

}

fun factorial(num: Int ) : Int{
    return if (num == 0) 1 else num* factorial(num-1)
}

fun isPalndromNumber(num : String) : Boolean{
 return num == num.reversed()
}

fun isPalndromString(str : String) : Boolean{
    return str == str.reversed()
}

fun findLagrestNumber(num : List<Int>) : Int? {
    return num.maxOrNull();
}

fun countVovalsInString(str : String) : Int{
    return str.count{ it in "aeiouAEIOU" }
}

fun swapTwoNum(){

    var num1= 5
    var num2 =8;

    println("Value of num1 $num1 and num2 $num2")

    num1 = num1 + num2
    num2 = num1 - num2
    num1 = num1 - num2

    println("Value of num1 $num1 and num2 $num2")
}


fun findSecondLargest(numbers: List<Int>): Int? {
    return numbers.sortedDescending()[1];
}
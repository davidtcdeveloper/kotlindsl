package com.davidtiago.kotlindsl.samplecode

class RegularClass {
    fun regularFunction() {
        println("This is a regular function")
    }
}

fun topLevel() {
    println("This is a top level")
}

val functionAsVariable: (Int) -> Unit = { parameter ->
    print("Function as variable parameter=$parameter")
}

val inferredFunctionAsVariable = { parameter: Int ->
    print("Function as variable parameter=$parameter")
}

fun highOrderFunction(parameterFunction: (Int) -> String): () -> String {
    val result = parameterFunction(10)
    return { result }
}

val callHighOrder = highOrderFunction(
    parameterFunction = { input -> input.toString() }
)

val callLambda = highOrderFunction { input ->
    input.toString()
}

fun invokeVariable() {
    callHighOrder()
    callHighOrder.invoke()

    callLambda()
}

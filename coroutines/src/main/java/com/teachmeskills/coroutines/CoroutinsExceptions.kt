package com.teachmeskills.coroutines

import kotlinx.coroutines.*
import java.lang.Exception

fun main() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }
    val scope = CoroutineScope(Dispatchers.IO + exceptionHandler + SupervisorJob())

    scope.launch {

        try {
            async {
                delay(1000)
                error("Some error")
            }.await()
        } catch (e: Exception) {
            println(e.message)
        }

    }
    scope.launch {
        delay(2000)
        println("I'm alive")
    }

    Thread.sleep(3000)
    println(scope.isActive)
}
package com.teachmeskills.coroutines

import kotlinx.coroutines.*
import java.lang.Exception

fun main() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }
    val scope = CoroutineScope(Dispatchers.IO + exceptionHandler + SupervisorJob())

    scope.launch {
        withContext(Dispatchers.Default) {
            delay(2000)
            println("I am here 1")
        }
        println("I am here 2")
    }

    Thread.sleep(3000)
}
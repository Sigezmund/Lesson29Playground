package com.teachmeskills.coroutines

import kotlinx.coroutines.*

fun main() {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        val asyncDeferred = async {
            delay(1000)
            "coroutine 1"
        }

        val job = launch {
            delay(1000)
            println("coroutine 2")
        }


        val result = asyncDeferred.await()
        println(result)
        job.join()
        println("all ended")
    }


    scope.cancel()
    Thread.sleep(5000)

}

fun syncTest() {
    val scope = CoroutineScope(Dispatchers.IO)

    scope.launch {
        val data1Deferred = async {  getData1() }
        val data2Deferred = async {  getData2() }
        val data1 = data1Deferred.await()
        val data2 = data2Deferred.await()
    }
}

suspend fun getData1(): String {
    delay(1000)
    return "Data1"
}

suspend fun getData2(): String {
    delay(1500)
    return "Data2"
}


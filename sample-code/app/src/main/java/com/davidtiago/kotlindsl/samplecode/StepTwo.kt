package com.davidtiago.kotlindsl.samplecode

import java.io.IOException
import okhttp3.*

/*
 * client.newCall is not required anymore
 */
fun OkHttpClient.call(block: () -> Request): Response = this.newCall(block()).execute()

class UsingStepTwo {

    private val client = OkHttpClient()

    fun httpGet() {
        client.call {
                request { builder ->
                    builder.url("https://publicobject.com/helloworld.txt")
                    builder.addHeader("headerKey", "headerValue")
                }
            }
            .use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                println(response.body?.string())
            }
    }
}

fun usingStepTwo() {
    val request = request { builder ->
        builder.url("https://publicobject.com/helloworld.txt")
        builder.addHeader("headerKey", "headerValue")
    }
    // ...
}

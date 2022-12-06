package com.davidtiago.kotlindsl.samplecode

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

fun OkHttpClient.call(block: () -> Request): Response = this.newCall(block()).execute()

class UsingStepTwo {

    private val client = OkHttpClient()

    fun httpGet() {
        client
            .call {
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

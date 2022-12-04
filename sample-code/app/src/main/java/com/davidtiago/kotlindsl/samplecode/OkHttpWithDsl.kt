package com.davidtiago.kotlindsl.samplecode

import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Sample calls extracted from OkHttp Samples:
 * https://github.com/square/okhttp/tree/master/samples/guide/src/main/java/okhttp3/recipes
 */
class OkHttpStandard{

    private val client = OkHttpClient()

    fun httpGet() {
        val request = Request.Builder()
            .url("https://publicobject.com/helloworld.txt")
            .addHeader("headerKey", "headerValue")
            .build()

        val call = client.newCall(request)

        call.execute()
            .use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                println(response.body?.string())
            }
        }
}

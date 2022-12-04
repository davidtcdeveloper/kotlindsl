package com.davidtiago.kotlindsl.samplecode

import java.io.IOException
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class UsingStepFour {

    private val client = OkHttpClient()

        fun wrongCall() {
        
            val JSON: MediaType = "application/json".toMediaType()
            val body = "{}".toRequestBody(JSON)
            
            // Wrong call: adds method GET and post body
            client.call {
                requestWithReceiver {
                url("https://publicobject.com/helloworld.txt")
                addHeader("headerKey", "headerValue")
                method("Get", null)
                post(body)
            }
        }
    }

    fun httpGet() {
        client
                .call {
                    requestWithReceiver {
                        url("https://publicobject.com/helloworld.txt")
                        addHeader("headerKey", "headerValue")
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

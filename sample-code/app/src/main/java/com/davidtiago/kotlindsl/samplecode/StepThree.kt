package com.davidtiago.kotlindsl.samplecode

import okhttp3.*

fun requestWithReceiver(block: Request.Builder.() -> Unit): Request =
        Request.Builder().also { builder -> builder.block() }.build()

private val client = OkHttpClient()

fun usingStepThree() {
    client.call {
        requestWithReceiver {
            url("https://publicobject.com/helloworld.txt")
            addHeader("headerKey", "headerValue")
        }
    }
}

package com.davidtiago.kotlindsl.samplecode

import okhttp3.*

/*
 * Request.Builder is not required anymore
 */
fun request(block: (Request.Builder) -> Unit): Request =
        Request.Builder().also { builder -> block(builder) }.build()

fun usingStepOne() {
    val request = request { builder ->
        builder.url("https://publicobject.com/helloworld.txt")
        builder.addHeader("headerKey", "headerValue")
    }
    // ...
}

package com.davidtiago.kotlindsl.samplecode

import okhttp3.*

/*
 * Request.Builder is not required anymore
 */
fun request(block: (Request.Builder) -> Unit): Request =
        Request.Builder().also { builder -> block(builder) }.build()

fun `using step1`() {
    val request = request { builder ->
        builder.url("https://publicobject.com/helloworld.txt")
        builder.addHeader("headerKey", "headerValue")
    }
    // ...
}

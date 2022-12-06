package com.davidtiago.kotlindsl.samplecode

import okhttp3.Request

fun requestWithReceiver(block: Request.Builder.() -> Unit): Request =
    Request.Builder().also { builder -> builder.block() }.build()

fun usingStepTwo() {
    requestWithReceiver {
        url("https://publicobject.com/helloworld.txt")
        addHeader("headerKey", "headerValue")
    }
}

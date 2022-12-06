@file:JvmName("StepFiveKt")

package com.davidtiago.kotlindsl.samplecode

import okhttp3.Request

fun requestWithReceiver(block: Request.Builder.() -> Unit): Request =
    Request.Builder().also { builder -> builder.block() }.build()

fun afterStepFour() {
    requestWithReceiver {
        url("https://api.github.com/repos/square/okhttp/issues")
        headers {
            ("User-Agent" to "OkHttp Headers.java") +
                    ("Accept" to "application/json; q=0.5") +
                    ("Accept" to "application/vnd.github.v3+json")

        }
    }
}

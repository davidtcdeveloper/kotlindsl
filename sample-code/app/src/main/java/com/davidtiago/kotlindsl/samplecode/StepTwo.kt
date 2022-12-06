package com.davidtiago.kotlindsl.samplecode

import okhttp3.Request

fun beforeStepTwo() {
    request { builder ->
        builder.url("https://api.github.com/repos/square/okhttp/issues")
        builder.header("User-Agent", "OkHttp Headers.java")
        builder.addHeader("Accept", "application/json; q=0.5")
        builder.addHeader("Accept", "application/vnd.github.v3+json")
    }
}

fun Request.Builder.headers(block: () -> List<Pair<String, String>>): Request.Builder {
    block().forEach {
        addHeader(it.first, it.second)
    }
    return this
}

fun afterStepTwo() {
    request { builder ->
        builder.url("https://api.github.com/repos/square/okhttp/issues")
        builder.headers {
            listOf(
                "User-Agent" to "OkHttp Headers.java",
                "Accept" to "application/json; q=0.5",
                "Accept" to "application/vnd.github.v3+json",
            )
        }
    }
}

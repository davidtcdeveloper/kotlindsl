package com.davidtiago.kotlindsl.samplecode

operator fun Pair<String, String>.plus(
    another: Pair<String, String>
): List<Pair<String, String>> =
    listOf(another, this)

fun afterStepThree() {
    request { builder ->
        builder.url("https://api.github.com/repos/square/okhttp/issues")
        builder.headers {
            ("User-Agent" to "OkHttp Headers.java") +
                    ("Accept" to "application/json; q=0.5") +
                    ("Accept" to "application/vnd.github.v3+json")

        }
    }
}

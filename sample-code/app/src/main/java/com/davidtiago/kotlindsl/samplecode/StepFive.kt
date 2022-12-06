package com.davidtiago.kotlindsl.samplecode

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

private val client = OkHttpClient()

fun beforeStepFive() {

    val jsonMediaType: MediaType = "application/json".toMediaType()
    val body = "{}".toRequestBody(jsonMediaType)

    // Wrong call: adds method GET and post body, multiple URLs and so on
    requestWithReceiver {
        url("https://publicobject.com/helloworld.txt")
        url("https://api.github.com/repos/square/okhttp/issues")
        headers {
            ("User-Agent" to "OkHttp Headers.java") +
                    ("Accept" to "application/json; q=0.5") +
                    ("Accept" to "application/vnd.github.v3+json")

        }
        method("Get", null)
        post(body)
    }
}

class RequestBuildReceiver(val requestBuilder: Request.Builder)

infix fun RequestBuildReceiver.get(url: String): GetBuildReceiver =
    GetBuildReceiver(requestBuilder.url(url))

class GetBuildReceiver(val requestBuilder: Request.Builder)

infix fun GetBuildReceiver.withHeaders(headers: () -> List<Pair<String, String>>): Request =
    requestBuilder.headers(headers).build()

fun requestTyped() {
    //TODO
}

fun afterStepFive() {
    // TODO
}

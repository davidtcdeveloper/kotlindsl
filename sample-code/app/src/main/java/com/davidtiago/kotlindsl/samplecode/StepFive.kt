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

interface BuilderReceiverMethod {
    val requestBuilder: Request.Builder
}

internal fun BuilderReceiverMethod.buildRequest() = requestBuilder.build()

class RequestBuildReceiver(override val requestBuilder: Request.Builder): BuilderReceiverMethod

fun RequestBuildReceiver.get(block: ()->String): GetBuildReceiver =
    GetBuildReceiver(requestBuilder.url(block()))

class GetBuildReceiver(override val requestBuilder: Request.Builder): BuilderReceiverMethod

class FinalBuilderReceiver(override val requestBuilder: Request.Builder): BuilderReceiverMethod

infix fun GetBuildReceiver.withHeaders(headers: () -> List<Pair<String, String>>): FinalBuilderReceiver =
    FinalBuilderReceiver(requestBuilder.headers(headers))

fun requestTyped(block: RequestBuildReceiver.() -> BuilderReceiverMethod) {
    RequestBuildReceiver(
        Request.Builder()
    ).block()
    .buildRequest()
}

fun afterStepFive() {
    requestTyped {
        get {
            "https://publicobject.com/helloworld.txt"
        } withHeaders {
            ("User-Agent" to "OkHttp Headers.java") +
                    ("Accept" to "application/json; q=0.5") +
                    ("Accept" to "application/vnd.github.v3+json")
        }
    }
}

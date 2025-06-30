package com.gameZone.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    const val TIME_OUT = 3000L
    fun getInstance(): HttpClient {
        return HttpClient {
            defaultRequest {
                url {
                    host = "rickandmortyapi.com"
                    protocol = URLProtocol.HTTPS
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            }
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                socketTimeoutMillis = TIME_OUT
                connectTimeoutMillis = TIME_OUT
                requestTimeoutMillis = TIME_OUT
            }

        }
    }
}
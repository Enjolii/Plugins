package com.ritense.valtimo.kvk.client

import com.ritense.kvkauthentication.plugin.KvkHandelsregisterAuthPlugin
import com.ritense.valtimo.kvk.model.ZoekenResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClientResponseException

class SimpleKvkHandelsregisterClient {

    fun search(
        kvkNumber: String,
        baseUrl: String,
        authenticationPlugin: KvkHandelsregisterAuthPlugin
    ): ZoekenResponse? {
        return try {
            val restClient = buildRestClient(baseUrl, authenticationPlugin)
            restClient.get()
                .uri { uriBuilder ->
                    uriBuilder.path("/api/v2/zoeken")
                        .queryParam("kvkNummer", kvkNumber)
                        .build()
                }
                .retrieve()
                .body(ZoekenResponse::class.java)
        } catch (e: WebClientResponseException) {
            logger.error { "HTTP Error: ${e.statusCode.value()} - ${e.message}" }
            null
        } catch (e: Exception) {
            logger.error { "Error occurred while fetching data from KVK API: ${e.message}" }
            null
        }
    }

    fun buildRestClient(baseUrl: String, authenticationPlugin: KvkHandelsregisterAuthPlugin): RestClient {
        return authenticationPlugin.applyAuth(RestClient.builder())
            .baseUrl(baseUrl)
            .build()
    }

    companion object {
        val logger = KotlinLogging.logger {}
    }
}

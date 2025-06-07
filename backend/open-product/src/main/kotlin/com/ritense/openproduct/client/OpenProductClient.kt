package com.ritense.openproduct.client

import com.ritense.tokenauthentication.plugin.TokenAuthenticationPlugin
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@Component
class OpenProductClient() {

    fun createProduct(
        baseUrl: String,
        authenticationPlugin: TokenAuthenticationPlugin,
        request: ProductRequest
    ) {
        val restClient = getRestclient(baseUrl, authenticationPlugin)
        val objectMapper = jacksonObjectMapper()
        val requestJson = objectMapper.writeValueAsString(request)

        restClient.post()
            .uri("/producten")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(requestJson)  // Use the JSON string instead of the object
            .retrieve()
            .toEntity(String::class.java)
            ?: throw IllegalStateException("Failed to create product")
    }

    fun getRestclient(baseUrl: String, authenticationPlugin: TokenAuthenticationPlugin) : RestClient {
        return authenticationPlugin.applyAuth(RestClient.builder())
            .baseUrl(baseUrl)
            .build()
    }

}

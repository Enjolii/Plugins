package com.ritense.valtimo.kvk.service

import com.ritense.kvkauthentication.plugin.KvkHandelsregisterAuthPlugin
import com.ritense.valtimo.kvk.client.SimpleKvkHandelsregisterClient
import com.ritense.valtimo.kvk.model.ZoekenDto
import javassist.NotFoundException

class KvkHandelsregisterService(
    private val kvkHandelsregisterClient: SimpleKvkHandelsregisterClient
) {

    fun zoeken(
        handelsregisterBaseUrl: String, kvkNumber: String, authenticationPlugin: KvkHandelsregisterAuthPlugin
    ): List<ZoekenDto> {
        val result = kvkHandelsregisterClient.search(kvkNumber, handelsregisterBaseUrl, authenticationPlugin)

        return if (result?.resultaten?.isNotEmpty() == true) {
            result.resultaten.map {
                ZoekenDto(
                    it.kvkNummer,
                    it.naam,
                    it.type
                )
            }
        } else {
            throw NotFoundException("no result found by kvk number")
        }
    }
}

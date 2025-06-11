/*
 * Copyright 2015-2024 Ritense BV, the Netherlands.
 *
 * Licensed under EUPL, Version 1.2 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ritense.openproduct.plugin

import com.ritense.openproduct.client.*
import com.ritense.plugin.annotation.Plugin
import com.ritense.plugin.annotation.PluginAction
import com.ritense.plugin.annotation.PluginActionProperty
import com.ritense.plugin.annotation.PluginProperty
import com.ritense.processlink.domain.ActivityTypeWithEventName
import com.ritense.tokenauthentication.plugin.TokenAuthenticationPlugin
import org.camunda.bpm.engine.delegate.DelegateExecution


@Plugin(
    key = "openproduct",
    title = "Open Product",
    description = "Plugin for interacting with the Open Product API"
)
class OpenProductPlugin(
    val openProductClient: OpenProductClient
) {

    @PluginProperty(key = "baseUrl", secret = false, required = true)
    lateinit var baseUrl: String

    @PluginProperty(key = "authenticationPluginConfiguration", secret = false)
    lateinit var authenticationPluginConfiguration: TokenAuthenticationPlugin

    @PluginAction(
        key = "create-product",
        title = "Create product plugin action",
        description = "Create product plugin action",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START],
    )
    fun createProduct(
        execution: DelegateExecution,
        @PluginActionProperty productName: String,
        @PluginActionProperty productTypeUUID: String,
        @PluginActionProperty ownerBSN: String,
        @PluginActionProperty published: Boolean,
        @PluginActionProperty productPrice: String,
        @PluginActionProperty frequentie: String,
        @PluginActionProperty status: String,
        @PluginActionProperty resultPV: String,
    ) {

        val freqenum = toFreqEnum(frequentie)
        val statusEnum = toStatusEnum(status)

        val result = openProductClient.createProduct(
            baseUrl,
            authenticationPluginConfiguration,
            ProductRequest(
                naam = productName,
                producttypeUuid = productTypeUUID,
                eigenaren = listOf(
                    EigenaarRequest(
                        bsn = ownerBSN
                    )
                ),
                gepubliceerd = published,
                prijs = productPrice,
                frequentie = freqenum,
                status = statusEnum,
            )
        )

        execution.setVariable(resultPV, result)
        println("Product created with name: $productName saved in the procesvariabele: $resultPV")
    }

    @PluginAction(
        key = "update-product",
        title = "Update product plugin action",
        description = "Update product plugin action",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START],
    )
    fun updateProduct(
        execution: DelegateExecution,
        @PluginActionProperty productUUID: String,
        @PluginActionProperty productName: String,
        @PluginActionProperty productTypeUUID: String,
        @PluginActionProperty ownerBSN: String,
        @PluginActionProperty published: Boolean,
        @PluginActionProperty productPrice: String,
        @PluginActionProperty frequentie: String,
        @PluginActionProperty status: String,
        @PluginActionProperty resultPV: String
    ) {
        val requestMap = mutableMapOf<String, Any>()

        if (productUUID.isNotBlank()) requestMap["uuid"] = productUUID
        if (productName.isNotBlank()) requestMap["naam"] = productName
        if (productTypeUUID.isNotBlank()) requestMap["producttypeUuid"] = productTypeUUID
        if (ownerBSN.isNotBlank()) requestMap["eigenaren"] = listOf(mapOf("bsn" to ownerBSN))
        requestMap["gepubliceerd"] = published
        if (productPrice.isNotBlank()) requestMap["prijs"] = productPrice
        if (frequentie.isNotBlank()) requestMap["frequentie"] = toFreqEnum(frequentie)
        if (status.isNotBlank()) requestMap["status"] = toStatusEnum(status)

        println(requestMap)

        val result = openProductClient.updateProduct(
            baseUrl,
            authenticationPluginConfiguration,
            requestMap
        )

        execution.setVariable(resultPV, result)
        println("Product updated with UUID: $productUUID saved in the procesvariabele: $resultPV")
    }

    fun toFreqEnum(frequentie: String): FrequentieEnum {
        return when (frequentie) {
            "eenmalig" -> FrequentieEnum.EENMALIG
            "jaarlijks" -> FrequentieEnum.JAARLIJKS
            "maandelijks" -> FrequentieEnum.MAANDELIJKS
            else -> throw IllegalArgumentException("Invalid frequency: $frequentie")
        }
    }

    fun toStatusEnum(status: String): StatusEnum {
        return when (status) {
            "initieel" -> StatusEnum.INITIEEL
            "gereed" -> StatusEnum.GEREED
            "actief" -> StatusEnum.ACTIEF
            "ingetrokken" -> StatusEnum.INGETROKKEN
            "geweigerd" -> StatusEnum.GEWEIGERD
            "verlopen" -> StatusEnum.VERLOPEN
            else -> throw IllegalArgumentException("Invalid status: $status")
        }
    }
}

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

import com.ritense.openproduct.client.EigenaarRequest
import com.ritense.openproduct.client.FrequentieEnum
import com.ritense.openproduct.client.OpenProductClient
import com.ritense.openproduct.client.ProductRequest
import com.ritense.openproduct.client.StatusEnum
import com.ritense.plugin.annotation.Plugin
import com.ritense.plugin.annotation.PluginAction
import com.ritense.plugin.annotation.PluginActionProperty
import com.ritense.plugin.annotation.PluginProperty
import com.ritense.processlink.domain.ActivityTypeWithEventName
import com.ritense.tokenauthentication.plugin.TokenAuthenticationPlugin


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
        @PluginActionProperty message: String
    ) {
        openProductClient.createProduct(
            baseUrl,
            authenticationPluginConfiguration,
            ProductRequest(
                naam = "test1",
                producttypeUuid = "9903945d-b639-49da-85ed-7305817f47ad",
                eigenaren = listOf(
                    EigenaarRequest(
                        bsn = "111111110"
                    )
                ),
                gepubliceerd = true,
                prijs = "1.00",
                frequentie = FrequentieEnum.MAANDELIJKS,
                status = StatusEnum.ACTIEF,
            )
        )
    }
}

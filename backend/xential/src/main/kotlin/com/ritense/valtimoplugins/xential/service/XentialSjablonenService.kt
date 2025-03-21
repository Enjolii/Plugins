/*
 * Copyright 2015-2025 Ritense BV, the Netherlands.
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

package com.ritense.valtimoplugins.xential.service

import com.ritense.plugin.service.PluginService
import com.ritense.valtimoplugins.mtlssslcontext.MTlsSslContext
import com.ritense.valtimoplugins.mtlssslcontext.plugin.MTlsSslContextPlugin
import com.ritense.valtimoplugins.xential.plugin.XentialPlugin
import com.rotterdam.esb.xential.model.Sjabloonitems
import org.springframework.web.client.RestClient

class XentialSjablonenService(
    private val pluginService: PluginService,
    private val esbClient: OpentunnelEsbClient
) {

    fun getTemplateList(sjabloongroepId: String?): Sjabloonitems {

        pluginService.findPluginConfiguration(MTlsSslContextPlugin::class.java) {
            true
        }.let { mLTSPlugin ->
            val mTlsSslContextPlugin = mLTSPlugin?.let { pluginService.createInstance(it.id) } as MTlsSslContextPlugin
            pluginService.findPluginConfigurations(XentialPlugin::class.java).first().let { plugin ->
                val xentialPlugin = pluginService.createInstance(plugin.id) as XentialPlugin
                esbClient.createRestClient(
                    baseUrl = xentialPlugin.baseUrl.toString(),
                    applicationName = xentialPlugin.applicationName,
                    applicationPassword = xentialPlugin.applicationPassword,
                    mTlsSslContextPlugin.createSslContext()
                )
                val api = esbClient.documentApi(restClient(mTlsSslContextPlugin))
                return api.geefSjablonenlijst(
                    gebruikersId = xentialPlugin.gebruikersId,
                    sjabloongroepId = sjabloongroepId.takeIf { !it.isNullOrBlank()}?: xentialPlugin.templateGroupId
                )
            }
        }
    }

    private fun restClient(mTlsSslContextAutoConfiguration: MTlsSslContext?): RestClient {
        pluginService.findPluginConfigurations(XentialPlugin::class.java).first().let { plugin ->
            val xentialPlugin = pluginService.createInstance(plugin.id) as XentialPlugin
            return esbClient.createRestClient(
                baseUrl = xentialPlugin.baseUrl.toString(),
                applicationName = xentialPlugin.applicationName,
                applicationPassword = xentialPlugin.applicationPassword,
                mTlsSslContextAutoConfiguration?.createSslContext()
            )
        }
    }
}

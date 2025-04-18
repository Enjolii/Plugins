/*
 * Copyright 2015-2022 Ritense BV, the Netherlands.
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

package com.ritense.valtimoplugins.slack.autoconfiguration

import com.ritense.plugin.service.PluginService
import com.ritense.resource.service.TemporaryResourceStorageService
import com.ritense.valtimoplugins.slack.client.SlackClient
import com.ritense.valtimoplugins.slack.plugin.SlackPluginFactory
import io.netty.handler.logging.LogLevel
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat

@AutoConfiguration
class SlackAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SlackClient::class)
    fun slackClient(
        restClientBuilder: RestClient.Builder,
    ): SlackClient {
        return SlackClient(restClientBuilder, null, null)
    }

    @Bean
    @ConditionalOnMissingBean(SlackPluginFactory::class)
    fun slackPluginFactory(
        pluginService: PluginService,
        slackClient: SlackClient,
        storageService: TemporaryResourceStorageService,
    ): SlackPluginFactory {
        return SlackPluginFactory(pluginService, slackClient, storageService)
    }

}

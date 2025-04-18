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

package com.ritense.valtimoplugins.spotler.autoconfiguration

import com.ritense.plugin.service.PluginService
import com.ritense.valtimoplugins.spotler.config.SpotlerConfigurationProperties
import com.ritense.valtimoplugins.spotler.plugin.SpotlerPluginFactory
import java.time.Duration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
@EnableConfigurationProperties(SpotlerConfigurationProperties::class)
class SpotlerConfiguration {

    @Bean
    @ConditionalOnMissingBean(SpotlerPluginFactory::class)
    fun spotlerPluginFactory(
        pluginService: PluginService,
        restTemplate: RestTemplate
    ) = SpotlerPluginFactory(
        pluginService,
        restTemplate
    )

    @Bean
    @ConditionalOnMissingBean(RestTemplate::class)
    fun restTemplate(
        spotlerConfigurationProperties: SpotlerConfigurationProperties
    ): RestTemplate {
        return RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(spotlerConfigurationProperties.connectTimeout ?: 60))
            .setReadTimeout(Duration.ofSeconds(spotlerConfigurationProperties.connectTimeout ?: 60))
            .build()
    }
}

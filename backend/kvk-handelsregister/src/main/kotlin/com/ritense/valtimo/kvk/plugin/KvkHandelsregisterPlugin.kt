package com.ritense.valtimo.kvk.plugin

import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ritense.kvkauthentication.plugin.KvkHandelsregisterAuthPlugin
import com.ritense.plugin.annotation.Plugin
import com.ritense.plugin.annotation.PluginAction
import com.ritense.plugin.annotation.PluginActionProperty
import com.ritense.plugin.annotation.PluginProperty
import com.ritense.processlink.domain.ActivityTypeWithEventName
import com.ritense.valtimo.kvk.service.KvkHandelsregisterService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.camunda.bpm.engine.delegate.DelegateExecution

@Plugin(
    key = "kvk-handelsregister",
    title = "Kvk Handelsregister Plugin",
    description = "Kvk Handelsregister Kamer van Koophandel API plugin"
)
@Suppress("UNUSED")
class KvkHandelsregisterPlugin(
    private val kvkHandelsregisterService: KvkHandelsregisterService
) {
    @PluginProperty(key = "handelsregisterBaseUrl", secret = false, required = true)
    lateinit var handelsregisterBaseUrl: String

    @PluginProperty(key = "authenticationPluginConfiguration", secret = false, required = true)
    lateinit var authenticationPluginConfiguration: KvkHandelsregisterAuthPlugin

    @PluginAction(
        key = "zoeken-op-kvk-nummer",
        title = "Kvk Zoeken",
        description = "KvK API zoeken",
        activityTypes = [ActivityTypeWithEventName.SERVICE_TASK_START]
    )
    fun handelsregisterZoeken(
        @PluginActionProperty kvkNummer: String,
        @PluginActionProperty resultProcessVariableName: String,
        execution: DelegateExecution
    ) {

        logger.info { "Looking for kvk nummer for case ${execution.businessKey}" }

        if (!kvkNummer.isValidKvkNumber()) {
            logger.info { "Provided Kvknumber is invalid for case ${execution.businessKey}" }
            return
        }

        try {
            kvkHandelsregisterService.zoeken(
                handelsregisterBaseUrl,
                kvkNummer,
                authenticationPluginConfiguration
            ).let {
                execution.processInstance.setVariable(
                    resultProcessVariableName, objectMapper.convertValue(it)
                )
            }
        } catch (e: Exception) {
            logger.info { "Error zoeken ${e.message}" }
        }
    }

    private fun String.isValidKvkNumber() = kvkRegex.matches(this)

    companion object {
        private val kvkRegex = Regex("""\d{8}""")
        private val logger = KotlinLogging.logger { }
        private val objectMapper = jacksonObjectMapper().findAndRegisterModules()

    }
}

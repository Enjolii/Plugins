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
 *
 */

import {PluginSpecification} from '@valtimo/plugin';
import {
    KvkHandelsregisterAuthConfigurationComponent
} from './components/kvk-handelsregister-auth-configuration/kvk-handelsregister-auth-configuration.component';
import {KVK_HANDELSREGISTER_AUTH_PLUGIN_LOGO_BASE64} from './assets';

const kvkHandelsregisterAuthPluginSpecification: PluginSpecification = {
    pluginId: 'kvk-handelsregister-auth',
    pluginConfigurationComponent: KvkHandelsregisterAuthConfigurationComponent,
    pluginLogoBase64: KVK_HANDELSREGISTER_AUTH_PLUGIN_LOGO_BASE64,
    pluginTranslations: {
        nl: {
            title: 'Kvk Handelsregister Authenticatie',
            description:
                'Deze plugin maakt het mogelijk om te authenticeren bij de Kvk Handelsregister API met een API sleutel.',
            configurationTitle: 'Configuratienaam',
            configurationTitleTooltip:
                'Onder deze naam zal de plugin te herkennen zijn in de rest van de applicatie',
            apikey: 'De API sleutel voor de Kvk Handelsregister API plugin',
        },
        en: {
            title: 'Kvk Handelsregister Authentication',
            description:
                'This plugin allows authentication with the Kvk Handelsregister API using an API key.',
            configurationTitle: 'Configuration name',
            configurationTitleTooltip:
                'This name will be used to identify the plugin in the rest of the application',
            apikey: 'The API key for the Kvk Handelsregister API plugin',
        },
    }
};

export {kvkHandelsregisterAuthPluginSpecification};

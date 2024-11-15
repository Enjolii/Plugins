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

import {PluginSpecification} from '@valtimo/plugin';
import {ObjectManagementConfigurationComponent} from "./components/object-management-configuration/object-management-configuration.component";
import {OBJECT_MANAGEMENT_PLUGIN_LOGO_BASE64} from "./assets/object-management-plugin-logo";

const objectManagementSpecification: PluginSpecification = {
  pluginId: 'object-management',
  pluginConfigurationComponent: ObjectManagementConfigurationComponent,
  pluginLogoBase64: OBJECT_MANAGEMENT_PLUGIN_LOGO_BASE64,
  pluginTranslations: {
    nl: {
      title: 'Object management',
      url: 'Object management',
      urlTooltip: 'Een URL naar de REST API van NotifyNL.',
      description: 'Verstuur SMS en E-mail met de NotifyNL service.',
      configurationTitle: 'Configuratienaam',
      configurationTitleTooltip:
        'De naam van de huidige plugin-configuratie. Onder deze naam kan de configuratie in de rest van de applicatie teruggevonden worden.',
      phoneNumber: 'Telefoonnummer',
      phoneNumberTooltip: 'Het telefoonnummer van de ontvanger',
      serviceId: 'Service ID',
      serviceIdTooltip: 'De unieke identifier van de Service in NotifyNL',
      secretKey: 'Secret key',
      secretKeyTooltip: 'De API secret key van NotifyNL',
      templateId: 'Template ID',
      templateIdTooltip: 'De unieke identifier van de template die gebruikt wordt voor dit bericht'
    },
    en: {
      title: 'Object management',
      url: 'Object management',
      urlTooltip: 'The URL of the Object Management REST API.',
      description: 'Send SMS and E-mail with the NotifyNL service.',
      configurationTitle: 'Configuration name',
      configurationTitleTooltip:
          'The name of the current plugin configuration. Under this name, the configuration can be found in the rest of the application.',
      phoneNumber: 'Phone number',
      phoneNumberTooltip: 'The mobile number of the recipient.',
      serviceId: 'Service ID',
      serviceIdTooltip: 'The unique ID of the Service in NotifyNL',
      secretKey: 'Secret key',
      secretKeyTooltip: 'The secret key of the NotifyNL API',
      templateId: 'Template ID',
      templateIdTooltip: 'The unique ID of the template that will be used for this message'
    }
  },
};

export {objectManagementSpecification};

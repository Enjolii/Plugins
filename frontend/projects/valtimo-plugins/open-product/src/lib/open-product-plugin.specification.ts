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

import {
    OpenProductConfigurationComponent
} from './components/open-product-configuration/open-product-configuration.component';
import {OPEN_PRODUCT_PLUGIN_LOGO_BASE64} from './assets';
import {PluginSpecification} from "@valtimo/plugin";
import {CreateProductConfigurationComponent} from "./components/create-product/create-product-configuration.component";
import {UpdateProductConfigurationComponent} from "./components/update-product/update-product-configuration.component";
import {DeleteProductConfigurationComponent} from "./components/delete-product/delete-product-configuration.component";


const openProductPluginSpecification: PluginSpecification = {
    pluginId: 'openproduct',
    pluginConfigurationComponent: OpenProductConfigurationComponent,
    pluginLogoBase64: OPEN_PRODUCT_PLUGIN_LOGO_BASE64,
    functionConfigurationComponents: {
        /*
         For each plugin action id received from the backend, a component is provided of the interface FunctionConfigurationComponent.
         These are used to configure each plugin action.
         */
        'create-product': CreateProductConfigurationComponent,
        'update-product': UpdateProductConfigurationComponent,
        'delete-product': DeleteProductConfigurationComponent
    },

    pluginTranslations: {
        nl: {
            title: 'Open Product Plugin',
            description:
                '',
            configurationTitle: 'Configuratienaam',
            configurationTitleTooltip:
                '',
            baseUrl: 'URL',
        },
        en: {
            title: 'Open Product Plugin',
            description: '',
            configurationTitle: 'Configuration name',
            configurationTitleTooltip:
                '',
            baseUrl: 'URL'
        },
        de: {
            title: 'Open Product Plugin',
            description:
                '',
            configurationTitle: '',
            configurationTitleTooltip:
                '',
            baseUrl: 'URL'
        },
    },
};

export {openProductPluginSpecification};

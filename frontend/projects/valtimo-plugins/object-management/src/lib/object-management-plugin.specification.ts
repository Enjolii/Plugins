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
import {
    ObjectManagementConfigurationComponent
} from "./components/object-management-configuration/object-management-configuration.component";
import {OBJECT_MANAGEMENT_PLUGIN_LOGO_BASE64} from "./assets";
import {CreateObjectConfigurationComponent} from "./components/create-object/create-object-configuration.component";
import {GetObjectsConfigurationComponent} from "./components/get-objects/get-objects-configuration.component";
import {DeleteObjectConfigurationComponent} from "./components/delete-object/delete-object-configuration.component";
import {UpdateObjectConfigurationComponent} from "./components/update-object/update-object-configuration.component";
import {FindObjectsConfigurationComponent} from "./components/find-object/find-objects-configuration.component";

const objectManagementPluginSpecification: PluginSpecification = {
    pluginId: 'object-management',
    pluginConfigurationComponent: ObjectManagementConfigurationComponent,
    pluginLogoBase64: OBJECT_MANAGEMENT_PLUGIN_LOGO_BASE64,
    functionConfigurationComponents: {
        'create-object': CreateObjectConfigurationComponent,
        'get-objects-unpaged': GetObjectsConfigurationComponent,
        'delete-object': DeleteObjectConfigurationComponent,
        'update-object': UpdateObjectConfigurationComponent,
        'find-objects': FindObjectsConfigurationComponent
    },
    pluginTranslations: {
        nl: {
            title: 'Object management',
            url: 'Object management',
            description: 'Plugin voor het uitvoeren van CRUD acties in de Object registratie',
            configurationTitle: 'Configuratienaam',
            objectManagementConfigurationTitle: 'Object Management configuratie titel',
            objectManagementConfigurationTitleTooltip: 'De titel van het geconfigureerde object management',
            'get-objects-unpaged': "Objecten ophalen",
            'delete-object': "Object verwijderen",
            'create-object': "Object aanmaken",
            'update-object': "Object bijwerken",
            'find-objects': "Objecten zoeken",
            objectManagementConfigurationIdTooltip: 'De id van de geconfigureerde object management instantie',
            listOfObjectProcessVariableName: 'Procesvariabele naam voor de objectlijst',
            listOfObjectProcessVariableNameTooltip: 'Bepaalt de procesvariabele naam die wordt gebruikt voor het opslaan van de objectlijst',
            objectUrlProcessNameTooltip: 'Bepaalt de variabele naam die wordt gebruikt waar de URL word opgehaald',
        },
        en: {
            title: 'Object management',
            url: 'Object management',
            description: 'Plugin for CRUD actions in the Objects registration',
            configurationTitle: 'Configuration Name',
            objectManagementConfigurationTitle: 'Object Management Configuration title',
            objectManagementConfigurationTitleTooltip: 'The title of the configured object management instance',
            'get-objects-unpaged': "Retrieve objects",
            'delete-object': "Delete object",
            'create-object': "Create object",
            'update-object': "Update object",
            'find-objects': "Find objects",
            objectManagementConfigurationIdTooltip: 'The id of the configured object management instance',
            listOfObjectProcessVariableName: 'Process variable name for the object list',
            listOfObjectProcessVariableNameTooltip: 'Defines the process variable name used for storing the object list',
            objectUrlProcessNameTooltip: 'Defines the variable name used to retrieve the URL',
        }
    },
};

export {objectManagementPluginSpecification};
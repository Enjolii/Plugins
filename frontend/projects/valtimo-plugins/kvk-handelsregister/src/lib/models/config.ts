import {PluginConfigurationData} from '@valtimo/plugin';

interface Config extends PluginConfigurationData {
    handelsregisterBaseUrl: string;
    authenticationPluginConfiguration: string;
}

interface ZoekenConfig {
    kvkNummer: string;
    resultProcessVariableName: string;
}

export {Config, ZoekenConfig};

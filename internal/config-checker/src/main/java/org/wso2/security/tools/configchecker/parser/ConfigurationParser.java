/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.security.tools.configchecker.parser;

import org.wso2.security.tools.configchecker.exception.ConfigCheckerException;
import org.wso2.security.tools.configchecker.model.ConfigNode;

/**
 * Interface for mapping the configurations into ConfigNode object.
 */
public interface ConfigurationParser {

    /**
     * Parsing the configurations into ConfigNode object.
     *
     * @param configurationString configuration string that needs to be parsed
     * @return ConfigNode object with the parsed configuration
     * @throws ConfigCheckerException when an error occurs during parsing the config string
     */
    public ConfigNode parse(String configurationString) throws ConfigCheckerException;
}

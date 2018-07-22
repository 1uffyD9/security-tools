/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.security.tools.scanmanager.webapp.util;

public class Constants {

    private Constants() {
    }

    public static final String STATIC_SCANNER_TYPE = "static";
    public static final String DYNAMIC_SCANNER_TYPE = "dynamic";
    public static final String DEPENDENCY_SCANNER_TYPE = "dependency";
    public static final String SCHEME = "http";
    public static final String SCAN_MANAGER_ENDPOINT = "scan-manager";
    public static final String SCANS_URI =  SCAN_MANAGER_ENDPOINT + "/scans";
    public static final String SCANNERS_URI = SCAN_MANAGER_ENDPOINT + "/scanners";
}

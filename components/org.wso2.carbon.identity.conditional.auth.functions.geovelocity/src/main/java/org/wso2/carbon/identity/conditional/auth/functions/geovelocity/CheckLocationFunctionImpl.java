/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.identity.conditional.auth.functions.geovelocity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Implementation of CheckLocationFunction function.
 * Check location by using given ip address.
 */

public class CheckLocationFunctionImpl implements  CheckLocationFunction {
    public String checkLocation(String ip) {
        String key = "39a2202599d94b2432ff1a075f1a35cfe99cb40982d1181adb2c41b6c947a251";
        ip = ip.trim();

        String location = "";
        try {
            if (ip.contains(",")) {
                String locationDetails[] = ip.split(",");
                ip = locationDetails[1].trim();
            }
        } catch (Exception e) {
        }
        URL url;
        try {
            url = new URL("http://api.ipinfodb.com/v3/ip-city/?key=" + key + "&ip=" + ip);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            String temporary = "";
            String locationDetails[] = null;
            while (null != (strTemp = br.readLine())) {
                temporary = strTemp;
            }
            locationDetails = temporary.split(";");

            location = locationDetails[8] + " " + locationDetails[9];
        } catch (Exception e) {
        }
        return location;
    }
}

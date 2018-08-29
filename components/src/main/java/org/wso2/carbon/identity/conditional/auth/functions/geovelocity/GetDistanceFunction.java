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

/**
 * Function to get the distance between two locations..
 */
@FunctionalInterface
public interface GetDistanceFunction {

    /**
     * Checks the user login browser by using userAgent.
     * @param latitude1 latitude of current login location.
     * @param latitude2 latitude of previous login location.
     * @param longitude1 longitude of current login location.
     * @param longitude2 longitude of previous login location.
     * @return distance between current and last login locations.
     */
    double distance(double latitude1, double latitude2, double longitude1, double longitude2);
}

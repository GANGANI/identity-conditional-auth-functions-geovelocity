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
 * Implementation of GetVelocityFunction function.
 * Get velocity by distance and timedifference.
 */
public class GetVelocityFunctionImpl implements GetVelocityFunction{
     public double velocity(String[] currentLocation, String[] lastLocation, int timeGap) {

        GetDistanceFuctionImpl locationDistance = new GetDistanceFuctionImpl();
        double latitude1 = Double.parseDouble(currentLocation[0]);
        double latitude2 = Double.parseDouble(lastLocation[0]);
        double longitude1 = Double.parseDouble(currentLocation[1]);
        double longitude2 = Double.parseDouble(lastLocation[1]);

        double locationGap = locationDistance.distance(latitude1, latitude2, longitude1, longitude2);

        double velocity = (locationGap / timeGap) * 3600000;
        return velocity;
    }
}

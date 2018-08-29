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
package org.wso2.carbon.identity.conditional.auth.functions.geovelocity.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.identity.application.authentication.framework.JsFunctionRegistry;
import org.wso2.carbon.identity.conditional.auth.functions.geovelocity.*;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.user.core.service.RealmService;

/**
 * OSGi declarative services component which handles registration and de-registration of user conditional auth
 * functions.
 */

@Component(
        name = "identity.conditional.auth.functions.geovelocity.component",
        immediate = true
)
public class GeovelocityFunctionsServiceComponent {
    private static final Log LOG = LogFactory.getLog(GeovelocityFunctionsServiceComponent.class);

    @Activate
    protected void activate(ComponentContext ctxt) {

        JsFunctionRegistry jsFunctionRegistry = GeovelocityFunctionsServiceHolder.getInstance().getJsFunctionRegistry();

        org.wso2.carbon.identity.conditional.auth.functions.geovelocity.CheckLocationFunction checkLocationImpl = new org.wso2.carbon.identity.conditional.auth.functions.geovelocity.CheckLocationFunctionImpl();
        jsFunctionRegistry.register(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "checkLocation", checkLocationImpl);
        org.wso2.carbon.identity.conditional.auth.functions.geovelocity.GetDistanceFunction checkDistanceImpl = new org.wso2.carbon.identity.conditional.auth.functions.geovelocity.GetDistanceFuctionImpl();
        jsFunctionRegistry.register(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "distance", checkDistanceImpl);
        org.wso2.carbon.identity.conditional.auth.functions.geovelocity.GetVelocityFunction getVelocityImpl = new org.wso2.carbon.identity.conditional.auth.functions.geovelocity.GetVelocityFunctionImpl();
        jsFunctionRegistry.register(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "velocity", getVelocityImpl);
    }


    @Deactivate
    protected void deactivate(ComponentContext ctxt) {

        JsFunctionRegistry jsFunctionRegistry = GeovelocityFunctionsServiceHolder.getInstance().getJsFunctionRegistry();
        if (jsFunctionRegistry != null) {

            jsFunctionRegistry.deRegister(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "checkLocation");
            jsFunctionRegistry.deRegister(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "distance");
            jsFunctionRegistry.deRegister(JsFunctionRegistry.Subsystem.SEQUENCE_HANDLER, "velocity");

        }
    }

    @Reference(
            name = "user.realmservice.default",
            service = RealmService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRealmService"
    )
    protected void setRealmService(RealmService realmService) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("RealmService is set in the conditional authentication user functions bundle");
        }
        GeovelocityFunctionsServiceHolder.getInstance().setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("RealmService is unset in the conditional authentication user functions bundle");
        }
        GeovelocityFunctionsServiceHolder.getInstance().setRealmService(null);
    }

    @Reference(
            name = "registry.service",
            service = RegistryService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRegistryService"
    )
    protected void setRegistryService(RegistryService registryService) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("RegistryService is set in the conditional authentication user functions bundle");
        }
        GeovelocityFunctionsServiceHolder.getInstance().setRegistryService(registryService);
    }

    protected void unsetRegistryService(RegistryService registryService) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("RegistryService is unset in the conditional authentication user functions bundle");
        }
        GeovelocityFunctionsServiceHolder.getInstance().setRegistryService(null);
    }
    @Reference(
            service = JsFunctionRegistry.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetJsFunctionRegistry"
    )
    public void setJsFunctionRegistry(JsFunctionRegistry jsFunctionRegistry) {

        GeovelocityFunctionsServiceHolder.getInstance().setJsFunctionRegistry(jsFunctionRegistry);
    }

    public void unsetJsFunctionRegistry(JsFunctionRegistry jsFunctionRegistry) {

        GeovelocityFunctionsServiceHolder.getInstance().setJsFunctionRegistry(null);
    }
}

/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.hc.client5.http.osgi.impl;

import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.impl.sync.CloseableHttpClient;
import org.apache.hc.client5.http.impl.sync.HttpClientBuilder;
import org.apache.hc.client5.http.osgi.services.HttpClientBuilderFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @since 4.3
 */
public final class OSGiClientBuilderFactory implements HttpClientBuilderFactory {

    private final BundleContext bundleContext;

    private final Map<String, ServiceRegistration> registeredConfigurations;

    private final List<CloseableHttpClient> trackedHttpClients;

    public OSGiClientBuilderFactory(
            final BundleContext bundleContext,
            final Map<String, ServiceRegistration> registeredConfigurations,
            final List<CloseableHttpClient> trackedHttpClients) {
        this.bundleContext = bundleContext;
        this.registeredConfigurations = registeredConfigurations;
        this.trackedHttpClients = trackedHttpClients;
    }

    @Override
    public HttpClientBuilder newBuilder() {
        return new OSGiHttpClientBuilder(bundleContext, registeredConfigurations, trackedHttpClients);
    }

}

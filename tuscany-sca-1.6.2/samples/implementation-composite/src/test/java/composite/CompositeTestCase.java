/*
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
 */
package composite;

import junit.framework.TestCase;

import org.apache.tuscany.sca.host.embedded.SCADomain;

public class CompositeTestCase extends TestCase {

    private SCADomain scaDomain;
    private Source source;

    @Override
    protected void setUp() throws Exception {
        scaDomain = SCADomain.newInstance();
        source = scaDomain.getService(Source.class, "SourceComponent");
    }
    
    @Override
    protected void tearDown() throws Exception {
        scaDomain.close();
    }

    public void test() throws Exception {
        System.out.println("Main thread " + Thread.currentThread());
        source.clientMethod("Client.main");
        System.out.println("Sleeping ...");
        Thread.sleep(1000);
        if (SourceImpl.callbackCount != 2) {
            throw new IllegalStateException("Expected callbacks not received");
        }
    }
}

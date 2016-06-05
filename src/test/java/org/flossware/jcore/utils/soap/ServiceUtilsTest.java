/*
 * Copyright (C) 2016 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.jcore.utils.soap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tests the ServiceUtils utility class.
 *
 * @author Scot P. Floess
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceUtilsTest {

    /**
     * Simple stub class for examining web endpoint methods.
     */
    @WebServiceClient(targetNamespace = "Target_Namespace", name = "Name")
    static class StubService extends Service {

        @WebEndpoint(name = "Web_Endpoint_Method")
        public Integer webEndpointMethod() {
            return 0;
        }

        public void nonWebEndpointMethod() {
        }

        public StubService(final URL url) {
            super(url, new QName("urn:enterprise.soap.sforce.com", "SforceService"));
        }
    }

    /**
     * Simple stub class for examining web endpoint methods.
     */
    static class NoAnnotationsStubService extends Service {

        public void webEndpointMethod() {
        }

        public void nonWebEndpointMethod() {
        }

        public NoAnnotationsStubService() {
            super(ServiceUtilsTest.class.getResource("/TestService.wsdl"), new QName("urn:enterprise.soap.sforce.com", "SforceService"));
        }
    }

    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = ServiceUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Test computing a QName with a null WebServiceClient.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_computeQName_null() {
        ServiceUtils.computeQName(null);
    }

    /**
     * Test compute a QName.
     */
    @Test
    public void test_computeQName() {
        Assert.assertEquals("Should be the correct QName", new QName("Target_Namespace", "Name"), ServiceUtils.computeQName(StubService.class.getAnnotation(WebServiceClient.class)));
    }

    /**
     * Test checking a method for being annotated as a web endpoint when null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_isWebEndpoint_null() {
        ServiceUtils.isWebEndpoint(null);
    }

    /**
     * Test checking a method for being annotated as a web endpoint when null.
     */
    @Test
    public void test_isWebEndpoint() throws NoSuchMethodException {
        Assert.assertTrue("Should be a web end point", ServiceUtils.isWebEndpoint(StubService.class.getMethod("webEndpointMethod", new Class[0])));
        Assert.assertFalse("Should not be a web end point", ServiceUtils.isWebEndpoint(StubService.class.getMethod("nonWebEndpointMethod", new Class[0])));
    }

    /**
     * Tests finding a port method in a service.
     */
    @Test
    public void test_getPortMethod() throws NoSuchMethodException {
        Assert.assertEquals("Should have found the port method", StubService.class.getMethod("webEndpointMethod", new Class[0]), ServiceUtils.getPortMethod(StubService.class));
    }

    /**
     * Tests not finding a port method in a service.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPortMethod_NotFound() {
        ServiceUtils.getPortMethod(NoAnnotationsStubService.class);
    }

    /**
     * Tests retrieving a port type for a class.
     */
    @Test
    public void test_getPortType() {
        Assert.assertEquals("Should have the correct return type", Integer.class, ServiceUtils.getPortType(StubService.class));
    }

    /**
     * Tests retrieving a port type for a class with no port method.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPortType_NotFound() {
        ServiceUtils.getPortType(NoAnnotationsStubService.class);
    }

    /**
     * Tests retrieving a port type for a service.
     */
    @Test
    public void test_getPortName() {
        Assert.assertEquals("Should have the correct name", "Web_Endpoint_Method", ServiceUtils.getPortName(StubService.class));
    }

    /**
     * Tests retrieving a port type for a service with no port method.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPortName_NotFound() {
        ServiceUtils.getPortName(NoAnnotationsStubService.class);
    }

    /**
     * Test retrieving a service name with a null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getServiceName_null() {
        ServiceUtils.getServiceName(null);
    }

    /**
     * Test retrieving a service name using a class that has no WebServiceClient annotation.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getServiceName_notFound() {
        ServiceUtils.getServiceName(NoAnnotationsStubService.class);
    }

    /**
     * Test retrieving a service name.
     */
    @Test
    public void test_getServiceName() {
        Assert.assertEquals("Should be the correct QName", new QName("Target_Namespace", "Name"), ServiceUtils.getServiceName(StubService.class));
    }

    /**
     * Test trying to create a service with a null URL.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_createService_URL_null() throws MalformedURLException {
        ServiceUtils.createService(null, new URL("http://foo"));
    }

    /**
     * Test trying to create a service with a null URL.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_createService_URL_NoSuchMethodException() throws MalformedURLException {
        ServiceUtils.createService(NoAnnotationsStubService.class, new URL("http://foo"));
    }

    /**
     * Test trying to create a service with a null URL.
     */
    @Test
    public void test_createService_URL() throws MalformedURLException {
        ServiceUtils.createService(StubService.class, ServiceUtilsTest.class.getResource("/TestService.wsdl"));
    }

    /**
     * Test trying to create a service with a null service.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_createService_String_nullService() {
        ServiceUtils.createService(null, "foo");
    }

    /**
     * Test trying to create a service.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_createService_String_MaleformedUrl() {
        ServiceUtils.createService(StubService.class, "htp://foo.bar/TestService.wsdl");
    }

    /**
     * Test trying to create a service.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_createService_String() {
        ServiceUtils.createService(StubService.class, "/TestService.wsdl");
    }
}

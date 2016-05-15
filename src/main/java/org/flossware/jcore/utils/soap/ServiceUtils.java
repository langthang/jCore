/*
 * Copyright (C) 2015 Scot P. Floess
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import org.flossware.jcore.utils.ObjectUtils;

/**
 * Web Service utility class.
 *
 * @author sfloess
 */
public class ServiceUtils {

    /**
     * Compute a QName from a WebServiceClient annotation.
     *
     * @param webServiceClient the annotation to use to compute a QName.
     *
     * @return the QName representation of the WebServiceClient annotation.
     *
     * @throws IllegalArgumentException if webServiceClient is null.
     */
    static QName computeQName(final WebServiceClient webServiceClient) {
        ObjectUtils.ensureObject(webServiceClient, "Must have a WebServiceClient annotation");

        return new QName(webServiceClient.targetNamespace(), webServiceClient.name());
    }

    /**
     * If the method has a WebEndpoint annotation, return true if not false.
     *
     * @param method the method to examine for a WebEndpoint annotation.
     *
     * @return true if the method has a WebEndpoint annotation or false if not.
     */
    public static boolean isWebEndpoint(final Method method) {
        ObjectUtils.ensureObject(method, "Must provide a method!");

        return null != method.getAnnotation(WebEndpoint.class);
    }

    /**
     * Finds the port method in the serviceClass.
     *
     * @param <S> the type of service.
     *
     * @param serviceClass the service class to find the port method.
     *
     * @return the port method in the service class.
     *
     * @throws IllegalArgumentException if serviceClass contains no port method.
     */
    public static <S extends Service> Method getPortMethod(final Class<S> serviceClass) {
        for (final Method method : serviceClass.getDeclaredMethods()) {
            if (isWebEndpoint(method)) {
                return method;
            }
        }

        throw new IllegalArgumentException("Service class [" + serviceClass.getName() + "] has no WebEndpoint!");
    }

    /**
     * Return the return type of the port method for serviceClass.
     *
     * @param <S> the type of service.
     *
     * @param serviceClass the service class.
     *
     * @return the return type of the port method.
     *
     * @throws IllegalArgumentException if serviceClass has not port method.
     */
    public static <S extends Service> Class getPortType(final Class<S> serviceClass) {
        return getPortMethod(serviceClass).getReturnType();
    }

    /**
     * Return the name attribute of the port method's web end point annotation for serviceClass.
     *
     * @param <S> the type of service.
     *
     * @param serviceClass the service class.
     *
     * @return the name attribute of the port method's web end point annotation for serviceClass.
     *
     * @throws IllegalArgumentException if serviceClass has not port method.
     */
    public static <S extends Service> String getPortName(final Class<S> serviceClass) {
        return getPortMethod(serviceClass).getAnnotation(WebEndpoint.class).name();
    }

    /**
     * Return the name attribute of the port method's web end point annotation for serviceClass.
     *
     * @param <S> the type of service.
     *
     * @param serviceClass the service class.
     *
     * @return the name attribute of the port method's web end point annotation for serviceClass.
     *
     * @throws IllegalArgumentException if serviceClass has not port method.
     */
    public static <S extends Service> QName getServiceName(final Class<S> serviceClass) {
        return computeQName(ObjectUtils.ensureObject(serviceClass, "Must have a service class").getAnnotation(WebServiceClient.class));
    }

    /**
     * Create a service using URL to the wsdl.
     *
     * @param <S> the type of service.
     *
     * @param serviceClass the service class.
     * @param wsdlResource the URL to the wsdl.
     *
     * @return a Service.
     *
     * @throws IllegalArgumentException if serviceClass is null or the Service cannot be instantiated.
     */
    public static <S extends Service> S createService(final Class<S> serviceClass, final URL wsdlResource) {
        ObjectUtils.ensureObject(serviceClass, "Must have a service");

        try {
            return (S) serviceClass.getConstructor(URL.class).newInstance(wsdlResource);
        } catch (final NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new IllegalArgumentException("Trouble creating service", ex);
        }
    }

    /**
     * Create a service using the wsdl resource.
     *
     * @param <S> the type of service.
     *
     * @param serviceClass the service class.
     * @param wsdlResource the URL to the wsdl.
     *
     * @return a Service.
     *
     * @throws IllegalArgumentException if serviceClass is null or the Service cannot be instantiated.
     */
    public static <S extends Service> S createService(final Class<S> serviceClass, final String wsdlResource) {
        try {
            return createService(ObjectUtils.ensureObject(serviceClass, "Must have a service"), new URL(wsdlResource));
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException("Trouble creating URL for WSDL resource [" + wsdlResource + "]", ex);
        }
    }

    /**
     * Default constructor not allowed.
     */
    private ServiceUtils() {
    }
}

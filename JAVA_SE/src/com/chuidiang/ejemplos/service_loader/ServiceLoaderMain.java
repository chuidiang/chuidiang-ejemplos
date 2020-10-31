package com.chuidiang.ejemplos.service_loader;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author fjabellan
 * @date 31/10/2020
 */
public class ServiceLoaderMain {
    public static void main(String[] args) {
        ServiceLoader<IfzService> serviceLoader = ServiceLoader.load(IfzService.class);
        final Iterator<IfzService> iterator = serviceLoader.iterator();
        iterator.forEachRemaining(service->{
            System.out.print(service + ": ");
            service.sayHello();
        });
    }
}

package com.acme.customers.api.rest.v1;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import com.acme.customers.api.rest.v1.filters.AuthFilter;
import com.acme.customers.api.rest.v1.filters.CorsFilter;
import com.acme.customers.api.rest.v1.mappers.EmptyPayloadMapper;
import com.acme.customers.api.rest.v1.mappers.ResourceNotFoundMapper;
import com.acme.customers.api.rest.v1.resources.CustomerResource;

@ApplicationPath("/v1")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        classes.add(JsonProcessingFeature.class);

        classes.add(CustomerResource.class);

        classes.add(EmptyPayloadMapper.class);
        classes.add(ResourceNotFoundMapper.class);
        classes.add(AuthFilter.class);
        classes.add(CorsFilter.class);


        return classes;
    }
}

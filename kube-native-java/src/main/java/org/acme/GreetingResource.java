package org.acme;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "name")
    String name;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/person")
    public List<Person> listAll() {
        return Person.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/person/{id}")
    public Person findById(@PathParam("id") Long id) {
        return Person.findById(id);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting")
    @Liveness
    public String greeting() {
        return "Kubernetes Native Java, " + name;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Readiness
    public String hello() {
        return "Hello ArnHemJUG 2022";
    }
}
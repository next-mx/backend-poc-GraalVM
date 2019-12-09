package com.poc.graalvm.res;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.poc.graal.obj.Comment;
import com.poc.graalvm.service.QuarkusService;



@Path("/comments/v1")
public class QuarkusResource {

    @Inject
    QuarkusService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("/path/{name}")
    public Comment addComment(@PathParam("name") String name) {
        return service.saveComment(name);
    }

}
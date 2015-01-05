package com.github.nordinh.comicollector.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.params.BooleanParam;
import io.dropwizard.jersey.params.DateTimeParam;
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/CollectedSeries")
public class CollectedSeriesResource {

    private static final Logger LOG = LoggerFactory.getLogger(CollectedSeriesResource.class);

    
    @Path("/series/collected")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed 
    public String findCollectedSeries(
        
        
        ) {
        StringBuilder sb = new StringBuilder();
        sb.append("Received parameters:\n");
        
        return sb.toString();
    }
    
}

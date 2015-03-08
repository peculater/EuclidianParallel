/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.billylieurance.euclidparallel;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author wlieurance
 */
@Path("point")
public class PointResource {

    @Context
    private UriInfo uricontext;
    
    @Context 
    ServletContext context;

    /**
     * Creates a new instance of PointResource
     */
    public PointResource() {
    }

    
    
    @PUT
    @Path("compare")
    @Produces("application/json")
    public String compare(String content) {
        long start, end, delta;
        start =  System.currentTimeMillis();
        Logger.getLogger(PointResource.class.getName()).log(Level.INFO, "Starting search...");
        EuclidManager euclid = (EuclidManager) context.getAttribute("EuclidManager");
        String returnable = euclid.getDistances(new TraitPoint(content));
        end = System.currentTimeMillis();
        delta = end - start;
        Logger.getLogger(PointResource.class.getName()).log(Level.INFO, "Finished search.   Took {0}ms", delta);
        return returnable;
    }
    
    @PUT
    @Path("add")
    @Produces("application/json")
    public String add(String content) {
        long start, end, delta;
        start =  System.currentTimeMillis();
        Logger.getLogger(PointResource.class.getName()).log(Level.INFO, "Starting add...");
        EuclidManager euclid = (EuclidManager) context.getAttribute("EuclidManager");
        euclid.add(new TraitPoint(content));
        String returnable = "[ 'added' ]";
        end = System.currentTimeMillis();
        delta = end - start;
        Logger.getLogger(PointResource.class.getName()).log(Level.INFO, "Finished add.   Took {0}ms", delta);
        return returnable;
    }
    
    
}

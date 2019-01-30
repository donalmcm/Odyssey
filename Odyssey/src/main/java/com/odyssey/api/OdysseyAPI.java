package com.odyssey.api;

import com.odyssey.model.Odyssey;
import com.HibernateUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/odyssey")
public class OdysseyAPI {

    @GET
    @Produces("application/json")
    public String getAllOdysseys() {
        return " ";
    }

}

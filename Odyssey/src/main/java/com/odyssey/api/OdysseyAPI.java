package com.odyssey.api;

import com.odyssey.model.Employee;
import com.odyssey.model.Odyssey;
import com.HibernateUtils;
import com.odyssey.model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/odyssey")
public class OdysseyAPI {

    SessionFactory factory = HibernateUtils.getSessionFactory();
    Session session = factory.getCurrentSession();

    @GET
    @Produces("application/json")
    public String getAllOdysseys() {
        return " ";
    }

    // create an employee
    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createEmployee(Employee mentor,Employee mentee, Topic topic) {
        session.getTransaction().begin();

        Odyssey newOdyssey = new Odyssey(mentor,mentee,topic);
        session.persist(newOdyssey);
        session.getTransaction().commit();
        session.close();
        return Response.ok(newOdyssey, MediaType.APPLICATION_JSON).build();
    }

}

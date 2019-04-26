package com.odyssey.api;

import com.HibernateUtil;
import com.odyssey.model.Availability;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/availabilities")
public class AvailabilityAPI {

    @GET
    @Produces("application/json")
    public Response getAllEmployees() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query<Availability> query = session.createNamedQuery("Availability.findAllAvailabilities", Availability.class);
            List<Availability> availabilities = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return Response.ok(availabilities, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }
}

package com.odyssey.api;

import com.odyssey.model.Employee;
import com.odyssey.model.Odyssey;
import com.HibernateUtil;
import com.odyssey.model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/odyssey")
public class OdysseyAPI {

    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();

    @GET
    @Produces("application/json")
    public String getAllOdysseys() {
        return " ";
    }

    // create an employee
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createEmployee(@FormParam("userId")int userId,
                                   @FormParam("mentorId") int mentorId,
                                   @FormParam("mentorDuration") int mentorDuration,
                                   @FormParam("topicId") String topic,
                                   @FormParam("availableDay") String day) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        try{
            session.getTransaction().begin();

            // use the current users id below - temporarily hardcoded
            Query<Employee> userQuery = session.createNamedQuery("Employee.findById",Employee.class);
            userQuery.setParameter("id",userId);
            Employee user = userQuery.getSingleResult();

            Query<Employee> mentorQuery = session.createNamedQuery("Employee.findById",Employee.class);
            mentorQuery.setParameter("id",mentorId);
            Employee mentor = mentorQuery.getSingleResult();

            Query<Topic> topicQuery = session.createNamedQuery("Topic.findById",Topic.class);
            topicQuery.setParameter("id",topic);
            Topic topicIn = topicQuery.getSingleResult();

            Odyssey newOdyssey = new Odyssey(mentor,user,topicIn);

            session.save(newOdyssey);
            session.getTransaction().commit();
            session.close();

            location = new URI("http://localhost:8080/index.html");
            return Response.temporaryRedirect(location).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

}

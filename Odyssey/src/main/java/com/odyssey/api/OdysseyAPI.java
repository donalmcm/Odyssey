package com.odyssey.api;

import com.google.gson.JsonObject;
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
import java.util.List;

@Path("/odysseys")
public class OdysseyAPI {

    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();

    @GET
    @Produces("application/json")
    public Response getAllOdysseys() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findAllOdysseys",Odyssey.class);
        List<Odyssey> odysseys = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return Response.ok(odysseys, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("countTopicsByOdyssey")
    @Produces("application/json")
    public Response getTopicCountByOdysseys() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findTopicCountByOdyssey",Odyssey.class);
        List<Odyssey> odysseys = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return Response.ok(odysseys, MediaType.APPLICATION_JSON).build();
    }


    @GET
    @Path("percentageComplete/{id}")
    @Produces("application/json")
    public double getPercentageCompleteOfOdysseys(@PathParam("id") int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findByOdysseyId",Odyssey.class);
        query.setParameter("id",id);
        Odyssey odyssey = query.getSingleResult();

        session.getTransaction().commit();
        session.close();

        return odyssey.getPercentageCompleteOfOdyssey();
    }


    // create an odyssey
    @POST
    @Path("create/{userId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createOdyssey(@PathParam("userId")int userId,
                                  @FormParam("topicId")int topicId,
                                   @FormParam("mentorDuration") int mentorDuration,
                                   @FormParam("dayOfMeetings") String dayOfMeetings,
                                   @FormParam("timeOfMeetings") int timeOfMeetings) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        try{
            session.getTransaction().begin();

            Query<Employee> userQuery = session.createNamedQuery("Employee.findById",Employee.class);
            userQuery.setParameter("id",userId);
            Employee user = userQuery.getSingleResult();

            // find employee with matching availability - if multiple pick random
            Query<Employee> mentorQuery = session.createNamedQuery("Employee.findById",Employee.class);
            mentorQuery.setParameter("id",2);
            Employee mentor = mentorQuery.getSingleResult();

            Query<Topic> topicQuery = session.createNamedQuery("Topic.findById",Topic.class);
            topicQuery.setParameter("id",topicId);
            Topic topicIn = topicQuery.getSingleResult();

            Odyssey newOdyssey = new Odyssey(mentor,user);
            newOdyssey.generateOdysseyMeetings(mentorDuration,dayOfMeetings,timeOfMeetings);

            session.persist(newOdyssey);
            session.getTransaction().commit();
            session.close();

            location = new URI("http://localhost:8080/myaccount/home.jsp");
            return Response.temporaryRedirect(location).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("getEmployeeOdysseys/{userId}")
    public Response getOdysseysByEmployee(@PathParam("userId") int userId) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByEmployee",Odyssey.class);
        query.setParameter("id",userId);

        List<Odyssey> odysseysByEmployee= query.getResultList();
        session.getTransaction().commit();
        session.close();
        return Response.ok(odysseysByEmployee, MediaType.APPLICATION_JSON).build();
    }

}

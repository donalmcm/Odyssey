package com.odyssey.api;

import com.google.gson.JsonObject;
import com.odyssey.model.Employee;
import com.odyssey.model.Odyssey;
import com.HibernateUtil;
import com.odyssey.model.Topic;
import com.odyssey.writers.OdysseyDetails;
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

    @GET
    @Path("details/{id}")
    @Produces("application/json")
    public Response getOdysseyDetails(@PathParam("id") int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findByOdysseyId",Odyssey.class);
        query.setParameter("id",id);
        Odyssey odyssey = query.getSingleResult();

        session.getTransaction().commit();
        session.close();

        OdysseyDetails odysseyDetails = new OdysseyDetails();
        odysseyDetails.setTopic(odyssey.getTopic().getName());
        odysseyDetails.setMentor(odyssey.getMentor().getFirstName());
        odysseyDetails.setMentee(odyssey.getMentee().getFirstName());
        odysseyDetails.setDuration(odyssey.getMentor().getMentorDuration());
        odysseyDetails.setPercentageComplete(odyssey.getPercentageCompleteOfOdyssey());

        return Response.ok(odysseyDetails, MediaType.APPLICATION_JSON).build();
    }

    // create an odyssey
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createOdyssey(@FormParam("userId")int userId,
                                   @FormParam("mentorId") int mentorId,
                                   @FormParam("mentorDuration") int mentorDuration,
                                   @FormParam("topicId") String topic,
                                   @FormParam("availableDay") String availableDay) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        try{
            session.getTransaction().begin();

            // use the current users id below - temporarily hardcoded
            Query<Employee> userQuery = session.createNamedQuery("Employee.findById",Employee.class);
            userQuery.setParameter("id",31);
            Employee user = userQuery.getSingleResult();

            Query<Employee> mentorQuery = session.createNamedQuery("Employee.findById",Employee.class);
            mentorQuery.setParameter("id",mentorId);
            Employee mentor = mentorQuery.getSingleResult();

            Query<Topic> topicQuery = session.createNamedQuery("Topic.findById",Topic.class);
            topicQuery.setParameter("id",topic);
            Topic topicIn = topicQuery.getSingleResult();

            Odyssey newOdyssey = new Odyssey(mentor,user);
            newOdyssey.generateOdysseyMeetings(mentorDuration,availableDay);

            session.persist(newOdyssey);
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

package com.odyssey.api;

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

@Path("topics")
public class TopicAPI {

    // Get all topics
    @GET
    @Produces("application/json")
    public Response getAllTopics() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query<Topic> query = session.createNamedQuery("Topic.findAll", Topic.class);
            List<Topic> topics = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return Response.ok(topics, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    // Get a topic by id
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getEmployeeById(@PathParam("id") String id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query<Topic> query = session.createNamedQuery("Topic.findById", Topic.class);
            query.setParameter("id", id);
            Topic topic = query.getSingleResult();

            session.getTransaction().commit();
            session.close();
            return Response.ok(topic, MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createTopic(@FormParam("topicName") String name) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        try {
            session.getTransaction().begin();

            Topic newTopic = new Topic();
            newTopic.setName(name);

            session.persist(newTopic);
            session.getTransaction().commit();
            session.close();

            location = new URI("http://odyssey-aws.eu-west-1.elasticbeanstalk.com/myaccount/admin.jsp");
            return Response.temporaryRedirect(location).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }
}

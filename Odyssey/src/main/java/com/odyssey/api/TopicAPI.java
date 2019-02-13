package com.odyssey.api;


import com.odyssey.model.Topic;
import com.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("topics")
public class TopicAPI {

    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = factory.getCurrentSession();

    // Get all topics
    @GET
    @Produces("application/json")
    public Response getAllTopics() {

        //try {
            session.getTransaction().begin();

            Query<Topic> query = session.createNamedQuery("Topic.findAll",Topic.class);
            List<Topic> topics = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return Response.ok(topics, MediaType.APPLICATION_JSON).build();
        //} catch ()
    }

    // Get a topic by id
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getEmployeeById(@PathParam("id") String id) {
        session.getTransaction().begin();

        Query<Topic> query = session.createNamedQuery("Topic.findById",Topic.class);
        query.setParameter("id",id);
        Topic topic = query.getSingleResult();

        session.getTransaction().commit();
        session.close();
        return Response.ok(topic,MediaType.APPLICATION_JSON_TYPE).build();

        // catch - not employee exists with that id
    }

    @POST
    @Path("create/{name}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createTopic(@PathParam("name") String name) {

        session.getTransaction().begin();
        Topic newTopic = new Topic(name);
        session.persist(newTopic);
        session.getTransaction().commit();
        session.close();
        return Response.ok(newTopic, MediaType.APPLICATION_JSON).build();
    }

}

package com.odyssey.api;

import com.odyssey.model.Employee;
import com.odyssey.model.Odyssey;
import com.HibernateUtils;
import com.odyssey.model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/odysseys")
public class OdysseyAPI {

    SessionFactory factory = HibernateUtils.getSessionFactory();
    Session session = factory.getCurrentSession();

    @GET
    @Produces("application/json")
    public Response getAllOdysseys() {

        //try{
        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findAllOdysseys",Odyssey.class);
        List<Odyssey> odysseys = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return Response.ok(odysseys, MediaType.APPLICATION_JSON).build();

//        } catch (Exception e) {
//
//            return e.printStackTrace();
//            session.getTransaction().rollback();
//        }
    }

    @GET
    @Path("/{topic}")
    @Produces("application/json")
    public Response getAllOdysseysByTopic(@PathParam("topic")String topic) {

        //try{
        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByTopic",Odyssey.class);
        List<Odyssey> odysseys = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return Response.ok(odysseys, MediaType.APPLICATION_JSON).build();

//        } catch (Exception e) {
//
//            return e.printStackTrace();
//            session.getTransaction().rollback();
//        }
    }

    // will be used to display the current users mentor odysseys
    @GET
    @Path("/{mentor}")
    @Produces("application/json")
    public Response getAllOdysseysByMentor(@PathParam("mentor")String mentor) {

        //try{
        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByMentor",Odyssey.class);
        List<Odyssey> odysseys = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return Response.ok(odysseys, MediaType.APPLICATION_JSON).build();

//        } catch (Exception e) {
//
//            return e.printStackTrace();
//            session.getTransaction().rollback();
//        }
    }
    @GET
    @Path("/{mentee}")
    @Produces("application/json")
    public Response getAllOdysseysByMentee(@PathParam("mentee")String mentee) {

        //try{
        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByMentee",Odyssey.class);
        List<Odyssey> odysseys = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return Response.ok(odysseys, MediaType.APPLICATION_JSON).build();

//        } catch (Exception e) {
//
//            return e.printStackTrace();
//            session.getTransaction().rollback();
//        }
    }

    // create an odyssey
    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createOdyssey(Employee mentor,Employee mentee, Topic topic) {
        session.getTransaction().begin();

        Odyssey newOdyssey = new Odyssey(mentor,mentee,topic);
        session.persist(newOdyssey);
        session.getTransaction().commit();
        session.close();
        return Response.ok(newOdyssey, MediaType.APPLICATION_JSON).build();
    }

}

package com.odyssey.api;

import com.HibernateUtil;
import com.odyssey.model.Availability;
import com.odyssey.model.OdysseyMeeting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/odysseyMeetings")
public class OdysseyMeetingAPI {


    @GET
    @Produces("application/json")
    public Response getAllOdysseyMeetings() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        //try{
        session.getTransaction().begin();

        Query<OdysseyMeeting> query = session.createNamedQuery("OdysseyMeeting.findAllOdysseyMeetings",OdysseyMeeting.class);
        List<OdysseyMeeting> odysseyMeetings = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return Response.ok(odysseyMeetings, MediaType.APPLICATION_JSON).build();

    }
}

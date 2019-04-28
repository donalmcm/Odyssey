package com.odyssey.api;

import com.HibernateUtil;
import com.odyssey.model.OdysseyMeeting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("odysseyMeetings")
public class OdysseyMeetingAPI {

    @GET
    @Produces("application/json")
    public Response getAllOdysseyMeetings() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query<OdysseyMeeting> query = session.createNamedQuery("OdysseyMeeting.findAllOdysseyMeetings", OdysseyMeeting.class);
            List<OdysseyMeeting> odysseyMeetings = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return Response.ok(odysseyMeetings, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("{id}/note")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createMeetingNote(@FormParam("meetingNote") String meetingNote, @PathParam("id") int id) {
        OdysseyMeeting odysseyMeeting = OdysseyMeeting.getOdysseyMeetingById(id);

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        try {
            session.getTransaction().begin();

            odysseyMeeting.setMeetingNote(meetingNote);

            session.update(odysseyMeeting);
            session.getTransaction().commit();
            session.close();

            location = new URI("http://odyssey-aws.eu-west-1.elasticbeanstalk.com/myaccount/home.jsp");
            return Response.temporaryRedirect(location).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }
}

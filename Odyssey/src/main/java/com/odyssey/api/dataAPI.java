package com.odyssey.api;

import com.HibernateUtil;
import com.odyssey.dataAnalytics.DataQueries;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/data")
public class dataAPI {

    @GET
    @Path("topicOccurrences")
    @Produces("application/json")
    public Response getTopicCountForOdysseys() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();
        final String query = "select topic_name, COUNT(*) FROM odyssey group by topic_name";

        session.getTransaction().commit();
        session.close();
        return Response.ok().build();
    }
}

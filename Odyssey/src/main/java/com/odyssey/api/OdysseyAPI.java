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
    public Response createOdyssey(@PathParam("userId")int userId, // CHANGE TO EMAIL
                                  @FormParam("topicId")String topicId,
                                   @FormParam("mentorDuration") int mentorDuration,
                                   @FormParam("dayOfMeetings") String dayOfMeetings,
                                   @FormParam("timeOfMeetings") int timeOfMeetings) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        String timeAsString = String.valueOf(timeOfMeetings);
        String dayAndTime = dayOfMeetings + timeAsString;
        try{
            session.getTransaction().begin();

            // GET FROM EMAIL------------------------------------------------------------------------------------------------
            Query<Employee> userQuery = session.createNamedQuery("Employee.findById",Employee.class);
            userQuery.setParameter("id",userId);
            Employee user = userQuery.getSingleResult();

            // find employee with matching availability - if multiple pick random
            Query<Employee> mentorQuery = session.createNamedQuery("Employee.findMentorForMentee",Employee.class);
            mentorQuery.setParameter("topic",topicId);
            mentorQuery.setParameter("mentorDuration",mentorDuration);
            mentorQuery.setParameter("menteeId",userId);
            List<Employee> employees = mentorQuery.getResultList();

            Employee mentor = findEmployeeByAvailability(dayAndTime,employees);

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

    @GET
    @Path("getOdysseysByMentor/{userId}")
    public Response getOdysseysByMentor(@PathParam("userId") int userId) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByMentor",Odyssey.class);
        query.setParameter("id",userId);

        List<Odyssey> odysseysByEmployee= query.getResultList();
        session.getTransaction().commit();
        session.close();
        return Response.ok(odysseysByEmployee, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("getOdysseysByMentee/{userId}")
    public Response getOdysseysByMentee(@PathParam("userId") int userId) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByMentee",Odyssey.class);
        query.setParameter("id",userId);

        List<Odyssey> odysseysByEmployee= query.getResultList();
        session.getTransaction().commit();
        session.close();
        return Response.ok(odysseysByEmployee, MediaType.APPLICATION_JSON).build();
    }

    // look into reflection to improve this function
    private Employee findEmployeeByAvailability(String dayTime,List<Employee> employees) {
        Employee mentor = new Employee();

        switch (dayTime) {
            case "monday10":
                for (Employee e : employees) {
                    if (e.getAvailability().isMonday10()) {
                        mentor = e;
                    }
                }
                break;
            case "monday11":
                for (Employee e : employees) {
                    if (e.getAvailability().isMonday11()) {
                        mentor = e;
                    }
                }
                break;
            case "monday12":
                for (Employee e : employees) {
                    if (e.getAvailability().isMonday12()) {
                        mentor = e;
                    }
                }
                break;
            case "monday14":
                for (Employee e : employees) {
                    if (e.getAvailability().isMonday14()) {
                        mentor = e;
                    }
                }
                break;
            case "monday15":
                for (Employee e : employees) {
                    if (e.getAvailability().isMonday15()) {
                        mentor = e;
                    }
                }
                break;
            case "monday16":
                for (Employee e : employees) {
                    if (e.getAvailability().isMonday16()) {
                        mentor = e;
                    }
                }
                break;
            case "tuesday10":
                for (Employee e : employees) {
                    if (e.getAvailability().isTuesday10()) {
                        mentor = e;
                    }
                }
                break;
            case "tuesday11":
                for (Employee e : employees) {
                    if (e.getAvailability().isTuesday11()) {
                        mentor = e;
                    }
                }
                break;
            case "tuesday12":
                for (Employee e : employees) {
                    if (e.getAvailability().isTuesday12()) {
                        mentor = e;
                    }
                }
                break;
            case "tuesday14":
                for (Employee e : employees) {
                    if (e.getAvailability().isTuesday14()) {
                        mentor = e;
                    }
                }
                break;
            case "tuesday15":
                for (Employee e : employees) {
                    if (e.getAvailability().isTuesday15()) {
                        mentor = e;
                    }
                }
                break;
            case "tuesday16":
                for (Employee e : employees) {
                    if (e.getAvailability().isTuesday16()) {
                        mentor = e;
                    }
                }
                break;
            case "wednesday10":
                for (Employee e : employees) {
                    if (e.getAvailability().isWednesday10()) {
                        mentor = e;
                    }
                }
                break;
            case "wednesday11":
                for (Employee e : employees) {
                    if (e.getAvailability().isWednesday11()) {
                        mentor = e;
                    }
                }
                break;
            case "wednesday12":
                for (Employee e : employees) {
                    if (e.getAvailability().isWednesday12()) {
                        mentor = e;
                    }
                }
                break;
            case "wednesday14":
                for (Employee e : employees) {
                    if (e.getAvailability().isWednesday14()) {
                        mentor = e;
                    }
                }
                break;
            case "wednesday15":
                for (Employee e : employees) {
                    if (e.getAvailability().isWednesday15()) {
                        mentor = e;
                    }
                }
                break;
            case "wednesday16":
                for (Employee e : employees) {
                    if (e.getAvailability().isWednesday16()) {
                        mentor = e;
                    }
                }
                break;
            case "thursday10":
                for (Employee e : employees) {
                    if (e.getAvailability().isThursday10()) {
                        mentor = e;
                    }
                }
                break;
            case "thursday11":
                for (Employee e : employees) {
                    if (e.getAvailability().isThursday11()) {
                        mentor = e;
                    }
                }
                break;
            case "thursday12":
                for (Employee e : employees) {
                    if (e.getAvailability().isThursday12()) {
                        mentor = e;
                    }
                }
                break;
            case "thursday14":
                for (Employee e : employees) {
                    if (e.getAvailability().isThursday14()) {
                        mentor = e;
                    }
                }
                break;
            case "thursday15":
                for (Employee e : employees) {
                    if (e.getAvailability().isThursday15()) {
                        mentor = e;
                    }
                }
                break;
            case "thursday16":
                for (Employee e : employees) {
                    if (e.getAvailability().isThursday16()) {
                        mentor = e;
                    }
                }
                break;
            case "friday10":
                for (Employee e : employees) {
                    if (e.getAvailability().isFriday10()) {
                        mentor = e;
                    }
                }
                break;
            case "friday11":
                for (Employee e : employees) {
                    if (e.getAvailability().isFriday11()) {
                        mentor = e;
                    }
                }
                break;
            case "friday12":
                for (Employee e : employees) {
                    if (e.getAvailability().isFriday12()) {
                        mentor = e;
                    }
                }
                break;
            case "friday14":
                for (Employee e : employees) {
                    if (e.getAvailability().isFriday14()) {
                        mentor = e;
                    }
                }
                break;
            case "friday15":
                for (Employee e : employees) {
                    if (e.getAvailability().isFriday15()) {
                        mentor = e;
                    }
                }
                break;
            case "friday16":
                for (Employee e : employees) {
                    if (e.getAvailability().isFriday16()) {
                        mentor = e;
                    }
                }
                break;
        }
        return mentor;
    }

}

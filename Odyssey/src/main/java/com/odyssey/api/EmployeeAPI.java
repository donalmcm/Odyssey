package com.odyssey.api;

import com.odyssey.model.Availability;
import com.odyssey.model.Employee;
import com.HibernateUtil;
import com.odyssey.model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/employees")
public class EmployeeAPI {



    @GET
    @Produces("application/json")
    public Response getAllEmployees() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findAllEmployees",Employee.class);
            List<Employee> employees = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return Response.ok(employees, MediaType.APPLICATION_JSON).build();

//        } catch (Exception e) {
//
//            return e.printStackTrace();
//            session.getTransaction().rollback();
//        }


    }

    // Get an Employee by id
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getEmployeeById(@PathParam("id") int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query<Employee> query = session.createNamedQuery("Employee.findById",Employee.class);
        query.setParameter("id",id);
        Employee employee = query.getSingleResult();

        session.getTransaction().commit();
        session.close();
        return Response.ok(employee,MediaType.APPLICATION_JSON_TYPE).build();

        // catch - not employee exists with that id
    }

    // get all mentors
    @GET
    @Path("mentors")
    @Produces("application/json")
    public Response getMentors() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query<Employee> query = session.createNamedQuery("Employee.findMentors",Employee.class);

        List<Employee> mentorList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return Response.ok(mentorList, MediaType.APPLICATION_JSON).build();
    }

    // get all mentees
    @GET
    @Path("mentees")
    @Produces("application/json")
    public Response getMentees() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query<Employee> query = session.createNamedQuery("Employee.findMentees",Employee.class);

        List<Employee> menteeList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return Response.ok(menteeList, MediaType.APPLICATION_JSON).build();
    }

    // get all mentees by topic
    @GET
    @Path("mentors/{topic}")
    @Produces("application/json")
    public Response getMenteesByTopic(@PathParam("topic") String topic) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query<Employee> query = session.createNamedQuery("Employee.findMenteesByTopic",Employee.class);
        query.setParameter("topic",topic);

        List<Employee> menteesByTopicList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return Response.ok(menteesByTopicList, MediaType.APPLICATION_JSON).build();
    }

    // create an employee
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createEmployee(@FormParam("firstName") String firstName,
                                   @FormParam("lastName") String lastName,
                                   @FormParam("email") String email) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        try{
            session.getTransaction().begin();

            Employee newEmployee = new Employee();
            newEmployee.setFirstName(firstName);
            newEmployee.setLastName(lastName);
            newEmployee.setEmail(email);

            session.persist(newEmployee);
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

    // create a mentor
    @POST
    @Path("becomeMentor")
    @Consumes("application/json")
    @Produces("application/json")   // pass in an employee
    public Response becomeMentor(@FormParam("topic") String topic,
                                 @FormParam("mentorDuration") int mentorDuration,
                                 @FormParam("availability") List<String> availability) {

        Topic topicIn = new Topic(topic);
        Availability availabilityIn = new Availability(); // get list from form
        Employee EmployeeIn = new Employee();

        EmployeeIn.becomeMentor(topicIn,availabilityIn,mentorDuration);

        return Response.ok(EmployeeIn, MediaType.APPLICATION_JSON).build();
    }

}




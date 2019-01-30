package com.odyssey.api;

import com.odyssey.model.Availability;
import com.odyssey.model.Employee;
import com.HibernateUtils;
import com.odyssey.model.Topic;
import com.odyssey.writers.EmployeeBodyWriter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeAPI {

    SessionFactory factory = HibernateUtils.getSessionFactory();
    Session session = factory.getCurrentSession();

    Availability testAvailabilty = new Availability(true,false,true,false,true);
    Topic testTopic = new Topic("testTopic");

    @GET
    @Produces("application/json")
    public Response getAllEmployees() {

        //try{
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
        session.getTransaction().begin();

        Query<Employee> query = session.createNamedQuery("Employee.findMentees",Employee.class);

        List<Employee> menteeList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return Response.ok(menteeList, MediaType.APPLICATION_JSON).build();
    }

    // create an employee
    @POST
    @Path("testCreate/{firstName}/{lastName}/{email}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createEmployee(@PathParam("firstName") String firstName,@PathParam("lastName") String lastName,
                                   @PathParam("email") String email) {

        session.getTransaction().begin();
        Employee testEmployee = new Employee(firstName,lastName,email);

        session.persist(testEmployee);
        session.getTransaction().commit();
        session.close();
        return Response.ok(testEmployee, MediaType.APPLICATION_JSON).build();
    }

}




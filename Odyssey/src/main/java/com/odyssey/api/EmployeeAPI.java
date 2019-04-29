package com.odyssey.api;

import com.HibernateUtil;
import com.odyssey.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        try {
            session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findAllEmployees", Employee.class);
            List<Employee> employees = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return Response.ok(employees, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    // Get an Employee by id
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getEmployeeById(@PathParam("id") int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findById", Employee.class);
            query.setParameter("id", id);
            Employee employee = query.getSingleResult();

            session.getTransaction().commit();
            session.close();

            return Response.ok(employee, MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    // get all mentors
    @GET
    @Path("mentors")
    @Produces("application/json")
    public Response getMentors() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findMentors", Employee.class);
            List<Employee> mentorList = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return Response.ok(mentorList, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    // get all mentees
    @GET
    @Path("mentees")
    @Produces("application/json")
    public Response getMentees() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findMentees", Employee.class);

            List<Employee> menteeList = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return Response.ok(menteeList, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    // get all mentors by topic
    @GET
    @Path("mentors/{topic}/excludingUser/{userId}")
    @Produces("application/json")
    public Response getMentorsByTopic(@PathParam("topic") String topic, @PathParam("userId") int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findMentorsByTopic", Employee.class);
            query.setParameter("id", id);
            query.setParameter("topic", topic);

            List<Employee> menteesByTopicList = query.getResultList();
            session.getTransaction().commit();
            session.close();

            return Response.ok(menteesByTopicList, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    // get all mentors awaiting excluding user
    @GET
    @Path("mentors/awaiting/excludingUser/{userId}")
    @Produces("application/json")
    public Response getMentorsByTopic(@PathParam("userId") int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findMentorsByAwaiting", Employee.class);
            query.setParameter("id", id);

            List<Employee> menteesAwaiting = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return Response.ok(menteesAwaiting, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    // get all mentors by topic and duration
    @GET
    @Path("mentors/{topic}/duration/{duration}/excludingUser/{userId}")
    @Produces("application/json")
    public Response getMentorsByTopic(@PathParam("topic") String topic, @PathParam("duration") int duration, @PathParam("userId") int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try{
            session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findMentorsByTopicAndDuration", Employee.class);
            query.setParameter("id", id);
            query.setParameter("topic", topic);
            query.setParameter("mentorDuration", duration);

            List<Employee> menteesByTopicList = query.getResultList();
            session.getTransaction().commit();
            session.close();

            return Response.ok(menteesByTopicList, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    // create an employee
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createEmployee(@FormParam("firstName") String firstName,
                                   @FormParam("lastName") String lastName,
                                   @FormParam("email") String email,
                                   @FormParam("password") String password) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        try {
            session.getTransaction().begin();

            Employee newEmployee = new Employee();
            newEmployee.setFirstName(firstName);
            newEmployee.setLastName(lastName);
            newEmployee.setEmail(email);
            newEmployee.setPassword(password);

            session.persist(newEmployee);
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

    // create a mentor
    @POST
    @Path("becomeMentor/{userId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response becomeMentor(@PathParam("userId") int userId, @FormParam("topic") String topic,
                                 @FormParam("mentorDuration") int mentorDuration,
                                 @FormParam("monday10") boolean monday10, @FormParam("monday11") boolean monday11,
                                 @FormParam("monday12") boolean monday12, @FormParam("monday14") boolean monday14,
                                 @FormParam("monday15") boolean monday15, @FormParam("monday16") boolean monday16,
                                 @FormParam("tuesday10") boolean tuesday10, @FormParam("tuesday11") boolean tuesday11,
                                 @FormParam("tuesday12") boolean tuesday12, @FormParam("tuesday14") boolean tuesday14,
                                 @FormParam("tuesday15") boolean tuesday15, @FormParam("tuesday16") boolean tuesday16,
                                 @FormParam("wednesday10") boolean wednesday10, @FormParam("wednesday11") boolean wednesday11,
                                 @FormParam("wednesday12") boolean wednesday12, @FormParam("wednesday14") boolean wednesday14,
                                 @FormParam("wednesday15") boolean wednesday15, @FormParam("wednesday16") boolean wednesday16,
                                 @FormParam("thursday10") boolean thursday10, @FormParam("thursday11") boolean thursday11,
                                 @FormParam("thursday12") boolean thursday12, @FormParam("thursday14") boolean thursday14,
                                 @FormParam("thursday15") boolean thursday15, @FormParam("thursday16") boolean thursday16,
                                 @FormParam("friday10") boolean friday10, @FormParam("friday11") boolean friday11,
                                 @FormParam("friday12") boolean friday12, @FormParam("friday14") boolean friday14,
                                 @FormParam("friday15") boolean friday15, @FormParam("friday16") boolean friday16) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        try {
            session.getTransaction().begin();

            Query<Employee> query = session.createNamedQuery("Employee.findById", Employee.class);
            query.setParameter("id", userId);
            Employee employee = query.getSingleResult();

            Query<Topic> topicQuery = session.createNamedQuery("Topic.findById", Topic.class);
            topicQuery.setParameter("id", topic);
            Topic topicIn = topicQuery.getSingleResult();

            Availability employeeAvailability = new Availability(monday10, monday11, monday12, monday14, monday15, monday16,
                    tuesday10, tuesday11, tuesday12, tuesday14, tuesday15, tuesday16,
                    wednesday10, wednesday11, wednesday12, wednesday14, wednesday15, wednesday16,
                    thursday10, thursday11, thursday12, thursday14, thursday15, thursday16,
                    friday10, friday11, friday12, friday14, friday15, friday16);
            session.persist(employeeAvailability);

            employee.becomeMentor(topicIn, employeeAvailability, mentorDuration);

            session.save(employee);
            session.getTransaction().commit();
            session.close();

            location = new URI("http://odyssey-aws.eu-west-1.elasticbeanstalk.com/myaccount/mentor.jsp");
            return Response.temporaryRedirect(location).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }


    // get all team members from a manager
    @GET
    @Path("getTeamMembers/manager/{userId}")
    @Produces("application/json")
    public Response getManagersTeamMembers(@PathParam("userId") int userId) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();
            Query<Employee> query = session.createNamedQuery("Employee.findById", Employee.class);
            query.setParameter("id", userId);
            Employee manager = query.getSingleResult();

            List<Employee> teamMembers = manager.getTeamMembers();
            session.getTransaction().commit();
            session.close();
            return Response.ok(teamMembers, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;

    }

    @GET
    @Path("getOdysseyCountWhereMentor/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOdysseyCountWhereMentor(@PathParam("userId") int userId) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        int odysseyCountWhereMentor=0;
        try {
            session.getTransaction().begin();

            Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByMentor", Odyssey.class);
            query.setParameter("id", userId);

            List<Odyssey> odysseysByEmployee = query.getResultList();
            session.getTransaction().commit();
            session.close();

            JSONObject obj = new JSONObject();
            JSONArray topics = new JSONArray();

            int odysseyCount = odysseysByEmployee.size();
            int meetingsHours=0;
            for (Odyssey odyssey : odysseysByEmployee) {
                int noOfMeetings = odyssey.getOdysseyMeetings().size();
                for(int i=0;i<noOfMeetings;i++) {
                    if(odyssey.getOdysseyMeetings().get(i).getIsCompleted()) {
                        meetingsHours++;
                    }
                }
                topics.add(odyssey.getTopic().getName());
            }

            obj.put("odysseyCount",odysseyCount);
            obj.put("meetingHours",meetingsHours);
            obj.put("topics",topics);


            return Response.ok(obj, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("getOdysseyCountWhereMentee/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOdysseyCountWhereMentee(@PathParam("userId") int userId) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        int odysseyCountWhereMentee=0;
        try {
            session.getTransaction().begin();

            Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByMentee", Odyssey.class);
            query.setParameter("id", userId);

            List<Odyssey> odysseysByEmployee = query.getResultList();
            session.getTransaction().commit();
            session.close();

            JSONObject obj = new JSONObject();
            JSONArray topics = new JSONArray();

            int odysseyCount = odysseysByEmployee.size();
            int meetingsHours=0;
            for (Odyssey odyssey : odysseysByEmployee) {
                int noOfMeetings = odyssey.getOdysseyMeetings().size();
                for(int i=0;i<noOfMeetings;i++) {
                    if(odyssey.getOdysseyMeetings().get(i).getIsCompleted()) {
                        meetingsHours++;
                    }
                }
                topics.add(odyssey.getTopic().getName());
            }

            obj.put("odysseyCount",odysseyCount);
            obj.put("meetingHours",meetingsHours);
            obj.put("topics",topics);


            return Response.ok(obj, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("getMenteeRating/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMenteeRating(@PathParam("userId") int userId) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        double menteeRating=0;
        try {
            session.getTransaction().begin();

            Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByMentee", Odyssey.class);
            query.setParameter("id", userId);

            List<Odyssey> odysseysByEmployee = query.getResultList();
            session.getTransaction().commit();
            session.close();

            JSONObject obj = new JSONObject();

            for(int i =0; i <odysseysByEmployee.size();i++) {
                for(int j =0; j< odysseysByEmployee.get(i).getOdysseyReviews().size();j++) {
                    if(odysseysByEmployee.get(i).getOdysseyReviews().get(j).isMentorReview()) {
                        Review r = odysseysByEmployee.get(i).getOdysseyReviews().get(j);
                        menteeRating += r.getRating();
                        menteeRating += r.getAttendance();
                        menteeRating += r.getPunctuality();
                        menteeRating += r.getMenteeEngagement();
                    }
                }
            }

            double averagedRating = menteeRating/4;
            obj.put("menteeRating",averagedRating);

            return Response.ok(obj, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("getMentorRating/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMentorRating(@PathParam("userId") int userId) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        double mentorRating=0;
        try {
            session.getTransaction().begin();

            Query<Odyssey> query = session.createNamedQuery("Odyssey.findOdysseysByMentor", Odyssey.class);
            query.setParameter("id", userId);

            List<Odyssey> odysseysByEmployee = query.getResultList();
            session.getTransaction().commit();
            session.close();

            JSONObject obj = new JSONObject();

            for(int i =0; i <odysseysByEmployee.size();i++) {
                for(int j =0; j< odysseysByEmployee.get(i).getOdysseyReviews().size();j++) {
                    if(odysseysByEmployee.get(i).getOdysseyReviews().get(j).isMenteeReview()) {
                        Review r = odysseysByEmployee.get(i).getOdysseyReviews().get(j);
                        mentorRating += r.getRating();
                        mentorRating += r.getAttendance();
                        mentorRating += r.getPunctuality();
                        mentorRating += r.getCourseMaterial();
                    }
                }
            }

            double averagedRating = mentorRating/4;
            obj.put("mentorRating",averagedRating);

            return Response.ok(obj, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }
}

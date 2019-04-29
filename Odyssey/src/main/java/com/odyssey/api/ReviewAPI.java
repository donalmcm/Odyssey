package com.odyssey.api;

import com.HibernateUtil;
import com.odyssey.model.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/reviews")
public class ReviewAPI {

    @GET
    @Produces("application/json")
    public Response getAllReviews() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();
            Query<Review> query = session.createNamedQuery("Review.findAllReviews",Review.class);
            List<Review> reviews = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return Response.ok(reviews, MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }


    @POST
    @Path("edit/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response createMeetingNote(@FormParam("punctuality") int punctuality, @FormParam("attendance") int attendance,
                                      @FormParam("courseMaterial") int courseMaterial, @FormParam("menteeEngagement") int menteeEngagement,
                                      @FormParam("rating") int rating, @FormParam("overallExperience") String overallExperience,
                                      @PathParam("id") int id) {

        Review review = Review.getReviewById(id);
        String currentPage = "";

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        URI location;
        try {
            session.getTransaction().begin();

            review.setPunctuality(punctuality);
            review.setAttendance(attendance);
            review.setRating(rating);
            review.setOverallExperience(overallExperience);

            if (courseMaterial != 0) {
                review.setCourseMaterial(courseMaterial);
            }
            if (menteeEngagement != 0) {
                review.setMenteeEngagement(menteeEngagement);
            }

            review.setSubmitted(true);

            session.update(review);
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

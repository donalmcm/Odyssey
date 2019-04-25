package com.odyssey.model;

import com.HibernateUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({@NamedQuery(name = "Review.findAllReviews", query = "select r from Review r"),
        @NamedQuery(name = "Review.findReviewById", query = "select r from Review r where r.id=:id")})

@XmlRootElement
@Entity
public class Review {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private int punctuality;

    @Column
    private int attendance;

    @Column // for mentee only
    private int courseMaterial;

    @Column // for mentor only
    private int menteeEngagement;

    @Column
    private int rating;

    @Column
    private String overallExperience;

    @Column
    private boolean mentorReview;

    @Column
    private boolean menteeReview;

    @Column
    private boolean submitted;

    @ManyToOne
    @JoinColumn
    private Odyssey odysseyReview;

    public Review() {
    }


    public static Review getReviewById(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query<Review> query = session.createNamedQuery("Review.findReviewById", Review.class);
        query.setParameter("id", id);
        Review review = query.getSingleResult();

        session.getTransaction().commit();
        session.close();
        return review;
    }

    public int getId() {
        return id;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public boolean isMentorReview() {
        return mentorReview;
    }

    public boolean isMenteeReview() {
        return menteeReview;
    }

    public void setMentorReview(boolean mentorReview) {
        this.mentorReview = mentorReview;
    }

    public void setMenteeReview(boolean menteeReview) {
        this.menteeReview = menteeReview;
    }

    public int getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(int punctuality) {
        this.punctuality = punctuality;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getCourseMaterial() {
        return courseMaterial;
    }

    public void setCourseMaterial(int courseMaterial) {
        this.courseMaterial = courseMaterial;
    }

    public int getMenteeEngagement() {
        return menteeEngagement;
    }

    public void setMenteeEngagement(int menteeEngagement) {
        this.menteeEngagement = menteeEngagement;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getOverallExperience() {
        return overallExperience;
    }

    public void setOverallExperience(String overallExperience) {
        this.overallExperience = overallExperience;
    }
}

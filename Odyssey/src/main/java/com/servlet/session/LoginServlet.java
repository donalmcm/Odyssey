package com.servlet.session;

import com.HibernateUtil;
import com.odyssey.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for username and password
        String emailIn = request.getParameter("email");
        String passwordIn = request.getParameter("password");

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();


        Query<Employee> query = session.createNamedQuery("Employee.findByEmail",Employee.class);
        query.setParameter("email",emailIn);
        if(!query.getResultList().isEmpty()) {
            Employee employee = query.getSingleResult();

            final String email = employee.getEmail();
            final String password = employee.getPassword();

            session.getTransaction().commit();
            session.close();
            if (email.equals(emailIn) && password.equals(passwordIn)) {
                //get the old session and invalidate
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                //generate a new session
                HttpSession newSession = request.getSession(true);

                //setting session to expiry in 15 mins
                newSession.setMaxInactiveInterval(15*60);

                Cookie userEmail = new Cookie("email",email);
                response.addCookie(userEmail);
                response.sendRedirect("myaccount/home.jsp");
                // For further security
                //cookie.setSecure(true); // will only send over https
                //cookie.setHttpOnly(true); // help with XSS attacks
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either username or password is wrong.</font>");
                rd.include(request, response);
            }
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username or password is wrong.</font>");
            rd.include(request, response);
        }


    }
}

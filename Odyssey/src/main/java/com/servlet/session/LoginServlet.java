package com.servlet.session;

import com.HibernateUtil;
import com.odyssey.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

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

        Query<Employee> query = session.createNamedQuery("Employee.findByEmail", Employee.class);
        query.setParameter("email", emailIn);
        if (!query.getResultList().isEmpty()) {
            Employee employee = query.getSingleResult();
            session.getTransaction().commit();
            session.close();

            final String email = employee.getEmail();
            final String password = employee.getPassword();

            if (email.equals(emailIn) && password.equals(passwordIn)) {
                //get the old session and invalidate
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                //generate a new session
                HttpSession newSession = request.getSession(true);

                //setting session to expiry in 15 mins
                newSession.setMaxInactiveInterval(15 * 60);

                Cookie userEmail = new Cookie("email", email);
                response.addCookie(userEmail);
                response.sendRedirect("myaccount/home.jsp");
                // For further security
                //cookie.setSecure(true); // will only send over https
                //cookie.setHttpOnly(true); // help with XSS attacks
            } else {

                String message = "Either username or password is wrong.";
                request.setAttribute("message", message); // This will be available as ${message}
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            String message = "Either username or password is wrong.";
            request.setAttribute("message", message); // This will be available as ${message}
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

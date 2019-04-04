package com.servlet.session;

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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final String email = "donal@email.com"; // change to actual user
    private final String password = "password";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for username and password
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (this.email.equals(email) && this.password.equals(password)) {
            //get the old session and invalidate
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //generate a new session
            HttpSession newSession = request.getSession(true);

            //setting session to expiry in 5 mins
            newSession.setMaxInactiveInterval(5*60);

            Cookie message = new Cookie("message", "Welcome");
            Cookie userEmail = new Cookie("email",this.email);
            response.addCookie(userEmail);
            response.addCookie(message);
            response.sendRedirect("myaccount/LoginSuccess.jsp");
            // For further security
            //cookie.setSecure(true); // will only send over https
            //cookie.setHttpOnly(true); // help with XSS attacks
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username or password is wrong.</font>");
            rd.include(request, response);
        }
    }
}

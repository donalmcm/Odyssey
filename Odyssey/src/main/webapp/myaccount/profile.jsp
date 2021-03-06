<%@ page import="com.odyssey.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Odyssey - Profile</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../static/css/index.css">
    <link rel="shortcut icon" type="image/x-icon" href="../static/img/odysseyLogo.png"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<%
    String email = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) email = cookie.getValue();
        }
    }
    Employee e = Employee.getEmployeeByEmail(email);
%>
<body onload="getEmployeeStats(<%=e.getId()%>)">
<div class="container-fluid">
    <div class="row banner">
        <div class="col-md-2 text-center banner-logo">
            <a href="home.jsp">
                <img alt="Odyssey Logo" src="../static/img/odysseyLogo.png" class="rounded"/>
            </a>
        </div>
        <div class="col-md-8 banner-title">
            <h1>
                Odyssey
            </h1>
        </div>
        <div class="col-md-1 text-center profile-icon">
            <a href="profile.jsp"><i class="far fa-user-circle fa-3x rounded"></i></a>
        </div>
        <form action="LogoutServlet" method="post">
            <button type="submit" class="col-md-1 text-center log-out-icon">
                <i class="fas fa-sign-out-alt fa-3x rounded"></i>
            </button>
        </form>
    </div>
    <div class="row main">
        <div class="col-md-2 side-nav">
            <ul class="nav flex-column nav-tabs">
                <li class="nav-item">
                    <a class="nav-link" href="mentor.jsp">Mentor</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="mentee.jsp">Mentee</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="manager.jsp">Manager</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="admin.jsp">Admin</a>
                </li>
            </ul>
        </div>
        <div class="row col-md-10">
            <div>
                <div class="col-md-12">
                    <h2>
                        <strong>Name: </strong><%=e.getFirstName() + " " + e.getLastName()%>
                    </h2>
                    <h4>
                        <strong>Title: </strong><%=e.getTitle()%>
                    </h4>
                    <h4>
                        <strong>Email: </strong><%=e.getEmail()%>
                    </h4>
                    <h4>
                        <strong>Location: </strong><%=e.getLocation()%>
                    </h4>
                </div>
            </div>
            <div id="mentor-stats">
                <div class="col-md-12 profile-mentor-card">
                    <div class="col-md-12 profile-mentor-title">
                        <div class="col-md-4">
                        <h1>Mentor Odysseys</h1>
                        </div>
                        <div class="col-md-8 mentor-rating">
                            <h1 id="mentor-rating"></h1>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <h3>Total Count</h3>
                        <h1 id="mentor-odyssey-count"></h1>
                    </div>
                    <div class="col-md-4">
                        <h3>Total Hours of Meetings</h3>
                        <h1 id="mentor-hour-count"></h1>
                    </div>
                    <div class="col-md-4">
                        <h3>All Topics Mentored</h3>
                        <div id="mentor-topic-list"></div>
                    </div>
                </div>
            </div>
            <div id="mentee-stats">
                <div class="col-md-12 profile-mentee-card">
                    <div class="col-md-12 profile-mentor-title">
                        <div class="col-md-4">
                            <h1>Mentee Odysseys</h1>
                        </div>
                        <div class="col-md-8 mentee-rating">
                            <h1 id="mentee-rating"></h1>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <h3>Total Count</h3>
                        <h1 id="mentee-odyssey-count"></h1>
                    </div>
                    <div class="col-md-4">
                        <h3>Total Hours of Meetings</h3>
                        <h1 id="mentee-hour-count"></h1>
                    </div>
                    <div class="col-md-4">
                        <h3>All Topics Menteed</h3>
                        <div id="mentee-topic-list"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../static/js/profile.js"></script>
</body>
</html>

<%@ page import="com.odyssey.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Odyssey - Profile</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../static/css/index.css">
    <link rel="shortcut icon" type="image/x-icon" href="../static/img/odysseyLogo.png" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<%
    String email = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("email")) email = cookie.getValue();
        }
    }
    Employee e = new Employee();
     e = Employee.getEmployeeByEmail(email);
%>
<div class="container-fluid">
    <div class="row banner">
        <div class="col-md-2 text-center banner-logo">
            <a href="home.jsp">
                <img alt="Odyssey Logo" src="../static/img/odysseyLogo.png" class="rounded" />
            </a>
        </div>
        <div class="col-md-8 banner-title">
            <h1>
                Odyssey
            </h1>
        </div>
        <div class="col-md-1 text-center profile-icon">
            <a href="profile.jsp"><i class="far fa-user-circle fa-3x" class="rounded"></i></a>
        </div>
        <form action="LogoutServlet" method="post">
            <input type="submit" value="Logout" >
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
        <div class="col-md-10">
            <div class="row">
                <div class="col-md-2">
                    <img alt="Default Profile Image" src="../static/img/odysseyLogo.png" />
                </div>
                <div class="col-md-10">
                    <h3>
                        <strong><%=e.getFirstName()%></strong>
                    </h3>
                    <h3>
                        Software Development Student
                    </h3>
                </div>
            </div>
            <div class="row justify-content-between">
                <div class="col-md-5 profile-card">
                    <div class="row profile-card-title">
                        <div class="col-md-10">
                            Specialities
                        </div>
                        <div class="col-md-2">
                            <button class="edit-button"><i class="fa fa-edit"></i></button>
                        </div>
                    </div>
                    <div class="row">
                        <li>Git</li>
                        <li>Gosu</li>
                    </div>
                </div>
                <div class="col-md-5 profile-card">
                    <div class="row profile-card-title">
                        <div class="col-md-10">
                            Personal Goals
                        </div>
                        <div class="col-md-2">
                            <button class="edit-button"><i class="fa fa-edit"></i></button>
                        </div>
                    </div>
                    <div class="row">
                        <li>JPA</li>
                        <li>Javascript</li>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-11 profile-card">
                    <div class="row profile-card-title">
                        <div class="col-md-11">
                            Mentoring Odysseys
                        </div>
                        <div class="col-md-1">
                            <button class="edit-button"><i class="fa fa-edit"></i></button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3 profile-subcard">
                            TOTAL<br>
                            5 ODYSSEYS
                        </div>
                        <div class="col-md-3 profile-subcard">
                            TOTAL HOURS<br>
                            37
                        </div>
                        <div class="col-md-3 profile-subcard">
                            TOPICS<br>
                            <li>Git</li>
                            <li>Gosu</li>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-11 profile-card">
                    <div class="row profile-card-title">
                        <div class="col-md-11">
                            Mentee Odysseys
                        </div>
                        <div class="col-md-1">
                            <button class="edit-button"><i class="fa fa-edit"></i></button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3 profile-subcard">
                            TOTAL<br>
                            3 ODYSSEYS
                        </div>
                        <div class="col-md-3 profile-subcard">
                            TOTAL HOURS<br>
                            21
                        </div>
                        <div class="col-md-3 profile-subcard">
                            TOPICS
                            <li>JPA</li>
                            <li>Javascript</li>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script src="../static/js/admin.js"></script>
</body>
</html>

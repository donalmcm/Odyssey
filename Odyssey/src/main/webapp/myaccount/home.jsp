<%@ page import="com.odyssey.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Odyssey - Home</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../static/css/index.css">
    <link rel="shortcut icon" type="image/x-icon" href="../static/img/odysseyLogo.png" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
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
<body onload="getOdysseys(<%=e.getId()%>);">
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
        <div class="col-md-1 text-center profile-icon">
            <a action><i class="far fa-user-circle fa-3x" class="rounded"></i></a>
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
                    <a class="nav-link disabled" href="admin.jsp">Admin</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="row">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>
                            Topic
                        </th>
                        <th>
                            Mentor
                        </th>
                        <th>
                            Mentee
                        </th>
                        <th>
                            Progress
                        </th>
                    </tr>
                    </thead>
                    <tbody id="odyssey-list">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="../static/js/home.js"></script>
</body>
</html>

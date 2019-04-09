<%@ page import="com.odyssey.model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: iDugin
  Date: 09/04/2019
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Odyssey - Management</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../static/css/index.css">
    <link rel="shortcut icon" type="image/x-icon" href="../static/img/odysseyLogo.png" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
    tle</title>
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
            <img alt="Odyssey Logo" src="../static/img/odysseyLogo.png" class="rounded" />
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
                <div class="col-md-6">
                    <h3>
                        Management
                    </h3>
                </div>
                <div class="col-md-6">
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4 manager-tabs">
                    <ul class="nav nav-pills">
                        <li class="active"><a data-toggle="pill" href="#home">Team Members</a></li>
                        <li><a data-toggle="pill" href="#reports">Reports</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                </div>
            </div>
            <div class="row tab-content">
                <div id="home" class="col-md-12 row tab-pane fade in active">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>
                                #
                            </th>
                            <th>
                                First Name
                            </th>
                            <th>
                                Last Name
                            </th>
                            <th>
                                Email
                            </th>
                        </tr>
                        </thead>
                        <tbody id="managers-team">
                        </tbody>
                    </table>
                </div>
                <div id="reports" class="col-md-12 row tab-pane fade">
                    <div class="col-md-6">
                        <canvas id="line-chart" width="500" height="400"></canvas>
                    </div>
                    <div class="col-md-6">
                        <canvas id="radar-chart" width="500" height="400"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script src="../static/js/manager.js"></script>

</body>
</html>

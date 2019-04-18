<%@ page import="com.odyssey.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Odyssey - Administration</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../static/css/index.css">
    <link rel="shortcut icon" type="image/x-icon" href="../static/img/odysseyLogo.png"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
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
    Employee e = new Employee();
    e = Employee.getEmployeeByEmail(email);
%>
<body onload="loadAdminPage(<%=e.isAdmin()%>);">
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
            <a href="profile.jsp"><i class="far fa-user-circle fa-3x" class="rounded"></i></a>
        </div>
        <form action="LogoutServlet" method="post">
            <input type="submit" value="Logout">
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
                    <h2 id="admin-page-title">
                    </h2>
                </div>
                <div class="col-md-6">
                </div>
            </div>
            <div id="admin-page-content">
                <div class="row">
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-4 manager-tabs"> <!-- styling name -->
                        <ul class="nav nav-pills">
                            <li class="active"><a data-toggle="pill" href="#home">Employees</a></li>
                            <li><a data-toggle="pill" href="#topics">Topics</a></li>
                        </ul>
                    </div>
                    <div class="col-md-4">
                    </div>
                </div>
                <div class="row tab-content">
                    <div id="home" class="col-md-12 row tab-pane fade in active">
                        <button type="button" class="btn btn-success btn-lg margin16" id="create-employee">
                            Create Employee
                        </button>
                        <div class="modal fade" id="create-employee-modal" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header new-employee-form-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h1><i class="far fa-user-circle fa-3x"></i> New Employee Form</h1>
                                    </div>
                                    <div class="modal-body" style="padding:40px 50px;">
                                        <form role="form" action="../api/employees/create" method="post">
                                            <div class="form-group">
                                                <label for="firstName">First Name</label>
                                                <input type="text" class="form-control" id="firstName" name="firstName"
                                                       placeholder="Enter first name">
                                            </div>
                                            <div class="form-group">
                                                <label for="lastName">Last Name</label>
                                                <input type="text" class="form-control" id="lastName" name="lastName"
                                                       placeholder="Enter last name">
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Email</label>
                                                <input type="text" class="form-control" id="email" name="email"
                                                       placeholder="Enter email">
                                            </div>
                                            <div class="form-group">
                                                <label for="password">Password</label>
                                                <input type="text" class="form-control" id="password"
                                                       name="password" placeholder="Enter password">
                                            </div>
                                            <button type="submit" class="btn btn-success btn-block">Create</button>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-danger btn-default pull-left"
                                                data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>Cancel
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h3 class="margin16">All Employees</h3>
                        <table class="employee-table">
                            <tr>
                                <th>
                                    First Name
                                </th>
                                <th>
                                    Last Name
                                </th>
                                <th>
                                    Email
                                </th>
                                <th>
                                    Title
                                </th>
                            </tr>
                            <tbody id="employee-list">
                            </tbody>
                        </table>
                    </div>
                    <div id="topics" class="col-md-12 row tab-pane fade">
                        <button type="button" class="btn btn-success btn-lg margin16" id="create-topic">
                            Create Topic
                        </button>
                        <div class="modal fade" id="create-topic-modal" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header new-employee-form-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h1><i class="far fa-user-circle fa-3x"></i> New Topic Form</h1>
                                    </div>
                                    <div class="modal-body" style="padding:40px 50px;">
                                        <form role="form" action="../api/topics/create" method="post">
                                            <div class="form-group">
                                                <label for="firstName">First Name</label>
                                                <input type="text" class="form-control" id="topicName" name="topicName"
                                                       placeholder="Enter Topic name">
                                            </div>
                                            <button type="submit" class="btn btn-success btn-block">Create</button>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-danger btn-default pull-left"
                                                data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>Cancel
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h3 class="margin16">All Topics</h3>
                        <table class="employee-table">
                            <tr>
                                <th>
                                    Topic
                                </th>
                                <th>
                                    Sub Topic
                                </th>
                            </tr>
                            <tbody id="topic-list">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../static/js/admin.js"></script>
</body>
</html>

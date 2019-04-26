<%@ page import="com.odyssey.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Odyssey - Home</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../static/css/index.css">
    <link rel="stylesheet" type="text/css" href="../static/css/home.css">
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
    Employee e = Employee.getEmployeeByEmail(email);
%>
<body onload="getOdysseys(<%=e.getId()%>,'<%=e.getFirstName()%>');">
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
                    <a class="nav-link disabled" href="admin.jsp">Admin</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="row">
                <h2 id="home-page-title">
                    Your Odysseys
                </h2>
                <div id="odyssey-list"></div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="edit-meeting-note-modal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header new-employee-form-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h1><i class="fa fa-edit fa-3x"></i> Edit Meeting Note</h1>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    <form role="form" method="post" id="edit-meeting-note-form">
                        <div class="form-group">
                            <label for="meetingNote">Meeting Note</label>
                            <textarea class="form-control" id="meetingNote" name="meetingNote" required
                                      placeholder="Enter notes here" style="height: 200px"></textarea>
                        </div>
                        <button type="submit" class="btn btn-success btn-block">Submit</button>
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
    <div class="modal fade" id="odyssey-review-modal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header new-employee-form-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h1><i class="fa fa-edit fa-3x"></i> Edit Odyssey Review</h1>
                </div>
                <div class="modal-body" style="padding:40px 50px;">
                    <form role="form" method="post" id="odyssey-review-form">
                        <div class="form-group">
                            <label for="punctuality">Punctuality</label>
                            <input class="form-control" type="range" id="punctuality" name="punctuality" min="1" max="5"
                                   required>
                        </div>
                        <div class="form-group">
                            <label for="attendance">Attendance</label>
                            <input class="form-control" type="range" id="attendance" name="attendance" min="1" max="5"
                                   required>
                        </div>
                        <div class="form-group" id="course-material-section">
                            <label for="courseMaterial">Course Material</label>
                            <input class="form-control" type="range" id="courseMaterial" name="courseMaterial" min="1"
                                   max="5" required>
                        </div>
                        <div class="form-group" id="mentee-engagement-section">
                            <label for="menteeEngagement">Mentee Engagement</label>
                            <input class="form-control" type="range" id="menteeEngagement" name="menteeEngagement"
                                   min="1" max="5" required>
                        </div>
                        <div class="form-group">
                            <label for="rating">Rating</label>
                            <input class="form-control" type="range" id="rating" name="rating" min="1" max="5" required>
                        </div>
                        <div class="form-group">
                            <label for="overallExperience">Overall Experience</label>
                            <textarea class="form-control" id="overallExperience" name="overallExperience" required
                                      placeholder="Enter your experience here" style="height: 200px"></textarea>
                        </div>
                        <button type="submit" class="btn btn-success btn-block">Submit</button>
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
</div>
<script src="../static/js/home.js"></script>
</body>
</html>

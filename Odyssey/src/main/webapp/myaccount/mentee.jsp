<%@ page import="com.odyssey.model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: iDugin
  Date: 09/04/2019
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Odyssey - Mentee</title>
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
            <img alt="Odyssey Logo" src="../static/img/odysseyLogo.png" class="rounded"/>
        </div>
        <div class="col-md-8 banner-title">
            <h1>
                Odyssey
            </h1>
        </div>
        <div class="col-md-1 text-center profile-icon">
            <a href="profile.html"><i class="far fa-user-circle fa-3x" class="rounded"></i></a>
        </div>
        <div class="col-md-1 text-center log-out-icon">
            <a href="../index.html"><i class="fas fa-sign-out-alt fa-3x" class="rounded"></i></a>
        </div>
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
                        Mentee
                    </h3>
                    <form>
                        <div class="form-group">
                            <label for="topic-filter">Select a topic</label>
                            <select onchange="getMentorsByTopic(this.value)" class="form-control" id="topic-filter"
                                    name="topicFilter"></select>
                        </div>
                        <div class="form-group">
                            <label for="duration-dropdown">Select duration in weeks</label>
                            <select onchange="getAvailabilitiesByTopicAndDuration(this.value)" class="form-control"
                                    id="duration-dropdown" name="duration"></select>
                        </div>
                        <div class="col-md-12" id="mentor-list-by-topic"></div>

                        <!-- Days of weeks -->
                        <div class="form-group">
                            <label for="select-day">Select A Day</label>
                            <ul class="nav nav-pills" id="select-day">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="pill"
                                       style="border: solid 2px cornflowerblue;margin-right: 4px"
                                       href="#home">Monday</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="pill"
                                       style="border: solid 2px cornflowerblue;margin-right: 4px"
                                       href="#tuesday">Tuesday</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="pill"
                                       style="border: solid 2px cornflowerblue;margin-right: 4px"
                                       href="#wednesday">Wednesday</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="pill"
                                       style="border: solid 2px cornflowerblue;margin-right: 4px"
                                       href="#thursday">Thursday</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="pill"
                                       style="border: solid 2px cornflowerblue;margin-right: 4px"
                                       href="#friday">Friday</a>
                                </li>
                            </ul>
                        </div>

                        <!-- Tab panes -->
                        <div class="form-group">
                            <label for="select-time">Select One Time Slot</label>
                            <!-- put in validation for one input -->
                            <div class="tab-content" id="select-time">
                                <div class="tab-pane container active" style="padding: 0" id="home">
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="monday10" id="monday10" value="true"
                                                                         disabled><span>10AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="monday11" id="monday11" value="true"
                                                                         disabled><span>11AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="monday12" id="monday12" value="true"
                                                                         disabled><span>12AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="monday14" id="monday14" value="true"
                                                                         disabled><span>14PM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="monday15" id="monday15" value="true"
                                                                         disabled><span>15PM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="monday16" id="monday16" value="true"
                                                                         disabled><span>16PM</span></label></div>
                                </div>
                                <div class="tab-pane container fade" style="padding: 0" id="tuesday">
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="tuesday10" id="tuesday10" value="true"
                                                                         disabled><span>10 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="tuesday11" id="tuesday11" value="true"
                                                                         disabled><span>11 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="tuesday12" id="tuesday12" value="true"
                                                                         disabled><span>12 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="tuesday14" id="tuesday14" value="true"
                                                                         disabled><span>14 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="tuesday15" id="tuesday15" value="true"
                                                                         disabled><span>15 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="tuesday16" id="tuesday16" value="true"
                                                                         disabled><span>16 AM</span></label></div>
                                </div>
                                <div class="tab-pane container fade" style="padding: 0" id="wednesday">
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="wednesday10" id="wednesday10"
                                                                         value="true"
                                                                         disabled><span>10 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="wednesday11" id="wednesday11"
                                                                         value="true"
                                                                         disabled><span>11 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="wednesday12" id="wednesday12"
                                                                         value="true"
                                                                         disabled><span>12 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="wednesday14" id="wednesday14"
                                                                         value="true"
                                                                         disabled><span>2 PM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="wednesday15" id="wednesday15"
                                                                         value="true"
                                                                         disabled><span>3 PM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="wednesday16" id="wednesday16"
                                                                         value="true"
                                                                         disabled><span>4 PM</span></label></div>
                                </div>
                                <div class="tab-pane container fade" style="padding: 0" id="thursday">
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="thursday10" id="thursday10" value="true"
                                                                         disabled><span>10 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="thursday11" id="thursday11" value="true"
                                                                         disabled><span>11 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="thursday12" id="thursday12" value="true"
                                                                         disabled><span>12 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="thursday14" id="thursday14" value="true"
                                                                         disabled><span>14 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="thursday15" id="thursday15" value="true"
                                                                         disabled><span>15 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="thursday16" id="thursday16" value="true"
                                                                         disabled><span>16 AM</span></label></div>
                                </div>
                                <div class="tab-pane container fade" style="padding: 0" id="friday">
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="friday10" id="friday10" value="true"
                                                                         disabled><span>10 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="friday11" id="friday11" value="true"
                                                                         disabled><span>11 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="friday12" id="friday12" value="true"
                                                                         disabled><span>12 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="friday14" id="friday14" value="true"
                                                                         disabled><span>14 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="friday15" id="friday15" value="true"
                                                                         disabled><span>15 AM</span></label></div>
                                    <div class="ck-button"><label><input class="resetToDisabled" type="checkbox"
                                                                         name="friday16" id="friday16" value="true"
                                                                         disabled><span>16 AM</span></label></div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" onclick="populateModal()" class="btn btn-success btn-block" id="begin-odyssey">Confirm
                        </button>
                    </form>
                    <div class="modal fade" id="odyssey-modal" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header new-odyssey-form-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h1><i class="far fa-user-circle fa-3x"></i> Create Odyssey</h1>
                                </div>
                                <div class="modal-body" style="padding:40px 50px;">
                                    <form role="form" action="api/odyssey/create" method="post">
                                        <div class="form-group">
                                            <label for="topicId">Topic</label>
                                            <input class="form-control" id="topicId" name="topicId"
                                                   readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="mentorDuration">Odyssey Duration In Weeks</label>
                                            <input class="form-control" id="mentorDuration"
                                                   name="mentorDuration" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="mentorDuration">Day of Meetings</label>
                                            <input class="form-control" id="dayOfMeetings"
                                                   name="dayOfMeetings" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label for="mentorDuration">Time of Meetings</label>
                                            <input class="form-control" id="timeOfMeetings"
                                                   name="timeOfMeetings" readonly>
                                        </div>
                                        <button type="submit" class="btn btn-success btn-block">Begin Odyssey</button>
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
                <div class="col-md-6">
                </div>
            </div>
            <div class="row">
            </div>
        </div>
    </div>
</div>
</div>
<script src="../static/js/mentee.js"></script>
</body>
</html>

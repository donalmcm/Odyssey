<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <title>Odyssey - Log In</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="static/css/index.css">
    <link rel="shortcut icon" type="image/x-icon" href="static/img/odysseyLogoWithTitle.png"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-6">
            <form action="LoginServlet" class="login-form" method="post">
                <div class="row">
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-8 text-center login-logo">
                        <img src="static/img/odysseyLogoWithTitle.png" class="rounded" alt="Odyssey">
                    </div>
                    <div class="col-md-2">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p style="color: red; text-align: center">${message}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <input type="text" placeholder="Enter Username" id="email" name="email" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <input type="password" placeholder="Enter Password" id="password" name="password" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <button type="submit" class="login-button">Login</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3">
        </div>
    </div>
</div>
</body>
</html>

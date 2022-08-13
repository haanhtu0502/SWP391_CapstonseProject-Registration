<%@page import="DAO.UserDAO"%>
<%@page import="DTO.Users"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Dashboard - Profile</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

        <!-- Font Awesome Icon -->
        <script src="https://kit.fontawesome.com/e7ea130b87.js" crossorigin="anonymous"></script>

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
                  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
                  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
                <![endif]-->

    </head>

    <body>

        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
                response.sendRedirect("Login.jsp");
            } else {
                String email = (String) session.getAttribute("email");
                int userId = (int) session.getAttribute("userId");
                int roleId = (int) session.getAttribute("roleId");
                String department = (String) session.getAttribute("department");
                if (roleId == 2) {
                    response.sendRedirect("profileLecturer.jsp");
                } else if (roleId == 4) {
                    response.sendRedirect("profileAdmin.jsp");
                } else {
        %>

        <!-- Header -->
        <header>
            <%@include file="header_profile.jsp" %>
        </header>
        <!-- /Header -->

        <div class="container head-title flex">
            <h1 class="col-sm-8" style="text-align: left;">Profile</h1>
            <h4 class="col-sm-4" style="text-align: right;">
                <a href="MainController?action=logout">
                    <span class="fa-solid fa-right-from-bracket fa-md"></span>
                    <span>Logout</span>
                </a>
            </h4>
        </div>


        <!--Profile-->
        <div class="container profile">
            <div class="card text-center col-12 col-sm-5">
                <img class="profile-pic img-fluid card-image-top" src="./img/sample-avatar.jpg" alt="Profile Picture">
                <div class="card-body">
                    <h2 class="card-title text-grey"><%= name%></h2>
                    <p><%= email%></p>
                </div>
            </div>
            <div class="col-12 col-sm-7">
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-1">
                                <span class="fa-solid fa-user fa-lg"></span>
                            </div>
                            <div class="col-sm-3">
                                <p class="mb-0">Full Name</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0"><%= name%></p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-1">
                                <span class="fa-solid fa-envelope fa-lg"></span>
                            </div>
                            <div class="col-sm-3">
                                <p class="mb-0">Email</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0"><%= email%></p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-1">
                                <span class="fa-solid fa-key fa-lg"></span>
                            </div>
                            <div class="col-sm-3">
                                <p class="mb-0">Code</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0"><%= userId%></p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-1">
                                <span class="fa-solid fa-building-columns fa-lg"></span>
                            </div>
                            <div class="col-sm-3">
                                <p class="mb-0">Department</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0"><%= department%></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--/Profile-->


        <!-- topic -->

        <footer>
            <%@include file="footer.jsp" %>
        </footer>

        <% }
            }
        %>

        <!-- preloader -->
        <div id='preloader'>
            <div class='preloader'></div>
        </div>
        <!-- /preloader -->


        <!-- jQuery Plugins -->
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>

    </body>

</html>
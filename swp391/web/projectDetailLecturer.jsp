<%@page import="java.util.List"%>
<%@page import="DTO.Users"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Project Detail</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />

        <!--       Bootstrap Icon -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

        <!-- Font Awesome Icon -->
        <!--        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />-->
        <script src="https://kit.fontawesome.com/e7ea130b87.js" crossorigin="anonymous"></script>

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="../css/style.css" />



    </head>

    <body>

        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
                response.sendRedirect("Login.jsp");
            } else {
                int roleId = (int) session.getAttribute("roleId");
                if (roleId == 1) {
                    response.sendRedirect("profile.jsp");
                } else if (roleId == 4) {
                    response.sendRedirect("profileAdmin.jsp");
                } else {
        %>

        <!-- Header -->
        <header>
            <%@include file="headerLecturer.jsp" %>
        </header>
        <!-- /Header -->

        <!--content-->
        <section id="project">
            <div class="container">
                <div class="project-title">
                    <h1 style="text-align: left">@${Group.groupName} - ID: ${Group.groupId}</h1>
                </div>

                <div class="container">
                    <div class="col-md-6">
                        <div class="project-content">
                            <h6 class="project-name">Team information</h6>
                            <hr>
                            <ul class="project-list">
                                <li class="project-item project-wrap">
                                    <i class="fa fa-solid fa-bars col-sm-1"></i>
                                    <span class="col-sm-4">Team Name</span>
                                    <span class="col-sm-7">${Group.groupName}</span>
                                </li>
                                <li class="project-item project-wrap">
                                    <i class="fa-solid fa-file-signature col-sm-1"></i>
                                    <span class="col-sm-4">Topic Name</span>
                                    <span class="col-sm-7">${Topic.name}</span>
                                </li>
                                <li class="project-item project-wrap">
                                    <i class="fa-solid fa-calendar col-sm-1"></i>
                                    <span class="col-sm-4">Semester</span>
                                    <span class="col-sm-7">${Sem.name}</span>
                                </li>
                                <li class="project-item project-wrap">
                                    <i class="fa fa-regular fa-building col-sm-1"></i>
                                    <span class="col-sm-4">Department</span>
                                    <div class="col-sm-7 project-wrap">
                                        <span class="blue-box">${DepName}</span>
                                    </div>
                                </li>
                            </ul>
                            <div class="project-item" style="margin-top: 5px">
                                <i class="fa-solid fa-lock col-sm-1"></i>
                                <div class="col-sm-2">
                                    <span class="green-box">${Pro.status ==1?"Lock":"Unlock"}</span>
                                </div>
                                <span class="col-sm-2"></span>
                                <i class="fa-solid fa-shield col-sm-1"></i>
                                <div class="col-sm-2">
                                    <c:choose >
                                        <c:when test="${Group.groupStatus == 1}">
                                            <span class="green-box">${Group.groupStatus == 1?"Public":"Private"}</span>                                     
                                        </c:when>
                                        <c:when test="${Group.groupStatus == 0}">
                                            <span class="orange-box">${Group.groupStatus == 1?"Public":"Private"}</span>                                  
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="project-content">
                            <h6 class="project-name">Team members</h6>
                            <hr>
                            <ul class="project-list">
                                <%!  List<Users> list;%>
                                <%
                                    list = (List<Users>) request.getAttribute("list");
                                    for (Users s : list) {
                                        out.print("<li class='project-item'>"
                                                + "<div class='col-sm-2'>"
                                                + "<img class=\"avatar\" src=\"../img/sample-avatar.jpg\" alt=\"Avatar\">"
                                                + "</div>"
                                                + "<span>" + s.getName() + "</span>"
                                                + "</li>");
                                    }

                                %>
                            </ul>

                        </div>
                    </div>
                </div>


            </div>
        </section>

        <!--/content-->

        <footer>
            <%@include file="footer.jsp" %>
        </footer>

        <% }
            }
        %>

        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/main.js"></script>
        <script src="../js/topic.js"></script>
    </body>
</html>

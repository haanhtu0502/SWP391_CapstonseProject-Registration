<%-- Document : topicDetail Created on : Jun 15, 2022, 8:47:17 AM Author : phamquang --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - Topic Detail</title>
        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />

        <!-- Font Awesome Icon -->
        <script src="https://kit.fontawesome.com/e7ea130b87.js" crossorigin="anonymous"></script>


        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="../css/style.css" />

        <!-- topic stylessheet -->
        <link type="text/css" rel="stylesheet" href="../css/topicdetail.css" />
    </head>

    <body>

        <% String name = (String) session.getAttribute("name");
            if (name == null) {
                response.sendRedirect("Login.jsp");
            } else {
        %>

        <!-- Header -->
        <header>
            <c:if test="${user.roleId==1}">
                <%@include file="header.jsp" %>
            </c:if>
            <c:if test="${user.roleId==2}">
                <%@include file="headerLecturer.jsp" %>
            </c:if>
            <c:if test="${user.roleId==4}">
                <%@include file="headerAdmin.jsp" %>
            </c:if>
        </header>
        <!-- /Header -->


        <!-- topic detail -->

        <section id="topicdetail">
            <div class="container">
                <div class="topicdetail__title">
                    <h1>Topic detail</h1>
                </div>

                <div class="container ">
                    <div class="col-md-6">
                        <div class="topicdetail__content">
                            <h6 class="topicdetail__name">Topic information</h6>
                            <hr>
                            <ul class="topicdetail__list">
                                <li class="topicdetail__item">
                                    <i class="fa fa-solid fa-bars col-sm-1"></i>
                                    <span class="col-sm-3">Topic name</span>
                                    <span class="col-sm-8">${topic.name}</span>
                                </li>
                                <li class="topicdetail__item">
                                    <i class="fa fa-regular fa-id-badge col-sm-1"></i>
                                    <span class="col-sm-3">Topic ID</span>
                                    <span class="col-sm-8">${topic.topicId}</span>
                                </li>
                                <li class="topicdetail__item">
                                    <i class="fa fa-regular fa-building col-sm-1"></i>
                                    <span class="col-sm-3">Department</span>
                                    <span class="col-sm-8">${topic.department.name}</span>
                                </li>
                                <li class="topicdetail__item">
                                    <i class="fa fa-solid fa-users col-sm-1"></i>
                                    <span class="col-sm-3">Members</span>
                                    <span class="col-sm-8">From 2 to 6 student</span>
                                </li>
                                <li class="topicdetail__item">
                                    <i class="fa fa-solid fa-user-plus col-sm-1"></i>
                                    <span class="col-sm-3">Lecturer</span>
                                    <span class="col-sm-8">${topic.user.name}</span>
                                </li>
                                <li class="topicdetail__item">
                                    <i class="fa fa-solid fa-graduation-cap col-sm-1"></i>
                                    <span class="col-sm-3">Category:</span>
                                    <span class="col-sm-8">${cate.categoryName}</span>
                                </li>
                                <li class="topicdetail__item">
                                    <i class="fa fa-regular fa-clipboard col-sm-1"></i>
                                    <span class="col-sm-3">Description:</span>
                                    <span class="col-sm-8">${topic.description}</span>
                                </li>
                            </ul>

                        </div>

                        <div class="topicdetail__content">
                            <h6 class="topicdetail__name">Proposed Tasks for student</h6>
                            <hr>

                            <div class="topicdetail__doc">
                                <h6>Task Package 1</h6>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                            </div>

                            <div class="topicdetail__doc">
                                <h6>Task Package 2</h6>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                            </div>

                            <div class="topicdetail__doc">
                                <h6>Task Package 3</h6>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="topicdetail__content">
                            <h6 class="topicdetail__name">Register content of Capstone Project</h6>
                            <hr>

                            <div class="topicdetail__doc">
                                <h6>Context</h6>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                            </div>

                            <div class="topicdetail__doc">
                                <h6>Problem</h6>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                            </div>

                            <div class="topicdetail__doc">
                                <h6>Proposed Solution</h6>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </section>

        <!-- topic detail -->

        <!-- topic -->

        <footer>
            <%@include file="footer.jsp" %>
        </footer>

        <% }%>
        <!-- preloader -->
        <div id='preloader'>
            <div class='preloader'></div>
        </div>
        <!-- /preloader -->

        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/main.js"></script>
    </body>

</html>
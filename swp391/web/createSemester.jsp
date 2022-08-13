<%-- 
    Document   : createSemester
    Created on : Jul 9, 2022, 3:27:17 PM
    Author     : phamquang
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Dashboard - Create Semester</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />

        <!-- Font Awesome Icon -->
        <script src="https://kit.fontawesome.com/e7ea130b87.js" crossorigin="anonymous"></script>

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="../css/style.css" />

        <!--CreateTopic css-->
        <link href="../css/createTopic.css" rel="stylesheet" type="text/css" />
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
                } else if (roleId == 2) {
                    response.sendRedirect("profileLecturer.jsp");
                } else {
        %>

        <!-- Header -->
        <header>
            <%@include file="headerAdmin.jsp" %>
        </header>
        <!-- /Header -->
        <section class="vh-100 mb-2">
            <div class="container py-5 h-100">
                <div class="row d-flex align-items-center justify-content-center h-100">
                    <!--                    <div class="col-md-5 col-lg-7 col-xl-6">
                                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg" class="" alt="Phone image">
                                        </div>-->
                    <div class="col-md-7 col-lg-5 col-xl-6 offset-xl-1" style="margin-top: 60px">
                        <form action="<c:url value="/semester/signup.do"/>">
                            <!-- Email input -->

                            <div class="form-outline mb-2">
                                <label class="form-label" for="form1Example13">Semester</label>
                                <input value="" type="text" name="semesterName" id="form1Example13" class="form-control form-control-lg" required />
                            </div>
                            <div class="text-danger" style="font-style: italic;">
                                <%
                                    if (request.getAttribute("error-msg") != null && !request.getAttribute("error-msg").equals("")) {
                                        out.print(request.getAttribute("error-msg"));
                                    }
                                    if (request.getAttribute("error-msg3") != null && !request.getAttribute("error-msg3").equals("")) {
                                        out.print(request.getAttribute("error-msg3"));
                                    }
                                %>
                            </div>
                            <div class="form-outline mb-2">
                                <label class="form-label" for="form1Example23">Start Date</label>
                                <input type="date" name="startDate" id="form1Example23" class="form-control form-control-lg" required />
                            </div>
                            <div class="text-danger" style="font-style: italic;">
                                <%
                                    if (request.getAttribute("error-msg1") != null && !request.getAttribute("error-msg1").equals("")) {
                                        out.print(request.getAttribute("error-msg1"));
                                    }
                                %>
                            </div>
                            <div class="form-outline mb-2">
                                <label class="form-label" for="form1Example23">End Date</label>
                                <input type="date" name="endDate" id="form1Example23" class="form-control form-control-lg" required />
                            </div>
                            <div class="text-danger" style="font-style: italic;">
                                <%
                                    if (request.getAttribute("error-msg2") != null && !request.getAttribute("error-msg2").equals("")) {
                                        out.print(request.getAttribute("error-msg2"));
                                    }
                                %>
                            </div>
                            <!-- Submit button -->
                            <button type="submit" class="btn btn-warning btn-lg btn-block margin-top">Create</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>

        <% }
            }
        %>
    </body>
</html>

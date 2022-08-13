<%-- Document : teamList Created on : Jun 15, 2022, 8:45:26 AM Author : phamquang --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Change Password</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />

        <!-- Font Awesome Icon -->
        <script src="https://kit.fontawesome.com/e7ea130b87.js" crossorigin="anonymous"></script>

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="../css/style.css" />

        <!--CreateTeam css-->
        <link href="../css/createTeam.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
                response.sendRedirect("Login.jsp");
            } else {
                int roleId = (int) session.getAttribute("roleId");
                if (roleId == 4) {
                    response.sendRedirect("profileAdmin.jsp");
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
        </header>
        <!-- /Header -->


        <section id="team" class="container">
            <!-- Create -->
            <h2>Change Your Password</h2>            
            <hr/>
            <div class ="form__createTeam">
                <form action="<c:url value="/header/save.do"/>">
                    <div class="row form-line col-12 col-sm-7 col-md-7">
                        <label class="label_dep">User Name:</label>
                        <input class="" type="text"  disabled  value="${name}"/>
                    </div>
                    <br/>
                    <div class="row form-line col-12 col-sm-7 col-md-7">
                        <label class="label_dep">Current Password</label>
                        <input class="" type="text" name="currentPassword"  value="" required/>
                    </div>
                    <br/>
                    <div class="row form-line col-12 col-sm-7 col-md-7">
                        <label class="label_dep">New Password</label>
                        <input class="" type="text" name="newPassword"  value="" required/>
                    </div>
                    <br/>
                    <div class="row form-line col-12 col-sm-7 col-md-7">
                        <label class="label_dep">Confirm New Password</label>
                        <input class="" type="text" name="confirmPassword"  value="" required/>
                    </div>
                    <br/>
                    <div class="row form-line col-12 col-sm-7 col-md-7">
                        <button type="submit" value="save" name="op" class="btn btn-Create"><i class="bi bi-box-arrow-down"></i>Save</button>
                    </div>

                </form>
                <div class="text-danger" style="font-style: italic;">${errorMessage}</div>
                <div class="text-success" style="font-style: italic;">${successMessage}</div>
            </div>



        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>

        <% }
            }
        %>

        <!-- preloader -->
        <!--        <div id='preloader'>
                    <div class='preloader'></div>
                </div>-->
        <!-- /preloader -->


        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/main.js"></script>
        <script src="../js/topic.js"></script>
    </body>



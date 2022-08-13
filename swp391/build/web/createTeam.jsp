<%-- Document : teamList Created on : Jun 15, 2022, 8:45:26 AM Author : phamquang --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Dashboard - Create Team</title>

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
                if (roleId == 2) {
                    response.sendRedirect("profileLecturer.jsp");
                } else if (roleId == 4) {
                    response.sendRedirect("profileAdmin.jsp");
                } else {
        %>

        <!-- Header -->
        <header>
            <%@include file="header.jsp" %>
        </header>
        <!-- /Header -->


        <section id="team" class="container">
            <!-- Create -->
            <h2>Create your team</h2>
            <p>You have to have a team before you can match a topic in this semester</p>
            <hr/>
            <div class ="form__createTeam">
                <div class="container">
                    <form action="<c:url value="/group/save.do"/>" class="form__control">
                        <div class="row form-line">
                            <div class="col-12 col-md-8">
                                <label class="label_dep">Department</label>
                                <input class="" type="text"  disabled  value="${depName}" required/>
                            </div>
                        </div>

                        <div class="row form-line">
                            <div class="col-12 col-md-8 teamAndMembers">
                                <input type="text" placeholder="Team name" id="groupName" name="groupName" required>                            
                                <div class="selection--members">
                                    <label for="members" class="label_member">Members</label>
                                    <input type="number" id="members" name="members" min="3" max="7" value="${members==null?5:members}"/>
                                </div>
                            </div> 
                        </div>

                        <div class="form-line2">
                            <div class="col-12 col-md-8">
                                <div class="selection--status">
                                    <label for="status" class="label_status">Status</label>
                                    <div id="status">
                                        <input type="radio" name="groupStatus" value="0" id="public" /> 
                                        <label for="public">Private</label>         
                                        <br/>
                                        <input type="radio" name="groupStatus" value="1" id="private"/>
                                        <label for="private">Public</label>
                                    </div>
                                </div>
                            </div>
                        </div>


                </div>
                <i class="bi bi-x-circle"></i><a class="btn btn-Close" href="<c:url value="../group/index.do"/>">Close</a>
                <button type="submit" value="create" name="op" class="btn btn-Create"><i class="bi bi-box-arrow-down"></i>Create</button>

                </form>
                <div class="text-danger" style="font-style: italic;">${errorMessage}</div>
            </div>


        </section>
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


        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../js/main.js"></script>
        <script src="../js/topic.js"></script>
    </body>



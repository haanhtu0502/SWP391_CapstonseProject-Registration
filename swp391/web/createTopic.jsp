<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : createTopic
    Created on : 02/07/2022, 1:31:21 PM
    Author     : HLong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Dashboard - Create Topic</title>

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
                } else if (roleId == 4) {
                    response.sendRedirect("profileAdmin.jsp");
                } else {
        %>

        <!-- Header -->
        <header>
            <%@include file="headerLecturer.jsp" %>
        </header>
        <!-- /Header -->

        <section id="topic" class="container">
            <div class="text-danger" style="font-style: italic;">${errorMessage}</div>
            <!-- Create -->
            <h2>New Topic</h2>

            <hr />
            <div class="form__createTopic">
                <div class="">
                    <form action="<c:url value="/topic/save.do"/>" class="form__control">
                        <div class="topic__dep">
                            <label class="label_dep">Department</label>
                            <input class="" type="text" disabled value="${depName}" name="departmentName" required />
                        </div>
                        <div class="col">

                            <input type="text" placeholder="Topic name" value="${topicName==null?"" : topicName}" id="groupName" name="topicName">
                        </div>
                        <div class="col">
                            <div class="topicdetail__doc">
                                <h6>Description</h6>
                                <textarea name="topicDetail" value="${topicDetail==null?"" : topicDetail}" id="" cols="30" rows="10">${topicDetail==null?"" : topicDetail}</textarea>
                            </div>
                        </div>
                        <div class="col">
                            <div class="selection">
                                <div class="selection--right">
                                    <label for="semester" class="label_category">Semester</label>
                                    <input class="" type="text" disabled value="${validSemester}" name="semester" required />
                                </div>
                            </div>

                            <div class="selection">
                                <div class="selection--right">
                                    <label for="business" class="label_category">Business</label>
                                    <select style="height: 35px"  id="business" name="business">
                                        <!--                                        <option value="0" name="semester">--Choose a Category--</option>-->
                                        <c:forEach var="item" items="${bList}" varStatus="loop">
                                            <c:if test="${item.userId==chosenBusiness}">
                                                <option  value="${item.userId}" selected="selected" >${item.name}</option>
                                            </c:if>
                                            <c:if test="${item.userId!=chosenBusiness}">
                                                <option  value="${item.userId}"  >${item.name}</option>
                                            </c:if>

                                        </c:forEach>

                                    </select>
                                </div>
                            </div>

                        </div>
                        <!--                        <div class="col ">
    
                                <textarea placeholder="Description" cols="30" rows="10" name="description"></textarea>
                            </div>-->

                        <button type="submit" value="close" name="op" class="btn btn-Close"><i class="bi bi-x-circle"></i>Close</button>
                        <button type="submit" value="create" name="op" class="btn btn-Create"><i class="bi bi-box-arrow-down"></i>Create</button>

                    </form>
                </div>
            </div>




        </section>

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

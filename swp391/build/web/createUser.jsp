

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                } else if (roleId == 2) {
                    response.sendRedirect("profileLecturer.jsp");
                } else {
        %>

        <!-- Header -->
        <header>
            <%@include file="headerAdmin.jsp" %>
        </header>
        <!-- /Header -->
        <section class="vh-100 mb-2" style="margin-top: 4rem">
            <div class="container py-5 h-100">
                <div class="row d-flex align-items-center justify-content-center h-100">
                    <!--                    <div class="col-md-5 col-lg-7 col-xl-6">
                                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg" class="" alt="Phone image">
                                        </div>-->
                    <div class="col-md-5  col-xl-6 offset-xl-1">
                        <form action="<c:url value="/user/signup.do"/>">
                            <!-- Email input -->
                            <div class="text-danger" style="font-style: italic;">${errMessage}</div>

                            <div class="form-outline mb-2">
                                <label class="form-label" for="form1Example13">FullName</label>
                                <input value="${signuser.name==null?"" : signuser.name}" type="text" name="fullname" id="form1Example13" class="form-control form-control-lg" required />

                            </div>

                            <div class="form-outline mb-2">
                                <label for="role" class="">Role</label>
                                <br/>
                                <select style="height: 35px"   id="role" name="role">
                                    <c:forEach var="item" items="${roleList}" varStatus="loop">
                                        <c:if test="${item.roleId==signuser.roleId}">
                                            <option  value="${item.roleId}" selected="selected" >${item.roleName}</option>
                                        </c:if>
                                        <c:if test="${item.roleId!=signuser.roleId}">
                                            <option  value="${item.roleId}"  >${item.roleName}</option>
                                        </c:if>                                                                        
                                    </c:forEach>                                                      
                                </select>

                            </div>

                            <div class="form-outline mb-2">
                                <label for="department" class="">Department</label>
                                <br/>
                                <select style="height: 35px"   id="department" name="department">
                                    <c:forEach var="item" items="${depList}" varStatus="loop">     
                                        <c:if test="${item.departmentId==signuser.departmentId}">
                                            <option  value="${item.departmentId}" selected="selected" >${item.name}</option>
                                        </c:if>
                                        <c:if test="${item.departmentId!=signuser.departmentId}">
                                            <option  value="${item.departmentId}"  >${item.name}</option>
                                        </c:if>

                                    </c:forEach>
                                    <option  value="0"  >None</option>
                                </select>
                            </div>


                            <div class="form-outline mb-2">
                                <label class="form-label" for="form1Example13">Email</label>
                                <input value="${signuser.email==null?"" : signuser.email}" type="email" name="email" placeholder="abc@gmail.com" id="form1Example13" class="form-control form-control-lg" required />

                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-2">
                                <label class="form-label" for="form1Example23">Password</label>
                                <input type="password" name="password" id="form1Example23" class="form-control form-control-lg" required />

                            </div>

                            <div class="form-outline mb-2">
                                <label class="form-label" for="form1Example23">Confirm password</label>
                                <input type="password" name="repassword" id="form1Example23" class="form-control form-control-lg" required />

                            </div>
                            <!-- Submit button -->
                            <button type="submit" class="btn btn-warning btn-lg btn-block margin-top">Create</button>
                        </form>
                    </div>
                    <div class="hidden-xs hidden-sm col-md-7 col-xl-6 img-contain">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg" class="img-fluid" alt="Phone image">
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

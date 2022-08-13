<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : headerAdminProfile
    Created on : 06/07/2022, 6:48:00 PM
    Author     : HLong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Header -->
        <header id="header">
            <div class="container">

                <div class="navbar-header">
                    <!-- Logo -->
                    <div class="navbar-brand">
                        <a class="logo" href="profileAdmin.jsp">
                            <img src="img/logo.png" alt="logo">
                        </a>
                    </div>
                    <!-- /Logo -->

                    <!-- Mobile toggle -->
                    <button class="navbar-toggle">
                        <span></span>
                    </button>
                    <!-- /Mobile toggle -->
                </div>

                <!-- Navigation -->
                <nav id="nav">
                    <ul class="main-menu nav navbar-nav navbar-right">
                        <li><a class="align-nav" href="<c:url value="/semester/index.do"/>">Manage Semesters</a></li>
                        <li><a class="align-nav" href="<c:url value="/user/index.do"/>">Manage Users</a></li>
                        <li><a class="align-nav" href="<c:url value="/topic/index.do"/>">Manage Topics</a></li>
                        <li><a class="align-nav" href="<c:url value="/contact/index.do"/>">Manage FeedBacks</a></li>
                        <li class="nav-item dropdown">
                            <a href="profileAdmin.jsp" role="button" aria-haspopup="true" aria-expanded="false">
                                <img class="avatar" src="img/sample-avatar.jpg" alt="Avatar">
                            </a>
                        </li>
                    </ul>
                </nav>
                <!-- /Navigation -->

            </div>
        </header>
        <!-- /Header -->
    </body>
</html>

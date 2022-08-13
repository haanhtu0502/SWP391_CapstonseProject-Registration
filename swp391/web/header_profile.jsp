<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : header
    Created on : Jun 15, 2022, 9:33:22 AM
    Author     : MinhPham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <!-- Header -->
        <header id="header">
            <div class="container">

                <div class="navbar-header">
                    <!-- Logo -->
                    <div class="navbar-brand">
                        <a class="logo" id="logo-brand" href="./profile.jsp">
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
                        <li><a class="align-nav" href="<%=request.getContextPath()%>/project/show">Project</a></li>
                        <li><a class="align-nav" href="<c:url value="/topic/index.do"/>">Topic</a></li>
                        <li><a class="align-nav" href="<c:url value="/group/index.do"/>">Team List</a></li>
                        <li><a class="align-nav" href="<c:url value="/contact/index.do"/>">Contact</a></li>
                        <li id="logo-dropdown">                                       
                                <a 
                                role="button" aria-haspopup="true" aria-expanded="false">
                                <img class="avatar" src="img/sample-avatar.jpg" alt="Avatar">
                            </a>
                            <div class="dropdown-password">
                                <a href="<c:url value="/header/changePassword.do"/>">
                                    Change Password 
                                </a>
                            </div>  
                            
                            
                        </li>
                    </ul>
                </nav>
                <!-- /Navigation -->

            </div>
        </header>
        <!-- /Header -->
        
        <script src="js/headerDropdown.js" type="text/javascript"></script>        
    </body>


</html>

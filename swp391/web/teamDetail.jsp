<%-- 
    Document   : teamDetail
    Created on : Jun 15, 2022, 8:46:18 AM
    Author     : phamquang
--%>
<%@page import="DTO.Users"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Dashboard - TeamDetail</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css"> 


        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />
        <!-- cdn font awesome -->

        <script src="https://kit.fontawesome.com/e7ea130b87.js" crossorigin="anonymous"></script>
        <!-- Custom stylesheet -->
        <link type="text/css" rel="stylesheet" href="../css/style.css" />

        <!--  stylessheet -->
        <link type="text/css" rel="stylesheet" href="../css/teamDetail.css" />

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

        <!-- topic -->
        <section id="team" class="container">

            <div class="team__header">
                <h2>Team detail</h2>
                <div class="btnControl">
                    <c:choose>
                        <c:when test="${countMembers == teamInfor.group.members }">
                            <button class="team__btn-disabled"><a href="#" data-toggle="tooltip" title="This team is full. You should join another team."><span class="fa-solid fa-right-from-bracket fa-md"></span> Quick Join A Team</a></button>
                        </c:when>
                        <c:when test="${checkUserId != 0}"><button class="team__btn-disabled"><a href="#" data-toggle="tooltip" title="You are already have team! You can not join this team."><span class="fa-solid fa-right-from-bracket fa-md"></span> Quick Join A Team</a></button> </c:when>
                        <c:when test="${checkUserId == 0}"><button class="team__btn"><a href="${root}/group/join.do?id=${userId}"><span class="fa-solid fa-right-from-bracket fa-md"></span>  Quick Join A Team</a> </button></c:when>
                    </c:choose>
                </div>
            </div>          



            <div class="teamDetail-body">
                <div class="teamMember-content">
                    <div class="teamMember-content_header">
                        <h4>Team members</h4>
                        <c:choose >
                            <c:when test="${countMembers == teamInfor.group.members}">
                                <div class="tdTbl__success"> <p class="membersCount">${countMembers}/${teamInfor.group.members}</p> </div>  
                            </c:when>
                            <c:when test="${countMembers <= teamInfor.group.members}">
                                <div class="tdTbl__warning"> <p class="membersCount">${countMembers}/${teamInfor.group.members}</p> </div>  
                            </c:when>
                        </c:choose>

                    </div>
                    <hr/>
                    <c:forEach var="item" items="${teamMembers}" varStatus="loop">

                        <ul class="teamMembers-list">
                            <li class="teamMember-item">
                                <div>
                                    <img class="avatar" src="../img/sample-avatar.jpg" />
                                    ${item.user.name} <br/>
                                    <span <c:if test="${item.leaderStatus == 1}">class="tdTbl__danger tab-leader"</c:if> >${item.leaderStatus == 1?"Leader":"" }</span>
                                    </div>
                                </li>
                                <div class="tabProgramContainer">
                                    <td class="tabProgramControl">
                                        <span class="tabProgram">Java</span>
                                        <span class="tabProgram">Web</span>
                                        <span class="tabProgram">C#</span>
                                        <span class="tabProgram">Backend</span>
                                    </td>
                                </div>
                            </ul>
                            <hr/>
                    </c:forEach>
                </div>
                <div class="teamInfor-content">
                    <div class="flex">
                        <h4 class="teamInfor-tittle">Team Information</h6>


                            <c:choose>
                                <c:when test="${checkLeaderId == 1}">
                                    <button class="team__btn "><a href="${root}/group/switch.do?id=${userId}">Switch to ${groupStatus == 0?"Public":"Private"}</a></button>        
                                </c:when>
                                <c:when test="${checkLeaderId == 0}">

                                </c:when>  
                            </c:choose>
                    </div>
                    <hr/>
                    <ul class="teamInfor-list">
                        <li class="teamInfor-item">
                            <i class="fa fa-solid fa-bars col-sm-1"></i>
                            <span class="col-sm-4">Team Name:</span>
                            <strong><span class="col-sm-12">${teamInfor.group.groupName}</span></strong>
                        </li>
                        <li class="teamInfor-item">
                            <i class="fa-solid fa-clone col-sm-1"></i>
                            <span class="col-sm-4">Team ID:</span>
                            <div>
                                <span class="col-sm-7">${teamInfor.groupId}</span>
                            </div>
                        </li>                        
                        <li class="teamInfor-item">
                            <i class="fa fa-regular fa-building col-sm-1"></i>
                            <span class="col-sm-4">Department:</span>
                            <div class="col-sm-7">
                                <span class="tabProgram">${teamInfor.department.name}</span>
                            </div>
                        </li>

                    </ul>
                    <div class="teamInfor-item" style="margin-top: 5px">                                                        
                        <i class="fa-solid fa-shield col-sm-1"></i>
                        <div class="col-sm-2">                                
                            <div class="quesIcon-control">
                                <c:choose >
                                    <c:when test="${groupStatus == 1}">
                                        <span class="tdTbl__success">${groupStatus == 1?"Public":"Private"}</span>  
                                        <span class="quesIcon"><a href="#" data-placement="right" data-toggle="tooltip" title="This team is visible to every actived student in this semester"><i class="fa-regular fa-circle-question"></i></a></span>
                                            </c:when>
                                            <c:when test="${groupStatus == 0}">
                                        <span class="tdTbl__warning">${groupStatus == 1?"Public":"Private"}</span>   
                                        <span class="quesIcon"><a href="#" data-placement="right" data-toggle="tooltip" title="This team is invisible to every actived student in this semester"><i class="fa-regular fa-circle-question"></i></a></span>
                                            </c:when>
                                        </c:choose>

                            </div>                                                                                                                                                                    
                        </div>
                    </div> 
                    <hr/>
                    <c:choose>
                        <c:when test="${checkProjectId != 0}">
                            <div class="teamInfor-item">
                                <strong><span class="col-sm-4">Description:</span></strong>
                                <span class="col-sm-12">${teamInfor.project.description}</span>
                            </div>    
                            <hr/>
                            <div class="teamInfor-topic">
                                <div class="teamInfor-item">                                
                                    <p class="center-block" style="font-weight: 700">${teamInfor.project.name == null?"This team have not matched any topic yet":""}${teamInfor.project.name}</p>                                
                                </div>
                            </div>
                        </c:when>

                        <c:when test="${checkProjectId == 0}">
                            <div class="teamInfor-item">
                                <strong><span class="col-sm-4">Description:</span></strong>
                                <span class="col-sm-12"></span>
                            </div>    
                            <hr/>
                            <div class="teamInfor-topic">
                                <div class="teamInfor-item">                                
                                    <p class="center-block" style="font-weight: 700">This team have not matched any topic yet</p>                                
                                </div>
                            </div>
                        </c:when>    
                    </c:choose>

                </div>
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
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
        <script src="./js/topic.js"></script>
    </body>

</html>

<%-- 
    Document   : topicListLecturer
    Created on : 02/07/2022, 2:01:20 PM
    Author     : HLong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Dashboard - Topic List</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />

        <!--       Bootstrap Icon -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

        <!-- Font Awesome Icon -->
        <script src="https://kit.fontawesome.com/e7ea130b87.js" crossorigin="anonymous"></script>

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="../css/style.css" />

        <!-- topic stylessheet -->
        <link type="text/css" rel="stylesheet" href="../css/topicTeamListStyle.css" />
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

        <!-- topic -->
        <section id="topic" class="container">
            <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="modal-title" id="exampleModalLongTitle">Being approved topic list</h2>

                        </div>
                        <div class="modal-body">

                            <c:if test="${!empty approvingTopicList}">
                                <table class=" table ">
                                    <thead>
                                        <tr>
                                            <th>Name</th>                             
                                            <th>Department</th>
                                            <th>Semester</th>
                                            <th></th>
                                        </tr>

                                    </thead>

                                    <c:forEach var="item2" items="${approvingTopicList}" varStatus="loop">
                                        <tbody>
                                            <tr>  
                                                <td>${item2.name}</td> 
                                                <td>${item2.department.name}</td>
                                                <td>${item2.semester.name}</td>
                                                <td>
                                                    <a  href="${root}/topic/approveadmin.do?topicId=${item2.topicId}" data-toggle="tooltip" data-placement="top" title="Approve topic" class="button"><i class="bi update bi-check-circle-fill"></i></a>
                                                    <a  href="${root}/topic/delete.do?topicId=${item2.topicId}" data-toggle="tooltip" data-placement="top" title="Deny topic" class="button"><i class="bi delete bi-x-circle-fill"></i></a>                    
                                                </td>
                                            </tr>

                                        </tbody>    
                                    </c:forEach>
                                </table>
                            </c:if>

                            <c:if test="${empty approvingTopicList}">
                                <div class="search-empty">
                                    <img src="../img/search-empty.png" class="search-empty-icon"/>
                                    <div class="search-empty-title">No under review topic available</div>
                                </div>
                            </c:if>

                        </div>
                        <div class="modal-footer ">

                            <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="topic__title flex">
                <h1>Manage Topic</h1>
                <div class="semester">
                    ${currentSem.name}
                    <div class="dropdown2">
                        <ul class="semester__list">
                            <c:forEach var="item" items="${semList}" varStatus="loop"> 
                                <li name="semester" class="semester__item" ><a  href="${root}/topic/semester.do?semester=${item.name}">${item.name}</a></li>             
                                </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="topic__container">
                <div class="topicListControl">
                    <h6 class="topic__text">
                        All of approved topics in semester ${currentSem.name}
                    </h6>
                    <div class="btnControl">

                        <button type="button" class="btn team__btn" data-toggle="modal" data-target="#myModal1">
                            Manage topics
                        </button>

                    </div>

                </div>

                <hr>

                <div class="topic__search">
                    <form action="<c:url value="/topic/search.do"/>">
                        <input placeholder=" " value="${searchText==null?"":searchText}" name="searchText" class="search__input" type="text">
                        <label for="search" class="search__label">Search by name</label>
                        <button type="submit" class="search-btn ">
                            <img src="../img/search-interface-symbol.png" alt="">
                        </button>
                    </form>

                    <div class="topic__filter">
                        <i class="fa-solid fa-filter"></i>Filters
                        <div class="dropdown1">
                            <ul class="filter__list">
                                <li class="filter__item" name="filter"><a href="${root}/topic/filter.do?filter=Quan tri kinh doanh">Quan tri kinh doanh</a></li>
                                <li class="filter__item" name="filter"><a href="${root}/topic/filter.do?filter=Cong nghe thong tin">Cong nghe thong tin</a></li>
                                <li class="filter__item" name="filter"><a href="${root}/topic/filter.do?filter=Ngon ngu Anh">Ngon ngu Anh</a></li>
                                <li class="filter__item" name="filter"><a href="${root}/topic/filter.do?filter=Ngon ngu Han Quoc">Ngon ngu Han Quoc</a></li>
                                <li class="filter__item" name="filter"><a href="${root}/topic/filter.do?filter=Ngon ngu Nhat">Ngon ngu Nhat</a></li>
                            </ul>
                        </div>
                    </div>

                </div>

                <c:if test="${!empty list}">
                    <table class="table topic__table">
                        <thead>
                            <tr>

                                <th>DEP.</th>
                                <th>Topic Name</th>
                                <th>Lecturer</th>
                                <th>Status</th>
                                <th>Detail</th>
                            </tr>
                        </thead>
                        <c:forEach var="item" items="${list}" varStatus="loop">

                            <tbody>
                                <tr>  

                                    <td>${item.department.name}</td>
                                    <td>${item.name}</td> 
                                    <td>${item.user.name}</td>
                                    <c:choose >
                                        <c:when test="${item.status==0}">
                                            <td> <span class="tdTbl__warning">available</span></td> 
                                        </c:when>  
                                        <c:otherwise>
                                            <td> <span class="tdTbl__warning">locked</span></td> 
                                        </c:otherwise>
                                    </c:choose>                                                 
                                    <td><a href="${root}/topic/detail.do?id=${item.topicId}"><i class="fa fa-solid fa-eye"></i></a></td> 
                                </tr>
                            </tbody>    
                        </c:forEach>
                    </table>
                </c:if>


                <c:if test="${empty list}">
                    <div class="search-empty">
                        <img src="../img/search-empty.png" class="search-empty-icon"/>
                        <div class="search-empty-title">Cannot find any group</div>

                    </div>
                </c:if>

                <hr>

                <c:if test="${!empty list}">

                    <div class="row pageBtn">
                        <div class="col" style="text-align: right;">
                            <br/>
                            <form action="<c:url value="/topic/${currTopicAction}.do" />">
                                <button type="submit" class="btn btn-warning  btn-info" name="op" value="FirstPage" title="First Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-bar-left"></i></button>
                                <button type="submit" class="btn btn-warning  btn-info" name="op" value="PreviousPage" title="Previous Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-left"></i></button>
                                <button type="submit" class="btn btn-warning  btn-info" name="op" value="NextPage" title="Next Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-right"></i></button>
                                <button type="submit" class="btn btn-warning  btn-info" name="op" value="LastPage" title="Last Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-bar-right"></i></button>
                                <input type="text" name="gotoPage" value="${page}" class="btn-warning btn-outline-info" style="padding:12px;text-align:left;color:#000;width:40px;" title="Enter page number"/>
                                <button type="submit" class="btn btn-warning  btn-info" name="op" value="GotoPage" title="Goto Page"><i class="bi bi-arrow-up-right-circle"></i></button>
                            </form>
                            Page ${page}/${totalPage}
                        </div>
                    </div>

                </c:if>




            </div>




        </section>
        <!-- topic -->

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
        <script>
            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            })
        </script>
    </body>
</html>

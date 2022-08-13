<%-- 
    Document   : manageUser
    Created on : 06/07/2022, 6:57:53 PM
    Author     : HLong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Admin - Manage Users</title>

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

        <!-- user stylessheet -->
        <link type="text/css" rel="stylesheet" href="../css/manageUserStyle.css" />

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

        <!-- user -->
        <section id="user" class="container">
            <div class="teamListControl flex">
                <div class="user__title">
                    <h1>Manage Users</h1>
                </div>
                <div class="btnControl">

                    <a href="<c:url value="/user/create.do"/>" class="team__btn">+ Create New User</a>
                </div>
            </div>


            <div class="user__container">



                <div class="user__search">
                    <form action="<c:url value="/user/search.do"/>">
                        <input placeholder=" " value="${searchUserText==null?"":searchUserText}" name="searchText" class="search__input" type="text">
                        <label for="search" class="search__label">Search by name</label>
                        <button type="submit" class="search-btn ">
                            <img src="img/search-interface-symbol.png" alt="">
                        </button>
                    </form>


                    <div class="topic__filter">
                        <i class="fa-solid fa-filter"></i>
                        <span class="hidden-xs hidden-sm">Filters</span>
                        <div class="dropdown1">
                            <ul class="filter__list">
                                <li class="filter__item" name="filter"><a href="${root}/user/filter.do?filter=Quan tri kinh doanh">Quan tri kinh doanh</a></li>
                                <li class="filter__item" name="filter"><a href="${root}/user/filter.do?filter=Cong nghe thong tin">Cong nghe thong tin</a></li>
                                <li class="filter__item" name="filter"><a href="${root}/user/filter.do?filter=Ngon ngu Anh">Ngon ngu Anh</a></li>
                                <li class="filter__item" name="filter"><a href="${root}/user/filter.do?filter=Ngon ngu Han Quoc">Ngon ngu Han Quoc</a></li>
                                <li class="filter__item" name="filter"><a href="${root}/user/filter.do?filter=Ngon ngu Nhat">Ngon ngu Nhat</a></li>
                            </ul>
                        </div>
                    </div>


                </div>

                <c:if test="${!empty list}">
                    <table class="table topic__table">
                        <thead>
                            <tr>

                                <th>Name</th>
                                <th>Department</th>
                                <th class="hidden-xs">Email</th>
                                <th>Role</th>

                            </tr>
                        </thead>
                        <c:forEach var="item" items="${list}" varStatus="loop">

                            <tbody>
                                <tr>  

                                    <td>${item.name}</td>
                                    <c:choose >
                                        <c:when test="${item.departmentId==1}">
                                            <td>Quan tri kinh doanh</td> 
                                        </c:when>
                                        <c:when test="${item.departmentId==2}">
                                            <td>Cong nghe thong tin</td> 
                                        </c:when> 
                                        <c:when test="${item.departmentId==3}">
                                            <td>Ngon ngu Anh</td> 
                                        </c:when> 
                                        <c:when test="${item.departmentId==4}">
                                            <td>Ngon ngu Han Quoc</td> 
                                        </c:when> 
                                        <c:when test="${item.departmentId==5}">
                                            <td>Ngon ngu Nhat</td> 
                                        </c:when> 
                                        <c:otherwise>
                                            <td>This is business</td> 
                                        </c:otherwise>
                                    </c:choose>   

                                    <td class="hidden-xs">${item.email}</td>
                                    <c:choose >
                                        <c:when test="${item.roleId==1}">
                                            <td> <span class="tdTbl__warning">Student</span></td> 
                                        </c:when>
                                        <c:when test="${item.roleId==2}">
                                            <td> <span class="tdTbl__warning">Lecturer</span></td> 
                                        </c:when> 
                                        <c:otherwise>
                                            <td> <span class="tdTbl__warning">Business</span></td> 
                                        </c:otherwise>
                                    </c:choose>                                                 

                                </tr>
                            </tbody>    
                        </c:forEach>
                    </table>
                </c:if>


                <c:if test="${empty list}">
                    <div class="search-empty">
                        <img src="../img/search-empty.png" class="search-empty-icon"/>
                        <div class="search-empty-title">Cannot find any user</div>

                    </div>
                </c:if>

                <hr>

                <c:if test="${!empty list}">

                    <div class="row pageBtn">
                        <div class="col" style="text-align: right;">
                            <br/>
                            <form action="<c:url value="/user/${currUserAction}.do" />">
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
        <!-- user -->

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

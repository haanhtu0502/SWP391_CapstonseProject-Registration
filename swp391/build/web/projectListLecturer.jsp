<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Dashboard - Project</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css" />

        <!--       Bootstrap Icon -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

        <!-- Font Awesome Icon -->
        <!--        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />-->
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
                } else if (roleId == 4) {
                    response.sendRedirect("profileAdmin.jsp");
                } else {
        %>

        <!-- Header -->
        <header>
            <%@include file="headerLecturer.jsp" %>
        </header>
        <!-- /Header -->

        <!-- topic -->
        <section id="topic" class="container">

            <div class="topic__title flex">
                <h1>Project List</h1>
                <div class="semester">
                    ${currentSem.name}
                    <div class="dropdown2">
                        <ul class="semester__list">
                            <c:forEach var="item" items="${semList}" varStatus="loop"> 
                                <li name="semester" class="semester__item" >
                                    <a  href="${root}/projectlecturer/semester.do?semester=${item.name}">
                                        ${item.name}
                                    </a>
                                </li>             
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
                </div>

                <hr>

                <div class="topic__search">
                    <form action="<c:url value="/projectlecturer/search.do"/>">
                        <input placeholder=" " value="${searchTextProject==null?"":searchTextProject}" name="searchText" class="search__input" type="text">
                        <label for="search" class="search__label">Search by project's name</label>
                        <button type="submit" class="search-btn ">
                            <img src="../img/search-interface-symbol.png" alt="">
                        </button>
                    </form>


                    <div class="topic__filter">
                        <i class="fa-solid fa-filter"></i>
                        <span class="hidden-xs hidden-sm">Filters</span>
                        <div class="dropdown1">
                            <ul class="filter__list">
                                <li class="filter__item"><a href="${root}/projectlecturer/filter.do?filter=Quan tri kinh doanh">Quan tri kinh doanh</a></li>
                                <li class="filter__item"><a href="${root}/projectlecturer/filter.do?filter=Cong nghe thong tin">Cong nghe thong tin</a></li>
                                <li class="filter__item"><a href="${root}/projectlecturer/filter.do?filter=Ngon ngu Anh">Ngon ngu Anh</a></li>
                                <li class="filter__item"><a href="${root}/projectlecturer/filter.do?filter=Ngon ngu Han Quoc">Ngon ngu Han Quoc</a></li>
                                <li class="filter__item"><a href="${root}/projectlecturer/filter.do?filter=Ngon ngu Nhat">Ngon ngu Nhat</a></li>
                            </ul>
                        </div>
                    </div>


                </div>

                <table class="table topic__table">
                    <thead>
                        <tr>
                            <th style="text-align: center">Group Name</th>
                            <th>Project Name</th>
                            <th class="hidden-xs">Lecturer</th>
                            <th class="hidden-xs hidden-sm" style="text-align: center">Status</th>
                            <th style="text-align: center">View</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${proList}" varStatus="loop">
                            <tr>
                                <td style="text-align: center">${item.group.groupName}</td>
                                <td>${item.name}</td>
                                <td class="hidden-xs">${item.lecturer.name}</td>
                                <td class="hidden-xs hidden-sm" style="text-align: center">${item.status==1 ? "Approved" : "Waiting"}</td>
                                <td style="text-align: center"><a href="${root}/projectlecturer/detail.do?id=${item.projectId}"><i class="fa-solid fa-eye"></i></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <hr>

                <c:if test="${!empty proList}">

                    <div class="row pageBtn">
                        <div class="col" style="text-align: right;">
                            <br/>
                            <form action="<c:url value="/projectlecturer/${currProjectAction}.do" />">
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
</html>

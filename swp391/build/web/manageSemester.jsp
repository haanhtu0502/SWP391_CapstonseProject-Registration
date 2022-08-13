<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Admin - Manage Semesters</title>

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

        <!-- sem stylessheet -->
        <link type="text/css" rel="stylesheet" href="../css/manageSemesterStyle.css" />  
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

        <!-- sem -->
        <section id="sem" class="container">

            <div class="sem__title">
                <h1>Manage Semesters</h1>
            </div>

            <div class="sem__container">
                <div class="semListControl">
                    <h6 class="sem__text">
                        List of semesters
                    </h6>
                    <div class="btnControl">
                        <a href="<c:url value="/semester/create.do"/>" class="team__btn">+ Create Semester</a>
                    </div>
                </div>

                <hr>

                <div class="sem__search">
                    <form action="<c:url value="/semester/search.do"/>">
                        <input placeholder=" " value="${searchTextSemester==null?"":searchTextSemester}" name="searchText" class="search__input" type="text">
                        <label for="search" class="search__label">Search by Semester's name</label>
                        <button type="submit" class="search-btn ">
                            <img src="../img/search-interface-symbol.png" alt="">
                        </button>
                    </form>

                    <!-- <div class="sem__filter">
                        <i class="fa-solid fa-filter"></i>Filters
                        <div class="dropdown1">
                            <ul class="filter__list">
                                <li class="filter__item">Quan tri kinh doanh</li>
                                <li class="filter__item">Cong nghe thong tin</li>
                                <li class="filter__item">Ngon ngu Anh</li>
                                <li class="filter__item">Ngon ngu Han Quoc</li>
                                <li class="filter__item">Ngon ngu Nhat</li>
                            </ul>
                        </div>
                    </div> -->

                </div>

                <table class="table sem__table">
                    <thead>
                        <tr>
                            <th class="hidden-xs">Id</th>
                            <th>Semester Name</th>
                            <th>Start date</th>
                            <th>End date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${list}" varStatus="loop">
                            <tr>
                                <td class="hidden-xs">${item.semesterId}</td>
                                <td>${item.name}</td>
                                <td>${item.startDate}</td>
                                <td>${item.endDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <hr>

                <c:if test="${!empty list}">

                    <div class="row pageBtn">
                        <div class="col" style="text-align: right;">
                            <br/>
                            <form action="<c:url value="/semester/${currSemesterAction}.do" />">
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
        <!-- sem -->

        <footer>
            <%@include file="footer.jsp" %>
        </footer>

        <% }
            }
        %>

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script src="js/sem.js"></script>
    </body>
</html>

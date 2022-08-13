<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Admin - Manage FeedBack</title>

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


        <section id="sem" class="container">

            <div class="sem__title">
                <h1>Manage FeedBack</h1>
            </div>

            <div class="sem__container">
                <div class="semListControl">
                    <h6 class="sem__text">
                        List of FeedBacks
                    </h6>

                </div>

                <hr>

                <div class="sem__search">
                    <form action="<c:url value="/contact/search.do"/>">
                        <input placeholder=" " value="${searchText==null?"":searchText}" name="searchText" class="search__input" type="text">
                        <label for="search" class="search__label">Search Student name</label>
                        <button type="submit" class="search-btn ">
                            <img src="../img/search-interface-symbol.png" alt="">
                        </button>
                    </form>

                </div>

                <table class="table sem__table">
                    <thead>
                        <tr>
                            <th>Student Name</th>
                            <th class="hidden-xs hidden-sm">Email</th>
                            <th>Subject</th>
                            <th class="hidden-xs">Message</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${list}" varStatus="loop">
                            <tr>
                                <td>${item.name}</td>
                                <td class="hidden-xs hidden-sm">${item.email}</td>
                                <td>${item.subject}</td>
                                <td class="hidden-xs">${item.feedBackMsg}</td>
                                <td><a href="${root}/contact/delete.do?feedBackId=${item.feedBackId}"  class="btn btn-sm tdTbl__danger"><i class="bi bi-x-circle-fill"></i> Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <hr>

                <c:if test="${!empty list}">

                    <div class="row pageBtn">
                        <div class="col" style="text-align: right;">
                            <br/>
                            <form action="<c:url value="/contact/${currContactAction}.do" />">
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

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script src="js/sem.js"></script>
    </body>
</html>

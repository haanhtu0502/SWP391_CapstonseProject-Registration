<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Dashboard - Topic</title>

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
        <section id="topic" class="container">

            <div class="topic__title flex">
                <h1>Topic List</h1>
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

                <div class="btn__control">
                    <h6 class="topic__text">
                        All of approved topics in semester ${currentSem.name}
                    </h6>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn team__btn" data-toggle="modal" data-target="#myModal">
                        Appliable list
                    </button>

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
                        <i class="fa fa-solid fa-sort"></i>
                        <span class="hidden-xs hidden-sm">Filters</span>
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
                    <table class="tablemanager table topic__table">
                        <thead>
                            <tr>

                                <th class="hidden-xs">DEP.</th>
                                <th>Name</th>                             
                                <th>Lecturer</th>
                                <th class="hidden-xs hidden-sm">Status</th>
                                <th>Detail</th>


                            </tr>
                        </thead>

                        <c:forEach var="item" items="${list}" varStatus="loop">

                            <tbody>
                                <tr>  

                                    <td class="hidden-xs">${item.department.name}</td>
                                    <td>${item.name}</td> 
                                    <td>${item.user.name}</td>
                                    <c:choose >
                                        <c:when test="${item.status==0}">
                                            <td class="hidden-xs hidden-sm"> <span class="tdTbl__warning">available</span></td> 
                                        </c:when>   
                                        <c:otherwise>
                                            <td class="hidden-xs hidden-sm"> <span class="tdTbl__warning">locked</span></td> 
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
                        <div class="search-empty-title">Cannot find any topic</div>

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






                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="exampleModalLongTitle">Appliable topic list</h2>

                            </div>
                            <div class="modal-body">

                                <c:if test="${!empty appliableTopicList}">
                                    <table class="tablemanager table topic__table">
                                        <thead>
                                            <tr>
                                                <th>Name</th>                             
                                                <th>Lecturer</th>
                                                <th>Status</th>
                                                <th>Detail</th>
                                                <th></th>

                                            </tr>

                                        </thead>

                                        <c:forEach var="topic" items="${appliableTopicList}" varStatus="loop">
                                            <tbody>
                                                <tr>  
                                                    <td>${topic.name}</td> 
                                                    <td>${topic.user.name}</td>
                                                    <c:choose >
                                                        <c:when test="${topic.status==0}">
                                                            <td> <span class="tdTbl__warning">available</span></td> 
                                                        </c:when>
                                                        <c:when test="${topic.status==1}">
                                                            <td> <span class="tdTbl__warning">pending</span></td> 
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <td> <span class="tdTbl__warning">locked</span></td> 
                                                        </c:otherwise>
                                                    </c:choose>                                                 
                                                    <td><a href="${root}/topic/detail.do?id=${topic.topicId}"><i class="fa fa-solid fa-eye"></i></a></td> 
                                                    <td><a href="${root}/topic/apply.do?id=${topic.topicId}" class="btn btn-success">Apply</a></td>
                                                </tr>

                                            </tbody>    
                                        </c:forEach>
                                    </table>
                                </c:if>

                                <c:if test="${empty appliableTopicList}">
                                    <div class="search-empty">
                                        <img src="../img/search-empty.png" class="search-empty-icon"/>
                                        <div class="search-empty-title">You don't have any appliable topic</div>

                                    </div>
                                </c:if>

                            </div>
                            <div class="modal-footer ">
                                <button class='btn btn-warning' href="#" title="Conditions to apply:" data-html="true" data-toggle="popover" data-placement="top" data-content="+ You must be leader to apply.<br>+ Your department must be the same with topic's.<br>+ Your group's semester must be current semester.<br>+ You can only apply 1 topic once<br>+ Topic status can not be locked.">
                                    Conditions
                                </button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>


        </section>
        <!-- topic -->

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
        <script>
//            $(document).ready(function () {
//                $('[data-toggle="tooltip"]').tooltip();
//            });
            $(document).ready(function () {
                $('[data-toggle="popover"]').popover();
            });
        </script>

    </body>

</html>
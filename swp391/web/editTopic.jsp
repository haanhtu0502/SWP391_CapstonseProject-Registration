<%-- 
    Document   : editTopic
    Created on : 02/07/2022, 1:41:28 PM
    Author     : HLong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Dashboard - Edit Topic</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="./css/bootstrap.min.css" />

        <!-- Font Awesome Icon -->
        <script src="https://kit.fontawesome.com/e7ea130b87.js" crossorigin="anonymous"></script>

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="./css/style.css" />

        <!--CreateTopic css-->
        <link href="./css/createTopic.css" rel="stylesheet" type="text/css" />
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
        <section id="topic" class="container">
            <!-- Create -->
            <h2>@Topic name</h2>

            <hr />
            <div class="form__createTopic">
                <div class="">
                    <form action="save.do" class="form__control">
                        <div class="topic__dep">
                            <label class="label_dep">Department</label>
                            <input class="" type="text" disabled value="@Department Name" required />
                        </div>
                        <div class="col">

                            <input type="text" placeholder="Topic name" id="groupName" name="groupName">
                        </div>
                        <div class="col">
                            <div class="topicdetail__doc">
                                <h6>Description</h6>
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                            </div>
                        </div>
                        <div class="col">
                            <div class="selection">



                                <div class="selection--right">
                                    <label for="category" class="label_category">Category</label>
                                    <select id="category" name="categoryList">
                                        <option value="0" name="groupCate">--Choose a Category--</option>
                                        <option value="1" name="groupCate">Digital Marketing</option>
                                        <option value="2" name="groupCate">Kinh doanh quoc te</option>
                                        <option value="3" name="groupCate">Quan tri khach san</option>
                                        <option value="4" name="groupCate">du lich & lu hanh</option>
                                        <option value="5" name="groupCate">Truyen thong da phuong tien</option>
                                        <option value="6" name="groupCate">Ky thuat phan mem</option>
                                        <option value="7" name="groupCate">He thong thong tin</option>
                                        <option value="8" name="groupCate">Tri tue nhan tao</option>
                                        <option value="9" name="groupCate">An toan thong tin</option>
                                        <option value="10" name="groupCate">Thiet ke do hoa</option>
                                        <option value="11" name="groupCate">Internet of things</option>
                                        <option value="12" name="groupCate">Phan mem o to</option>
                                        <option value="13" name="groupCate">Xu li dich vu so</option>
                                        <option value="14" name="groupCate">Ngon ngu Anh</option>
                                        <option value="15" name="groupCate">Ngon ngu Nhat</option>
                                        <option value="16" name="groupCate">Ngon ngu Han Quoc</option>
                                    </select>
                                </div>
                            </div>

                        </div>
                        <!--                        <div class="col ">
    
                                <textarea placeholder="Description" cols="30" rows="10" name="description"></textarea>
                            </div>-->
                </div>
                <button type="submit" class="btn btn-Close"><i class="bi bi-x-circle"></i>Close</button>
                <button type="submit" class="btn btn-Create"><i class="bi bi-box-arrow-down"></i>Create</button>

                </form>
            </div>


        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>

        <% }
            }
        %>

        <script type="text/javascript" src="./js/jquery.min.js"></script>
        <script type="text/javascript" src="./js/bootstrap.min.js"></script>
        <script type="text/javascript" src="./js/main.js"></script>
        <script src="./js/topic.js"></script>
    </body>
</html>

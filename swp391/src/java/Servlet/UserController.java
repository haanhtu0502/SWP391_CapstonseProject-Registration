/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.DepartmentDAO;
import DAO.UserDAO;
import DTO.Department;
import DTO.Role;
import DTO.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE161714 Ha Anh Tu
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getAttribute("action").toString();
        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
        String prevAction = (String) session.getAttribute("prevUserAction");
        if (prevAction == null) {
            session.setAttribute("prevUserAction", action);
            prevAction = action;
        }
        session.setAttribute("currUserAction", action);
        String currAction = (String) session.getAttribute("currUserAction");
        switch (action) {
            case "index":
                ArrayList<Users> list = new ArrayList();
                list = ud.readAllNotAdmin();
                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("prevTopicAction", "index");
                pagination(request, response, list);
                request.getRequestDispatcher("/manageUser.jsp").forward(request, response);
                break;
            case "search":
                String searchText = request.getParameter("searchText");
                //        pagination
                if (searchText == null) {
                    list = ud.readAllNotAdmin();
                } else {
                    list = ud.searchUserByName(searchText);
                }

                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("prevUserAction", "search");
                pagination(request, response, list);
                session.setAttribute("searchUserText", searchText);
                session.setAttribute("currUserAction", "pagesearch");
                request.getRequestDispatcher("/manageUser.jsp").forward(request, response);
                break;
            case "pagesearch":
                searchText = (String) session.getAttribute("searchUserText");
                if (searchText == null) {
                    list = ud.readAllNotAdmin();
                } else {
                    list = ud.searchUserByName(searchText);
                }
                pagination(request, response, list);
                session.setAttribute("prevUserAction", "pagesearch");
                request.getRequestDispatcher("/manageUser.jsp").forward(request, response);
                break;
            case "filter":
                String filter = request.getParameter("filter");

                //        pagination
                String prevFilter = (String) session.getAttribute("prevUserFilter");
                if (prevFilter == null) {
                    session.setAttribute("prevUserFilter", filter);
                    prevFilter = filter;
                }
                session.setAttribute("currUserFilter", filter);
                String currFilter = (String) session.getAttribute("currUserFilter");
                list = ud.filterByDepartment(filter);
                if (!prevAction.equals(currAction) || !prevFilter.equals(currFilter)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                switch (currFilter) {
                    case "Cong nghe thong tin":
                        session.setAttribute("prevUserFilter", "Cong nghe thong tin");
                        break;
                    case "Quan tri kinh doanh":
                        session.setAttribute("prevUserFilter", "Quan tri kinh doanh");
                        break;
                    case "Ngon ngu Anh":
                        session.setAttribute("prevUserFilter", "Ngon ngu Anh");
                        break;
                    case "Ngon ngu Nhat":
                        session.setAttribute("prevUserFilter", "Ngon ngu Nhat");
                        break;
                    case "Ngon ngu Han Quoc":
                        session.setAttribute("prevUserFilter", "Ngon ngu Han Quoc");
                        break;
                }
                pagination(request, response, list);
                request.setAttribute("filter", filter);
                session.setAttribute("prevUserAction", "filter");
                session.setAttribute("currUserAction", "pagefilter");
                request.getRequestDispatcher("/manageUser.jsp").forward(request, response);
                break;
            case "pagefilter":
                filter = (String) session.getAttribute("prevUserFilter");
                list = ud.filterByDepartment(filter);
                pagination(request, response, list);
                session.setAttribute("prevUserAction", "pagefilter");
                request.getRequestDispatcher("/manageUser.jsp").forward(request, response);
                break;
            case "create":
                DepartmentDAO dd = new DepartmentDAO();
                ArrayList<Department> depList = dd.readAll();
                ArrayList<Role> roleList = ud.readAllRole();
                request.setAttribute("depList", depList);
                request.setAttribute("roleList", roleList);
                request.getRequestDispatcher("/createUser.jsp").forward(request, response);
                break;
            case "signup":
                dd = new DepartmentDAO();
                list = ud.readAll();
                int id = list.size() + 1;
                String name = request.getParameter("fullname");
                int roleId = Integer.parseInt(request.getParameter("role"));
                int depId = Integer.parseInt(request.getParameter("department"));
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                depList = dd.readAll();
                roleList = ud.readAllRole();
                request.setAttribute("depList", depList);
                request.setAttribute("roleList", roleList);
                Users user = user = new Users(id, name, password, 1, depId, email, roleId);
                if (!password.equals(repassword)) {
                    request.setAttribute("signuser", user);
                    request.setAttribute("errMessage", "Wrong confirm password!!");
                    request.getRequestDispatcher("/createUser.jsp").forward(request, response);
                } else if (roleId == 3 && depId != 0) {
                    request.setAttribute("signuser", user);
                    request.setAttribute("errMessage", "Business's department must be none!!");
                    request.getRequestDispatcher("/createUser.jsp").forward(request, response);
                } else if (!checkEmail(request, response, list, email)) {
                    request.setAttribute("signuser", user);
                    request.setAttribute("errMessage", "Email already exist!!");
                    request.getRequestDispatcher("/createUser.jsp").forward(request, response);
                } else if (roleId != 3 && depId==0) {
                    request.setAttribute("signuser", user);
                    request.setAttribute("errMessage", "Student or lecturer must have department!!");
                    request.getRequestDispatcher("/createUser.jsp").forward(request, response);
                }
                if (roleId != 3) {
                    ud.create(user);
                } else {
                    ud.createBusiness(user);
                }

                list = ud.readAll();

                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("currUserAction", "index");
                session.setAttribute("prevUserAction", "index");
                pagination(request, response, list);
                request.getRequestDispatcher("/manageUser.jsp").forward(request, response);

                break;
        }
    }

    private boolean checkEmail(HttpServletRequest request, HttpServletResponse response, ArrayList<Users> list, String email) {
        for (Users u : list) {
            if (u.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    private void pagination(HttpServletRequest request, HttpServletResponse response, ArrayList<Users> list) {
        int pageSize = 5;//Kich thuoc trang                        
        //Xac dinh so thu tu cua trang hien tai

        HttpSession session = request.getSession();
        session.setAttribute("totalPage", null);
        Integer page = (Integer) session.getAttribute("page");
        if (page == null) {
            page = 1;
        }

        //Xac dinh tong so trang
        Integer totalPage = (Integer) session.getAttribute("totalPage");
        if (totalPage == null) {
            int count = list.size();//Dem so luong records
            totalPage = (int) Math.ceil((double) count / pageSize);//Tinh tong so trang
        }

        String op = request.getParameter("op");
        if (op == null) {
            op = "FirstPage";
        }
        switch (op) {
            case "FirstPage":
                page = 1;
                break;
            case "PreviousPage":
                if (page > 1) {
                    page--;
                }
                break;
            case "NextPage":
                if (page < totalPage) {
                    page++;
                }
                break;
            case "LastPage":
                page = totalPage;
                break;
            case "GotoPage":
                page = Integer.parseInt(request.getParameter("gotoPage"));
                if (page <= 0) {
                    page = 1;
                } else if (page > totalPage) {
                    page = totalPage;
                }
                break;
        }

        //Lay trang du lieu duoc yeu cau
        List slist;
        int n1 = (page - 1) * pageSize;
        int n2 = n1 + pageSize - 1;
        try {
            slist = list.subList(n1, n2 + 1);
        } catch (Exception e) {
            slist = list.subList(n1, list.size());
        }//Doc mot trang

        //Luu thong tin vao session va request
        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        request.setAttribute("list", slist);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

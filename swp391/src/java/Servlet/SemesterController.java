/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import DAO.SemesterDAO;
import DTO.Semester;
import java.io.IOException;
//import java.util.Date;
import java.sql.Date;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 
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
 * @author phamquang
 */
@WebServlet(name = "SemesterController", urlPatterns = {"/semester"})
public class SemesterController extends HttpServlet {

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
        String prevAction = (String) session.getAttribute("prevSemesterAction");
        if (prevAction == null) {
            session.setAttribute("prevSemesterAction", action);
            prevAction = action;

        }
        session.setAttribute("currSemesterAction", action);
        String currAction = (String) session.getAttribute("currSemesterAction");
        switch (action) {
            case "index":
                SemesterDAO semDao = new SemesterDAO();
                ArrayList<Semester> list = semDao.readAll();
                //        pagination
                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("prevSemesterAction", "index");
                pagination(request, response, list);
                //        pagination 

                request.getRequestDispatcher("/manageSemester.jsp").forward(request, response);
                break;
            case "search":
                semDao = new SemesterDAO();
                String searchText = request.getParameter("searchText");
                ArrayList<Semester> list1 = null;

                //        pagination
                if (searchText == null) {
                    list1 = semDao.readAll();
                } else {
                    list1 = semDao.readByName(searchText);
                }
                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("prevSemesterAction", "search");
                pagination(request, response, list1);
                session.setAttribute("searchTextSemester", searchText);
                session.setAttribute("currSemesterAction", "pagesearch");
                //        pagination

                request.getRequestDispatcher("/manageSemester.jsp").forward(request, response);
                break;
            case "pagesearch":
                searchText = (String) session.getAttribute("searchTextSemester");
                semDao = new SemesterDAO();

                if (searchText == null) {
                    list1 = semDao.readAll();
                } else {
                    list1 = semDao.readByName(searchText);
                }
                pagination(request, response, list1);
                session.setAttribute("prevSemesterAction", "pagesearch");

                request.getRequestDispatcher("/manageSemester.jsp").forward(request, response);

                break;
            case "create":
                request.getRequestDispatcher("/createSemester.jsp").forward(request, response);
                break;
            case "signup":
                semDao = new SemesterDAO();
                ArrayList<Semester> list2 = null;
                list2 = semDao.readAll();
                int id = list2.size() + 1;
                String semesterName = request.getParameter("semesterName");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                Semester validSem= semDao.readCurrentSemester();
                String previousSem = validSem.getEndDate().toString();
                Date sDate = Date.valueOf(startDate);
                Date eDate = Date.valueOf(endDate);
                Date today = Date.valueOf(previousSem);
                String regexFA = "(^[F][A])+([0-9]{4}$)";
                String regexSU = "(^[S][U])+([0-9]{4}$)";
                String regexSP = "(^[S][P])+([0-9]{4}$)";
                for (Semester semester : list2) {
                    if (semester.getName().equals(semesterName)) {
                        request.setAttribute("error-msg3", "Semester existed!");
                        request.getRequestDispatcher("/createSemester.jsp").forward(request, response);
                    }
                }
                if (!semesterName.matches(regexFA) && !semesterName.matches(regexSU) && !semesterName.matches(regexSP)) {
                    request.setAttribute("error-msg", "Must be FA or SU or SP + Years!");
                    request.getRequestDispatcher("/createSemester.jsp").forward(request, response);
                } else if(sDate.compareTo(today) == -1){
                    request.setAttribute("error-msg1", "Start date cannot before previous semester's End date!");
                    request.getRequestDispatcher("/createSemester.jsp").forward(request, response);
                } else if(eDate.compareTo(sDate) == -1 || eDate.compareTo(sDate) == 0){
                    request.setAttribute("error-msg2", "End date cannot before Start date!");
                    request.getRequestDispatcher("/createSemester.jsp").forward(request, response);
                } else {
                    Semester sem = new Semester(id, semesterName, sDate, eDate);
                    semDao.create(sem);
                }
                list2 = semDao.readAll();
                session.setAttribute("semList", list2);
                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("currSemesterAction", "index");
                session.setAttribute("prevSemesterAction", "index");
                pagination(request, response, list2);

                request.getRequestDispatcher("/manageSemester.jsp").forward(request, response);
                break;

        }
    }

    private void pagination(HttpServletRequest request, HttpServletResponse response, ArrayList<Semester> list) {
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

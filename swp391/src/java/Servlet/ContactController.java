/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.FeedBackDAO;
import DAO.UserDAO;
import DTO.FeedBack;
import DTO.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE161740 Pham Nguyen Hung Anh
 */
@WebServlet(name = "ContactController", urlPatterns = {"/contact"})
public class ContactController extends HttpServlet {

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
        Users user = (Users) session.getAttribute("user");
        FeedBackDAO fb = new FeedBackDAO();

        //        pagination
        String prevAction = (String) session.getAttribute("prevContactAction");
        if (prevAction == null) {
            session.setAttribute("prevContactAction", action);
            prevAction = action;
        }
        session.setAttribute("currContactAction", action);
        String currAction = (String) session.getAttribute("currContactAction");
//        pagination
        switch (action) {
            case "index":
                ArrayList<FeedBack> list = fb.readAll();

                //        pagination
                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("prevContactAction", "index");
                pagination(request, response, list);
                //        pagination 

                if (user.getRoleId() == 1) {
                    request.getRequestDispatcher("contact.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("adminViewFeedBack.jsp").forward(request, response);
                }
                break;

            case "search":
                String searchText = request.getParameter("searchText");

                ArrayList<FeedBack> list2 = fb.searchStudentByName(searchText);

                session.setAttribute("prevContactAction", "search");
                pagination(request, response, list2);
                //        pagination

                request.setAttribute("searchText", searchText);

                if (user.getRoleId() == 1) {
                    request.getRequestDispatcher("contact.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("adminViewFeedBack.jsp").forward(request, response);
                }
                break;

            case "save":
                //Luu toy vao db
                save(request, response);
                break;
            case "delete":
                    String id = request.getParameter("feedBackId");
                    fb.delete(id);
                

                //        pagination
                ArrayList<FeedBack> list1 = fb.readAll();
                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }

                session.setAttribute("prevContactAction", "index");
                pagination(request, response, list1);
                //        pagination 
                request.getRequestDispatcher("adminViewFeedBack.jsp").forward(request, response);
                break;
        }

    }

    public void save(HttpServletRequest request, HttpServletResponse response) {
        FeedBackDAO fb = new FeedBackDAO();
        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
        try {
            String op = request.getParameter("op");
            if (op.equals("save")) {

                int feedbackId = fb.countFeedBackId();
                feedbackId = feedbackId + 1;
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String subject = request.getParameter("subject");
                String msg = request.getParameter("message");

                int studentID = (int) session.getAttribute("userId");

                FeedBack fbo = new FeedBack(feedbackId, name, email, subject, msg, studentID);
                fb.create(fbo);
            }
            request.setAttribute("successMessage", "Sending FeedBack successful");
            request.getRequestDispatcher("contact.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void pagination(HttpServletRequest request, HttpServletResponse response, ArrayList<FeedBack> list) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.UserDAO;
import DTO.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "HeaderController", urlPatterns = {"/header"})
public class HeaderController extends HttpServlet {

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
        UserDAO ud = new UserDAO();

        switch (action) {
            case "changePassword":
                String name = (String) session.getAttribute("name");
                session.setAttribute("name", name);
                request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                break;
            case "save":
                //Luu toy vao db
                save(request, response);
                break;
        }
    }

    public void save(HttpServletRequest request, HttpServletResponse response) {
        UserDAO u = new UserDAO();
        HttpSession session = request.getSession();
        try {
            String op = request.getParameter("op");
            if (op.equals("save")) {
                int userId = (int) session.getAttribute("userId");
                String currentPassword = (String) session.getAttribute("password");
                String currentInputPassword = request.getParameter("currentPassword");
                String newPassword = request.getParameter("newPassword");
                String confirmPassword = request.getParameter("confirmPassword");

                if (currentPassword == null ? currentInputPassword == null : currentPassword.equals(currentInputPassword)) {
                    if (newPassword == null ? confirmPassword == null : newPassword.equals(confirmPassword)) {
                        u.changePassword(newPassword, userId);
                        request.setAttribute("successMessage", "Saving successful");
                    } else {
                        request.setAttribute("errorMessage", "The confirm password is not match with the new password");
                    }
                } else {
                    request.setAttribute("errorMessage", "The current password is not correct");
                }

                request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(GroupController.class.getName()).log(Level.SEVERE, null, ex);
            //Chi hien lai view create va thong bao loi
            request.getRequestDispatcher("/changePassword.jsp");
            request.setAttribute("errorMessage", "Saving can not excuted");
        }
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

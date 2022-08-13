/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import DAO.ProjectDAO;
import DAO.UserDAO;
import DTO.Groups;
import DTO.Project;
import DTO.Semester;
import DTO.Topic;
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
 * @author phamquang
 */
@WebServlet(name = "ProjectController", urlPatterns = {"/project/*"})
public class ProjectController extends HttpServlet {

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

        String path = request.getPathInfo();

        if (path.equals("/show")) {
            HttpSession ss = request.getSession();
            String UserEmail = (String) ss.getAttribute("email");
            String UserDepartment = (String) ss.getAttribute("department");
            UserDAO manager = new UserDAO();
            Users user = manager.load(UserEmail);
            ProjectDAO proDao = new ProjectDAO();
            Groups group = proDao.readGroup(user.getUserId());
            int GroupId = proDao.readGroupId(UserEmail);
            int TopicId = proDao.readTopicId(GroupId);
            String error = "Your group hasn't chosen a topic yet!";
            Topic topic = proDao.readTopic(TopicId);
            if (topic == null) {
                request.setAttribute("error", error);
            } else {
                int SemId = topic.getSemester().getSemesterId();
                Semester sem = proDao.readSemester(SemId);
                request.setAttribute("Sem", sem);
            }
            Project pro = proDao.readProject(GroupId);
            List<Users> list = proDao.listUser(GroupId);
            request.setAttribute("list", list);
            request.setAttribute("Pro", pro);
            request.setAttribute("DepName", UserDepartment);
            request.setAttribute("Group", group);
            request.setAttribute("Topic", topic);
            request.getRequestDispatcher("/project.jsp").forward(request, response);
        }//else 
//        if (path.equals("/lecView")) {
//            ProjectDAO proDao = new ProjectDAO();
//            List<Project> proList = proDao.readAllProject();
//            request.setAttribute("proList", proList);
//            request.getRequestDispatcher("/projectListLecturer.jsp").forward(request, response);
//        } else
//        if (path.equals("/detail")) {
//            int id = (int) request.getAttribute("id");
//            
//            request.getRequestDispatcher("/projectDetailLecturer.jsp").forward(request, response);
//        }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.DepartmentDAO;
import DAO.GroupsDAO;
import DAO.LectureTopicDAO;
import DAO.PendingTopicGroupDAO;
import DAO.SemesterDAO;
import DAO.TopicDAO;
import DAO.UserDAO;
import DTO.Category;
import DTO.Department;
import DTO.Groups;
import DTO.PendingTopicGroup;
import DTO.Semester;
import DTO.Topic;
import DTO.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author SE161714 Ha Anh Tu
 */
@WebServlet(name = "TopicController", urlPatterns = {"/topic"})
public class TopicController extends HttpServlet {

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
        PendingTopicGroupDAO ptg = new PendingTopicGroupDAO();
        SemesterDAO sd = new SemesterDAO();
        Semester currSem = null;
        TopicDAO td = new TopicDAO();

//        pagination
        String prevAction = (String) session.getAttribute("prevTopicAction");
        if (prevAction == null) {
            session.setAttribute("prevTopicAction", action);
            prevAction = action;

        }
        session.setAttribute("currTopicAction", action);
        String currAction = (String) session.getAttribute("currTopicAction");
//        pagination
        switch (action) {
            case "index":
                currSem = (Semester) session.getAttribute("currentSem");
                ArrayList<Topic> list = td.readAll(currSem.getName());

                if (user.getRoleId() == 1) {
                    ArrayList<Topic> appliableTopicList = checkValid(request, response);
                    session.setAttribute("appliableTopicList", appliableTopicList);
                } else if (user.getRoleId() == 2) {
                    int depId = (int) session.getAttribute("depId");
                    ArrayList<PendingTopicGroup> pendingTopicList = ptg.readAll(depId, user.getUserId());
                    ArrayList<Topic> approvingTopicList = td.readAllAproveTopic();
                    session.setAttribute("approvingTopicList", approvingTopicList);
                    session.setAttribute("pendingTopicList", pendingTopicList);
                } else if (user.getRoleId() == 4) {
                    ArrayList<Topic> approvingTopicList = td.readAllAproveTopic();
                    session.setAttribute("approvingTopicList", approvingTopicList);
                }
                //        pagination
                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("prevTopicAction", "index");
                pagination(request, response, list);
                //        pagination 

                if (user.getRoleId() == 1) {
                    request.getRequestDispatcher("/topic.jsp").forward(request, response);
                } else if (user.getRoleId() == 2) {
                    request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("/manageTopic.jsp").forward(request, response);
                }
                break;
            case "search":
                currSem = (Semester) session.getAttribute("currentSem");
                String searchText = request.getParameter("searchText");
                ArrayList<Topic> list2 = null;

                //        pagination
                if (searchText == null) {
                    list2 = td.readAll(currSem.getName());
                } else {
                    list2 = td.searchByName(searchText, currSem.getName());
                }

                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("prevTopicAction", "search");
                pagination(request, response, list2);
                session.setAttribute("searchText", searchText);
                session.setAttribute("currTopicAction", "pagesearch");
                //        pagination

                if (user.getRoleId() == 1) {
                    request.getRequestDispatcher("/topic.jsp").forward(request, response);
                } else if (user.getRoleId() == 2) {
                    request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("/manageTopic.jsp").forward(request, response);
                }
                break;
            case "pagesearch":
                searchText = (String) session.getAttribute("searchText");
                currSem = (Semester) session.getAttribute("currentSem");
                if (searchText == null) {
                    list2 = td.readAll(currSem.getName());
                } else {
                    list2 = td.searchByName(searchText, currSem.getName());
                }
                pagination(request, response, list2);
                session.setAttribute("prevTopicAction", "pagesearch");

                if (user.getRoleId() == 1) {
                    request.getRequestDispatcher("/topic.jsp").forward(request, response);
                } else if (user.getRoleId() == 2) {
                    request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("/manageTopic.jsp").forward(request, response);
                }
                break;
            case "filter":
                currSem = (Semester) session.getAttribute("currentSem");
                String filter = request.getParameter("filter");

                //        pagination
                String prevFilter = (String) session.getAttribute("prevTopicFilter");
                if (prevFilter == null) {
                    session.setAttribute("prevTopicFilter", filter);
                    prevFilter = filter;
                }
                session.setAttribute("currTopicFilter", filter);
                String currFilter = (String) session.getAttribute("currTopicFilter");
                ArrayList<Topic> list3 = td.filterByDepartment(filter, currSem.getName());
                if (!prevAction.equals(currAction) || !prevFilter.equals(currFilter)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                switch (currFilter) {
                    case "Cong nghe thong tin":
                        session.setAttribute("prevTopicFilter", "Cong nghe thong tin");
                        break;
                    case "Quan tri kinh doanh":
                        session.setAttribute("prevTopicFilter", "Quan tri kinh doanh");
                        break;
                    case "Ngon ngu Anh":
                        session.setAttribute("prevTopicFilter", "Ngon ngu Anh");
                        break;
                    case "Ngon ngu Nhat":
                        session.setAttribute("prevTopicFilter", "Ngon ngu Nhat");
                        break;
                    case "Ngon ngu Han Quoc":
                        session.setAttribute("prevTopicFilter", "Ngon ngu Han Quoc");
                        break;
                }
                pagination(request, response, list3);
                request.setAttribute("filter", filter);
                session.setAttribute("prevTopicAction", "filter");
                session.setAttribute("currTopicAction", "pagefilter");
                //        pagination

                if (user.getRoleId() == 1) {
                    request.getRequestDispatcher("/topic.jsp").forward(request, response);
                } else if (user.getRoleId() == 2) {
                    request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("/manageTopic.jsp").forward(request, response);
                }
                break;
            case "pagefilter":
                filter = (String) session.getAttribute("prevTopicFilter");
                currSem = (Semester) session.getAttribute("currentSem");
                list3 = td.filterByDepartment(filter, currSem.getName());
                pagination(request, response, list3);
                session.setAttribute("prevTopicAction", "pagefilter");

                if (user.getRoleId() == 1) {
                    request.getRequestDispatcher("/topic.jsp").forward(request, response);
                } else if (user.getRoleId() == 2) {
                    request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("/manageTopic.jsp").forward(request, response);
                }
                break;
            case "detail":
                int id = Integer.parseInt(request.getParameter("id"));
                Topic topic = td.readById(id);
                Category cate = td.readCategoryByTopicId(id);
                request.setAttribute("topic", topic);
                request.setAttribute("cate", cate);
                request.getRequestDispatcher("/topicDetail.jsp").forward(request, response);
                break;
            case "semester":
                String semester = request.getParameter("semester");
                SemesterDAO sem = new SemesterDAO();
                Semester currentSem = sem.read(semester);
                session.setAttribute("currentSem", currentSem);
                ArrayList<Topic> list4 = td.readAll(currentSem.getName());

                ArrayList<Semester> semList = (ArrayList<Semester>) session.getAttribute("semList");

                //        pagination
                String prevSemName = (String) session.getAttribute("prevTopicSemester");
                if (prevSemName == null) {
                    session.setAttribute("prevTopicSemester", semester);
                    prevSemName = semester;
                }
                session.setAttribute("currTopicSemester", semester);
                String currSemName = (String) session.getAttribute("currTopicSemester");
                if (!prevAction.equals(currAction) || !prevSemName.equals(currSemName)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                for (int i = 0; i < semList.size(); i++) {
                    if (currSemName.equals(semList.get(i).name)) {
                        session.setAttribute("prevTopicSemester", semList.get(i).name);
                    }
                }
                session.setAttribute("prevTopicAction", "semester");
                session.setAttribute("currTopicAction", "pagesemester");

                pagination(request, response, list4);
                //        pagination

                if (user.getRoleId() == 1) {
                    request.getRequestDispatcher("/topic.jsp").forward(request, response);
                } else if (user.getRoleId() == 2) {
                    request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("/manageTopic.jsp").forward(request, response);
                }
                break;
            case "pagesemester":
                currSem = (Semester) session.getAttribute("currentSem");
                list4 = td.readAll(currSem.getName());
                pagination(request, response, list4);
                session.setAttribute("prevTopicAction", "pagesemester");

                if (user.getRoleId() == 1) {
                    request.getRequestDispatcher("/topic.jsp").forward(request, response);
                } else if (user.getRoleId() == 2) {
                    request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("/manageTopic.jsp").forward(request, response);
                }
                break;
            case "apply":
                currSem = (Semester) session.getAttribute("currentSem");
                int topicId = Integer.parseInt(request.getParameter("id"));
                int userId = (int) session.getAttribute("userId");
                int depId = (int) session.getAttribute("depId");
                int groupId = td.getGroupIdByUser(userId);
                td.addPendingTable(groupId, topicId, depId);
                ArrayList<Topic> appliableTopicList2 = checkValid(request, response);
                session.setAttribute("appliableTopicList", appliableTopicList2);
                ArrayList<Topic> list5 = td.readAll(currSem.getName());

                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("currTopicAction", "index");
                session.setAttribute("prevTopicAction", "index");

                pagination(request, response, list5);

                checkValid(request, response);
                request.getRequestDispatcher("/topic.jsp").forward(request, response);
                break;
            case "approve":
                currSem = (Semester) session.getAttribute("currentSem");
                GroupsDAO gd = new GroupsDAO();
                topicId = Integer.parseInt(request.getParameter("topicId"));
                groupId = Integer.parseInt(request.getParameter("groupId"));
                topic = td.read(topicId);
                Groups group = gd.read(groupId);
                ptg.deletebyTopicId(topicId);
                ptg.deletebyGroupId(groupId);
                td.updateLockTopic(topicId);
                td.createProject(topic, group, user.getUserId());

                list = td.readAll(currSem.getName());

                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("currTopicAction", "index");
                session.setAttribute("prevTopicAction", "index");

                pagination(request, response, list);

                ArrayList<PendingTopicGroup> pendingTopicList = ptg.readAll(user.getDepartmentId(), user.getUserId());
                session.setAttribute("pendingTopicList", pendingTopicList);

                request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                break;
            case "deny":
                currSem = (Semester) session.getAttribute("currentSem");
                topicId = Integer.parseInt(request.getParameter("topicId"));
                groupId = Integer.parseInt(request.getParameter("groupId"));
                ptg.deny(topicId, groupId);

                list = td.readAll(currSem.getName());

                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("currTopicAction", "index");
                session.setAttribute("prevTopicAction", "index");

                pagination(request, response, list);

                pendingTopicList = ptg.readAll(user.getDepartmentId(), user.getUserId());
                session.setAttribute("pendingTopicList", pendingTopicList);

                request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                break;
            case "create":
                currSem = sd.readCurrentSemester();
                UserDAO ud = new UserDAO();
                int departmentId = user.getDepartmentId();
                DepartmentDAO dd = new DepartmentDAO();
                Department dep = dd.read(departmentId);
                request.setAttribute("depName", dep.getName());
                ArrayList<Users> bussinessList = ud.readAllBusiness();
                request.setAttribute("bList", bussinessList);
                request.setAttribute("chosenBusiness", bussinessList.get(0).getUserId());
                request.setAttribute("validSemester", currSem.getName());
                request.getRequestDispatcher("/createTopic.jsp").forward(request, response);
                break;
            case "save":
                save(request, response);
                break;
            case "delete":
                currSem = (Semester) session.getAttribute("currentSem");
                LectureTopicDAO ltd = new LectureTopicDAO();
                topicId = Integer.parseInt(request.getParameter("topicId"));
                ltd.delete(topicId);

                td.delete(topicId);

                ArrayList<Topic> approvingTopicList = td.readAllAproveTopic();
                session.setAttribute("approvingTopicList", approvingTopicList);

                list = td.readAll(currSem.getName());

                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("currTopicAction", "index");
                session.setAttribute("prevTopicAction", "index");

                pagination(request, response, list);

                if (user.getRoleId() == 2) {
                    request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                } else if (user.getRoleId() == 4) {
                    request.getRequestDispatcher("/manageTopic.jsp").forward(request, response);
                }
                break;
            case "update":
                ud = new UserDAO();
                topicId = Integer.parseInt(request.getParameter("topicId"));
                topic = td.read(topicId);
                request.setAttribute("topicName", topic.getName());
                request.setAttribute("topicDetail", topic.getDescription());
                request.setAttribute("validSemester", sd.readCurrentSemester().getName());
                request.setAttribute("depName", topic.getDepartment().getName());
                bussinessList = ud.readAllBusiness();
                request.setAttribute("bList", bussinessList);
                request.setAttribute("chosenBusiness", topic.getBusinessId());
                request.setAttribute("topicId", topicId);
                request.getRequestDispatcher("/updateTopic.jsp").forward(request, response);
                break;
            case "saveupdate":
                topicId = Integer.parseInt(request.getParameter("topicId"));
                int category = 0;
                currSem = (Semester) session.getAttribute("currentSem");
                Semester validSem = sd.readCurrentSemester();
                depId = (int) session.getAttribute("depId");
                int lecId = (int) session.getAttribute("userId");
                String topicName = request.getParameter("topicName");
                String topicDetail = request.getParameter("topicDetail");
                int semId = validSem.getSemesterId();
                int bussinessId = Integer.parseInt(request.getParameter("business"));
                int check = td.checkTopicName(topicName);
                Semester sem1 = new Semester();

                sem1.setSemesterId(semId);

                if (depId == 2) {
                    category = 6;
                } else if (depId == 1) {
                    category = 2;
                } else if (depId == 3) {
                    category = 14;
                } else if (depId == 4) {
                    category = 16;
                } else if (depId == 5) {
                    category = 15;
                }
                topic = new Topic(topicId, topicName, category, topicDetail, bussinessId, depId, sem1);
                td.update(topic);
                approvingTopicList = td.readAllAproveTopic();
                session.setAttribute("approvingTopicList", approvingTopicList);

                list = td.readAll(currSem.getName());

                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("currTopicAction", "index");
                session.setAttribute("prevTopicAction", "index");

                pagination(request, response, list);
                request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);
                break;
            case "approveadmin":
                currSem = (Semester) session.getAttribute("currentSem");
                topicId = Integer.parseInt(request.getParameter("topicId"));
                td.updateApproveStatus(topicId);
                approvingTopicList = td.readAllAproveTopic();
                session.setAttribute("approvingTopicList", approvingTopicList);
                list = td.readAll(currSem.getName());

                if (!prevAction.equals(currAction)) {
                    session.setAttribute("totalPage", null);
                    session.setAttribute("page", null);
                }
                session.setAttribute("currTopicAction", "index");
                session.setAttribute("prevTopicAction", "index");

                pagination(request, response, list);
                request.getRequestDispatcher("/manageTopic.jsp").forward(request, response);
                break;

        }
    }

    public void save(HttpServletRequest request, HttpServletResponse response) {
        TopicDAO td = new TopicDAO();
        SemesterDAO sd = new SemesterDAO();
        HttpSession session = request.getSession();
        Semester currSem = (Semester) session.getAttribute("currentSem");
        String prevAction = (String) session.getAttribute("prevTopicAction");
        String currAction = (String) session.getAttribute("currTopicAction");
        try {
            String op = request.getParameter("op");
            if (op.equals("create")) {
                int category = 0;
                Semester validSem = sd.readCurrentSemester();
                int depId = (int) session.getAttribute("depId");
                int lecId = (int) session.getAttribute("userId");
                String topicName = request.getParameter("topicName");
                String topicDetail = request.getParameter("topicDetail");

                int bussinessId = Integer.parseInt(request.getParameter("business"));
                int check = td.checkTopicName(topicName);
                if (check != 0) {
                    UserDAO ud = new UserDAO();
                    DepartmentDAO dd = new DepartmentDAO();
                    request.setAttribute("errorMessage", "Topic name already exist");
                    request.setAttribute("validSemester", validSem.getName());
                    Department dep = dd.read(depId);
                    request.setAttribute("depName", dep.getName());
                    request.setAttribute("topicName", topicName);
                    request.setAttribute("topicDetail", topicDetail);
                    ArrayList<Users> bussinessList = ud.readAllBusiness();
                    request.setAttribute("bList", bussinessList);
                    request.setAttribute("chosenBusiness", bussinessId);
                    request.getRequestDispatcher("/createTopic.jsp").forward(request, response);
                } else {
                    Semester sem = new Semester();

                    sem.setSemesterId(validSem.getSemesterId());

                    if (depId == 2) {
                        category = 6;
                    } else if (depId == 1) {
                        category = 2;
                    } else if (depId == 3) {
                        category = 14;
                    } else if (depId == 4) {
                        category = 16;
                    } else if (depId == 5) {
                        category = 15;
                    }

                    int count = td.countTopic();
                    count = count + 1;
                    Topic topic = new Topic(count, topicName, category, topicDetail, bussinessId, depId, sem);
                    td.create(topic);
                    td.createTopicLecturer(lecId, topic.getTopicId());
                    ArrayList<Topic> approvingTopicList = td.readAllAproveTopic();
                    session.setAttribute("approvingTopicList", approvingTopicList);
                }

            }

            ArrayList<Topic> list = td.readAll(currSem.getName());

            if (!prevAction.equals(currAction)) {
                session.setAttribute("totalPage", null);
                session.setAttribute("page", null);
            }
            session.setAttribute("currTopicAction", "index");
            session.setAttribute("prevTopicAction", "index");

            pagination(request, response, list);
            request.getRequestDispatcher("/topicListLecturer.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(GroupController.class.getName()).log(Level.SEVERE, null, ex);
            //Chi hien lai view create va thong bao loi
            request.getRequestDispatcher("/createTeam.jsp");
            request.setAttribute("errorMessage", "Invalid data.");
        }
    }

    private void pagination(HttpServletRequest request, HttpServletResponse response, ArrayList<Topic> list) {
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

    private ArrayList<Topic> checkValid(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        TopicDAO td = new TopicDAO();
        SemesterDAO sd = new SemesterDAO();
        Semester currSem = sd.readCurrentSemester();

        int groupId = td.getGroupIdByUser(userId);
        int leaderStatus = td.checkLeader(userId);
        int depId = td.checkDepartment(userId);
        int semId = td.checkSemester(userId);
        int topicStatus = td.checkHaveTopic(groupId);

        ArrayList<Topic> list = td.readAll(currSem.getName());
        ArrayList<Topic> appliableTopicList = new ArrayList();

        for (Topic item : list) {
            boolean check = td.checkHaveApplied(item.getTopicId(), groupId);
            if (leaderStatus == 1 && depId == item.getDepartmentId() && semId == item.getSemester().getSemesterId() && topicStatus == 0 && item.getStatus() != 1 && check == false) {
                appliableTopicList.add(item);
            }
        }

        return appliableTopicList;

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

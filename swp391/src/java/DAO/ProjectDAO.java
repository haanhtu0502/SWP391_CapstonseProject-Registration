/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Groups;
import DTO.Project;
import DTO.Semester;
import DTO.Topic;
import DTO.Users;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SE161740 Pham Nguyen Hung Anh
 */
public class ProjectDAO {

    public Groups readGroup(int id) {
        Groups group = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT Groups.GroupId ,Groups.GroupName, Groups.groupStatus\n"
                        + "from Users, StudentGroup AS StuGro, Groups\n"
                        + "WHERE Users.UserId = StuGro.StudentId and \n"
                        + "StuGro.GroupId = Groups.GroupId and Users.UserId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    group = new Groups();
                    group.setGroupId(rs.getInt("GroupId"));
                    group.setGroupName(rs.getString("GroupName"));
                    group.setGroupStatus(rs.getInt("groupStatus"));
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return group;
    }

    public int readGroupId(String email) {
        int Id = 0;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "select StudentGroup.GroupId\n"
                        + "  from Users, StudentGroup\n"
                        + "  WHERE Users.UserId = StudentGroup.StudentId and Users.Email = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Id = rs.getInt("GroupId");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return Id;
    }

    public List<Users> listUser(int Id) {

        ArrayList<Users> list = new ArrayList<>();

        String sql = "Select Users.Name\n"
                + "  from Users, StudentGroup as StuGro\n"
                + "  WHERE StuGro.StudentId = Users.UserId and StuGro.GroupId = ?";

        try {
            Connection conn = DBUtils.makeConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Users(rs.getString("Name")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int readTopicId(int grpId) {
        int Id = 0;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT Project.TopicId\n"
                        + "  from Project WHERE Project.GroupId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, grpId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Id = rs.getInt("TopicId");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return Id;
    }

    public Topic readTopic(int id) {
        Topic topic = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "select * from Topic WHERE Topic.TopicId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    topic = new Topic();
                    topic.setTopicId(rs.getInt("TopicId"));
                    topic.setName(rs.getString("Name"));
                    topic.setCatergoryId(rs.getInt("CategoryId"));
                    topic.setDescription(rs.getString("Description"));
                    topic.setBusinessId(rs.getInt("BusinessId"));
                    topic.setDepartmentId(rs.getInt("DepartmentId"));
                    int semesterID = rs.getInt("SemesterId");
                    Semester semester = new Semester();
                    semester.setSemesterId(semesterID);
                    topic.setSemester(semester);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return topic;
    }

    public Semester readSemester(int id) {
        Semester semester = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT * from Semester\n"
                        + "  WHERE Semester.SemesterId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    semester = new Semester();
                    semester.setSemesterId(rs.getInt("SemesterId"));
                    semester.setName(rs.getString("Name"));
                    semester.setStartDate(rs.getDate("StartDate"));
                    semester.setEndDate(rs.getDate("EndDate"));
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return semester;
    }

    public Project readProject(int id) {
        Project project = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "select * from Project \n"
                        + "  WHERE Project.GroupId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    project = new Project();
                    project.setProjectId(rs.getInt("ProjectId"));
                    project.setDescription(rs.getString("Description"));
                    project.setName(rs.getString("Name"));
                    project.setSourceCode(rs.getString("SourceCode"));
                    project.setTopicId(rs.getInt("TopicId"));
                    project.setStatus(rs.getInt("Status"));
                    project.setGroupId(rs.getInt("GroupId"));
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return project;
    }

    public List<Project> readAllProject(int semesterId) {
        ArrayList<Project> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.makeConnection();
            String sql = "SELECT ProjectId, Description, Project.Name AS proName, SourceCode, TopicId, Project.[Status] AS Sta, "
                    + "Project.GroupId, Groups.GroupName AS grpName, "
                    + "LecturerId , Users.Name AS lecName, Groups.SemID AS semID, Semester.SemesterId "
                    + "from Project, Users, Groups, Semester "
                    + "WHERE Project.LecturerId = Users.UserId AND Project.GroupId = Groups.GroupId And "
                    + "Semester.SemesterId = semID AND Semester.SemesterId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, semesterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProjectId = rs.getInt("ProjectId");
                String Description = rs.getString("Description");
                String SourceCode = rs.getString("SourceCode");
                String Name = rs.getString("proName");
                int TopicId = rs.getInt("TopicId");
                int Status = rs.getInt("Sta");
                int GroupId = rs.getInt("GroupId");
                String lecName = rs.getString("lecName");
                String grpName = rs.getString("grpName");
                Groups group = new Groups();
                group.setGroupName(grpName);
                Users user = new Users();
                user.setName(lecName);
                Project project = new Project(ProjectId, Description, Name, SourceCode, TopicId, Status, GroupId, group, user);
                list.add(project);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Project> searchByName(String name, int semesterId) {
        ArrayList<Project> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.makeConnection();
            String sql = "SELECT ProjectId, Description, Project.Name AS proName, SourceCode, TopicId, Project.[Status] AS Sta, "
                    + "Project.GroupId, Groups.GroupName AS grpName, "
                    + "LecturerId , Users.Name AS lecName, Groups.SemID AS semID, Semester.SemesterId "
                    + "from Project, Users, Groups, Semester "
                    + "WHERE Project.LecturerId = Users.UserId AND Project.GroupId = Groups.GroupId And "
                    + "Semester.SemesterId = semID AND Semester.SemesterId = ? AND Project.Name LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, semesterId);
            ps.setString(2, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProjectId = rs.getInt("ProjectId");
                String Description = rs.getString("Description");
                String SourceCode = rs.getString("SourceCode");
                String Name = rs.getString("proName");
                int TopicId = rs.getInt("TopicId");
                int Status = rs.getInt("Sta");
                int GroupId = rs.getInt("GroupId");
                String lecName = rs.getString("lecName");
                String grpName = rs.getString("grpName");
                Groups group = new Groups();
                group.setGroupName(grpName);
                Users user = new Users();
                user.setName(lecName);
                Project project = new Project(ProjectId, Description, Name, SourceCode, TopicId, Status, GroupId, group, user);
                list.add(project);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Project> filterByDepartment(String DepName, int semesterId) {
        ArrayList<Project> list = new ArrayList<>();

        try {
            Connection conn = DBUtils.makeConnection();
            String sql = "SELECT ProjectId, Description, Project.Name AS proName, SourceCode, TopicId, Project.[Status] AS Sta, "
                    + "Project.GroupId, Groups.GroupName AS grpName, "
                    + "LecturerId , Users.Name AS lecName, Groups.SemID AS semID, Semester.SemesterId, Department.Name "
                    + "from Project, Users, Groups, Semester, Department "
                    + "WHERE Project.LecturerId = Users.UserId AND Project.GroupId = Groups.GroupId And "
                    + "Semester.SemesterId = semID AND Semester.SemesterId = ? AND Department.DepartmentId = Users.DepartmentId "
                    + "AND Department.Name LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, semesterId);
            ps.setString(2, "%" + DepName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProjectId = rs.getInt("ProjectId");
                String Description = rs.getString("Description");
                String SourceCode = rs.getString("SourceCode");
                String Name = rs.getString("proName");
                int TopicId = rs.getInt("TopicId");
                int Status = rs.getInt("Sta");
                int GroupId = rs.getInt("GroupId");
                String lecName = rs.getString("lecName");
                String grpName = rs.getString("grpName");
                Groups group = new Groups();
                group.setGroupName(grpName);
                Users user = new Users();
                user.setName(lecName);
                Project project = new Project(ProjectId, Description, Name, SourceCode, TopicId, Status, GroupId, group, user);
                list.add(project);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Groups readGroupByProjectId(int id) {
        Groups group = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT Groups.GroupId ,Groups.GroupName, Groups.groupStatus\n"
                        + "from Groups, Project "
                        + "where Project.GroupId = Groups.GroupId AND Project.ProjectId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    group = new Groups();
                    group.setGroupId(rs.getInt("GroupId"));
                    group.setGroupName(rs.getString("GroupName"));
                    group.setGroupStatus(rs.getInt("groupStatus"));
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return group;
    }

    public int readStudentIdByGrpId(int id) {
        int StuId = 0;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "select top 1 StudentGroup.StudentId\n"
                        + "from StudentGroup WHERE StudentGroup.GroupId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    StuId = rs.getInt("StudentId");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return StuId;
    }

    public int readDepId(int id) {
        int DepId = 0;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "select Users.DepartmentId\n"
                        + "from Users WHERE Users.UserId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DepId = rs.getInt("DepartmentId");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return DepId;
    }

    public String readDepName(int id) {
        String DepName = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "select Department.Name\n"
                        + "from Department WHERE Department.DepartmentId = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DepName = rs.getString("Name");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return DepName;
    }
}

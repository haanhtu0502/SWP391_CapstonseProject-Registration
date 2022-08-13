/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Category;
import DTO.Department;
import DTO.Groups;
import DTO.Semester;
import DTO.Topic;
import DTO.Users;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SE161714 Ha Anh Tu
 */
public class TopicDAO {

    public static ArrayList<Topic> readAll(String currentSem) {
        Connection cn = null;
        ArrayList<Topic> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "  select ltt.*,d.Name as DepartmentName,u.Name as LecturerName\n"
                        + "                         from (select t.*,lt.LecturerId from dbo.LecturerTopic lt join dbo.Topic t on lt.TopicId = t.TopicId) ltt \n"
                        + "                         join dbo.Users u on ltt.LecturerId=u.UserId \n"
                        + "                         join dbo.Department d on ltt.DepartmentId=d.DepartmentId\n"
                        + "			    join dbo.Semester s on ltt.SemesterId=s.SemesterId\n"
                        + "			    where s.Name like ? and ltt.ApproveStatus=1 ";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + currentSem + "%");
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int topicID = rs.getInt("topicId");
                    String name = rs.getString("Name");
                    int category = rs.getInt("categoryId");
                    String description = rs.getString("description");
                    int businessId = rs.getInt("businessId");
                    int depId = rs.getInt("DepartmentId");
                    String depName = rs.getString("DepartmentName");
                    String lecName = rs.getString("LecturerName");
                    int status = rs.getInt("status");
                    int semesterId = rs.getInt("semesterId");
                    Semester sem = new Semester();
                    sem.setSemesterId(semesterId);
                    Users user = new Users();
                    Department dep = new Department();
                    dep.setName(depName);
                    user.setName(lecName);
                    Topic topic = new Topic(topicID, name, category, description, businessId, depId, user, dep, sem, status);
                    list.add(topic);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static ArrayList<Topic> readAllAproveTopic() {
        Connection cn = null;
        ArrayList<Topic> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "   select ltt.TopicId,ltt.Name,ltt.SemesterId,d.Name as DepartmentName,u.Name as LecturerName\n"
                        + "                                                 from (select t.*,lt.LecturerId from dbo.LecturerTopic lt join dbo.Topic t on lt.TopicId = t.TopicId) ltt \n"
                        + "                                                join dbo.Users u on ltt.LecturerId=u.UserId \n"
                        + "                                                 join dbo.Department d on ltt.DepartmentId=d.DepartmentId                       			  \n"
                        + "                        			    where ltt.ApproveStatus=0";
                PreparedStatement stm = cn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int topicID = rs.getInt("topicId");
                    String name = rs.getString("Name");                     
                    String depName = rs.getString("DepartmentName");                                 
                    int semesterId = rs.getInt("semesterId");
                    SemesterDAO sd= new SemesterDAO();
                    Semester sem = sd.readById(semesterId);                     
                    Department dep = new Department();
                    dep.setName(depName);                 
                    Topic topic = new Topic(topicID, name, 0, dep,sem);
                    list.add(topic);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static ArrayList<Topic> searchByName(String name, String currentSem) {
        Connection cn = null;
        ArrayList<Topic> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "  select ltt.*,d.Name as DepartmentName,u.Name as LecturerName\n"
                        + "                         from (select t.*,lt.LecturerId from dbo.LecturerTopic lt join dbo.Topic t on lt.TopicId = t.TopicId) ltt \n"
                        + "                         join dbo.Users u on ltt.LecturerId=u.UserId \n"
                        + "                         join dbo.Department d on ltt.DepartmentId=d.DepartmentId\n"
                        + "			    join dbo.Semester s on ltt.SemesterId=s.SemesterId\n"
                        + "                         where ltt.name like ? and ltt.ApproveStatus=1 and s.Name like ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                stm.setString(2, "%" + currentSem + "%");
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int topicID = rs.getInt("topicId");
                    String topicName = rs.getString("Name");
                    int category = rs.getInt("categoryId");
                    String description = rs.getString("description");
                    int businessId = rs.getInt("businessId");
                    int depId = rs.getInt("DepartmentId");
                    String depName = rs.getString("DepartmentName");
                    String lecName = rs.getString("LecturerName");
                    int semesterId = rs.getInt("semesterId");
                    int status = rs.getInt("status");
                    Users user = new Users();
                    Semester sem = new Semester();
                    sem.setSemesterId(semesterId);
                    Department dep = new Department();
                    dep.setName(depName);
                    user.setName(lecName);
                    Topic topic = new Topic(topicID, topicName, category, description, businessId, depId, user, dep, sem, status);
                    list.add(topic);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static ArrayList<Topic> filterByDepartment(String name, String currentSem) {
        Connection cn = null;
        ArrayList<Topic> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "   select ltt.*,d.Name as DepartmentName,u.Name as LecturerName\n"
                        + "  from (select t.*,lt.LecturerId from dbo.LecturerTopic lt join dbo.Topic t on lt.TopicId = t.TopicId) ltt \n"
                        + "  join dbo.Users u on ltt.LecturerId=u.UserId \n"
                        + "  join dbo.Department d on ltt.DepartmentId=d.DepartmentId\n"
                        + "  join dbo.Semester s on ltt.SemesterId=s.SemesterId\n"
                        + "  where d.Name like ? and ltt.ApproveStatus=1 and s.Name like ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                stm.setString(2, "%" + currentSem + "%");
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int topicID = rs.getInt("topicId");
                    String topicName = rs.getString("Name");
                    int category = rs.getInt("categoryId");
                    String description = rs.getString("description");
                    int businessId = rs.getInt("businessId");
                    int depId = rs.getInt("DepartmentId");
                    String depName = rs.getString("DepartmentName");
                    String lecName = rs.getString("LecturerName");
                    int status = rs.getInt("status");
                    int semesterId = rs.getInt("semesterId");
                    Semester sem = new Semester();
                    sem.setSemesterId(semesterId);
                    Users user = new Users();
                    Department dep = new Department();
                    dep.setName(depName);
                    user.setName(lecName);
                    Topic topic = new Topic(topicID, topicName, category, description, businessId, depId, user, dep, sem, status);
                    list.add(topic);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static Category readCategoryByTopicId(int id) {
        Connection cn = null;
        Category cate = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "  select c.*\n"
                        + "  from dbo.Topic t join dbo.Category c on t.CategoryId=c.CategoryId\n"
                        + "  where t.TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int cateId = rs.getInt("CategoryId");
                    String cateName = rs.getString("CategoryName");
                    cate = new Category(cateId, cateName);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return cate;

    }

    public static Topic readById(int id) {
        Connection cn = null;
        Topic topic = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "    select ltt.*,d.Name as DepartmentName,u.Name as LecturerName\n"
                        + "  from (select t.*,lt.LecturerId from dbo.LecturerTopic lt join dbo.Topic t on lt.TopicId = t.TopicId) ltt \n"
                        + "  join dbo.Users u on ltt.LecturerId=u.UserId \n"
                        + "  join dbo.Department d on ltt.DepartmentId=d.DepartmentId\n"
                        + "  where ltt.TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int topicID = rs.getInt("topicId");
                    String topicName = rs.getString("Name");
                    int category = rs.getInt("categoryId");
                    String description = rs.getString("description");
                    int businessId = rs.getInt("businessId");
                    int depId = rs.getInt("DepartmentId");
                    String depName = rs.getString("DepartmentName");
                    String lecName = rs.getString("LecturerName");
                    Users user = new Users();
                    Department dep = new Department();
                    dep.setName(depName);
                    user.setName(lecName);
                    topic = new Topic(topicID, topicName, category, description, businessId, depId, user, dep);

                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return topic;
    }

    public static ArrayList<Topic> findRange(int n1, int n2) {
        Connection cn = null;
        ArrayList<Topic> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "  select ltt.*,d.Name as DepartmentName,u.Name as LecturerName\n"
                        + "  from (select t.*,lt.LecturerId from dbo.LecturerTopic lt join dbo.Topic t on lt.TopicId = t.TopicId) ltt \n"
                        + "  join dbo.Users u on ltt.LecturerId=u.UserId \n"
                        + "  join dbo.Department d on ltt.DepartmentId=d.DepartmentId														   \n"
                        + "  WHERE ltt.TopicId BETWEEN ? and ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, n1);
                stm.setInt(2, n2);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int topicID = rs.getInt("topicId");
                    String name = rs.getString("Name");
                    int category = rs.getInt("categoryId");
                    String description = rs.getString("description");
                    int businessId = rs.getInt("businessId");
                    int depId = rs.getInt("DepartmentId");
                    String depName = rs.getString("DepartmentName");
                    String lecName = rs.getString("LecturerName");
                    Users user = new Users();
                    Department dep = new Department();
                    dep.setName(depName);
                    user.setName(lecName);
                    Topic topic = new Topic(topicID, name, category, description, businessId, depId, user, dep);
                    list.add(topic);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static int count() {
        Connection cn = null;
        int count = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select count(TopicId) as topics from dbo.topic";
                PreparedStatement stm = cn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("topics");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return count;
    }

    public static Topic read(Object id) {
        Connection cn = null;
        Topic topic = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = " select ltt.*,d.Name as DepartmentName,u.Name as LecturerName\n"
                        + "                         from (select t.*,lt.LecturerId from dbo.LecturerTopic lt join dbo.Topic t on lt.TopicId = t.TopicId) ltt \n"
                        + "                         join dbo.Users u on ltt.LecturerId=u.UserId \n"
                        + "                         join dbo.Department d on ltt.DepartmentId=d.DepartmentId\n"
                        + "			    where ltt.TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    int topicID = rs.getInt("topicId");
                    String name = rs.getString("Name");
                    int category = rs.getInt("categoryId");
                    String description = rs.getString("description");
                    int businessId = rs.getInt("businessId");
                    int depId = rs.getInt("DepartmentId");
                    String depName = rs.getString("DepartmentName");
                    String lecName = rs.getString("LecturerName");
                    int status = rs.getInt("status");
                    int semesterId = rs.getInt("semesterId");
                    Semester sem = new Semester();
                    sem.setSemesterId(semesterId);
                    Users user = new Users();
                    Department dep = new Department();
                    dep.setName(depName);
                    user.setName(lecName);
                    topic = new Topic(topicID, name, category, description, businessId, depId, user, dep, sem, status);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return topic;
    }

    public static int checkDepartment(int id) {
        Connection cn = null;
        int depId = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DepartmentId from dbo.Users where UserId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    depId = rs.getInt("DepartmentId");

                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return depId;

    }

    public static int checkHaveTopic(int id) {
        Connection cn = null;
        int status = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select g.GroupId\n"
                        + "  from dbo.Groups g join dbo.Project p on g.GroupId=p.GroupId\n"
                        + "  where g.GroupId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    status = rs.getInt("GroupId");
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return status;
    }

    public static boolean checkHaveApplied(int topicId, int groupId) {
        Connection cn = null;
        ArrayList<Integer> topicList = new ArrayList();

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "  select pgt.TopicId\n"
                        + "                            from dbo.PendingGroupTopic pgt \n"
                        + "                             where pgt.TopicId=? and pgt.groupId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, topicId);
                stm.setInt(2, groupId);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    int Id = rs.getInt("TopicId");
                    topicList.add(Id);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();

        }
        if (topicList.size() == 0) {
            return false;
        }
        return true;
    }

    public static int checkLeader(int id) {
        Connection cn = null;
        int status = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select sg.LeaderStatus from dbo.Users u join dbo.StudentGroup sg on u.UserId=sg.StudentId where u.UserId =?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    status = rs.getInt("LeaderStatus");

                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return status;
    }
    
      public int checkTopicName(String name){
        Connection cn = null;
        int check=0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select TopicId from dbo.Topic where Name like ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, name);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    check = rs.getInt("TopicId");
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        return check;
    }

    public static int checkSemester(int id) {
        Connection cn = null;
        int semId = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = " select g.SemID\n"
                        + "  from dbo.StudentGroup sg join dbo.Users u on sg.StudentId=u.UserId join dbo.Groups g on sg.GroupId=g.GroupId \n"
                        + "  where u.UserId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    semId = rs.getInt("SemID");
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return semId;
    }

     public static void updateApproveStatus(int id) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Topic set ApproveStatus=1 where TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public static void updatePendingTopic(int id) {
        Connection cn = null;
        int status = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Topic set Status=1 where TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void updateLockTopic(int id) {
        Connection cn = null;
        int status = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Topic set Status=1 where TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void updateWaitingTopic(int id) {
        Connection cn = null;
        int status = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Topic set Status=0 where TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void updatePendingTopicGroup(int groupId) {
        Connection cn = null;
        int status = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Groups set TopicStatus=1 where GroupId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, groupId);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void addPendingTable(int groupId, int topicId, int depId) {
        Connection cn = null;
        int status = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.PendingGroupTopic(GroupId,TopicId,DepartmentId) values(?,?,?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, groupId);
                stm.setInt(2, topicId);
                stm.setInt(3, depId);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static int getGroupIdByUser(int userId) {
        Connection cn = null;
        int groupId = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select g.GroupId\n"
                        + "  from dbo.StudentGroup sg join dbo.Users u on sg.StudentId=u.UserId join dbo.Groups g on sg.GroupId=g.GroupId \n"
                        + "  where u.UserId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, userId);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    groupId = rs.getInt("GroupId");
                }
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return groupId;
    }

    public static int countLecturerTopic() {
        Connection cn = null;
        int count = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select top 1 ID from dbo.LecturerTopic order by ID desc";
                PreparedStatement stm = cn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("ID");
                }
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return count;
    }

    public static int countTopic() {
        Connection cn = null;
        int count = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select top 1 TopicId from dbo.Topic order by TopicId desc";
                PreparedStatement stm = cn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    count=rs.getInt("TopicId");
                }
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return count;
    }

    public static void create(Topic topic) {
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Topic values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, topic.getTopicId());
                stm.setString(2, topic.getName());
                stm.setInt(3, 0);
                stm.setInt(4, topic.getCatergoryId());
                stm.setString(5, topic.getDescription());
                stm.setInt(6, topic.getBusinessId());
                stm.setInt(7, topic.getDepartmentId());
                stm.setInt(8, topic.getSemester().getSemesterId());
                stm.setInt(9, 0);

                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void createTopicLecturer(int lecId, int topicId) {
        Connection cn = null;
        int count = countLecturerTopic();
        count += 1;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.LecturerTopic values(?, ?, ?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, count);
                stm.setInt(2, lecId);
                stm.setInt(3, topicId);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static int countProject() {
        Connection cn = null;
        int count = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select ProjectId as projects from dbo.Project";
                PreparedStatement stm = cn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    count++;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return count;
    }

    public static void createProject(Topic topic, Groups group, int userId) {
        Connection cn = null;
        int count = countProject();
        count += 1;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Project values(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, count);
                stm.setString(2, topic.getDescription());
                stm.setString(3, topic.getName());
                stm.setString(4, "");
                stm.setInt(5, topic.getTopicId());
                stm.setInt(6, 1);
                stm.setInt(7, group.getGroupId());
                stm.setInt(8, userId);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void update(Topic topic) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Topic set Name=?,CategoryId=?,Description=?,BusinessId=?,DepartmentId=?,SemesterId =? where TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, topic.getName());
                stm.setInt(2, topic.getCatergoryId());
                stm.setString(3, topic.getDescription());
                stm.setInt(4, topic.getBusinessId());
                stm.setInt(5, topic.getDepartmentId());
                stm.setInt(6, topic.getSemester().getSemesterId());
                stm.setInt(7, topic.getTopicId());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
  

    public static void delete(Object id) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "delete from dbo.Topic where TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}

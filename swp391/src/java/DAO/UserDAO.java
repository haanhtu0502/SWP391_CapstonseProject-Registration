/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Department;
import DTO.Role;
import DTO.Users;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HLong
 */
public class UserDAO {

    public static ArrayList<Users> readAll() {
        Connection cn = null;
        ArrayList<Users> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Users\n";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int userID = rs.getInt("UserId");
                    String name = rs.getString("Name");
                    String password = rs.getString("Password");
                    int status = rs.getInt("Status");
                    int departmentId = rs.getInt("DepartmentId");
                    String email = rs.getString("Email");
                    int roleId = rs.getInt("RoleId");
                    Users user = new Users(userID, name, password, status, departmentId, email, roleId);
                    list.add(user);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static ArrayList<Users> readAllNotAdmin() {
        Connection cn = null;
        ArrayList<Users> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Users\n"
                        + "where RoleId !=4";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int userID = rs.getInt("UserId");
                    String name = rs.getString("Name");
                    String password = rs.getString("Password");
                    int status = rs.getInt("Status");
                    int departmentId = rs.getInt("DepartmentId");
                    String email = rs.getString("Email");
                    int roleId = rs.getInt("RoleId");
                    Users user = new Users(userID, name, password, status, departmentId, email, roleId);
                    list.add(user);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static ArrayList<Role> readAllRole() {
        Connection cn = null;
        ArrayList<Role> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Roles \n"
                        + "where RoleId!=4";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int RoleID = rs.getInt("RoleId");
                    String name = rs.getString("RoleName");
     
                    Role role = new Role(RoleID, name);
                    list.add(role);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static ArrayList<Users> searchUserByName(String userName) {
        Connection cn = null;
        ArrayList<Users> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Users\n"
                        + "where RoleId !=4 and name like ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + userName + "%");
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserId");
                    String name = rs.getString("Name");
                    String password = rs.getString("Password");
                    int status = rs.getInt("Status");
                    int departmentId = rs.getInt("DepartmentId");
                    String email = rs.getString("Email");
                    int roleId = rs.getInt("RoleId");
                    Users user = new Users(userID, name, password, status, departmentId, email, roleId);
                    list.add(user);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static ArrayList<Users> filterByDepartment(String depName) {
        Connection cn = null;
        ArrayList<Users> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Users u join dbo.Department d on u.DepartmentId=d.DepartmentId\n"
                        + "where d.Name like ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + depName + "%");
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserId");
                    String name = rs.getString("Name");
                    String password = rs.getString("Password");
                    int status = rs.getInt("Status");
                    int departmentId = rs.getInt("DepartmentId");
                    String email = rs.getString("Email");
                    int roleId = rs.getInt("RoleId");
                    Users user = new Users(userID, name, password, status, departmentId, email, roleId);
                    list.add(user);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static ArrayList<Users> readAllBusiness() {
        Connection cn = null;
        ArrayList<Users> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + " from dbo.Users  where roleId=3\n";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int userID = rs.getInt("UserId");
                    String name = rs.getString("Name");
                    String password = rs.getString("Password");
                    int status = rs.getInt("Status");
                    int departmentId = rs.getInt("DepartmentId");
                    String email = rs.getString("Email");
                    int roleId = rs.getInt("RoleId");
                    Users user = new Users(userID, name, password, status, departmentId, email, roleId);
                    list.add(user);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static Users read(String email, String password) {
        Connection cn = null;
        Users user = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.Users where Email=? and Password=? COLLATE Latin1_General_CS_AS";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    int UserId = rs.getInt("UserId");
                    String Name = rs.getString("Name");
                    String Password = rs.getString("Password");
                    int Status = rs.getInt("Status");
                    int DepartmentId = rs.getInt("DepartmentId");
                    String Email = rs.getString("Email");
                    int RoleId = rs.getInt("RoleId");
                    user = new Users(UserId, Name, Password, Status, DepartmentId, Email, RoleId);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    public static Users readByToken(String token) {
        Connection cn = null;
        Users user = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Users\n"
                        + "where Cookie=? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int UserId = rs.getInt("UserId");
                    String Name = rs.getString("Name");
                    String Password = rs.getString("Password");
                    int Status = rs.getInt("Status");
                    int DepartmentId = rs.getInt("DepartmentId");
                    String Email = rs.getString("Email");
                    int RoleId = rs.getInt("RoleId");
                    user = new Users(UserId, Name, Password, Status, DepartmentId, Email, RoleId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    public static String readUserDep(int DepartmentId) {
        Connection cn = null;
        String Department = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select D.Name from Users U join Department D on U.DepartmentId=D.DepartmentId where U.DepartmentId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, DepartmentId);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    String Name = rs.getString("Name");
                    Department = Name;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return Department;
    }

    public static String readDepartmentByUserID(int DepartmentId) {
        Connection cn = null;
        String Department = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select D.Name from Users U join Department D on U.DepartmentId=D.DepartmentId where U.UserID=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, DepartmentId);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    String Name = rs.getString("Name");
                    Department = Name;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return Department;
    }

    // phan Quang
    public Users load(String email) {
        String sql = "SELECT * from Users WHERE Users.Email = ?";
        Users user = null;
        try {
            Connection conn = DBUtils.makeConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new Users();

                user.setUserId(rs.getInt("UserId"));
                user.setName(rs.getString("Name"));
                user.setPassword(rs.getString("Password"));
                user.setStatus(rs.getInt("Status"));
                user.setDepartmentId(rs.getInt("DepartmentId"));
                user.setEmail(rs.getString("Email"));
                user.setRoleId(rs.getInt("RoleId"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public static void create(Users user) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Users values(?, ?, ?, ?, ?, ?, ?, null)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, user.getUserId());
                stm.setString(2, user.getName());
                stm.setString(3, user.getPassword());
                stm.setInt(4, user.getStatus());
                stm.setInt(5, user.getDepartmentId());
                stm.setString(6, user.getEmail());
                stm.setInt(7, user.getRoleId());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
     public static void createBusiness(Users user) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "  insert into dbo.Users (UserId,Name,Password,Email,RoleId) values (?,?,?,?,?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, user.getUserId());
                stm.setString(2, user.getName());
                stm.setString(3, user.getPassword());                          
                stm.setString(4, user.getEmail());
                stm.setInt(5, user.getRoleId());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void update(Users user) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Users set Name=?, Password=?,Status=?,DepartmentId=?,Email=?,RoleId=? where UserId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, user.getName());
                stm.setString(2, user.getPassword());
                stm.setInt(3, user.getStatus());
                stm.setInt(4, user.getDepartmentId());
                stm.setString(5, user.getEmail());
                stm.setInt(6, user.getRoleId());
                stm.setInt(7, user.getUserId());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public static void changePassword(String password,int id){
       Connection cn = null;
        
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Users set Password = ? where UserId = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, password);
                stm.setInt(2, id);
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
                String sql = "delete dbo.Users where UserId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static boolean updateUserToken(String token, String email) {
        Connection cn = null;
        boolean up = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Users\n"
                        + "set Cookie=?\n"
                        + "where Email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2, email);
                int count = pst.executeUpdate();
                up = count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return up;
    }
}

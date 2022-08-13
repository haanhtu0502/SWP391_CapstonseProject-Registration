/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Category;
import DTO.Department;
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
public class DepartmentDAO {

    public static ArrayList<Department> readAll() {
        Connection cn = null;
        ArrayList<Department> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Department\n";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int departmentId = rs.getInt("DepartmentId");
                    String name = rs.getString("Name");
                    Department department = new Department(departmentId, name);
                    list.add(department);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static Department read(Object id) {
        Connection cn = null;
        Department department = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.Department where DepartmentId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    department = new Department();
                    department.setDepartmentId(rs.getInt("DepartmentId"));
                    department.setName(rs.getString("Name"));
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return department;
    }

    public static void create(Department department) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Department values(?, ?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, department.getDepartmentId());
                stm.setString(2, department.getName());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void update(Department department) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Department set Name=? where DepartmentId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, department.getName());
                stm.setInt(2, department.getDepartmentId());
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
                String sql = "delete dbo.Department where DepartmentId=?";
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

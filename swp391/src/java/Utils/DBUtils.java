/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HLong
 */
public class DBUtils {
    public static Connection makeConnection() throws Exception{
        Connection cn=null;
        String IP="3.1.206.17";
        String instanceName="EC2AMAZ-3SG5JQ9";
        String port="1433";
        String uid="sa";
        String pwd="Swp391@02";
        String db="SWP391";
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://" +IP+"\\"+ instanceName+":"+port
                 +";databasename="+db+";user="+uid+";password="+pwd;
        Class.forName(driver);
        cn=DriverManager.getConnection(url);
        return cn;
    }
}

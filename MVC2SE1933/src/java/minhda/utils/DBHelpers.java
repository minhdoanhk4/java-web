/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minhda.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author msi2k
 */
public class DBHelpers implements Serializable{
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        //1. Load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create Connection String
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1933;instanceName=SQL2019";
        //3. Open Connection
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        
        return con;
    }
    
}

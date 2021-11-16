/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author albfre
 */
public class Conexion {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/transacciones?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER="root";
    private static final String JDBC_PASSWORD="admin";
    
    
    public static Connection getConnection() throws SQLException{
      return DriverManager.getConnection  (JDBC_URL, JDBC_USER, JDBC_PASSWORD);
   }
   public static void close(ResultSet rs) throws SQLException{
       rs.close();
   }
   public static void close(Statement smt) throws SQLException{
       smt.close();
   }
   public static void close(Connection con) throws SQLException{
       con.close();
   }
   
}

package REPO;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnect1 {
  public static String USER = "sa";
    public static String PASSWORD = "2004";
    public static String URL = "jdbc:sqlserver://localhost:1433;databaseName=DUAN01;encrypt=true;trustServerCertificate=true; ";
     static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public static Connection getConnection(){
        Connection cn = null;
        try {
            cn= DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
      }
        public static void main(String[] args) {
        Connection cn = getConnection();
        if (cn!=null) {
            System.out.println("Connect Success");
        }else{
           System.out.println("Connect Error"); 
        }
    }
}

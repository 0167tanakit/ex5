/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author มะลิในทุ่งทานตะวัน
 */
public class studentdatabase {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
String derbyClientDriver = "org.apache.derby.jdbc.ClientDriver";
Class.forName(derbyClientDriver);
  String url = "jdbc:derby://localhost:1527/employee";
        //String url="jdbc:mysql://localhost:3306/employee?serverTimezone=UTC";
        String user = "app";
        //String user = "root";
        String passwd = "app";
        //String passwd = "root";
   Connection con = DriverManager.getConnection(url, user, passwd);
        //create statement
       
       student n1 = new student(1, "John", 3.14);
       student n2 = new student(2, "Marry", 3.57);
        insertstudentPreparedStatement(con, n1);
        insertstudentPreparedStatement(con, n2);
    
        con.close();
    }
      public static ArrayList<student> getAllstudent (Connection con) throws SQLException {
        String sql = "select * from student order by id";
        PreparedStatement ps = con.prepareStatement(sql);
        ArrayList<student> studentList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
           student n = new student();
           n.setId(rs.getInt("id"));
           n.setName(rs.getString("name"));
         n.setgpa(rs.getDouble("gpa"));
           studentList.add(n);
       }
       rs.close();
       return studentList;
       
    }
    
    public static void insertstudentPreparedStatement(Connection con, student n) throws SQLException {
       String sql = "insert into student (id, name, gpa)" + 
               " values (?,?,?)";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, n.getId());
       ps.setString(2, n.getName());
       ps.setDouble(3, n.getgpa());
       int result = ps.executeUpdate();
        //display result
        System.out.println("Insert " + result + " row");
   }
   public static void deletestudentPreparedStatement(Connection con, student n) throws SQLException {
       String sql ="delete from student where id = ?";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, n.getId());
       int result = ps.executeUpdate();
        //display result
        System.out.println("Delete " + result + " row");
   }
   public static void updatestudentgpaPreparedStatement(Connection con, student n) throws SQLException {
       String sql = "update student set gpa  = ? where id = ? ";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setDouble(1, n.getgpa());
       ps.setInt(2, n.getId());
       int result = ps.executeUpdate();
        //display result
        System.out.println("update " + result + " row");
   }
   public static void updatestudentNamePreparedStatement(Connection con, student n) throws SQLException {
       String sql = "update student set name  = ? where id = ? ";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setString(1, n.getName());
       ps.setInt(2, n.getId());
       int result = ps.executeUpdate();
        //display result
        System.out.println("update " + result + " row");
   }
   public static student getstudentByIdPreparedStatement(Connection con, int id) throws SQLException {
       student n = null;
       String sql = "select * from student where id = ?";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if (rs.next()) {
           n = new student();
           n.setId(rs.getInt("id"));
           n.setName(rs.getString("name"));
           n.setgpa(rs.getDouble("gpa"));
       }
       return n;
   }
}

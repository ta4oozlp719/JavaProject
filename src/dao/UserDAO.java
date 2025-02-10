package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConnectionPool;
import javax.naming.NamingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.SQLException;


public class UserDAO {
   public boolean insert(String uid, String jsonstr) throws NamingException,SQLException {
      Connection conn=ConnectionPool.get();
      PreparedStatement stmt=null;
      
      try {
      String sql="INSERT INTO user(id,jsonstr) VALUES(?,?)";
      stmt = conn.prepareStatement(sql);
      stmt.setString(1,uid);
      stmt.setString(2,jsonstr); 
      
      int count=stmt.executeUpdate();
      return (count==1)?true:false;
      
      
      }finally {
         
         if(stmt!=null) stmt.close();
         if(conn!=null)conn.close();
      }
   }
   public boolean exists(String uid) throws NamingException,SQLException {
      /*
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysns?serverTimezone=UTC", "root", "1111");
      */
      Connection conn=ConnectionPool.get();
      PreparedStatement stmt=null;
      ResultSet rs=null;
      try {
         String sql="SELECT id FROM user WHERE id=?";
         stmt = conn.prepareStatement(sql);
         stmt.setString(1,uid);
         rs=stmt.executeQuery();
         return rs.next();
         
         
      }finally {
         if(rs !=null) rs.close();
         if(stmt !=null)stmt.close();
         if(conn !=null)conn.close();
      }
   }
   public int login(String uid, String upass) throws NamingException,SQLException, ParseException {
      Connection conn=null;
      PreparedStatement stmt=null;
      ResultSet rs=null;
      
      try {
      String sql="SELECT jsonstr FROM user WHERE id=?";
      conn=ConnectionPool.get();
      stmt = conn.prepareStatement(sql);
      stmt.setString(1,uid);
      
      rs=stmt.executeQuery();
      if (!rs.next()) return 1;
      
      String jsonstr=rs.getString("jsonstr");
      JSONObject obj=(JSONObject)(new JSONParser()).parse(jsonstr);
      String pass=obj.get("password").toString();
      
      if(!upass.equals(pass)) return 2;
      return 0;
      
      }finally {
         if(rs !=null) rs.close();
         if(stmt !=null)stmt.close();
         if(conn !=null)conn.close();
      }
      
   }
   public boolean delete(String uid) throws NamingException,SQLException {
      /*
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysns?serverTimezone=UTC", "root", "1111");
      */
      Connection conn=ConnectionPool.get();
      PreparedStatement stmt=null;
      ResultSet rs=null;
      try {
         String sql="DELETE FROM user WHERE id=?";
         stmt = conn.prepareStatement(sql);
         stmt.setString(1,uid);
         int count=stmt.executeUpdate();
         return (count>0)?true:false;
         
         
      }finally {
         if(rs !=null) rs.close();
         if(stmt !=null)stmt.close();
         if(conn !=null)conn.close();
      }
   }
   

public String getList() throws SQLException, NamingException {
      
      Connection conn=ConnectionPool.get();
      PreparedStatement stmt=null;
      ResultSet rs=null;
      try {
         
         String sql = "SELECT*FROM user"; // ? 대신 컬럼명 사용
         stmt = conn.prepareStatement(sql);
   
         rs = stmt.executeQuery(); // executeQuery에 SQL문을 넣지 않고 실행
         
         String str="[";
         int cnt=0;
         while(rs.next()) {
            if(cnt++>0) str+=",";
            str+=rs.getString("jsonstr");
         }
         return str+"]";
         
         
         
      }finally {
         if(rs!=null) rs.close();

         if(stmt!=null) stmt.close();
         if(conn!=null) conn.close();

   
      }   
   
   }
public String get(String uid) throws NamingException,SQLException {
   /*
   Class.forName("com.mysql.jdbc.Driver");
   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysns?serverTimezone=UTC", "root", "1111");
   */
   Connection conn=ConnectionPool.get();
   PreparedStatement stmt=null;
   ResultSet rs=null;
   try {
      String sql="SELECT jsonstr FROM user WHERE id=?";
      stmt = conn.prepareStatement(sql);
      stmt.setString(1,uid);
      rs=stmt.executeQuery();
      return rs.next()? rs.getString("jsonstr") : "{}";
      
      
      
   }finally {
      if(rs !=null) rs.close();
      if(stmt !=null)stmt.close();
      if(conn !=null)conn.close();
   }
}
   
public boolean update(String uid, String jsonstr) throws NamingException,SQLException {
   Connection conn=ConnectionPool.get();
   PreparedStatement stmt=null;
   
   try {
   String sql="UPDATE user SET jsonstr=? WHERE id=?";
   stmt = conn.prepareStatement(sql);
   stmt.setString(1,jsonstr);
   stmt.setString(2,uid);
    
   
   int count=stmt.executeUpdate();
   return (count==1)?true:false;
   
   
   }finally {
      
      if(stmt!=null) stmt.close();
      if(conn!=null)conn.close();
   }
}



}
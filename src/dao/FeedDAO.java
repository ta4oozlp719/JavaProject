package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.ConnectionPool;

public class FeedDAO {
   public boolean insert(String jsonstr) throws NamingException,SQLException, ParseException {
      Connection conn=ConnectionPool.get();
      PreparedStatement stmt=null;
      ResultSet rs=null;
      
      try {
         synchronized(this) {
            String sql="SELECT no FROM feed ORDER BY no DESC LIMIT 1";
            stmt=conn.prepareStatement(sql);
            rs=stmt.executeQuery(sql);
            
            int max=(!rs.next())?0:rs.getInt("no");
            stmt.close(); rs.close();
            
            JSONParser parser=new JSONParser();
            JSONObject jsonobj=(JSONObject)parser.parse(jsonstr);
            jsonobj.put("no",max+1);
            
            // phase 2. add "user" property --------------
            String uid=jsonobj.get("id").toString();
            
            sql="SELECT jsonstr FROM user WHERE id =?";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,uid);
            rs=stmt.executeQuery();
            
            if(rs.next()) {
               String usrstr=rs.getString("jsonstr");
               JSONObject usrobj=(JSONObject)parser.parse(usrstr);
               usrobj.remove("password");
               usrobj.remove("ts");
               jsonobj.put("user", usrobj);
            
               
            }
            stmt.close(); rs.close();
            
            
            //phase 3. insert jsonobj to the table --------------
            sql="INSERT INTO feed(no,id,jsonstr) VALUES(?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,max+1);
            stmt.setString(2,uid);
            stmt.setString(3,jsonobj.toJSONString()); 
      
            
            int count=stmt.executeUpdate();
            return (count==1)?true:false;
         }
      
      }finally {
         if(rs!=null) rs.close();

         if(stmt!=null) stmt.close();
         if(conn!=null)conn.close();
      }
   }
      public String getList() throws SQLException, NamingException {
         
         Connection conn=ConnectionPool.get();
         PreparedStatement stmt=null;
         ResultSet rs=null;
         try {
            
            String sql = "SELECT*FROM feed order by no desc"; // ? 대신 컬럼명 사용
            stmt = conn.prepareStatement(sql);
      
            rs = stmt.executeQuery(); // executeQuery에 SQL문을 넣지 않고 실행
            /*
            ArrayList<FeedObj> feeds =new ArrayList<FeedObj>();
            
            while(rs.next()) {
               feeds.add(new FeedObj(rs.getString("id"),rs.getString("content"),rs.getString("ts"),rs.getString("images")));
            }
                        
            return feeds; 
            */
            String str="[";
            int cnt=0;
            while(rs.next()) {
               if(cnt++>0) str+=",";
               str+=rs.getString("jsonstr");
               
               
            }
            return str+"]";
            
         }finally {
            if(rs!=null) stmt.close();

            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
   
      
         }
   }
      public String getGroup(String frids, String maxNo) throws SQLException, NamingException {

          Connection conn = ConnectionPool.get();
          PreparedStatement stmt = null;
          ResultSet rs = null;
          try {
              String sql = "SELECT jsonstr FROM feed WHERE id IN(" + frids + ")";
              if (maxNo != null) {
                  sql += " AND no < " + maxNo;  // WHERE 절과 조건 사이에 공백 추가
              }
              sql += " ORDER BY no DESC LIMIT 3";  // ORDER BY 절 앞뒤로 공백 추가

              stmt = conn.prepareStatement(sql);  // PreparedStatement 초기화
              rs = stmt.executeQuery();  // executeQuery() 메서드에 SQL문을 넣도록 수정

              String str = "[";
              int cnt = 0;
              while (rs.next()) {
                  if (cnt++ > 0) str += ",";
                  str += rs.getString("jsonstr");
              }
              return str + "]";

          } finally {
              if (rs != null) rs.close();  // ResultSet 닫기
              if (stmt != null) stmt.close();  // PreparedStatement 닫기
              if (conn != null) conn.close();  // Connection 닫기
          }
      }
}
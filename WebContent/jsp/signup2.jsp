<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="dao.*" %>

<%
request.setCharacterEncoding("utf-8");
String uid=request.getParameter("id");
//String upass=request.getParameter("ps");
//String uname=request.getParameter("name");

String jsonstr=request.getParameter("jsonstr");


UserDAO dao=new UserDAO();
if (dao.exists(uid)){
	out.print("EX");
	return;
}
if(dao.insert(uid,jsonstr)==true){
	session.setAttribute("id",uid);
	out.print("OK");
}
else{
	
	out.print("ER");
}
%>
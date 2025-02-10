<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>

<%
   request.setCharacterEncoding("utf-8");
   String uid=request.getParameter("id");
   
   session.setAttribute("id", uid);
%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>

<%
	String uid=request.getParameter("id");
	out.print((new FriendDAO()).getList(uid));
%>

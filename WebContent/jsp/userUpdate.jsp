<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="util.*" %>

<%
request.setCharacterEncoding("utf-8");

String uid = null, jsonstr=null, ufname=null;
byte[] ufile = null;

ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
List items = sfu.parseRequest(request);
Iterator iter = items.iterator();

while (iter.hasNext()) {
    FileItem item = (FileItem) iter.next();
    String name = item.getFieldName();

    if (item.isFormField()) {
        String value = item.getString("utf-8");

        if (name.equals("id")) uid = value;
    	if(name.equals("jsonstr")) jsonstr=value;
        
    } else {
        if (name.equals("image")) {
            ufname = item.getName();
            ufile = item.get();

            String root = application.getRealPath(java.io.File.separator);
            FileUtil.saveImage(root, ufname, ufile);
        }
    }
}

UserDAO dao = new UserDAO();

if (dao.update(uid,jsonstr)==true) {
	out.print("OK");
    //response.sendRedirect("main.jsp");
} else {
    out.print("ER");
   
}

%>

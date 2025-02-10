<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>

<%
    request.setCharacterEncoding("utf-8");
    String uid = request.getParameter("id");
    
    UserDAO dao = new UserDAO();
    
    // 회원이 존재하는지 확인
    if (dao.exists(uid)) {
        // 회원 탈퇴 처리
        if (dao.delete(uid)) {
            out.print("OK");  // 회원 탈퇴 성공
        } else {
            out.print("ER");  // 회원 탈퇴 처리 중 오류 발생
        }
    } else {
        out.print("NA");  // 회원 정보가 없는 경우
    }
%>

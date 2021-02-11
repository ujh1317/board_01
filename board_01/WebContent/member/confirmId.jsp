<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" %>

<%
	String id = request.getParameter("id");
	MemberDAO memberDAO = MemberDAO.getInstance();
	
	int check = memberDAO.confirmId(id);
%>

{
	"check":<%=check%>
}
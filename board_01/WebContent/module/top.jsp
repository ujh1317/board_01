<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>

<c:if test="${empty sessionScope.memId}">
	<input type="button" value="로그인" onclick="location='${ctxpath}/member/main.do'">
	<input type="button" value="회원가입" onclick="location='${ctxpath}/member/inputForm.do'">
</c:if>

<c:if test="${!empty sessionScope.memId}">
	<input type="button" value="로그아웃" onclick="location.href='${ctxpath}/member/logout.do'">
	<input type="button" value="회원정보변경" onclick="location.href='${ctxpath}/member/modify.do'">
</c:if>

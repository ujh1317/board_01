<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/module/header.jsp" %>
<fmt:requestEncoding value="utf-8"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardList</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<td colspan="2" align="center"><font size="7"><b>게시판</b></font></td>
		</tr>
		<tr>
			<td align="right">
				<a href="${ctxpath}/board/writeForm.do">글쓰기</a>
			</td>
		</tr>
	</table>
	
	<c:if test="${count==0}">
	<table>
		<tr>
			<td align="center">게시글이 없습니다.</td>
		</tr>
	</table>
	</c:if>
	<c:if test="${count>0}">
	<table>
		<tr>
			<td>글번호</td>
			<td>글제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="dto" items="${boardList}">
			<tr>
				<td>
					<c:out value="${number}"/>
					<c:set var="number" value="${number+1}"/>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>
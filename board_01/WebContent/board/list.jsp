<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/module/header.jsp" %>
<fmt:requestEncoding value="utf-8"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board list</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<td colspan="2" align="center"><font size="7"><b>게시판</b></font></td>
		</tr>
		<tr>
			<td align="center">
				<select>
					<option selected value="${list_category}">
					<c:if test="${empty list_category}">
						카테고리 선택
					</c:if>
				</select>
			</td>
		</tr>
		
	</table>
</body>
</html>
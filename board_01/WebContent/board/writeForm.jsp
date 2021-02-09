<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeForm</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<td colspan="2" align="center"><font size="7"><b>write</b></font></td>
		</tr>
		<tr>
			<td align="right">
				<a href="${ctxpath}/board/list.do">목록</a>
			</td>
		</tr>
	</table>
	<table border="1">
		<tr>
			<td>작성자</td>
			<td>${dto.name}<input type="hidden" name="writer" value="${dto.name}"></td>
		</tr>
		<tr>
			<td>글제목</td>
			<td><input type="text" name="title" id="title" size="60"></td>
		</tr>
		<tr>
			<td>글내용</td>
			<td><textarea name="content" id="content" rows="20" cols="90"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<%-- 원글 --%>
			<c:if test="">
				<input type="submit" value="글쓰기">
			</c:if>
			<%-- 댓글 --%>
			<c:if test="">
				<input type="submit" value="댓글쓰기">
			</c:if>
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</body>
</html>
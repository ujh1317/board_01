<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>inputForm</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form name="inputform" method="post" action="${ctxpath}/member/inputPro.do">
		<table>
			<tr>
				<td colspan="2" align="center"><font size="7"><b>게시판</b></font></td>
			</tr>
			<tr>
				<td align="right">
					<a href="${ctxpath}/board/list.do">목록</a>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>사용자ID</td>
				<td><input type="text" name="id" id="id" size="30"></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pw" id="pw" size="30"></td>
			</tr>
			<tr>
				<td>암호확인</td>
				<td><input type="password" name="pw2" id="pw2" size="30"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td>주민번호</td>
				<td>
					<input type="text" name="ju1" id="ju1" size="6" onkeyup="if(this.value.length()==6) inputform.ju2.focus();">
					- <input type="password" name="ju2" id="ju2" size="7" onkeyup="if(this.value.length()==7) inputform.email.focus();">
				</td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="email" name="email" id="email" size="30"></td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td><input type="text" name="zipcode" id="zipcode" size="6"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" name="addr" id="addr" size="70"><br>
					상세주소 : <input type="text" name="addr2" id="addr2" size="60">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
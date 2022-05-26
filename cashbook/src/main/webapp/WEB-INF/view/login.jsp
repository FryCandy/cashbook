<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>login.jsp</title>
</head>
<body class = "container">
<%
	//오류메서지 받을 변수
	String msg = "";
	//오류값 받기
		if(request.getParameter("msg")!=null){
		if(request.getParameter("msg").equals("failLogin")){
			msg ="아이디 혹은 비밀번호가 맞지 않습니다.";
		}
	}
%>
	<h1>Login Form</h1>
	<h2><%=msg%></h2>
	<!-- 입력없이 guest로 로그인 -->
	<div>
		<form action="<%=request.getContextPath()%>/LoginController" method ="post">
			<input type = "text" name = "memberId" value="geust" readonly="readonly">
			<input type = "password" name = "memberPw" value="1234" readonly="readonly">
			<button type = "submit" class="btn btn-success">바로 로그인</button>
		</form>
	</div>
	<br>
	<!-- 기존 로그인 기능 -->
	<form action="<%=request.getContextPath()%>/LoginController" method ="post">
	<table class="table table-bordered">
		<tr>
			<td>memberId</td>
			<td><input type = "text" name = "memberId"></td>
		</tr>
		<tr>
			<td>memberPw</td>
			<td><input type = "password" name = "memberPw"></td>
		</tr>
		<tr>
			<td colspan = "2">
				<button type = "submit" class="btn btn-success">로그인</button>
				<a  href="<%=request.getContextPath()%>/InsertMemberController" type ="button" class="btn btn-info">회원가입</a>
			</td>
		</tr>
	</table>
	</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>login.jsp</title>
</head>
<body class = "container">
	<h1>Login Form</h1>
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
			</td>
		</tr>
	</table>
	</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import ="vo.Member"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>InsertMemberForm</title>
</head>
<body class = "container">
<%
	//오류 메세지 받기
	String msg ="";
	if(request.getParameter("msg")!=null){
		if(request.getParameter("msg").equals("notMatch")){
			msg ="비밀번호와 확인비밀번호가 일치하지 않습니다.";
		}
	}
%>
	<h1>회원가입</h1>
	<!-- 오류메세지 출력 -->
	<h2><%=msg %></h2>
	<form method="post" action="<%=request.getContextPath()%>/InsertMemberController" class="was-validated">
		<div class="form-group">
			<table class="table table-bordered">
				<tr>
					<td>memberId</td>
					<td>
						<input type = "text" name ="memberId">
					</td>
				</tr>
				<tr>
					<td>
						비밀번호 입력
					</td>
					<td>
						<input type = "password" name ="memberPw1" required>
						<div class="invalid-feedback">Please fill out this field.</div>
					</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td>
						<input type = "password" name ="memberPw2" required>
						<div class="invalid-feedback">Please fill out this field.</div>
					</td>
				</tr>
				<tr>
					<td>name</td>
					<td>
						<input type ="text" name="name"  required>
						<div class="invalid-feedback">Please fill out this field.</div>
					</td>
				</tr>
				<tr>
					<td>gender</td>
					<td>
 						<select name = "gender">
							<option value="남">남</option>
							<option value="여">여</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>age</td>
					<td>
						<input type = "number" name="age"  required>
						<div class="invalid-feedback">Please fill out this field.</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type ="submit" class="btn btn-primary">회원가입</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
		<a  href="<%=request.getContextPath()%>/LoginController" type ="button" class="btn btn-outline-secondary">취소</a>
</body>
</html>
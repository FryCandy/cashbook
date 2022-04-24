<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import ="vo.Member"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>memberOne</title>
</head>
<body class = "container">
<%
	//컨트롤러 값 받기
	Member member = (Member)request.getAttribute("member");
%>
<h1>회원정보 상세보기</h1>
	<table class="table table-bordered">
	<tr>
		<td>memberId</td>
		<td><%=member.getMemberId() %></td>
	</tr>
	<tr>
		<td>name</td>
		<td><%=member.getName() %></td>
	</tr>
	<tr>
		<td>gender</td>
		<td><%=member.getGender() %></td>
	</tr>
	<tr>
		<td>age</td>
		<td><%=member.getAge() %></td>
	</tr>
	<tr>
		<td>createDate</td>
		<td><%=member.getCreateDate() %></td>
	</tr>
	</table>
		<a  href="<%=request.getContextPath()%>/UpdateMemberController" type ="button" class="btn btn-primary">수정</a>
		<a  href="<%=request.getContextPath()%>/DeleteMemberController" type ="button" class="btn btn-danger">회원탈퇴</a>
		<a  href="<%=request.getContextPath()%>/CashBookListByMonthController" type ="button" class="btn btn-secondary">리스트</a>
	
</body>
</html>
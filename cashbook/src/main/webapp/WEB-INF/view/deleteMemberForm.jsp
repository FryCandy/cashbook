<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import ="vo.Member"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>deleteMemberForm</title>
</head>
<body class = "container">
<%
	//오류메시지 출력 변수
	String msg ="";
	//컨트롤러 값 받기
	Member member = (Member)request.getAttribute("member");
	//오류 메세지 값 받기
	if(request.getParameter("msg")!=null){
		if(request.getParameter("msg").equals("wrongPw")){
			msg= "기존 비밀번호가 일치하지 않습니다.";
		}
	}
	
%>
	<h1>회원탈퇴</h1>
	<!-- 오류 메세지 출력 -->
	<h2><%=msg %></h2>
	<form method="post" action="<%=request.getContextPath()%>/DeleteMemberController" class="was-validated">
		<div class="form-group">
			<table class="table table-bordered">
				<tr>
					<td>memberId</td>
					<td>
						<input type = "text" name ="memberId" readonly="readonly" value="<%=request.getAttribute("memberId") %>">
					</td>
				</tr>
				<tr>
					<td>
						비밀번호 입력
					</td>
					<td>
						<input type = "password" name ="memberPw" required>
						<div class="invalid-feedback">Please fill out this field.</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type ="submit" class="btn btn-danger">삭제</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
		<a  href="<%=request.getContextPath()%>/SelectMemberOneController" type ="button" class="btn btn-outline-secondary">취소</a>
		<a  href="<%=request.getContextPath()%>/CashBookListByMonthController" type ="button" class="btn btn-secondary">리스트</a>
</body>
</html>
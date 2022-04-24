<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import ="vo.Member"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>updateMemberForm</title>
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
		else if(request.getParameter("msg").equals("notMatch")){
			msg="새로운 비밀번호와 비밀번호 확인 값이 일치하지 않습니다.";
		}
	}
	//비밀번호 변경 여부 체크
	String newPw="close";//close면 변경 x ,open이면 비밀번호 변경
	
%>
	<h1>회원정보 수정</h1>
	<!-- 오류 메세지 출력 -->
	<h2><%=msg %></h2>
	<form method="post" action="<%=request.getContextPath()%>/UpdateMemberController" class="was-validated">
		<div class="form-group">
			<table class="table table-bordered">
				<tr>
					<td>memberId</td>
					<td>
						<input type = "text" name ="memberId" readonly="readonly" value="<%=member.getMemberId() %>">
					</td>
				</tr>
				<tr>
					<td>
						현재 비밀번호 입력
					</td>
					<td>
						<input type = "password" name ="memberPw" required>
						<!-- 비밀번호 변경 버튼 -->
						<a  href="<%=request.getContextPath()%>/UpdateMemberController?newPw=open" type ="button" class="btn btn-outline-secondary">비밀번호 변경</a>
						<div class="invalid-feedback">Please fill out this field.</div>
					</td>
				</tr>
				<%
					if(request.getParameter("newPw")!=null&&request.getParameter("newPw").equals("open")){
						newPw="open"; //비밀번호 변경 체크
						
				%>
				<!-- 새로운 비밀번호 입력 여부 -->
					<tr>
						<td>새로운 비밀번호 입력</td>
						<td>
							<input type = "password" name ="newMemberPw1" value="" required>
							<a  href="<%=request.getContextPath()%>/UpdateMemberController" type ="button" class="btn btn-outline-secondary">취소</a>
							<div class="invalid-feedback">Please fill out this field.</div>
						</td>
					</tr>
					<tr>
						<td>새로운 비밀번호 확인</td>
						<td>
							<input type = "password" name ="newMemberPw2"  value="" required>
							<div class="invalid-feedback">Please fill out this field.</div>
						</td>
					</tr>
				<%
					}		
				%>
				<tr>
					<td>name</td>
					<td>
						<input type ="text" name="name" value="<%=member.getName()%>" required>
						<div class="invalid-feedback">Please fill out this field.</div>
					</td>
				</tr>
				<tr>
					<td>gender</td>
					<td>
						<select name = "gender">
							<option value="<%=member.getGender()%>"><%=member.getGender()%></option>
							<option value="남">남</option>
							<option value="여">여</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>age</td>
					<td>
						<input type = "number" name="age" value="<%=member.getAge() %>" required>
						<div class="invalid-feedback">Please fill out this field.</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<!-- 비밀번호 변경여부 체크 -->
						<input type ="hidden" name = "newPw" value="<%=newPw%>">
						<button type ="submit" class="btn btn-primary">수정</button>
					</td>
				</tr>
			</table>
		</div>
	</form>
		<a  href="<%=request.getContextPath()%>/SelectMemberOneController" type ="button" class="btn btn-outline-secondary">취소</a>
		<a  href="<%=request.getContextPath()%>/CashBookListByMonthController" type ="button" class="btn btn-secondary">리스트</a>
</body>
</html>
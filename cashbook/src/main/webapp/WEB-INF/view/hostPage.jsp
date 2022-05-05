<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<title>HostPage</title>
</head>
<body class = "container">
<h1>관리자 페이지</h1>
	<h3>로그인 회원 리스트</h3>
	<a  href="<%=request.getContextPath()%>/CashBookListByMonthController" type ="button" class="btn btn-secondary">리스트</a>
	<form method ="post" action="${pageContext.request.contextPath}/host/HostPageController">
		<c:if test="${loginList != null }">
			<table class="table table-bordered">
				<tr>
					<td>회원아이디</td>
					<td>session주소</td>
					<td>강제로그아웃</td>
				</tr>
			
			<c:forEach var="m" items="${loginList}">
				<tr>
					<td>${m.sessionId}</td>
					<td>${m.session}</td>
					<td>
						<button type="submit" class="btn btn-danger" name="sessionId" value="${m.sessionId}"> 강제로그아웃</button>					
					</td>
				</tr>
			</c:forEach> 
			</table>
		</c:if>
	<h3>로그아웃될 회원 session 리스트</h3>
		<c:if test="${overlapList != null }">
			<table class="table table-bordered">
				<tr>
					<td>회원아이디</td>
					<td>session주소</td>
				</tr>
			
			<c:forEach var="m" items="${overlapList}">
				<tr>
					<td>${m.sessionId}</td>
					<td>${m.session}</td>
					</td>
				</tr>
			</c:forEach> 
			</table>
		</c:if>
	</form>
</body>
</html>
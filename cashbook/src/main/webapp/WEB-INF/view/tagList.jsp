<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder" %>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>tagList</title>
</head>
<body class = "container">
<%
	List<Map<String,Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
%>
	<h1>tag List By Rank</h1>
	<!-- 검색 기능 추가 -->
	<form method ="post" action="<%=request.getContextPath() %>/TagListController">
	<div>수입/지출별 검색 :
		<select name ="kind">
		<%
			if(!request.getAttribute("kind").equals("")){
		%>
			<option value ="<%=request.getAttribute("kind") %>"><%=request.getAttribute("kind") %>
		<%
			}
		%>
			<option value = "">전체</option>
			<option value ="수입">수입</option>
			<option value ="지출">지출</option>
		</select>
	날짜별 검색 :
	<input type ="date" name = "beginDate" value ="<%=request.getAttribute("beginDate")%>"> ~
	<input type ="date" name = "endDate" value="<%=request.getAttribute("endDate")%>">
	<button type ="submit">검색</button>
	</div>
	</form>
	<table class="table table-bordered">
		<tr>
			<th>rank</th>
			<th>tag</th>
			<th>count</th>
		</tr>
		<%
			for(Map<String,Object> map : list){
		%>
				<tr>
					<td><%=map.get("rank") %></td>
					<td><a href="<%=request.getContextPath()%>/CashBookListByTagController?tag=<%=map.get("tag") %>"><%=map.get("tag") %></a></td>
					<td><%=map.get("count") %></td>
				</tr>
		<%
			}
		%>		
		<a  href="<%=request.getContextPath()%>/CashBookListByMonthController" type ="button" class="btn btn-secondary">리스트</a>
		
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<title>CashBookListByMonth</title>
</head>
<body class = "container">
<%
	int year = (int)request.getAttribute("year");
	int month = (int)request.getAttribute("month");
%>
	<h2><%=year%>년 <%=month %>월</h2>
	<a href = "<%=request.getContextPath()%>/CashBookListByMonthController?year=<%=year%>&month=<%=month-1%>">이전</a>
	<a href = "<%=request.getContextPath()%>/CashBookListByMonthController?year=<%=year%>&month=<%=month+1%>">다음</a>
	<table class="table table-bordered">
		<tr>
			<th>day</th>
			<th>kind</th>
			<th>cash</th>
		</tr>
		<%
			List<Map<String,Object>> cashbookList = (List<Map<String,Object>>)request.getAttribute("cashbookList");
			for (Map m : cashbookList){
		%>
				<tr>
					<th><%=m.get("day") %></th>
					<th><%=m.get("kind") %></th>
					<th><%=m.get("cash") %></th>
				</tr>
		<%
			}
		%>
	</table>
	
</body>
</html>
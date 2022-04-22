<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<title>CashBookListByTag</title>
</head>
<body class = "container">
<%
	//컨트롤러 값 받기
	List<Map<String,Object>> cashBookList = (List<Map<String,Object>>)request.getAttribute("list");
%>
	<h1><%=cashBookList.get(1).get("tag") %>태그 List</h1>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>tag</th>
				<th>kind</th>
				<th>cash</th>
				<th>cashDate</th>
				<th>memo</th>
				<th>상세보기</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					for (Map<String,Object> map : cashBookList){
				%>
					<tr>
							<td><%=map.get("tag") %></td>
							<td><%=map.get("kind") %></td>
							<td><%=map.get("cash") %></td>
							<td><%=map.get("cashDate") %></td>
							<td><%=map.get("memo") %></td>
							<td>
								<a href="<%=request.getContextPath()%>/CashBookOneController?cashBookNo=<%=map.get("cashBookNo")%>">보기</a>
								<a href="<%=request.getContextPath()%>/UpdateCashBookController?cashBookNo=<%=map.get("cashBookNo")%>" >수정</a>
								<a  href="<%=request.getContextPath()%>/DeleteCashBookController?cashBookNo=<%=map.get("cashBookNo")%>" >삭제</a>
							</td>
						
					</tr>
				<%
					}
				%>
			</tr>
		</tbody>
	</table>
	<a  href="<%=request.getContextPath()%>/CashBookListByMonthController" type ="button" class="btn btn-secondary">리스트</a>
</body>
</html>
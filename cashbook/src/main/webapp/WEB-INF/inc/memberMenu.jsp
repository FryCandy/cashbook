<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
	<!-- 회원 정보 -->
	<h4>
		${sessionLoginMember.memberId} 님 반갑습니다.
		<a href="<%=request.getContextPath()%>/SelectMemberOneController" type="button" class="btn btn-outline-primary btn-sm " >회원정보보기</a>
		<a href="<%=request.getContextPath()%>/LogoutController" type="button" class="btn btn-outline-primary btn-sm " >로그아웃</a>
		<div>
			현재 접속자 수 : ${currentCount}
			오늘(${stats.day}) 접속자 수 : ${stats.cnt} 
			전체 접속자 수 : ${totalCount}
		</div>
	</h4>
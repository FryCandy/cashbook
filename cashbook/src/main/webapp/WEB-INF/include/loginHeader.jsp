<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
	<!-- 회원 정보 -->
                <h3>${sessionLoginMember.memberId}님 반갑습니다.</h3>
                <br>
                <h4>
                	<a href="${pageContext.request.contextPath}/member/selectMemberOneController" type="button" class="btn btn-outline-primary btn-sm " >회원정보보기</a>
					<a href="${pageContext.request.contextPath}/member/logoutController" type="button" class="btn btn-outline-primary btn-sm " >로그아웃</a>
                </h4>
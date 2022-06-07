<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>cashBook :Home</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/assets/css/bootstrap.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/assets/vendors/iconly/bold.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/assets/css/app.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/template/assets/images/favicon.svg" type="image/x-icon">
</head>

<!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
<body>
    <div id="app">
    	<!-- 좌측 사이드바 부분 include -->
        <div id="sidebar" class="active">
        	<div id="includeSidebar" class="sidebar-wrapper active">
        	</div>
        </div>
        
        <!-- 메인부분 시작 -->
        <div id="main">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>

			<!-- 로그인 정보 부분 -->
            <div id="loginHeader" class="page-heading"></div>
            
            <div class="page-content">
                <section class="row">
                    <div class="col-9 col-lg-9">
                        <div class="row">
                            <div class="col-4 col-lg-4 col-md-4">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon purple">
                                                    <i class="iconly-boldShow"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">현재접속자 수</h6>
                                                <h6 class="font-extrabold mb-0">${currentCount}</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4 col-lg-4 col-md-4">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon blue">
                                                    <i class="iconly-boldProfile"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">오늘 접속자 수</h6>
                                                <h6 class="font-extrabold mb-0">${stats.cnt}</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4 col-lg-4 col-md-4">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon green">
                                                    <i class="iconly-boldAdd-User"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">전체접속자수</h6>
                                                <h6 class="font-extrabold mb-0">${totalCount}</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <!-- 가계부 달력 표시 부분 -->
                        <div class="row">
	                        <div class="col-12">
	                                <div class="card">
	                                    <div class="card-header">
	                                        <h4>월간 가계부 ${year}년 ${month}월</h4>
											<p class="text-right">
												<!-- 페이징부분 --> 
												<a href = "${pageContext.request.contextPath}/CashBookListByMonthController?year=${year}&month=${month-1}">이전달</a>
												<a href = "${pageContext.request.contextPath}//CashBookListByMonthController?year=${year}&month=${month}">다음달</a>
												<!-- 태그 부분  -->
												<a href = "${pageContext.request.contextPath}//TagListController?year=${year}&month=${month}&today=${today}" type = "button" class="btn btn-outline-success">TagList</a>
											</p>
	                                    </div>
	                                    <div class="card-body">
	                                        <div class="table-responsive">
	                                            <table class="table table-hover table-lg">
                                             		<thead>
														<tr>
															<th class="text-danger">일</th>
															<th>월</th>
															<th>화</th>
															<th>수</th>
															<th>목</th>
															<th>금</th>
															<th class="text-primary">토</th>
														</tr>
													</thead>
	                                                <tbody>
														<tr>
															<c:forEach begin="1" end="${totalTd}" varStatus="status" >
																<c:if test="${(status.count-startBlank)>0 && (status.count-startBlank)<= endDay}">
																		<c:set var="textColor" value=""/><!-- 반목문이 반복될때마다 색 초기화 -->	
																	<c:if test="${status.count%7==1}"><!--일요일엔 색 빨간색  -->
																		<c:set var="textColor" value="text-danger"/>	
																	</c:if>
																	<c:if test="${status.count%7==0}"><!--토요일에 색 파란색  -->
																		<c:set var="textColor" value="text-primary"/>	
																	</c:if>
																	<td class="${textColor}">
																		${status.count-startBlank}<!-- 날짜출력 -->
																		<!-- 추가입력 버튼 -->
																		<a href="${pageContext.request.contextPath}/InsertCashBookController?year=${year}&month=${month}&day=${status.count-startBlank}" class="btn btn-light btn-sm">입력</a>
																		<!-- 해당 날짜의 cashBook 목록 출력 -->
																		<c:forEach var="m" items="${cashBookList}">
																			<c:if test="${(status.count-startBlank)==m.day}">
																				<!-- 날짜별 상세보기 버튼 -->
																				<div>
																					<a href="${pageContext.request.contextPath}/member/cashBookOneController?cashBookNo=${m.cashBookNo}">
																						[${m.kind}]
																						${m.cash}원
																						${m.memo}
																					</a>
																				</div>
																			</c:if>
																		</c:forEach>
																	</td>
																</c:if>
																<!-- 빈칸에 해당되는 날짜의 경우 -->
																<c:if test="${status.count<=startBlank || (status.count-startBlank)> endDay}">
																	<td>&nbsp;</td><!-- 빈칸출력 -->
																</c:if>
																<c:if test="${status.count<totalTd && status.count%7==0 }"><!-- 7번째 칸마다 줄바꾸기 -->
																	</tr><tr><!-- 새로운 행 추가 -->
																</c:if>
															</c:forEach>
														</tr>
													</tbody>
	                                            </table>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
                            <div class="col-12 col-xl-4">
                            	
			<div>
	</div>	

	<table class="table table-bordered">

		
	</table>
                            </div>
                            
                        </div>
                    </div>
                    <div class="col-12 col-lg-3">
                        <div class="card">
                            <div class="card-body py-4 px-5">
                                <div class="d-flex align-items-center">
                                    <div class="avatar avatar-xl">
                                        <img src="assets/images/faces/1.jpg" alt="Face 1">
                                    </div>
                                    <div class="ms-3 name">
                                        <h5 class="font-bold">John Duck</h5>
                                        <h6 class="text-muted mb-0">@johnducky</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <h4>Recent Messages</h4>
                            </div>
                            <div class="card-content pb-4">
                                <div class="recent-message d-flex px-4 py-3">
                                    <div class="avatar avatar-lg">
                                        <img src="assets/images/faces/4.jpg">
                                    </div>
                                    <div class="name ms-4">
                                        <h5 class="mb-1">Hank Schrader</h5>
                                        <h6 class="text-muted mb-0">@johnducky</h6>
                                    </div>
                                </div>
                                <div class="recent-message d-flex px-4 py-3">
                                    <div class="avatar avatar-lg">
                                        <img src="assets/images/faces/5.jpg">
                                    </div>
                                    <div class="name ms-4">
                                        <h5 class="mb-1">Dean Winchester</h5>
                                        <h6 class="text-muted mb-0">@imdean</h6>
                                    </div>
                                </div>
                                <div class="recent-message d-flex px-4 py-3">
                                    <div class="avatar avatar-lg">
                                        <img src="assets/images/faces/1.jpg">
                                    </div>
                                    <div class="name ms-4">
                                        <h5 class="mb-1">John Dodol</h5>
                                        <h6 class="text-muted mb-0">@dodoljohn</h6>
                                    </div>
                                </div>
                                <div class="px-4">
                                    <button class='btn btn-block btn-xl btn-light-primary font-bold mt-3'>Start
                                        Conversation</button>
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <h4>Visitors Profile</h4>
                            </div>
                            <div class="card-body">
                                <div id="chart-visitors-profile"></div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

            <footer>
                <div class="footer clearfix mb-0 text-muted">
                    <div class="float-start">
                        <p>2021 &copy; Mazer</p>
                    </div>
                    <div class="float-end">
                        <p>Crafted with <span class="text-danger"><i class="bi bi-heart"></i></span> by <a
                                href="http://ahmadsaugi.com">A. Saugi</a></p>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script type="text/javascript">
    //좌측 사이드바 include
    	$("#includeSidebar").load('${pageContext.request.contextPath}/include/sidebarController');
	    $("#loginHeader").load('${pageContext.request.contextPath}/include/loginHeaderController');
    //로그인정보 include
    </script>
    <script src="${pageContext.request.contextPath}/template/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/template/assets/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/template/assets/vendors/apexcharts/apexcharts.js"></script>
    <script src="${pageContext.request.contextPath}/template/assets/js/pages/dashboard.js"></script>
    <script src="${pageContext.request.contextPath}/template/assets/js/main.js"></script>
</body>

</html>
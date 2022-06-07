<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>cashBook : login</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/assets/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/assets/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/assets/css/pages/auth.css">
</head>

<body>
    <div id="auth">
        <div class="row h-150">
            <div class="col-lg-8 col-12">
                <div id="auth-left">
                    <div class="auth-logo">
                        <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/template/assets/images/logo/cashBookLogo.PNG" alt="logo"></a>
                    </div>
                    <h2 class="auth-title">로그인</h2>
		<form action="${pageContext.request.contextPath}/all/loginController" method ="post">
			<input type = "text" name = "memberId" value="admin" readonly="readonly">
			<input type = "password" name = "memberPw" value="1234" readonly="readonly">
			<button type = "submit" class="btn btn-success">관리자 아이디로 로그인</button>
		</form>
                    <p class="auth-subtitle mb-5"></p>
                    <h2>${msg}</h2>

                    <form action="${pageContext.request.contextPath}/all/loginController" method ="post">
                        <div class="form-group position-relative has-icon-left mb-4">
                            <input type="text" name ="memberId" value="guest" class="form-control form-control-xl" placeholder="Username">
                            <div class="form-control-icon">
                                <i class="bi bi-person"></i>
                            </div>
                        </div>
                        <div class="form-group position-relative has-icon-left mb-4">
                            <input type="password" name="memberPw" value="1234" class="form-control form-control-xl" placeholder="Password">
                            <div class="form-control-icon">
                                <i class="bi bi-shield-lock"></i>
                            </div>
                        </div>
                        <div class="form-check form-check-lg d-flex align-items-end">
                            <input class="form-check-input me-2" type="checkbox" value="" id="flexCheckDefault">
                            <label class="form-check-label text-gray-600" for="flexCheckDefault">
                                로그인 정보 저장(미구현)
                            </label>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block btn-lg shadow-lg mt-5">일반회원 로그인</button>
                    </form>
                    <div class="text-center mt-5 text-lg fs-4">
                        <p class="text-gray-600">계정이 없으신가요? <a href="${pageContext.request.contextPath}insertMemberController"
                                class="font-bold"> 회원가입</a></p>
<!--
                       <p><a class="font-bold" href="auth-forgot-password.html">Forgot password?</a>.</p>
-->
                    </div>
                </div>
            </div>
            <div class="col-lg-4 d-none d-lg-block">
                <div id="auth-right">

                </div>
            </div>
        </div>

    </div>
</body>

</html>
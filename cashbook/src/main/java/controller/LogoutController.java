package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/logoutController")
public class LogoutController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그아웃의 경우 현재의 세션값을 지워버리고 새로운 세션값을 갱신시키는 방법 사용
		System.out.println(request.getSession()+"<-session LogoutController");//디버깅
		request.getSession().invalidate();//session 갱신 메서드
		response.sendRedirect(request.getContextPath()+"/member/loginController");//LoginController로 돌려보냄
	}


}

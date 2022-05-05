package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;
@WebServlet("/host/HostPageController")
public class HostPageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//관리자 전용 페이지 임으로 로그인 여부와, level이 5인지 체크
	//체크 실패시 CashBookListByMonthController로 리다이렉트
	HttpSession session = request.getSession();
	//로그인 여부 확인
	if(session.getAttribute("sessionLoginMember") == null) {
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
		return;
	}
	//관리자 level인지 확인
	Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
		if(sessionLoginMember.getLevel() != 5) {
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
			return;
		}
	//체크 성공시 관리자 페이지 보여줌
		request.getRequestDispatcher("/WEB-INF/view/hostPage.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

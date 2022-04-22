package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    //로그인 폼   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 되어 있는 멤버면 CashBookByMonthController로 리다이렉트
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionMemberId") != null) {
			response.sendRedirect(request.getContextPath()+"/CashBookByMonthController");
			return;
		}
		//로그인이 되어 있지 않은 상태면 forward
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	//로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//로그인 되어 있는 멤버면 리다이랙트
	//널체크
	//요청값 처리
	String memberId = request.getParameter("memberId");
	System.out.println(memberId+"<-memberId LoginController.dopost");
	String memberPw = request.getParameter("memberPw");
	System.out.println(memberPw+"<-memberPw LoginController.dopost");
	Member member = new Member();
	member.setMemberId(memberId);
	member.setMemberPw(memberPw);
	// 모델 호출
	MemberDao memberDao = new MemberDao();
	String returnMemberId = memberDao.selectMemberByIdPw(member);
	if(returnMemberId == null) {
		//로그인 실패시 로그인 폼을 재요청
		System.out.println("failLogin");//디버깅
		response.sendRedirect(request.getContextPath()+"/LoginController?msg=failLogin");
		return;
	}
	//로그인 성공
	HttpSession session = request.getSession(); //현재 연결한 클라이언트(브라우저)에 대한 세션 값을 받아옴
	session.setAttribute("sessionMemberId", returnMemberId);
	response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}

}

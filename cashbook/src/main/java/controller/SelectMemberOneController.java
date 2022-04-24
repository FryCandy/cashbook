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
@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
		HttpSession session = request.getSession();
	    String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionMemberId == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        System.out.println("notLogin");
	        return;
	    }
	    //모델 호출
	    MemberDao memberDao = new MemberDao();
	    Member meber = memberDao.selectMemberOne(sessionMemberId);
	    request.setAttribute("member", meber);
	    request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
	}

}

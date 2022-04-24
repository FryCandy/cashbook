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

@WebServlet("/DeleteMemberController")
public class DeleteMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
		HttpSession session = request.getSession();
	    String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionMemberId == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        System.out.println("noLogin");//디버깅
	        return;
	      }
	    //id정보로 회원탈퇴 폼 호출
	    request.setAttribute("memberId", sessionMemberId);
	    request.getRequestDispatcher("/WEB-INF/view/deleteMemberForm.jsp").forward(request, response);
			}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
		HttpSession session = request.getSession();
	    String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionMemberId == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        System.out.println("noLogin");//디버깅
	        return;
	      }
		//널체크
	    if(request.getParameter("memberPw")==null||request.getParameter("memberId")==null) {
	    	//메세지와 함께 DeleteMemberController 다시 요청
	    	response.sendRedirect(request.getContextPath()+"/DeleteMemberController?msg=null");
	    	return;
	    }
	    //요청값 처리
	    Member member = new Member();
	    member.setMemberId(request.getParameter("memberId"));
	    member.setMemberPw(request.getParameter("memberPw"));
	    //디버깅
	    System.out.println(member.toString()+"DeleteMemberController.dopost");
	    //Dao에 delete 요청
	    MemberDao memberDao = new MemberDao();
	    int row = memberDao.deleteMember(member);
	    if (row==1) { //성공시 로그 아웃 후에 Logincontroller으로 돌려보냄
	    	System.out.println("삭제성공 DeleteMemberController.dopist");
	    	request.getSession().invalidate();//session 갱신 메서드-로그아웃
	    	response.sendRedirect(request.getContextPath()+"/LoginController");
	    	return;
	    }else if(row==0) {// row==0이면 영향받은 행이 없으므로 (row 기본값 -1), 삭제실패,비밀번호오류
	    	System.out.println("삭제실패 deleteMemberController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/DeleteMemberController?msg=wrongPw");
	    	return;
	    }else if (row==-1) {//row가 -1이면 sql이 작동 안함
	    	System.out.println("예외 발생 DEleteMemberController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/DeleteMemberController?msg=exception");
	    	return;
	    }
	}

}

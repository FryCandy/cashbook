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

@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 되어 있는 멤버면 CashBookListByMonthController로 리다이렉트
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionLoginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
			return;
		}
		//insertMemberForm.jsp 호출
		request.getRequestDispatcher("/WEB-INF/view/insertMemberForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("UTF-8");
		//로그인 되어 있는 멤버면 CashBookListByMonthController로 리다이렉트
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionLoginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
			return;
		}
	    //널 체크
	    if(request.getParameter("name")==null||request.getParameter("age")==null||request.getParameter("memberId")==null||request.getParameter("gender")==null) {
	    	System.out.println("null insertMembercontroller.dopost");
	    	response.sendRedirect(request.getContextPath()+"/UpdateMemberController?msg=null");//요청값에 null있으면 UpdateMemberController로 돌려보냄
	    	return;
	    }
	    //요청값 처리
	    String memberPw =null; //비밀번호가 들어갈 변수 초기화
	    if(request.getParameter("memberPw1")!=null&&request.getParameter("memberPw2")!=null&&!request.getParameter("memberPw1").equals("")&&request.getParameter("memberPw1").equals(request.getParameter("memberPw2"))) {
	    // null, 빈칸이 아닌 비밀번호가 둘이 일치한다면 memberPw에 저장
	     memberPw = request.getParameter("memberPw1");
	    }else if(request.getParameter("memberPw1")!=null&&request.getParameter("memberPw2")!=null&&!request.getParameter("memberPw1").equals("")&&!request.getParameter("memberPw1").equals(request.getParameter("memberPw2"))){
	    	// null, 빈칸은 아니지만 비밀번호가 둘이 일치하지 않는다면 msg와 함께 돌려보냄
	    	response.sendRedirect(request.getContextPath()+"/InsertMemberController?msg=notMatch");
	    	return;
	    }
	    Member member = new Member();
	    member.setMemberId(request.getParameter("memberId"));
	    member.setName(request.getParameter("name"));
	    member.setAge(Integer.parseInt(request.getParameter("age")));
	    member.setGender(request.getParameter("gender"));
	    member.setMemberPw(memberPw);
	    //디버깅
	    System.out.println(member.toString()+"<-insertMemberController.dopost");
	    //Dao에 insert 요청
	    MemberDao memberDao = new MemberDao();
	    int row = memberDao.insertMember(member);
	    if (row==1) { //성공시 Logincontroller으로 돌려보냄
	    	System.out.println("가입성공 InsertMemberController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/LoginController");
	    	return;
	    }else if(row==0) {// row==0이면 영향받은 행이 없으므로 (row 기본값 -1), 가입실패
	    	System.out.println("가입실패 insertMemberController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/InsertMemberController?msg=fail");
	    	
	    }else if (row==-1) {//row가 -1이면 sql이 작동 안함
	    	System.out.println("예외 발생 insertMemberController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/InsertMemberController?msg=exception");
	    }
	    
	    
	    
	
	}
}

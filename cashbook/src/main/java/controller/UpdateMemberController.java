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
@WebServlet("/UpdateMemberController")
public class UpdateMemberController extends HttpServlet {
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
	    //Dao 호출
	    MemberDao memberDao = new MemberDao();
	    //id정보로 DB의 상세보기 값 호출
	    Member member = new Member();
	    member  = memberDao.selectMemberOne(sessionMemberId);
	    request.setAttribute("member", member);
	    request.getRequestDispatcher("/WEB-INF/view/updateMemberForm.jsp").forward(request, response);
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("UTF-8");
		//session 값 요청
		HttpSession session = request.getSession();
	    String sessionMemberId = (String)session.getAttribute("sessionMemberId");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionMemberId == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        System.out.println("noLogin");//디버깅
	        return;
	      }
	    //널 체크
	    if(request.getParameter("name")==null||request.getParameter("age")==null||request.getParameter("memberPw")==null||request.getParameter("memberId")==null||request.getParameter("gender")==null) {
	    	System.out.println("null UpdateMembercontroller.dopost");
	    	response.sendRedirect(request.getContextPath()+"/UpdateMemberController");//요청값에 null있으면 UpdateMemberController로 돌려보냄
	    	return;
	    }
	    //form 요청 값 처리
	    Member member = new Member();
	    member.setMemberId(request.getParameter("memberId"));
	    member.setName(request.getParameter("name"));
	    member.setAge(Integer.parseInt(request.getParameter("age")));
	    member.setGender(request.getParameter("gender"));
	    member.setMemberPw(request.getParameter("memberPw"));
	    System.out.println(member.toString()+"<-UPdateMemeberController.dopost");//디버깅
	    
	    String newMemberPw="";
	    if(request.getParameter("newPw")==null) {
	    	System.out.println("1245");
	    }
	    if(request.getParameter("newPw").equals("open")){//비밀번호 변경 요청 여부 확인
	    	if(request.getParameter("newMemberPw1")!=null&&!request.getParameter("newMemberPw1").equals("")&&request.getParameter("newMemberPw1").equals(request.getParameter("newMemberPw2"))) {
	    		//새로운 비밀번호가 null,"" 이 아니고 pw1,pw2가 일치하면 newMemberPw에 저장
	    		newMemberPw=request.getParameter("newMemberPw1");
	    		System.out.println(newMemberPw+"<- newMemberPw UpdateMemberController.dopost");//디버깅
	    	}else if(request.getParameter("newMemberPw1")!=null&&!request.getParameter("newMemberPw1").equals("")&&!request.getParameter("newMemberPw1").equals(request.getParameter("newMemberPw2"))){
	    		//null, ""은 아니지만, pw1,pw2가 일치하지 않을 경우 msg와 함께 돌려보냄
	    		response.sendRedirect(request.getContextPath()+"/UpdateMemberController?msg=notMatch");
	    		return;
	    	}
	    }
	    //Dao에 update 요청
	    MemberDao memberDao = new MemberDao();
	    int row = memberDao.updateMemberByIdPw(member, newMemberPw);
	    System.out.println(row+"<-row UpdateMemberController.dopist");
	    if (row==1) { //성공시 SelectMemberOnecontroller으로 돌려보냄
	    	System.out.println("수정성공 UpdateMemberController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/SelectMemberOneController");
	    	return;
	    }else if(row==0) {// row==0이면 영향받은 행이 없으므로 (row 기본값 -1), 비밀번호 오류
	    	System.out.println("수정실패비밀번호오류 UpdateMemberController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/UpdateMemberController?msg=wrongPw");
	    	
	    }else if (row==-1) {//row가 -1이면 sql이 작동 안함
	    	System.out.println("예외 발생 UpdateMemberController.dopist");
	    	response.sendRedirect(request.getContextPath()+"/UpdateMemberController?msg=exception");
	    }
	    
	    

	    
	}
}

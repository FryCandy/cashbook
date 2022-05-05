package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		//로그인 되어 있는 멤버면 CashBookListByMonthController로 리다이렉트
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionLoginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
			return;
		}
		//로그인이 되어 있지 않은 상태면 forward
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	//로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 되어 있는 멤버면 CashBookListByMonthController로 리다이렉트
		HttpSession session = request.getSession();//현재 연결한 클라이언트(브라우저)에 대한 세션 값을 받아옴
		if(session.getAttribute("sessionLoginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
			return;
		}
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
		Member loginMember = new Member();
		loginMember = memberDao.selectMemberByIdPw(member); //DB에서 로그인 성공시 memberId와 level이 리턴
		
		//로그인 실패시 로그인 폼을 재요청
		if(loginMember == null) {
			System.out.println("failLogin");//디버깅
			response.sendRedirect(request.getContextPath()+"/LoginController?msg=failLogin");
			return;
		}
		
		//로그인 성공시 세션에 loginMember정보 저장
		session.setAttribute("sessionLoginMember",loginMember);
		
		//현재 로그인 member 보기 기능
		if((List<Map<String,Object>>)request.getServletContext().getAttribute("loginList")==null) { //loginList의 값이 null이라면
			List<Map<String,Object>> list = new ArrayList<>(); //리스트에 아이디와, session 저장
			Map<String,Object> map = new HashMap<>();
			map.put("sessionId", loginMember.getMemberId());
			map.put("session",session);
			list.add(map);
			request.getServletContext().setAttribute("loginList", list); //ServletContext()에 id와 session을 리스트로 저장
		} else { // application에 loginList가 null이 아니라면
			//이미 있는 로그인 아이디인지 체크 후 중복이라면 기존 로그인의 session과 sessionList을 overlapList에 저장
			for(Map m : (List<Map<String,Object>>)request.getServletContext().getAttribute("loginList")) {
				if((loginMember.getMemberId()).equals(m.get("sessionId"))) {//loginList에 중복된 로그인 요청이 들어오면
					//application내에 overlapList가 있는지 체크
					if((List<Map<String,Object>>)request.getServletContext().getAttribute("overlapList")==null) {//application내에 overlapList가 없다면
						List<Map<String,Object>> overlapList = new ArrayList<>(); // 새로 overlapList을 만들어서
							Map<String,Object> map = new HashMap<>();
							map.put("sessionId", m.get("sessionId"));
							map.put("session",m.get("session"));
							overlapList.add(map); //overlapList에 기존의 session과 Id를저장
						request.getServletContext().setAttribute("overlap", overlapList); //overlapList는 application 공간에 저장
					} else {
					//application내에 이미 overlapList가 있다면
					List<Map<String,Object>> overlapList = (List<Map<String,Object>>)request.getServletContext().getAttribute("overlapList"); //기존의 overlapList를 가져와서
						Map<String,Object> map = new HashMap<>();
						map.put("sessionId", loginMember.getMemberId());
						map.put("session",session);
						overlapList.add(map); //overlapList에 sessionId와 session을 추가 한후
					request.getServletContext().setAttribute("overlapList", overlapList);//overlapList을 다시 application 공간에 저장
					}
				}
			}
			//중복된 로그인이든 아니든 새로 로그인된 정보를 loginList에  추가
			//loginList가 null이 아니므로
			List<Map<String,Object>> loginList = (List<Map<String,Object>>)request.getServletContext().getAttribute("loginList"); //기존의 loginList를 가져와서
			Map<String,Object> map = new HashMap<>();
			map.put("sessionId", loginMember.getMemberId());
			map.put("session",session);
			loginList.add(map); //loginList에 sessionId와 session을 추가 한후
			request.getServletContext().setAttribute("loginList", loginList);//loginList을 다시 application 공간에 저장
		}
		//로그인 성공, CashBookListByMonthController로 redirect
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
		
		
	}

}

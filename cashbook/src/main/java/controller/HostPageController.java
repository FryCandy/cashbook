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

import vo.Member;
@WebServlet("/host/hostPageController")
public class HostPageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//관리자 전용 페이지 임으로 로그인 여부와, level이 5인지 체크
	//체크 실패시 CashBookListByMonthController로 리다이렉트
	HttpSession session = request.getSession();
	//로그인 여부 확인
	if(session.getAttribute("sessionLoginMember") == null) {
		response.sendRedirect(request.getContextPath()+"/cashBookListByMonthController");
		return;
	}
	//관리자 level인지 확인
	Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
		if(sessionLoginMember.getLevel() != 5) {
			response.sendRedirect(request.getContextPath()+"/cashBookListByMonthController");
			return;
		}
	//체크 성공시 관리자 페이지 보여줌
		request.getRequestDispatcher("/WEB-INF/view/hostPage.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 강제 로그 아웃 기능
		String sessionId = null;
		String session = null;
		if(request.getParameter("sessionId")!=null) {
			sessionId = request.getParameter("sessionId");
			System.out.println(sessionId +"<--sessionId /host/hostPageController.dopost");
		}
		List<Map<String,Object>> loginList = (List<Map<String,Object>>)request.getServletContext().getAttribute("loginList"); //기존의 loginList 호출
		for(Map m : loginList) {//loginList에서 강제 로그아웃할 회원의 session 불러오기
			if(sessionId.equals(m.get("sessionId"))){
				session = String.valueOf(m.get("session"));
				System.out.println(session +"<--session /host/hostPageController.dopost");
			}
		}
		//overlapList에 session,sessionId를 넣어 강제 로그아웃 시키는 기능
		if((List<Map<String,Object>>)request.getServletContext().getAttribute("overlapList") == null) { //application의 overlapList가 null이라면
			List<Map<String,Object>> overlapList = new ArrayList<>(); //새로운 overlapList를 만들어서
			Map<String,Object> map = new HashMap<>();
			map.put("sessionId", sessionId);
			map.put("session",session);
			overlapList.add(map); //overlapList에 sessionId와 session을 추가 한후
			request.getServletContext().setAttribute("overlapList", overlapList);//overlapList을  application 공간에 저장
		} else { //application 내에 overlapList가 존재하면
			List<Map<String,Object>> overlapList = (List<Map<String,Object>>)request.getServletContext().getAttribute("overlapList"); //기존의 overlapList를 호출
			Map<String,Object> map = new HashMap<>();
			map.put("sessionId", sessionId);
			map.put("session",session);
			overlapList.add(map); //overlapList에 sessionId와 session을 추가 한후
			request.getServletContext().setAttribute("overlapList", overlapList);//overlapList을 다시 application 공간에 저장
		}
		//완료후 HostPageController로 redirect
		response.sendRedirect(request.getContextPath()+"/host/hostPageController");
		
		
	}
}

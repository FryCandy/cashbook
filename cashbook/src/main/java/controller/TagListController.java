package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HashtagDao;
import vo.Member;
@WebServlet("/TagListController")
public class TagListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
		HttpSession session = request.getSession();
	    Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionLoginMember == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        System.out.println("noLogin");
	        return;
	      }
		//널체크
	    //-${String.format(`%02d`,month)}-${String.format(`%02d`,today)}
		if(request.getParameter("todayDate")==null) {
			System.out.println("null TagController.doGet");
			response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");//널오류 발생시 CashBookListByMonthController로 돌려보냄
			return;
		}
		//요청값 받기
		String todayDate = request.getParameter("todayDate");
		System.out.println(todayDate+"<--todayDate TagController.doGet");
		String beginDate = "2000-01-01";//검색창 시작 날짜 임의 지정 2000-01-01
		String kind =""; //kind(수입/지출) 기본값 "" 설정
		//Dao에 요청
		HashtagDao hashtagDao = new HashtagDao();
		//전체 태그 순위 리스트 요청
		List<Map<String,Object>> list = hashtagDao.selectTagRankList();
		request.setAttribute("list", list); // 전체태그 순위 리스트
		request.setAttribute("beginDate", beginDate);//beginDate를 기본값으로 보냄
		request.setAttribute("endDate", todayDate);//오늘날짜를 endDate로 보냄
		request.setAttribute("kind", kind);//kind를 기본값으로 보냄
		
		request.getRequestDispatcher("/WEB-INF/view/tagList.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
		HttpSession session = request.getSession();
	    Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionLoginMember == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        return;
	      }
		//인코딩
		request.setCharacterEncoding("UTF-8");
		//널체크
		if(request.getParameter("kind")==null||request.getParameter("beginDate")==null||request.getParameter("endDate")==null) {
			System.out.println("null TagController.doPost");
			response.sendRedirect(request.getContextPath()+"/WEB-INF/view/tagList.jsp");//널오류 발생시 taglist.jsp로 돌려보냄
			return;
		}
		///WEB-INF/view/tagList.jsp에서 post로 넘어온 요청값 처리
		String kind =request.getParameter("kind");
		String beginDate =request.getParameter("beginDate");
		String endDate =request.getParameter("endDate");
		//디버깅
		System.out.println(kind+"<--kind TagController.doPost");
		System.out.println(beginDate+"<--beginDate TagController.doPost");
		System.out.println(endDate+"<--endDate TagController.doPost");
		//Dao에 요청
		HashtagDao hashtagDao = new HashtagDao();
		//검색 결과에 따른 태그 순위 리스트 요청
		List<Map<String,Object>> list = hashtagDao.selectTagRankListByOption(kind, beginDate, endDate);
		request.setAttribute("list", list);//검색 결과에 따른 태그 순위리스트
		request.setAttribute("kind", kind);//검색시 입력한 kind
		request.setAttribute("beginDate", beginDate);//검색시 입력한 beginDate
		request.setAttribute("endDate", endDate);//검색시 입력한 endDate
		request.getRequestDispatcher("/WEB-INF/view/tagList.jsp").forward(request, response);
	
	
	}

}

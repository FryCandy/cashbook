package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StatsDao;
import vo.Member;
import vo.Stats;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/member/homeController")
public class HomeController extends HttpServlet {
    public HomeController() {
    }
    private StatsDao statsDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용할 Dao 호출
		statsDao = new StatsDao();
		//session에 로그인 정보 요청
		HttpSession session = request.getSession();
	    Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
		//DB에 날짜별 전체 접속자 수 모델값 요청
		Stats stats = statsDao.selectStatsOneByNow(); 
		int totalCount = statsDao.selectStatsTotalCount();
		request.setAttribute("stats", stats);
		request.setAttribute("totalCount", totalCount);
		//home.jsp로 redirect
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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

import dao.CashBookDao;
@WebServlet("/CashBookListByTagController")
public class CashBookListByTagController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//인코딩
	request.setCharacterEncoding("UTF-8");
	//session 값 요청
	HttpSession session = request.getSession();
    String sessionMemberId = (String)session.getAttribute("sessionMemberId");
    //로그인이 안되어있을 경우 LoginController로 보냄
    if(sessionMemberId == null) {
        response.sendRedirect(request.getContextPath()+"/LoginController");
        return;
      }
	if(request.getParameter("tag")==null){//널값이 들어오면 CashBookListByMonthController로 돌려보냄
		System.out.println("null CashBookListByTagController.doGet");//디버깅
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
		return;
	}
	//요청값 받기
	String tag = request.getParameter("tag");
	System.out.println(tag+"<--tag CashBookListByTagController.doGet");
	//CashBookDao에 요청
	CashBookDao cashBookDao = new CashBookDao();
	List<Map<String,Object>> list = cashBookDao.selectcashBookListbyTag(tag);
	request.setAttribute("list", list);// 태그에 따른 cashbookList 저장
	request.getRequestDispatcher("/WEB-INF/view/cashBookListByTag.jsp").forward(request, response);
	}

}

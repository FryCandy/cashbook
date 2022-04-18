package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
@WebServlet("/CashBookListByMonthController")
public class CashBookListByMonthController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1) 월별 가계부 리스트 요청 처리 분석
		//요청값이 없을 경우 오늘 날짜가 기준
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH)+1; //Calendar.month값은 -1값이다
		if(request.getParameter("year")!=null && !request.getParameter("year").equals("")) {
			year = Integer.parseInt(request.getParameter("year"));
		}
		if(request.getParameter("month")!=null && !request.getParameter("month").equals("")) {
			month = Integer.parseInt(request.getParameter("month"));
		}
		if(month==0) {
			month =12;
			year= year-1;
		}
		if(month==13) {
			month = 1;
			year= year+1;
		}
		//디버깅
		System.out.println(year +"<-year");
		System.out.println(month +"<-month");
		
		//2)모델값(월별 가계부 리스트)을 반환하는 비지니스 로직(모델) 호출
		CashbookDao cashbookDao = new CashbookDao();
		List<Map<String,Object>> cashbookList = cashbookDao.selectcashbookListbyMonth(year, month);
		request.setAttribute("cashbookList", cashbookList);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		//3)뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/CashBookListByMonth.jsp").forward(request, response);
	}

}

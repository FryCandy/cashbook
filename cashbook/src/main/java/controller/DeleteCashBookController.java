package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashBookDao;
import vo.Member;

@WebServlet("/DeleteCashBookController")
public class DeleteCashBookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 값 요청
		HttpSession session = request.getSession();
	    Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
	    //로그인이 안되어있을 경우 LoginController로 보냄
	    if(sessionLoginMember == null) {
	        response.sendRedirect(request.getContextPath()+"/LoginController");
	        return;
	      }
		//널체크
		if(request.getParameter("cashBookNo")==null) {//cashBookNo 가 null이면 CashBookListByMonthController으로 보냄
		  response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController?msg=null");
		  return;
		}
		//요청값 처리
		int cashBookNo = Integer.parseInt(request.getParameter("cashBookNo"));
		System.out.println(cashBookNo+"<-cashBookNo DeleteCashBookController");
		//Dao.CashBookDao DB에 delete 요청
		CashBookDao cashBookdao = new CashBookDao();
		cashBookdao.deleteCashBook(cashBookNo);
		//삭제 완료 후 클라리언트에게 CashBookListByMonthController를 요청
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}

	
	
	
}

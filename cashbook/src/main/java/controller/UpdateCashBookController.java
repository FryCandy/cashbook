package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashBookDao;
import vo.CashBook;
import vo.Member;

@WebServlet("/UpdateCashBookController")
public class UpdateCashBookController extends HttpServlet {
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
		System.out.println(cashBookNo+"<-cashBookNo UpdateCashBookController.doget");
		////Dao.CashBookDao DB값 요청
		CashBookDao cashBookDao = new CashBookDao();
		CashBook cashBook = new CashBook();
		cashBook= cashBookDao.selectCashBookOne(cashBookNo); //상세보기 값 요청
		request.setAttribute("cashBook", cashBook);
		request.getRequestDispatcher("/WEB-INF/view/updateCashBookForm.jsp").forward(request, response);
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
		//요청값 처리
		//request 분석
		request.setCharacterEncoding("UTF-8"); // 폼값(post)에 한글이 있는데 한글이 utf-8방식으로 인코딩 되었다.
		int cashBookNo = Integer.parseInt(request.getParameter("cashBookNo")); 
		System.out.println(cashBookNo +"<-cashBookNo UpdateCashBookController.dopost");
		String cashDate = request.getParameter("cashDate"); 
		System.out.println(cashDate +"<-cashDate updateCashBookController");
		String kind = request.getParameter("kind");
		System.out.println(kind +"<-kind updateCashBookController");
		int cash = Integer.parseInt(request.getParameter("cash"));
		System.out.println(cash +"<-cash updateCashBookController");
		String memo = request.getParameter("memo");
		System.out.println(memo +"<-memo updateCashBookController");
		//태그 값 구하기
		List<String> hashtag = new ArrayList<String>(); //tag가 들어갈 list
		String memo2 = memo.replace("#"," #"); //##방지 #을 " #"으로 바꾸는 문자열을 새로만들어서 memo2에 저장
		String[] arr = memo2.split(" "); // memo를 " "토큰으로 나눔
		for(String s : arr) {
			if(s.startsWith("#")) { //문장이 #으로 시작하면
				String temp = s.replace("#",""); //#은 모두 공백으로 바꾼후 temp에 임시 저장한 후
				if(!temp.equals("")) {//temp가 빈칸이 아니라면
				hashtag.add(temp);//리스트에 저장
				}
			}
		}
		//디버깅
		System.out.println(hashtag.size()+"hashtag.size UpdateCashBookController.dopost");
		for(String h : hashtag) {
			System.out.println(h+"<--hashtag UpdateCashBookController.dopost");
		}
		//
		CashBook cashBook = new CashBook();
		cashBook.setCashBookNo(cashBookNo);
		cashBook.setCashDate(cashDate);
		cashBook.setKind(kind);
		cashBook.setCash(cash);
		cashBook.setMemo(memo);
		
		//Dao에 모델 값 요청
		CashBookDao cashBookDao = new CashBookDao();
		cashBookDao.updateCashBook(cashBook,hashtag);
		//완료후 CashBookListByMonthController
		response.sendRedirect(request.getContextPath()+"/CashBookListByMonthController");
	}

}

package listener;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.StatsDao;
import vo.Stats;

@WebListener
public class CountListner implements HttpSessionListener {
	private StatsDao statsDao;


    public void sessionCreated(HttpSessionEvent se)  { 
    	//현재 접속자 수 --> 톰켓 안에 세션의 수
    	int currentCount = (int)se.getSession().getServletContext().getAttribute("currentCount");
    	se.getSession().getServletContext().setAttribute("currentCount", currentCount+1);
    	//날짜별 or 전체 접속자 수 --> DB이용
    	this.statsDao = new StatsDao();
    	Stats stats = statsDao.selectStatsOneByNow();
    	System.out.print(stats.toString()+"<- stats CountListner");
    	if(stats.getDay()==null) { //오늘 날짜 카운트가 없다
    		statsDao.insertStats();//오늘날짜로 카운트 1 추가
    	}else {//오늘 날짜 카운트가 있다
    		statsDao.updateStatsByNow();//오늘 날짜 카운트 1증가
    		
    	}
    	
    }
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	//세션이 사라질때 현재 접속자수 감소
    	int currentCount = (int)se.getSession().getServletContext().getAttribute("currentCount");
    	se.getSession().getServletContext().setAttribute("currentCount", currentCount-1);
    	// 접속중인 회원리스트에서 삭제
    	List<Map<String,Object>> loginList = (List<Map<String,Object>>)se.getSession().getServletContext().getAttribute("loginList"); //기존의 loginList를 가져옴
    	//loginlist에서 사라질 세션의 로그인 정보 삭제
    	for(int i = 0; i < loginList.size(); i = i+1) {
			if(loginList.get(i).get("session").equals(se.getSession())) {
				loginList.remove(i);
			}
    	}
    	se.getSession().getServletContext().setAttribute("loginList", loginList);//loginList을 다시 application 공간에 저장
    	
    }
	
}

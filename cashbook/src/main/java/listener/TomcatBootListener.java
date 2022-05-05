package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class TomcatBootListener implements ServletContextListener {

    
    public TomcatBootListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { //톰켓이꺼지고 난후
         // TODO Auto-generated method stub
    }
    @Override
    public void contextInitialized(ServletContextEvent sce)  { 
    	//현재 로그인 하고 있는 아이디를 위한 리스트
    	//중복 로그인으로 체크된 세션을 위한 리스트
    	//현재 사용자수
    	sce.getServletContext().setAttribute("currentCount", 0);
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("db드라이버로딩");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }
	
}

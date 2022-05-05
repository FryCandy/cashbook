package filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class CheckLoginOverlapFilter implements Filter {

    public CheckLoginOverlapFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session = req.getSession();
		System.out.println(session+"<-session CheckLoginOverlapFilter");
		String session1 = (String.valueOf(session)); //버그방지 형변환
		//overlapList에 있는 회원을 강제 로그아웃 시키는 기능
		if(request.getServletContext().getAttribute("overlapList")!=null){//overlapList가 null이 아니라면
			for(Map<String,Object> m : (List<Map<String,Object>>)request.getServletContext().getAttribute("overlapList")) {
				System.out.println(m.get("session")+"<-m.get(\"session\") CheckLoginOverlapFilter"); //디버깅
				System.out.println((String.valueOf(m.get("session")).equals(session1))); //디버깅
				if((String.valueOf(m.get("session")).equals(session1))) {//현재 요청한 session이 overlapList에 있는 session이라면
					m.put("session" ,"logout"); // 무한루프를 피하기위해 session 초기화
					res.sendRedirect(req.getContextPath()+"/LogoutController");//로그아웃컨트롤러로 보내서 로그아웃
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

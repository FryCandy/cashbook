package filter;

import java.io.IOException;

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

import vo.Member;

@WebFilter("/member/*")
public class CheckMemberLoginFilter implements Filter {

    public CheckMemberLoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//member/맵팅된 컨트롤러에 로그인회원만 접속가능
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession session = req.getSession();
		//로그인정보가 없으면 loginController로 Redirect
		if(session.getAttribute("sessionLoginMember")==null) {
			res.sendRedirect(req.getContextPath()+"/all/loginController?msg=noLogin");
			return;
		}
		//세션에 로그인 정보 요청
		Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
	    //level이 1이 안되는 경우loginController로 보냄
	    if((int)(sessionLoginMember.getLevel()) < 1) {
	        res.sendRedirect(req.getContextPath()+"/all/loginController?msg=needAuth");
	        return;
	      }
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

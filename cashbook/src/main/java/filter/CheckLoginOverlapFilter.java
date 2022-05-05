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
		System.out.println(session+"////1234");
		System.out.println(request.getServletContext().getAttribute(session.getAttribute("sessionMemberId")+"overlap")+"///3456");
		if(session.equals(request.getServletContext().getAttribute(session.getAttribute("sessionMemberId")+"overlap"))) {//만일 session이 (sessionMemberIdoverlap) 값과 같다면
			request.getServletContext().setAttribute(session.getAttribute("sessionMemberId")+"overlap","logout"); // (sessionMemberIdoverlap)에 임의의 값 logout을 넣고(다시 필터되는것방지)
			res.sendRedirect(req.getContextPath()+"/LogoutController");//로그아웃컨트롤러로 보내서 로그아웃
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

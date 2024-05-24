package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.UserUtils;

public class AuthenticationAdminFilter implements Filter {

	@Override
	public void destroy() {

		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;		
		String uri = req.getRequestURI();		
		
		

		boolean isLogin = uri.endsWith("login.jsp");
		boolean isLoginservlet = uri.endsWith("LoginServlet");
	
		boolean isAdminLoggedIn = UserUtils.isAdminLoggedIn(req);		

		boolean isCss= uri.endsWith("css");
		boolean isJpg= uri.endsWith("jpg");
		boolean isPng= uri.endsWith("png");
		boolean isJpeg= uri.endsWith("jepg");
		
		if(isCss||isJpg||isPng||isJpeg) {
			chain.doFilter(request, response);
			return;
		}
		
		if (!isAdminLoggedIn && !(isLogin || isLoginservlet )) {
			res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
			return; 
		}
		else if (isAdminLoggedIn && (isLogin || isLoginservlet )) {
			res.sendRedirect(req.getContextPath());
			return; 
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		
	}

}

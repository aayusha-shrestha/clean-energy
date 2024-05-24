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

import util.MessageUtil;
import util.StringUtils;
import util.UserUtils;

public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		req.setAttribute(StringUtils.ERROR_MESSAGE, MessageUtil.getErrorMessage(req));
		
		String uri = req.getRequestURI();
				
		boolean isLogin = uri.endsWith("login.jsp");
		boolean isLoginservlet = uri.endsWith("LoginServlet");
		boolean isRegister = uri.endsWith("register.jsp");
		boolean isRegisterServlet = uri.endsWith("RegisterServlet");
		boolean isAddProduct = uri.endsWith("addProduct.jsp");
		boolean isUpdateProductServlet = uri.endsWith("UpdateProductServlet");
		boolean isDeleteProductServlet = uri.endsWith("DeleteProductServlet");
		boolean isContact = uri.endsWith("contact.jsp");
		boolean isAdminMessagePage = uri.endsWith("adminMessage.jsp");
		boolean isHome =  uri.endsWith("/") || uri.isEmpty() || uri.endsWith("index.jsp");

		boolean isProduct = uri.endsWith("product");
		boolean isCart= uri.endsWith("cart");
		boolean isAdmin= uri.endsWith("admin");
		boolean isCss= uri.endsWith("css");
		boolean isJpg= uri.endsWith("jpg");
		boolean isPng= uri.endsWith("png");
		boolean isJpeg= uri.endsWith("jepg");
		
		boolean isUserLoggedIn = UserUtils.isUserLoggedIn(req);
		boolean isAdminLoggedIn = UserUtils.isAdminLoggedIn(req);	
		
		if(isAddProduct || isUpdateProductServlet || isDeleteProductServlet || isContact || isAdminMessagePage) {
			isAdmin=true;
		}
		
		if(isCss||isJpg||isPng||isJpeg) {
			chain.doFilter(request, response);
			return;
		}
		if(!isAdmin) {
			if(!isAdminLoggedIn) {
				
				if (!isUserLoggedIn && !(isLogin || isLoginservlet|| isRegister || isRegisterServlet || isHome || isProduct || isCart )) {
					res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
					return; 
				}
				else if (isUserLoggedIn && (isLogin || isLoginservlet || isRegister || isRegisterServlet)) {
					res.sendRedirect(req.getContextPath());
					return; 
				} else {
					
				}	
			}else {
				res.sendRedirect(req.getContextPath()+StringUtils.ADMIN_SERVLET);
				return; 
			}			
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}

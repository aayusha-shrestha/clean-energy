package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class UserUtils {

	public static final int getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session != null && session.getAttribute("userId") != null) ? (int) session.getAttribute("userId") : 0;
	}

	public static final String getUserName(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session != null && session.getAttribute("username") != null)? String.valueOf(session.getAttribute("username")): "";
	}

	public static final boolean isUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("userId") != null && session.getAttribute("userRole")!= null && session.getAttribute("userRole").toString().equalsIgnoreCase(StringUtils.USER_DEFAULT_ROLE);
	}
	public static final boolean isAdminLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("userId") != null && session.getAttribute("userRole")!= null && session.getAttribute("userRole").toString().equalsIgnoreCase(StringUtils.USER_ADMIN_ROLE);
	}
}

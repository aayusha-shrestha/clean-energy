package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class MessageUtil {
	public static final String getErrorMessage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String message = (request.getAttribute(StringUtils.SUCCESS_MESSAGE))!=null?request.getAttribute(StringUtils.SUCCESS_MESSAGE).toString():"";
        
        if(message.isEmpty()) {
            message = (request.getAttribute(StringUtils.ERROR_MESSAGE))!=null?request.getAttribute(StringUtils.ERROR_MESSAGE).toString():"";	
        }
        if(message.isEmpty()) {
            message = session.getAttribute("message")!=null?session.getAttribute("message").toString():"";
            session.removeAttribute("message");
        }
    	return message;
	}
	public static final void setErrorMessage(HttpServletRequest request,String message) {
		HttpSession session = request.getSession();
	    session.setAttribute("message", message );
	}
}

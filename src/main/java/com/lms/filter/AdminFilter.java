package com.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

//@WebFilter("/GetAll")
@WebFilter(urlPatterns = { "/GetAll" , "/add.jsp"})
public class AdminFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	System.out.println("Filter is being invoked");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false); // Don't create a new session if it doesn't exist

        if (session != null && session.getAttribute("isAdmin") != null && (Boolean) session.getAttribute("isAdmin")) {
            // User has admin privileges, allow access
        	System.out.println("Filter is being invoked");
            chain.doFilter(request, response);
        } else {
            // User does not have admin privileges, handle accordingly (e.g., redirect or show access denied)
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect( httpRequest.getContextPath() + "/accessDenied.jsp");
//            response.sendRedirect(request.getContextPath() + "/GetAll");
        }
    }

    // Other filter methods
}

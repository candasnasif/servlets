package com.hacettepe.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */

public class AuthFilter implements Filter {

	private FilterConfig config;

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		ServletContext context = config.getServletContext();
		HashMap<String, Account> accounts = (HashMap<String, Account>) context.getAttribute("accounts");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		Account myAccount = null;
		if (session != null && session.getAttribute("account") != null) {
		
			myAccount = (Account) session.getAttribute("account");
			myAccount = accounts.get(myAccount.getUsername());
			
		}
		String uname;
		String password;
		uname = request.getParameter("username");
		password = request.getParameter("password");
		if(uname == null && password == null) {
			uname = myAccount.getUsername();
			password = myAccount.getPassword();
		}
		int control = 0;
		if(accounts != null)
		for (String key : accounts.keySet()) {
			if ((accounts.get(key).getUsername().equals(uname) && accounts.get(key).getPassword().equals(password))
					) {
				myAccount = accounts.get(key);
				control = 1;
				break;
			}
		}
		if (control == 1) {
			 session = req.getSession(true);
			session.setAttribute("account", myAccount);
			chain.doFilter(request, response);

		}

		else {
			PrintWriter out = response.getWriter();
			out.println("Wrong username or password!");
			out.close();
			RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/address.xhtml");
			RequetsDispatcherObj.include(request, response);

		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}

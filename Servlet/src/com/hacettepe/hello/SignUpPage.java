package com.hacettepe.hello;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */

public class SignUpPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute("accounts", new HashMap<String,Account>());
		super.init();
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpPage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/address.xhtml");

		if (request.getParameter("Sign Up") != null) {
			RequetsDispatcherObj = request.getRequestDispatcher("/signup.jsp");
		}
		else if(request.getParameter("Log In") != null) {
			RequetsDispatcherObj = request.getRequestDispatcher("/Servlet1");
		}
		RequetsDispatcherObj.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("Sign Up1") != null && request.getParameter("name") != null
				&& request.getParameter("pass") != null) {
			HttpSession session = request.getSession(true);
			HashMap<String,Account> accounts = (HashMap<String,Account>) this.getServletContext().getAttribute("accounts");
			String uname = request.getParameter("name");
			String password = request.getParameter("pass");
			String rePassword = request.getParameter("pass1");
			if (password.equals(rePassword)) {
				Account newAccount = new Account(uname, password);
				accounts.put(uname, newAccount);
			}

		}
		doGet(request, response);
	}

}

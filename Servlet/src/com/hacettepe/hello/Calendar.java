package com.hacettepe.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * Servlet implementation class HelloWorld
 */

public class Calendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Integer eventID = 0;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Calendar() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		this.getServletContext().setAttribute("sharedEvents", new HashMap<String,Event>());

		super.init();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/UserInterface.jsp");
		

		 if (request.getParameter("Create New Event") != null) {
			RequetsDispatcherObj = request.getRequestDispatcher("/calendar.jsp");
		} 
		 else if (request.getParameter("Edit An Event") != null && request.getParameter("eventID") != null) {
			 eventID = Integer.valueOf(request.getParameter("eventID"));
				RequetsDispatcherObj = request.getRequestDispatcher("/updateEvent.jsp");
			} 

		RequetsDispatcherObj.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	  if (request.getParameter("Create Event") != null && request.getParameter("Title") != null
				&& request.getParameter("Location") != null && request.getParameter("Description") != null
				&& request.getParameter("dateType") != null && request.getParameter("BeginHours") != null) {
		  		HttpSession session = request.getSession();
		  		Account myAccount = (Account)session.getAttribute("account");
		  		Integer ID = (Integer)session.getAttribute("ID");
		  		if(ID == null) {
		  			ID = 1;
		  			session.setAttribute("ID", ID);
		  		}
		  		else {
		  			ID = ID + 1;
		  		}
		  		session.setAttribute("ID", ID);
				String title = request.getParameter("Title");
				String location = request.getParameter("Location");
				String description = request.getParameter("Description");
				String Date = request.getParameter("dateType");
				String timeStart = request.getParameter("BeginHours");
				String timeEnd = request.getParameter("EndHours");				
				myAccount.addEvent(new Event(ID, title, location, description,Date, timeStart, timeEnd));
				
			

		}
	  else if(request.getParameter("Delete An Event") != null && request.getParameter("eventID") != null) {
		  HttpSession session = request.getSession();
	  		Account myAccount = (Account)session.getAttribute("account");
	  		HashMap<String,Event> sharedEvents = (HashMap<String,Event>)this.getServletContext().getAttribute("sharedEvents");
	  		String delKey ="";
	  		for (String key : sharedEvents.keySet()) {
	  			String parts[] = key.split(" ");
				if(parts[0].equals(myAccount.getUsername()) && sharedEvents.get(key).getEventID() == Integer.valueOf(request.getParameter("eventID"))) {
					delKey = key ;
					break;
				}
			}
	  		sharedEvents.remove(delKey);
	  		myAccount.removeEvent(Integer.valueOf(request.getParameter("eventID")));
		  
	  }
	  else if(request.getParameter("Update Event") != null  && request.getParameter("TitleU") != null
				&& request.getParameter("LocationU") != null && request.getParameter("DescriptionU") != null
				&& request.getParameter("dateTypeU") != null && request.getParameter("BeginHoursU") != null) {
		  String upKey ="";
		  	HttpSession session = request.getSession();
	  		Account myAccount = (Account)session.getAttribute("account");
	  		String title = request.getParameter("TitleU");
			String location = request.getParameter("LocationU");
			String description = request.getParameter("DescriptionU");
			String Date = request.getParameter("dateTypeU");
			String timeStart = request.getParameter("BeginHoursU");
			String timeEnd = request.getParameter("EndHoursU");		
			Event newEvent = new Event(eventID, title, location, description,Date, timeStart, timeEnd);
			int upControl = 0;
			
			for (Integer key : myAccount.myEvents.keySet()) {
				if(key == eventID) {
					upControl = 1;
				}
			}
			if (upControl == 1) {
				myAccount.addEvent(newEvent);
			}
			HashMap<String,Event> sharedEvents = (HashMap<String,Event>)this.getServletContext().getAttribute("sharedEvents");
			int control = 0;
			for (String key : sharedEvents.keySet()) {
	  			String parts[] = key.split(" ");
				if(parts[0].equals(myAccount.getUsername()) && sharedEvents.get(key).getEventID() == eventID) {
					control = 1;
					upKey = key ;
					break;
				}
			}
			if(control == 1)
			sharedEvents.put(myAccount.getUsername()+" "+eventID, newEvent);
		  
	  }
	  else if(request.getParameter("Share An Event") != null && request.getParameter("eventID") != null) {
		  	HttpSession session = request.getSession();
	  		Account myAccount = (Account)session.getAttribute("account");
	  		for (Integer key : myAccount.myEvents.keySet()) {
				if(key == Integer.valueOf(request.getParameter("eventID"))) {
					HashMap<String,Event> sharedEvents = (HashMap<String,Event>)this.getServletContext().getAttribute("sharedEvents");
					sharedEvents.put(myAccount.getUsername() +" "+(String)request.getParameter("eventID"), myAccount.myEvents.get(key));
				}
			}
		  
	  }

	

		doGet(request, response);
	}

}

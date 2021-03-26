package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import helper.info.*;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import javax.servlet.http.*;

import helper.db.*;
import helper.info.*;


/**
 * Servlet implementation class TestServlet
 */

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MYSQLdb mySQLdb;

    /**
     * Default constructor. 
     */
    public TestServlet() {
    	super();
    	mySQLdb = new MYSQLdb();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("--->Entering  doGet() TestServlet");
		
		//HTTP erantzunaren edukian idazteko
		PrintWriter http_out = response.getWriter();
		
		//Eskaeran type parametroa irakurri
		String type = request.getParameter("type");
		
		if(type != null) {	//Bezeroak parametroak bidali ahal dituen jakiteko
			if(type.equals("registerUser")) {
				System.out.println("\tregisterUser has been called");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String username = request.getParameter("username");
				
				if(email != null && password != null && username != null) {
					System.out.println("\tExtracting request parameters: " + email + " " + password + " " + username);
					mySQLdb.setUserInfo(email, password, username);
					System.out.println("\tUpdating users table in the database");
				}
				else {
					http_out.println("Parametroak ez dira ondo bidali !");
				}
			}
			else if(type.equals("getUsername")) {
				System.out.println("\tgetUsername has been called");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String username = request.getParameter("username");
				
				if(email != null && password != null) {
					System.out.println("\tExtracting request parameters: " + email + " " + password + " " + username);
					username = mySQLdb.getUsername(email, password);
					System.out.println("\tRetrieved data from db: "+username);
					http_out.println("Aplikazioan kautotu zara: "+username);
				}
				else {
					System.out.println("Parametroak ez dira ondo bidali");
				}
			}
			else if(type.equals("registerMessage")) {
				System.out.println("\tregisterMessage has been called");
				String username = request.getParameter("username");
				String message = request.getParameter("message");
				
				if(username != null && message != null) {
					System.out.println("\tExtracting request parameters: " + username + " " + message);
					mySQLdb.setMessageInfo(message, username);
					System.out.println("\tUpdating messages table in the database");
					
					http_out.println("Ekintza ondo burutu da");
				}
				else {
					System.out.println("Parametroak ez dira ondo bidali");
				}
			}
			else if(type.equals("getAllMessages")){
				System.out.println("\tgetAllMessages has been called");
				ArrayList<MessageInfo> messageList = mySQLdb.getAllMessages();
				String format = request.getParameter("format");
				if(format != null) {
					if(format.equals("json")) {
						Gson gson = new Gson();
						String messageList_json = gson.toJson(messageList);
						System.out.println("\tmessageList_json : "+messageList_json);
						response.setContentType("application/json");
						http_out.println(messageList_json);
					}
					else if(format.equals("jsp")) {
						System.out.println("\tRedirecting the user to loginForm.jsp");
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/loginForm.jsp");
						rd.forward(request, response);
					}
					else {
						http_out.println("'format' parametroak ez du balio egokia");
					}
				}
				
			}
			else {
				http_out.println("'type' parametroaren balioa ez da zuzena !");
			}
		}
		else {
			http_out.println("Ez da 'type' parametrorik bidali !");
		}
		
		System.out.println("<---Exiting  doGet() TestServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

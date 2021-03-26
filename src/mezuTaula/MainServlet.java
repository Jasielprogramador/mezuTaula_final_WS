package mezuTaula;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.db.MYSQLdb;
import helper.info.MessageInfo;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MYSQLdb mysqLdb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
		System.out.println("Entering init() MainServlet ...");
		
		mysqLdb = new MYSQLdb();
		
		
		System.out.println("Exiting init() MainServlet ...");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//hiru funtzionalitate programatu behar ditugu:
		//1-mezuen zerrenda ateratzea
		//2-mezu bat datu basean gorde
		//3-log out 
		
		System.out.println("Entering doGet() MainServlet");
		response.setHeader("Cache-Control", "no-cache");
		
		HttpSession session = request.getSession(false);	//saioa sortuta ez badago ez sortu
		
		if(session == null) {
			System.out.println("user not logged in");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login_form.jsp");
			rd.forward(request, response);
			
			System.out.println("Redirected to login form...");
		}
		else {
			System.out.println("\tUser logged in");
			
			//servlet hau deitzen denean, "action" parametroa "logout" balioarekin bidali egiten da 
			// orduan saioa amaitu eta erabiltzailea login orri eramango da
			
			String action = request.getParameter("action");
			if(action != null) {
				if(action.equals("logout")) {
					session.invalidate();	//Para cerrar la sesion
					
					System.out.println("\tRedirecting user to the login form");
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/login_form.jsp");
					rd.forward(request, response);
				}
			}
			else {
				
				//mezuak irakurri
				ArrayList<MessageInfo> messageList = mysqLdb.getAllMessages();
				request.setAttribute("messageList", messageList);
				
				//saioaren iraungitze denbora lortu
				int inactive_interval = session.getMaxInactiveInterval();
				request.setAttribute("inactive_interval", inactive_interval);
				
				
				System.out.println("\tRedirecting user to viewMessages.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/viewMessages.jsp");
				rd.forward(request, response);
			}
		}
		
		System.out.println("Exiting doGet() MainServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

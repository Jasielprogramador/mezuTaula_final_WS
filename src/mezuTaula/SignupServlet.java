package mezuTaula;

import java.io.IOException;
import helper.info.*;
import helper.db.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SignupServlet extends HttpServlet {
	

	private MYSQLdb mySQLdb;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
       
        System.out.println("--->Entering init() SingupServlet");
        
        mySQLdb = new MYSQLdb();
        
        System.out.println("--->Exiting init() SingupServlet");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--->Entering doPost() SingupServlet");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		
		if(email != null && password != null && username != null) {
			System.out.println("\tExtracting request parameters: " + email + " " + password + " " + username);
			mySQLdb.setUserInfo(email, password, username);
			System.out.println("\tUpdating users table in the database");
			
			System.out.println("\tRedirecting the user to loginForm.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/loginForm.jsp");
			rd.forward(request, response);
			
		}
		
		System.out.println("--->Exiting doPost() SingupServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

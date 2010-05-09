package org.wslhk.servlet.captcha;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;
import java.lang.Integer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CaptchaCheck extends HttpServlet {
//String[] arrQuestion=null;
	/**
	 * Constructor of the object.
	 */
	public CaptchaCheck() {
		super();
		//System.out.println("CaptchaCheck");
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String code=request.getParameter("code");
		String sessioncode=(String)session.getAttribute("Captcha");
		request.setAttribute("code", code);
		PrintWriter out=response.getWriter();
		if(code.equals(sessioncode)){
			out.print("true");
			//request.setAttribute("msg","true");
		}else{
			out.print("false");
			//request.setAttribute("msg","false");
		}
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}


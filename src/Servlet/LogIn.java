package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import JDBC.DaoClass;
import JDBC.User;

public class LogIn extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DaoClass dao = new DaoClass();
		
			System.out.println("!!!test!!!");
		
		User user = new User();
		user.setEmail(req.getParameter("text_Email"));
		user.setPassWord(req.getParameter("text_PassWord"));
		
		try {
			String name =dao.queryUser(user);
			if(name != ""){
				System.out.println("ok");
				HttpSession session=req.getSession(true);// Servlet��  ����session����
				session.setAttribute("UserName", name);
				session.setAttribute("Email", user.getEmail());
				req.setAttribute("Email", user.getEmail());//�滻Ϊsession
				
				//��������session��arrlist�� ��ת
				RequestDispatcher reqd = req.getRequestDispatcher("jsp/MyLinks.jsp");
				reqd.forward(req, resp);
			}
			else {
				//��¼ʧ��
				req.setAttribute("erro", "���������������������");
				req.setAttribute("goto", "html/LogIn.html");
				RequestDispatcher reqd = req.getRequestDispatcher("jsp/Erro.jsp");
				reqd.forward(req, resp);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

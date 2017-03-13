package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JDBC.DaoClass;
import JDBC.User;

public class SignIn extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DaoClass dao = new DaoClass();
		try {
				if(dao.chechEmail(req.getParameter("text_Email")))
				{
				
					User user = new User();		
					user.setEmail(req.getParameter("text_Email"));
					user.setUserName(req.getParameter("text_UserName"));
					user.setPassWord(req.getParameter("text_PassWord"));
					dao.addUser(user);
					//ע��ɹ�������
					req.setAttribute("erro", "ע��ɹ�");
					req.setAttribute("goto", "html/LogIn.html");
				}
				else {
					//ע��ʧ�ܣ�����
					req.setAttribute("erro", "��������ע��");
					req.setAttribute("goto", "html/LogIn.html");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher reqd = req.getRequestDispatcher("jsp/Erro.jsp");
		reqd.forward(req, resp);
	}
	
}

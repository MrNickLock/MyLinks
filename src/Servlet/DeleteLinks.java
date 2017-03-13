package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JDBC.DaoClass;
import oracle.net.aso.i;

public class DeleteLinks extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DaoClass dao = new DaoClass();
		
		
		//����ids
				String ids = req.getParameter("ids");
				System.out.println(ids);
				//���idsΪ����,�� ȥ���ŷָ���?
				String[] arr_ids = ids.split(",");
				//��ѭ��д�����ݿ�
				for(int i=0;i<arr_ids.length;i++)
				{
					System.out.println(arr_ids[i]);
					try {
						
							dao.deleteLinks(arr_ids[i]);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				RequestDispatcher reqd = req.getRequestDispatcher("jsp/MyLinks.jsp");
				reqd.forward(req, resp);
				
	}

}

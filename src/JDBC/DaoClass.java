package JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Servlet.Update;

public class DaoClass implements Dao 
{

	@Override
	//==��ѯ����===================================================
	public ArrayList query(String Age) throws Exception 
	{
		// TODO Auto-generated method stub

		Connection conn = new DB_Conn().getConn();
		String sql = "select * from users where age = ?";
		
		PreparedStatement ppst = conn.prepareStatement(sql);
		
		ppst.setString(1, Age);
		ResultSet rs = ppst.executeQuery();//ִ�в�ѯQuery�����з���ֵ������ResultSet
		
		//���ת�� �� ArrayList ����
		/*
		 * 				          �����װ					 ����
		 * ��ѯ�����������Ϣ�� =========> ��ͬ�� user����  ========>����
		 * 
		 * 
		 */
	
		ArrayList aList = new ArrayList();
		
		while(rs.next())
		{
			//�����װ �� user ����
			User user = new User();
			user.setUserName(rs.getString(1));
			user.setPassWord(rs.getString(2));

			
			//��user��������
			aList.add(user);
			
			
		}
		
		//�ȿ����
		rs.close();
		ppst.close();
		conn.close();
		
		return aList;
	}

//==���Email�Ƿ��ظ�==================================================
	public boolean chechEmail(String Email) throws Exception 
	{
		boolean flag = true;
		Connection conn = new DB_Conn().getConn();
		String sql = "select email from users where email = ?";
		PreparedStatement ppst = conn.prepareStatement(sql);
		ppst.setString(1, Email);
		ResultSet rs = ppst.executeQuery();//ִ�в�ѯQuery�����з���ֵ������ResultSet
		
		if (rs.next()) {
			flag = false;
		}
		
		//�ȿ����
		rs.close();
		ppst.close();
		conn.close();
		
		return flag;
	}
//==������û�==========================================================
	public void addUser( User user) throws Exception
	{
		Connection conn = new DB_Conn().getConn();
		String sql = "insert into users(email,username,password) values(?,?,?)";
		PreparedStatement ppst = conn.prepareStatement(sql);
		ppst.setString(1, user.getEmail());
		ppst.setString(2, user.getUserName());
		ppst.setString(3, user.getPassWord());
		
		ppst.executeUpdate();
		
		ppst.close();
		conn.close();
	}
	
//==�û����ѯ===================================================
	public String queryUser(User user) throws Exception 
	{
		//ƴ��sql��
//		boolean flag = false;
//		Connection conn = new DB_Conn().getConn();
//		String sql = "select * from users where 1=1 ";
//	if (user.getEmail()!="") {
//		sql+="and email='"+user.getEmail()+"' ";
//	}
//	if (user.getPassWord()!="") {
//		sql+="and password='"+user.getPassWord()+"' ";
//	}
//		//PreparedStatement ppst = conn.prepareStatement(sql);
//		Statement st = conn.createStatement();
//		//ppst.setString(1, Age);
//		//ResultSet rs = ppst.executeQuery();//ִ�в�ѯQuery�����з���ֵ������ResultSet
//		ResultSet rs =st.executeQuery(sql);
//		System.out.println(rs);
//		
//		if(rs.next())
//		{
//
//			flag=true;
//		}
//		
//		//�ȿ����
//		rs.close();
//		//ppst.close();
//		st.close();
//		conn.close();
//		
//		return flag;
		
		
		// �� ����
		String flag = "";
		Connection conn = new DB_Conn().getConn();
		String sql = "select * from users where email=? and password=?";
		PreparedStatement ppst = conn.prepareStatement(sql);
		ppst.setString(1, user.getEmail());
		ppst.setString(2, user.getPassWord());
		ResultSet rs = ppst.executeQuery();//ִ�в�ѯQuery�����з���ֵ������ResultSet
		
		if(rs.next())
		{

			flag=rs.getString(2);
		}
		
		//�ȿ����
		rs.close();
		ppst.close();
		conn.close();
		
		return flag;
	}
	
	
	//��ѯlinks
	public ArrayList<Links> linksQuery(String email) throws Exception {
		
		Connection conn = new DB_Conn().getConn();
		String sql = "select linkname,linka,linkid from links where useremail =? order by linkid";
		
		PreparedStatement ppst = conn.prepareStatement(sql);
		
		ppst.setString(1, email);
		ResultSet rs = ppst.executeQuery();//ִ�в�ѯQuery�����з���ֵ������ResultSet
		
		//���ת�� �� ArrayList ����
		/*
		 * 				          �����װ					 ����
		 * ��ѯ�����������Ϣ�� =========> ��ͬ�� user����  ========>����
		 * 
		 * 
		 */
	
		ArrayList<Links> aList = new ArrayList<Links>();
		
		while(rs.next())
		{
			//�����װ �� user ����
			Links links = new Links();
			links.setLinkname(rs.getString(1));
			links.setLinka(rs.getString(2));
			links.setLinkid(rs.getString(3));
			
			//��links��������
			aList.add(links);
			System.out.println("ookkk");
			
			
		}
		
		//�ȿ����
		rs.close();
		ppst.close();
		conn.close();
		
		return aList;
		
	}
	
//==���links
	public void addLinks(String name ,String a ,String email) throws Exception {
		Connection conn = new DB_Conn().getConn();
		String sql = "insert into links(linkid,linkname,linka,useremail) values(seqlinkid.nextval,?,?,?)";
		
		PreparedStatement ppst = conn.prepareStatement(sql);
		
		ppst.setString(1, name);
		ppst.setString(2, a);
		ppst.setString(3, email);
		ppst.executeUpdate();//ִ�в�ѯQuery�����з���ֵ������ResultSet
		
		
		//�ȿ����
		
		ppst.close();
		conn.close();

	}
	//����links
	public void updateLinks(String id ,String name ,String a) throws Exception {
		System.out.println("Update");
		Connection conn = new DB_Conn().getConn();
		String sql = "update links  set linkname=?,linka=? where linkid=?";
		
		PreparedStatement ppst = conn.prepareStatement(sql);
		
		ppst.setString(1, name);
		ppst.setString(2, a);
		ppst.setString(3, id);
		ppst.executeUpdate();//ִ�в�ѯQuery�����з���ֵ������ResultSet
		
		
		//�ȿ����
		
		ppst.close();
		conn.close();

	}
	//ɾ��links
	//����links
		public void deleteLinks(String id) throws Exception {
			System.out.println("delete");
			Connection conn = new DB_Conn().getConn();
			String sql = "delete from links  where linkid=?";
			
			PreparedStatement ppst = conn.prepareStatement(sql);
			ppst.setString(1, id);
			ppst.executeUpdate();//ִ�в�ѯQuery�����з���ֵ������ResultSet
			//�ȿ����
			
			ppst.close();
			conn.close();

		}
}

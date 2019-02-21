package za.co.vzap.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import za.co.vzap.dao.MySqlGeneralDAO;
import za.co.vzap.dao.MySqlReportDAO;
import za.co.vzap.dao.concrete.DaoFactory;
import za.co.vzap.dao.interfaces.IListDAO;
import za.co.vzap.dao.interfaces.MySqlIInsertDAO;

public class MySqlConnectDAO extends DaoFactory {
	private static final String URL="jdbc:mysql://127.0.0.1:3306/";
	private static final String DATABASE="reservationsystem";
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String USER="root";
	private static final String PASSWORD="root";

	public Connection openConnection(){
		try {
			System.out.println("before class.forName()...>>>>>>>>");
			Class.forName(DRIVER).newInstance();
			System.out.println("Found Driver...>>>>>>>>");
			Connection connection=DriverManager.getConnection(URL+DATABASE, USER, PASSWORD);
			return connection;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	@Override
	public IListDAO getListDAO() {
		return new MySqlReportDAO();
		// TODO Auto-generated method stub
		
	}
	@Override
	public MySqlIInsertDAO getInsertDAO() {
		// TODO Auto-generated method stub
		return new MySqlGeneralDAO();
	}
}

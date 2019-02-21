package za.co.vzap.dao.concrete;

import java.sql.Connection;

import za.co.vzap.dao.interfaces.IListDAO;
import za.co.vzap.dao.interfaces.MySqlIInsertDAO;
import za.co.vzap.database.MySqlConnectDAO;

public abstract class DaoFactory {
	
	public abstract Connection openConnection();
	public abstract IListDAO getListDAO();
	public abstract MySqlIInsertDAO getInsertDAO();
	public static DaoFactory getDatabase(){
		return new MySqlConnectDAO();
	}
}

package core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Database {

	private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=flight";
	private String sqluser = "sa";
	private String sqlpass = "yzh1314126";
	private Connection conn; 
	private Statement stmt; 
	

	public void connect() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url, sqluser, sqlpass);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
//		conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//		conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	}
	
	public ResultSet getSelect(String sql) throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
		}
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public void setUpdate(String sql) throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
		}
		stmt.executeUpdate(sql);
	}
	
	public void setInsert(String sql) throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
		}
		stmt.executeUpdate(sql); 
	}
	
	public int setDelete(String sql) throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
		}
		int row = stmt.executeUpdate(sql);
		return row;
	}
	
	public List<String> getFlightYearOrMonth(int flag,String itemYear) throws SQLException{
		List<String> list = new ArrayList<String>();
		String sql = "select date from flight";
		
		try {
			connect();
			ResultSet rs = stmt.executeQuery(sql);
			if(flag == 1) {
				while(rs.next()) {
					String year = rs.getString("date").split("-")[0];
					if(!list.contains(year)) {
						list.add(year);
					}
				}
			}else {
				while(rs.next()) {

					if(itemYear.equals(rs.getString("date").split("-")[0])) {	
						String month = rs.getString("date").split("-")[1];
						if(!list.contains(month)) {
							list.add(month);
						}
					}
				}
			}
			return list;
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
		}
		return list;
	}
}

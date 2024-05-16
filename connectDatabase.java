package CSDL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDatabase {

	public static Connection getConnection() {		
		Connection c=null;
    try {		
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://PHUDAYY\\PHU:1433;databaseName=Java;encrypt=true;trustServerCertificate=true;";
        String userName="sa";
        String password="123456789";     
        c = DriverManager.getConnection(url, userName, password);
    } catch (Exception e) {
		e.printStackTrace();
	}
    return c;
	}
    
	public static void closeConnection(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void printInfo(Connection c) {
		try {
			if(c!=null) {
				DatabaseMetaData mtdt = c.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
				System.out.println(mtdt.getDatabaseProductVersion());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

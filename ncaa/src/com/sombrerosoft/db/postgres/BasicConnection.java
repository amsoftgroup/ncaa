package com.sombrerosoft.db.postgres;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class BasicConnection {

	private static String host = "192.168.1.210";
	private static int port = 5432;
	private static String database = "geospatial";
	private static String user = "geospatial";
	private static String password = "****";

	private static String TAG = "Database";

	//Static class instantiation
	public Connection getConnection() {
		Connection conn = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://192.168.1.210/geospatial";
			Properties props = new Properties();
			props.setProperty("user","geospatial");
			props.setProperty("password","****");
			//props.setProperty("ssl","true");
			conn = DriverManager.getConnection(url, props);

			//String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
			//Connection conn = DriverManager.getConnection(url);

			/*
				new com.amsoftgroup.dbpool.JDCConnectionDriver(
						"org.postgresql.Driver", 
						"jdbc:postgresql://" + host + ":" + port + "/" + database,
						user, 
						password);
			 */
			System.out.println("Connection Succeeded: ");

		}catch(Exception e){
			System.out.println("Connection Failed: " + e.toString());
		}
		return conn;
	}
}





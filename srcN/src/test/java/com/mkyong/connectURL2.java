package com.mkyong;

import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;

public class connectURL2 {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			// the mysql driver string
			Class.forName("com.mysql.jdbc.Driver");

			// the mysql url
			String url = "jdbc:mysql://localhost/test";

			// get the mysql database connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "shall123");

			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/terer", "root", "shall123");

			Calendar calendar = Calendar.getInstance();
			java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

			// the mysql insert statement
			String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
					+ " values (?, ?, ?, ?, ?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, "Barney");
			preparedStmt.setString(2, "Rubble");
			preparedStmt.setDate(3, startDate);
			preparedStmt.setBoolean(4, false);
			preparedStmt.setInt(5, 5000);

			// execute the preparedstatement
			preparedStmt.execute();

			// now do whatever you want to do with the connection
			// ...
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users");

			while (rs.next()) {
				System.out.println("test");
			}

			// STEP 4: Execute a query
			System.out.println("Creating table in given database...");
			stmt = con1.createStatement();
			stmt.executeUpdate("DROP TABLE test");
			String sql = "CREATE TABLE test " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
					+ " last VARCHAR(255), " + " age INTEGER, " + " PRIMARY KEY ( id ))";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			getTables(con);
			con.close();
			con1.close();
			
		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(2);
		}
	}
	 
	 
	 
	 private static HashMap getTables(Connection con) {
			
			String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
					+ " values (?, ?, ?, ?, ?)";
			HashMap cache= new HashMap<String, String>();
			
			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='TEST';");
				while (rs.next()) {
					
					
					
					System.out.println("Table bame is " + rs.getString(3));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
	 
	private static void getColumns(Connection con) {
		
		String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
				+ " values (?, ?, ?, ?, ?)";

		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES where table_schema='test';");
			while (rs.next()) {
				System.out.println("Table bame is " + rs.getString(3) +rs.getString(8 )+ rs.getString(9));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
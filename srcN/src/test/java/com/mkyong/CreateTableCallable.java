package com.mkyong;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class CreateTableCallable implements Callable<String> {
	
	private String table;
	Connection con;
	Connection con1;
	
	CreateTableCallable()
	{
		
	}
	CreateTableCallable(Connection cona, Connection cona1, String table1)
	{
		con=cona;
		con1=cona1;
		table=table1;
	}
	
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

			CreateTableCallable cc = new CreateTableCallable();
		//	HashMap cache = cc.getTables(con);

			
			
		//	createTables(cache, con1);

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

//	private static void createTables(HashMap cache, Connection con1) {
		private static void createTables(  Connection con1) {
		// get
		//

		Statement stmt;
		try {
			stmt = con1.createStatement();

		 stmt.executeUpdate("DROP TABLE test1");
			String sql = "CREATE TABLE test1 " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
					+ " last VARCHAR(255), " + " age INTEGER, " + " PRIMARY KEY ( id ))";

			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

/*	private HashMap getTables(Connection con) {

		HashMap cache = new HashMap();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES where table_schema='test';");

			while (rs.next()) {

				System.out.println("Table bame is " + rs.getString(3));
				cache = getColumns(con, rs.getString(3), cache);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cache;

	}

	private HashMap getColumns(Connection con, String table, HashMap cache) {

		String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
				+ " values (?, ?, ?, ?, ?)";

		try {
			Statement stmt = con.createStatement();
			String query1 = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'test' and TABLE_NAME='"
					+ table + "';";

			// ResultSet rs = stmt.executeQuery("SELECT * FROM
			// INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='TEST';");
			ResultSet rs = stmt.executeQuery(query1);
			ArrayList<String> colList = new ArrayList<String>();

			while (rs.next()) {
				System.out.println("Inside get columns Table bame is " + rs.getString(4) + "|" + rs.getString(8) + "|"
						+ rs.getString(9));

				colList.add(rs.getString(4) + "|" + rs.getString(8) + "|" + rs.getString(9));

			}

			cache.put(table, colList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cache;
	}*/

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("table is " + table);
		createTables(  con1);
		return table;
	}
}
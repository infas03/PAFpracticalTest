package com.PAF;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;


public class Appointment_2 {
	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/helthcare","root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String addAppointment (String USER_ID, String APP_NO, String DOCTOR_NAME, String PAYMENT)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into appointment_2(`user_id`,`app_no`,`doctor_name`,`payment`)" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, USER_ID);
			preparedStmt.setString(2, APP_NO);			
			preparedStmt.setString(3, DOCTOR_NAME);
			preparedStmt.setDouble(4, Double.parseDouble(PAYMENT));
			//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Appointment add successfully";
		}
		catch (Exception e)
		{
			 output = "Error while inserting";
			 System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteAppointment(String USER_ID)
	{
		String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting.";
		 }
		 // create a prepared statement
		 String qry = "delete from appointment_2 where user_id =?";
		 PreparedStatement preparedStmt = con.prepareStatement(qry);
		
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(USER_ID));

		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 output = "Deleted successfully";
	}
	catch (Exception e)
	{
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
	}
	return output; 
		
	}
	
	public String readAppointment_2()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User ID</th>"
					+"<th>Appointment ID</th><th>Doctor name</th>"
					+ "<th>Payment Amount</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from appointment_2";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String user_id = rs.getString("USER_ID");
				String app_no = rs.getString("APP_NO");
				String doctor_name = rs.getString("DOCTOR_NAME");
				String payment = Double.toString(rs.getDouble("PAYMENT"));
				// Add into the html table
				output += "<tr><td><input id=\"hidAppIDUpdate\" name=\"hidAppIDUpdate\" type=\"hidden\" value=\"" + user_id + "\">"
						+ user_id + "</td>";
				output += "<td>" + app_no + "</td>"; 
				output += "<td>" + doctor_name + "</td>";
				output += "<td>" + payment + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btnUpdate btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"Appointment_2.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"hidAppIDDelete\" type=\"hidden\" value=\"" + user_id + "\">"
						+ "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}

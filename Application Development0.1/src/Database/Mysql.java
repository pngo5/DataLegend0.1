package Database;


import java.sql.*;
import java.sql.DriverManager;

import Objects.BusSchedule;
import Objects.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import GUI.AlertBox;
public class Mysql {
	public static void main(String[] args) throws Exception {
		//getConnection();
		 //get();
		getBusSchedule();
	}
	
	/**
	 * This method is to retrieve the bus object and input into the table view
	 *
	 * @return
	 */
	public static void getBusSchedule() {
	
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");
			
			//This is for getting the bus count
			PreparedStatement busCount = con.prepareStatement("SELECT COUNT(bus_id) FROM schedule");
			ResultSet bCount = busCount.executeQuery();
			
			//int count = bCount.getInt(1);
			
			//This is for getting the bus schedule
			PreparedStatement stmta = con.prepareStatement("SELECT * FROM schedule WHERE BINARY bus_id AND "
					+ "depart_city AND arrival_city AND arrival_city AND arrival_city");
			ResultSet rsa = stmta.executeQuery();
			
			
			int b = rsa.getInt("bus_id ");
			
			System.out.print(b);

			
			if(rsa.next()) { 
				
           
			}
			else 
				AlertBox.display("ERROR!", "Incorrect Information!");   
			con.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static ArrayList<String> get() {
		
		try {
			Connection conn = getConnection();
			
			PreparedStatement statement = conn.prepareStatement("SELECT user_id, password, first_name, last_name, address, zip, state, email, ssn, question, answer, admin  FROM users");
			
			ResultSet result = statement.executeQuery(); 
			
			ArrayList<String> array = new ArrayList<String>();
			
			while(result.next()) {
				System.out.println(result.getString("first_name"));
				System.out.println(" ");
				System.out.println(result.getString("last_name"));
				
				
				array.add(result.getString("first_name"));
			}
			System.out.println("All records have been selected");
			
			return array;
		}catch(Exception e) {System.out.println(e);}
		return null;
	}
	
	
	
	public static void post(User u) throws Exception {
		final String user = u.getUserName();
		final String password = u.getPassword();
		final String var1 = u.getFirstName();
		final String var2 = u.getLastName();
		final String addy = u.getAddress();
		final String zip = u.getZip();
		final String state = u.getState();
		final String email = u.getEmail();
		final String ssn = u.getSsn();
		final String question = u.getSecQuestions();
		final String answer = u.getSecAnswers();
		final int admin = 0;
		
		
		try {
		Connection conn = getConnection();
		PreparedStatement posted = conn.prepareStatement("INSERT INTO users  (user_id, password, first_name, last_name, address, zip, state, email, ssn, question, answer, admin) VALUES('"+user+"','"+password+"','"+var1+"', '"+var2+"' , '"+addy+"' , '"+zip+"' , '"+state+"','"+email+"','"+ssn+"', '"+question+"' , '"+answer+"' , '"+admin+"')");
		posted.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Insert Completed12");
			System.out.println(u.getUserName());
		}
	}
	
	
	
	public static Connection getConnection() throws Exception  {
		try {
			String url = "jdbc:mysql://34.74.172.98/bus_database";
			String username = "root";
			String password = "cis3270";
			
			Connection conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("Connection successful");
			
			Statement statement = conn.createStatement();
			ResultSet rst = statement.executeQuery("select user from mysql.user;");
			
			while (rst.next()) {
				System.out.println(rst.getString(1));
			}
			
			return conn;
		}catch(Exception e) {System.out.println(e);}
		
		return null;
	}

}


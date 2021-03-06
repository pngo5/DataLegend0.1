package Database;


import java.sql.*;

import java.sql.DriverManager;

import Objects.BusSchedule;
import Objects.Tickets;
import Objects.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import GUI.AlertBox;
import GUI.Login;
import GUI.MainApplication;



public class Mysql {
	Stage window;
	static User user;
	static ObservableList<BusSchedule> data;
	static ObservableList<BusSchedule> data1;
	private TableColumn busNumber;
	private TableColumn startTime;
	private TableColumn endTime;
	private TableColumn startLocation;
	private TableColumn endLocation;
	private TableColumn passengerCount;
	public static void main(String[] args) throws Exception {
		//getConnection();
		 //get();
	
	}
	
	/**
	 * This method is to retrieve the bus object and input into the table view
	 *
	 * @return
	 */
	public static ObservableList<BusSchedule> getBusSchedule() {
	
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");		
			data = FXCollections.observableArrayList();			
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM schedule");
		
			while(rs.next()) {
				data.add(new BusSchedule(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(6)));
			}
			return data;
	
			
	}catch(Exception e) {
		System.out.print(e);
	}
		return null;
	}
	
	
	public static User getUserObject() {
		
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");		
						
			ResultSet rs = con.createStatement().executeQuery("select users.user_id, users.password,"
					+ " users.first_name, users.last_name, users.address, users.zip, users.state, users.email, users.zip, "
					+ "users.ssn, users.question, users.answer, users.admin;");
			
		
			while(rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getInt(12));
			}
			return user;
	
			
	}catch(Exception e) {
		System.out.print(e);
	}
		return null;
		
	}
	
	
	
	
	

	/**
	 * 
	 * 
	 * @return
	 */
	
	public static ObservableList<BusSchedule> getUserBookingSchedule() {
		final String uID= Login.username;
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");		
			data1 = FXCollections.observableArrayList();	
			//
			//TODO
			ResultSet rs = con.createStatement().executeQuery("select booking.user_id, schedule.* FROM booking INNER JOIN schedule ON (schedule.bus_id = booking.bus_id) AND (booking.user_id = '"+uID+"');");
			
		
			while(rs.next()) {

			data1.add(new BusSchedule(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
	
			}
			return data1;
	}catch(Exception e) {
		System.out.print(e);
	}
		return null;
	}
	
	public static void userDeleteBus(String s, String a) {
		/**
		private int busID;
		private String departCity;
		private String arrivalCity;
		private String departTime;
		private String arrivalTime;
		private int passengerCount;
		 */
			String busID = s;
			String uID =a;
			
			try {
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement("DELETE FROM booking WHERE user_id= '"+uID+"' AND bus_id='"+busID+"';");
			posted.executeUpdate();
			}catch(Exception e) {System.out.println(e);}
			finally {
				System.out.println("Insert Completed");
				
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
	public static void post(BusSchedule bs) throws Exception{
		final String busID= bs.getBusID();
		final String departCity=bs.getDepartCity();
		final String arrivalCity=bs.getArrivalCity();
		final String departTime=bs.getDepartCity();
		final String arrivalTime=bs.getArrivalTime();
		final int passengerCount=bs.getPassengerCount();
		
		try {
		Connection conn = getConnection();
		PreparedStatement posted = conn.prepareStatement("INSERT INTO schedule  (bus_id, depart_city, arrival_city, depart_time, arrival_time, passenger_count) VALUES('"+busID+"','"+departCity+"','"+arrivalCity+"', '"+departTime+"' , '"+arrivalTime+"' , '"+passengerCount+"')");
		posted.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Insert Completed12");
			System.out.println(bs.getBusID());
		}
	}
	/**
	 * This gets the admin to add the table to 
	 * 
	 * @param s
	 */
	public static void adminUpdateBus(BusSchedule s) {
	/**
	 * private int busID;
	private String departCity;
	private String arrivalCity;
	private String departTime;
	private String arrivalTime;
	private int passengerCount;
	 */
		final String busID = s.getBusID();
		final String departCity = s.getDepartCity();
		final String arrivalCity = s.getArrivalCity();
		final String departTime = s.getDepartTime();
		final String arrivalTime = s.getArrivalTime();
		final int passengerCount = s.getPassengerCount();
		
		try {
		Connection conn = getConnection();
		PreparedStatement posted = conn.prepareStatement("INSERT INTO schedule  (bus_id, depart_city, arrival_city, depart_time ,"
				+ " arrival_time, passenger_count) VALUES('"+busID+"','"+departCity+"','"+arrivalCity+"', '"+departTime+"' ,"
						+ " '"+arrivalTime+"' , '"+passengerCount+"')");
		posted.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Insert Completed");
			System.out.println(s.getBusID());
		}
		
	}
	
	public static void userUpdateBus(String a, String b) {
		/**
		 * private int busID;
		private String departCity;
		private String arrivalCity;
		private String departTime;
		private String arrivalTime;
		private int passengerCount;
		 */
			final String a1 = a;
			final String a2 =b;
			
			
			
			try {
			Connection conn = getConnection();

			PreparedStatement posted = conn.prepareStatement("INSERT INTO booking  (bus_id, user_id) VALUES('"+a1+"','"+a2+"')");
			
			posted.executeUpdate();
			
			
			}catch(Exception e) {System.out.println(e);}
			finally {
				System.out.println("Insert Completed");
			}
			
		}

	
	
	public static void adminDeleteBus(String s) {
		/**
		private int busID;
		private String departCity;
		private String arrivalCity;
		private String departTime;
		private String arrivalTime;
		private int passengerCount;
		 */
			String busID = s;
			
			try {
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement("DELETE FROM schedule WHERE bus_id ='"+busID+"'");
			posted.executeUpdate();
			}catch(Exception e) {System.out.println(e);}
			finally {
				System.out.println("Insert Completed");
				
			}
			
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
	public static void  nothingPost(User w) throws Exception {
		final String user = w.getUserName();
		final String password = w.getPassword();
		final String var1 = w.getFirstName();
		final String var2 = w.getLastName();
		final String addy = w.getAddress();
		final String zip = w.getZip();
		final String state = w.getState();
		final String email = w.getEmail();
		final String ssn = w.getSsn();
		final String question = w.getSecQuestions();
		final String answer = w.getSecAnswers();
		try {
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement("INSERT INTO users  (user_id, password, first_name, last_name, address, zip, state, email, ssn, question, answer, admin) VALUES('"+user+"','"+password+"','"+var1+"', '"+var2+"' , '"+addy+"' , '"+zip+"' , '"+state+"','"+email+"','"+ssn+"', '"+question+"' , '"+answer+"')");
			posted.executeUpdate();
			}catch(Exception e) {System.out.println(e);}
			finally {
				System.out.println("Insert Completed12");
				System.out.println(w.getUserName());
			}
		}
	public static boolean checkMemberID(String 
			userName) throws SQLException, ClassNotFoundException,SQLIntegrityConstraintViolationException {
		Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery("SELECT * FROM users WHERE user_Id='"+userName+"'");
		return rst.next();
	}
	
	
	public static boolean checkBusID(String busID) 
			throws SQLException, ClassNotFoundException,SQLIntegrityConstraintViolationException{
		Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery("SELECT * FROM schedule WHERE bus_id='"+busID+"'");
		return rst.next();
	}
	
	public static boolean checkUserBUS(String busid, String userid) 
			throws SQLException, ClassNotFoundException,SQLIntegrityConstraintViolationException{
		Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery("SELECT * FROM booking WHERE bus_id='"+busid+"' AND bus_id='"+userid+"'");
		return rst.next();
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


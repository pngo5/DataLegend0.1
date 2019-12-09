package GUI;

/**
 * Creating objects for our bus system
 * @author abdulhakimdarman
 *
 */

import java.sql.Date;

public class BusSystem {
	
	private long BusNumber;
	private String startTime;
	private String endTime;
	private String startLocation;
	private String endLocation;
	private int passengerCount;
	
	public long getBusNumber() {
		return BusNumber;
	}


	public void setBusNumber(long busNumber) {
		BusNumber = busNumber;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getStartLocation() {
		return startLocation;
	}


	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}


	public String getEndLocation() {
		return endLocation;
	}


	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}


	public double getpassengerCount() {
		return passengerCount;
	}


	public void setpassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}
	
	public BusSystem() {
		BusNumber = 0;
		startTime = " 1 AM";
		startLocation = "Atl";
		endLocation = "Washington D.C";
		passengerCount = 0;
	}
	
	
	public BusSystem(long BusNumber, String startTime,String endTime, 
			String startLocation, String endLocation, int passengerCount) {
		this.BusNumber = BusNumber;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.passengerCount = passengerCount;
	}
	
	

}

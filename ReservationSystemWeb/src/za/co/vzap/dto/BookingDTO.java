package za.co.vzap.dto;

import java.io.Serializable;

public class BookingDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startDate;
	private String endDate;
	private String booked;
	private String roomName;
	private String clientName;//meant to be clientID
	private String clientSurname;
	private String meetingDescription;
	private int noOfAttendees;
	
	public int getNumberOfAttendees() {
		return noOfAttendees;
	}

	public void setNumberOfAttendees(int noOfAttendees) {
		this.noOfAttendees = noOfAttendees;
	}

	public String getBooked() {
		return booked;
	}

	public BookingDTO(String clientName,String clientSurname,String startDate,String endDate,String booked,String roomName,String meetingDescription,int noOfAttendees){
		setClientName(clientName);
		setClientSurname(clientSurname);
		setStartDate(startDate);
		setEndDate(endDate);
		setBooked(booked);
		setRoomName(roomName);
		setMeetingDescription(meetingDescription);
		setNumberOfAttendees(noOfAttendees);
	}

	public String getClientSurname() {
		return clientSurname;
	}

	public void setClientSurname(String clientSurname) {
		this.clientSurname = clientSurname;
	}

	public String getMeetingDescription() {
		return meetingDescription;
	}

	public void setMeetingDescription(String meetingDescription) {
		this.meetingDescription = meetingDescription;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientID) {
		this.clientName = clientID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String isBooked() {
		return booked;
	}

	public void setBooked(String booked) {
		this.booked = booked;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		return "BookingDTO [startDate=" + startDate + ", endDate=" + endDate + ", booked=" + booked + ", roomName="
				+ roomName + ", clientName=" + clientName + ", clientSurname=" + clientSurname + ", meetingDescription="
				+ meetingDescription + ", numberOfAttendees=" + noOfAttendees + "]";
	}

	
	
}

package za.co.vzap.dto;

import java.io.Serializable;

public class WaitingListDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientName;
	private String clientSurname;
	private String roomName;
	private String dateStart;
	private String dateEnd;
	private boolean waitingStatus;
	
	public WaitingListDTO(String clientName,String clientSurname,String roomName,String dateStart,String dateEnd,boolean waitingStatus){
		setClientName(clientName);
		setClientSurname(clientSurname);
		setRoomName(roomName);
		setDateStart(dateStart);
		setDateEnd(dateEnd);
		setWaitingStatus(waitingStatus);
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientSurname() {
		return clientSurname;
	}

	public void setClientSurname(String clientSurname) {
		this.clientSurname = clientSurname;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public boolean isWaitingStatus() {
		return waitingStatus;
	}

	public void setWaitingStatus(boolean waitingStatus) {
		this.waitingStatus = waitingStatus;
	}

	@Override
	public String toString() {
		return "WaitingListDTO [clientName=" + clientName + ", clientSurname=" + clientSurname + ", roomName="
				+ roomName + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", waitingStatus=" + waitingStatus
				+ "]";
	}
	
}

package za.co.vzap.dto;

public class RoomReportDTO {
	private String clientName;
	private String clientSurname;
	private String roomName;
	private int percentageFull;

	public RoomReportDTO(String clientName,String clientSurname,String roomName,int percentageFull){
		setClientName(clientName);
		setClientSurname(clientSurname);
		setRoomName(roomName);
		setPercentageFull(percentageFull);
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

	public int getPercentageFull() {
		return percentageFull;
	}

	public void setPercentageFull(int percentageFull) {
		this.percentageFull = percentageFull;
	}

	@Override
	public String toString() {
		return "RoomReportDTO [clientName=" + clientName + ", clientSurname=" + clientSurname + ", roomName=" + roomName
				+ ", percentageFull=" + percentageFull + "]";
	}
	
}

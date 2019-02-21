package za.co.vzap.dto;

import java.io.Serializable;

public class AvailableRoomDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomName;
	private boolean roomAvailable;
	
	public AvailableRoomDTO(String roomName,boolean roomAvailable){
		setRoomName(roomName);
		setRoomAvailable(roomAvailable);
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public boolean isRoomAvailable() {
		return roomAvailable;
	}

	public void setRoomAvailable(boolean roomAvailable) {
		this.roomAvailable = roomAvailable;
	}

	@Override
	public String toString() {
		return "AvailableRoomDTO [roomName=" + roomName + ", roomAvailable=" + roomAvailable + "]";
	}

}

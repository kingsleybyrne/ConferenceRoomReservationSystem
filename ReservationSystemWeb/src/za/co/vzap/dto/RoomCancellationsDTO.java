package za.co.vzap.dto;

import java.io.Serializable;

public class RoomCancellationsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomName;
	private int roomCancellations;
	
	public RoomCancellationsDTO(String roomName,int roomCancellations){
		setRoomName(roomName);
		setRoomCancellations(roomCancellations);
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomCancellations() {
		return roomCancellations;
	}

	public void setRoomCancellations(int roomCancellations) {
		this.roomCancellations = roomCancellations;
	}

	@Override
	public String toString() {
		return "RoomCancellationsDTO [roomName=" + roomName + ", roomCancellations=" + roomCancellations + "]";
	}
	
}

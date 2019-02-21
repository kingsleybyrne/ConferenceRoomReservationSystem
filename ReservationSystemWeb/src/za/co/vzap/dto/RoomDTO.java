package za.co.vzap.dto;

import java.io.Serializable;

public class RoomDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomName;
	private int roomCapacity;
	private int roomCount;
	private String buildingName;
	
	public RoomDTO(String roomName,int roomCapacity,int roomCount,String buildingName){
		setRoomName(roomName);
		setRoomCapacity(roomCapacity);
		setRoomCount(roomCount);
		setBuildingName(buildingName);
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	@Override
	public String toString() {
		return "RoomDTO [roomName=" + roomName + ", roomCapacity=" + roomCapacity
				+ ", roomCount=" + roomCount + ", buildingName=" + buildingName + "]";
	}
	
}

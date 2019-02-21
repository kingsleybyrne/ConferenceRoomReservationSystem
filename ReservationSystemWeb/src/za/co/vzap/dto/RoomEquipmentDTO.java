package za.co.vzap.dto;

import java.io.Serializable;

public class RoomEquipmentDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomName;
	private String roomEquipment;
	private int equipmentQuantity;
	private String clientName;
	private String clientSurname;
	private String dateStart;
	private String dateEnd;
	private boolean standardEquipment;
	
	public boolean isStandardEquipment() {
		return standardEquipment;
	}
	public void setStandardEquipment(boolean standardEquipment) {
		this.standardEquipment = standardEquipment;
	}
	public RoomEquipmentDTO(String clientName,String clientSurname,String roomName,String dateStart,String dateEnd,String roomEquipment,int equipmentQuantity,boolean standardEquipment){
		setClientName(clientName);
		setClientSurname(clientSurname);
		setRoomName(roomName);
		setRoomEquipment(roomEquipment);
		setEquipmentQuantity(equipmentQuantity);
		setDateStart(dateStart);
		setDateEnd(dateEnd);
		setStandardEquipment(standardEquipment);
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

	public String getRoomEquipment() {
		return roomEquipment;
	}

	public void setRoomEquipment(String roomEquipment) {
		this.roomEquipment = roomEquipment;
	}

	public int getEquipmentQuantity() {
		return equipmentQuantity;
	}

	public void setEquipmentQuantity(int equipmentQuantity) {
		this.equipmentQuantity = equipmentQuantity;
	}
	@Override
	public String toString() {
		return "RoomEquipmentDTO [roomName=" + roomName + ", roomEquipment=" + roomEquipment + ", equipmentQuantity="
				+ equipmentQuantity + ", clientName=" + clientName + ", clientSurname=" + clientSurname + ", dateStart="
				+ dateStart + ", dateEnd=" + dateEnd + "]";
	}
	
}

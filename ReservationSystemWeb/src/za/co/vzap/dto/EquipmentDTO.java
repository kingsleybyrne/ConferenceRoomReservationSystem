package za.co.vzap.dto;

import java.io.Serializable;

public class EquipmentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String equipmentName;
	private int equipmentCount;
	
	public EquipmentDTO(String equipmentName, int equipmentCount){
		setEquipmentName(equipmentName);
		setEquipmentCount(equipmentCount);
	}

	public int getEquipmentCount() {
		return equipmentCount;
	}

	public void setEquipmentCount(int equipmentCount) {
		this.equipmentCount = equipmentCount;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	@Override
	public String toString() {
		return "EquipmentDTO [equipmentName=" + equipmentName + "]";
	}
	
}

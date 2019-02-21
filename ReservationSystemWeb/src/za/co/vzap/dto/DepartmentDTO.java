package za.co.vzap.dto;

import java.io.Serializable;

public class DepartmentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String departmentName;

	public DepartmentDTO(String departmentName){
		setDepartmentName(departmentName);
	}
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Override
	public String toString() {
		return "DepartmentDTO [departmentName=" + departmentName + "]";
	}
	
}

package za.co.vzap.dto;

import java.io.Serializable;

public class ClientDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientName;
	private String clientSurname;
	private String clientTitle;
	private String clientPhoneNumber;
	private String clientEmail;
	private String department;
	
	public ClientDTO(String clientName,String clientSurname,String clientTitle,String clientPhoneNumber,String clientEmail,String department){
		setClientName(clientName);
		setClientSurname(clientSurname);
		setClientTitle(clientTitle);
		setClientPhoneNumber(clientPhoneNumber);
		setClientEmail(clientEmail);
		setDepartment(department);
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getClientTitle() {
		return clientTitle;
	}

	public void setClientTitle(String clientTitle) {
		this.clientTitle = clientTitle;
	}

	public String getClientPhoneNumber() {
		return clientPhoneNumber;
	}

	public void setClientPhoneNumber(String clientPhoneNumber) {
		this.clientPhoneNumber = clientPhoneNumber;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	@Override
	public String toString() {
		return "ClientDTO [clientName=" + clientName + ", clientSurname=" + clientSurname + ", clientTitle="
				+ clientTitle + ", clientPhoneNumber=" + clientPhoneNumber + ", clientEmail=" + clientEmail
				+ ", department=" + department+ "]";
	}

	
	
}

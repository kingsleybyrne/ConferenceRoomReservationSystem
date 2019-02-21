package za.co.vzap.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientUsername;
	private String clientPassword;
	private boolean clientAdminStatus;
	

	public UserDTO(String clientUsername,String clientPassword,boolean clientAdminStatus){
		setClientPassword(clientPassword);
		setClientUsername(clientUsername);
		setClientAdminStatus(clientAdminStatus);
	}
	public boolean isClientAdminStatus() {
		return clientAdminStatus;
	}
	
	public void setClientAdminStatus(boolean clientAdminStatus) {
		this.clientAdminStatus = clientAdminStatus;
	}

	public String getClientUsername() {
		return clientUsername;
	}

	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	@Override
	public String toString() {
		return "UserDTO [clientUsername=" + clientUsername + ", clientPassword=" + clientPassword
				+ ", clientAdminStatus=" + clientAdminStatus + "]";
	}
	
}

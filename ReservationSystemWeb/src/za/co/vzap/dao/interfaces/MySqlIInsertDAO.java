package za.co.vzap.dao.interfaces;

import java.sql.SQLException;

import za.co.vzap.dto.BookingDTO;
import za.co.vzap.dto.ClientDTO;
import za.co.vzap.dto.DepartmentDTO;
import za.co.vzap.dto.EquipmentDTO;
import za.co.vzap.dto.RoomEquipmentDTO;
import za.co.vzap.dto.WaitingListDTO;

public interface MySqlIInsertDAO {
	public BookingDTO insertBooking(BookingDTO bookingObject) throws SQLException;
	public ClientDTO insertClient(ClientDTO clientObject) throws SQLException;
	public DepartmentDTO insertDepartment(DepartmentDTO departmentObject) throws SQLException;
	public EquipmentDTO insertEquipment(EquipmentDTO equipmentObject) throws SQLException;
	public WaitingListDTO insertWaitingList(WaitingListDTO waitingListObject) throws SQLException;
	public RoomEquipmentDTO insertRoomEquipment(RoomEquipmentDTO roomEquipmentObject) throws SQLException;
	public String RoomCancellations(String roomName,BookingDTO bookingObject) throws SQLException;
	//help with adding equipment to a room
}

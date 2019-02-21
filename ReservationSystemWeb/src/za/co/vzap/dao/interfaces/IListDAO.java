package za.co.vzap.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import za.co.vzap.dto.AvailableRoomDTO;
import za.co.vzap.dto.BookingDTO;
import za.co.vzap.dto.ClientDTO;
import za.co.vzap.dto.DepartmentDTO;
import za.co.vzap.dto.EquipmentDTO;
import za.co.vzap.dto.RoomCancellationsDTO;
import za.co.vzap.dto.RoomDTO;
import za.co.vzap.dto.RoomEquipmentDTO;
import za.co.vzap.dto.UserDTO;
import za.co.vzap.dto.WaitingListDTO;

public interface IListDAO {
	public List<ClientDTO> clientAll() throws SQLException;
	public List<EquipmentDTO> equipmentAll() throws SQLException;
	public List<DepartmentDTO> departmentAll() throws SQLException;
	public List<BookingDTO> bookingAll() throws SQLException;
	public List<WaitingListDTO> waitingListAll() throws SQLException;
	public List<UserDTO> userListAll() throws SQLException;
	public List<RoomDTO> roomListAll() throws SQLException;
	public List<AvailableRoomDTO> availableRoomListAll() throws SQLException;
	public List<RoomEquipmentDTO> roomEquipmentListAll() throws SQLException;
	public List<RoomCancellationsDTO> roomCancellationsListAll() throws SQLException;
}

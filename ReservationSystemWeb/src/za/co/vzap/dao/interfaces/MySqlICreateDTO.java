package za.co.vzap.dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

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

public interface MySqlICreateDTO {
	public ClientDTO createClient(ResultSet rset) throws SQLException;
	public EquipmentDTO createEquipment(ResultSet rset) throws SQLException;
	public DepartmentDTO createDepartment(ResultSet rset) throws SQLException;
	public BookingDTO createBooking(ResultSet rset) throws SQLException;
	public WaitingListDTO createWaitingList(ResultSet rset) throws SQLException;
	public UserDTO createUser(ResultSet rset) throws SQLException;
	public RoomDTO createRoom(ResultSet rset) throws SQLException;
	public AvailableRoomDTO createAvailableRoom(ResultSet rset) throws SQLException;
	public RoomEquipmentDTO createRoomEquipment(ResultSet rset) throws SQLException;
	public RoomCancellationsDTO createRoomCancellations(ResultSet rset) throws SQLException;
}

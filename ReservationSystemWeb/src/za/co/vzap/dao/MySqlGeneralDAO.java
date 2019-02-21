package za.co.vzap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import za.co.vzap.dao.concrete.DaoFactory;
import za.co.vzap.dao.interfaces.MySqlICreateDTO;
import za.co.vzap.dao.interfaces.MySqlIInsertDAO;
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

public class MySqlGeneralDAO implements MySqlIInsertDAO,MySqlICreateDTO{
	private static final String ADDDEPARTMENT="INSERT INTO department(department.departmentName) values(?)";
	private static final String ADDEQUIPMENT="INSERT INTO equipment(equipment.equipmentName,equipment.equipmentCount) VALUES (?,0)";
	private static final String ADDCLIENT=" insert into client(client.clientName,client.clientSurname,client.clientTitle,client.clientPhoneNumber,client.clientEmail,client.departmentID,client.clientPassword) values(?,?,?,?,?,?,1,?)";
	private static final String ADDBOOKING="insert into booking (booking.clientID,booking.dateStart,booking.dateEnd,booking.booked,booking.roomName,booking.meetingDescription,booking.numberOfAttendees) values (?,?,?,?,?,?,?)";
	private static final String SELECTCLIENTID="select distinct client.clientID from client WHERE client.clientName=? AND client.clientSurname=?";
	private static final String ADDWAITINGLIST="INSERT INTO waitinglist(waitinglist.bookingID,waitinglist.waitingStatus) VALUES(?,?)";
	private static final String ADDROOMEQUIPMENT="INSERT INTO roomequipment(roomequipment.equipmentID,roomequipment.roomID,roomequipment.equipmentQuantity,roomequipment.standardBoolean) VALUES(?,?,?,?)";
	private static final String SELECTROOMID="SELECT distinct room.roomID FROM room,roomequipment,booking,client WHERE room.roomName=? AND room.roomID=roomequipment.roomID AND client.clientName=? AND client.clientSurname=?";
	private static final String SELECTBOOKINGID="SELECT distinct booking.bookingID FROM booking,client WHERE client.clientName=? AND client.clientSurname=? AND booking.roomName=?";
	private static final String UPDATECANCELLATIONS="update room set room.roomCancellations=room.roomCancellations+1 where room.roomName=?";
	private static final String SELECTEQUIPMENTID="SELECT equipment.equipmentID FROM equipment WHERE equipment.equipmentName=?";
	private static final String SELECTCLIENTBOOKING="SELECT distinct booking.clientID FROM booking,client,room WHERE client.clientID=booking.clientID AND client.clientName=? AND client.clientSurname=? AND room.roomName=booking.roomName AND booking.roomName=?";
	private static final String SETBOOKINGSTATUS="UPDATE booking SET booking.booked='Cancelled' WHERE booking.clientID=? AND booking.roomName=?";
	private static final String SETROOMFREE="UPDATE room SET room.roomAvailable=1 WHERE room.roomName=?";
	private static final String SETEQUIPMENTCOUNT="UPDATE equipment SET equipment.equipmentCount=equipment.equipmentCount+1 WHERE equipment.equipmentName=?";
	private static final String UPDATEBOOKINGSTATUS="UPDATE booking SET booking.booked=? WHERE booking.clientID=? AND booking.roomName=?";
	private static final String UPDATEROOMCOUNT="UPDATE room SET room.roomCount=room.roomCount+1 WHERE room.roomName=?";
	private static final String UPDATEROOMCANCELS="UPDATE room SET room.roomCancellations=room.roomCancellations+1 WHERE room.roomName=?";
	//private static final String SEARCH_ON_ROOMNAME="SELECT * FROM room WHERE roomName = ?";//new
	private static final String SEARCH_BOOKING= "SELECT * FROM booking WHERE roomName = ?";//new 
	private static final String SEARCH_CLIENT= "SELECT * FROM client WHERE clientID = ?";//new
	private static final String SELECTEQUIPEMENTFROMROOM="SELECT equipment.equipmentID FROM equipment,room WHERE equipment.equipmentName=? AND room.roomName=?";
	private static final String UPDATEEQUIPMENTQUANTITY="UPDATE roomequipment SET roomequipment.equipmentQuantity=? WHERE roomequipment.roomEquipmentID=?";
	private static final String DELETEEQUIPMENTFROMROOM="DELETE FROM roomequipment WHERE roomequipment.roomEquipmentID=?";
	//private static final String GET_BUILDING_NAME = "SELECT buildingName FROM building WHERE buildingID = ?";
	
	private ClientDTO getClient(int id, Connection c) throws SQLException {
		PreparedStatement pstmt1=c.prepareStatement(SEARCH_CLIENT);
		pstmt1.setInt(1, id);
		ResultSet rs1 = pstmt1.executeQuery();
		rs1.next();
		rs1.getInt(1);
		ClientDTO client = new ClientDTO(rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6), String.valueOf(rs1.getInt(7)));
		rs1.getString(8);
		rs1.getBoolean(9);
		rs1.close();
		pstmt1.close();
		if(c != null){
			c.close();
		}
		return client;
	}
	public void removeEquipmentFromBooking(RoomEquipmentDTO roomEquipment) throws SQLException{
		Connection c = DaoFactory.getDatabase().openConnection();
		PreparedStatement ps = c.prepareStatement(SELECTEQUIPEMENTFROMROOM);
		ps.setString(1, roomEquipment.getRoomEquipment());
		ps.setString(2, roomEquipment.getRoomName());
		ResultSet rs=ps.executeQuery();
		int equipmentID=rs.getInt(1);
		ps.close();
		PreparedStatement pstmt=c.prepareStatement(DELETEEQUIPMENTFROMROOM);
		pstmt.setInt(1, equipmentID);
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	public void changeEquipmentQuantity(RoomEquipmentDTO roomEquipment) throws SQLException{
		Connection c = DaoFactory.getDatabase().openConnection();
		PreparedStatement ps = c.prepareStatement(SELECTEQUIPEMENTFROMROOM);
		ps.setString(1, roomEquipment.getRoomEquipment());
		ps.setString(2, roomEquipment.getRoomName());
		ResultSet rs=ps.executeQuery();
		int equipmentID=rs.getInt(1);
		ps.close();
		PreparedStatement pstmt=c.prepareStatement(UPDATEEQUIPMENTQUANTITY);
		pstmt.setInt(1, equipmentID);
		pstmt.setInt(2, roomEquipment.getEquipmentQuantity());
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	public BookingDTO findBooking(String roomName) throws SQLException {
		Connection c = DaoFactory.getDatabase().openConnection();
		PreparedStatement ps = c.prepareStatement(SEARCH_BOOKING);
		ps.setString(1, roomName);
		ResultSet rs = ps.executeQuery();
		rs.next();
		@SuppressWarnings("unused")
		int bID = rs.getInt(1);
		int id  = rs.getInt(2);
		String dStart = rs.getString(3);
		String dEnd = rs.getString(4);
		String booked = rs.getString(5);
		String rName = rs.getString(6);
		String descrpt = rs.getString(7);
		int noOfAttendees = rs.getInt(8);
		ClientDTO temp = getClient(id, c);
		BookingDTO b = new BookingDTO(temp.getClientName(), temp.getClientSurname(), dStart, dEnd, booked, rName, descrpt, noOfAttendees);
		rs.close();
		ps.close();
		if(c != null){
			c.close();
		}
		return b;
	}
	@Override
	public ClientDTO insertClient(ClientDTO clientObject) throws SQLException {
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ADDCLIENT);
		pstmt.setString(1, clientObject.getClientName());
		pstmt.setString(2, clientObject.getClientSurname());
		pstmt.setString(3, clientObject.getClientTitle());
		pstmt.setString(4, clientObject.getClientPhoneNumber());
		pstmt.setString(5, clientObject.getClientEmail());
		pstmt.setString(6, clientObject.getDepartment());
		pstmt.executeUpdate();
		pstmt.close();
		PreparedStatement pstmt1=c.prepareStatement("SELECT client.departmentID from department,client where client.departmentID=department.departmentID AND client.clientName=? AND client.clientSurname=?");
		pstmt1.setString(1, clientObject.getClientName());
		pstmt1.setString(2, clientObject.getClientSurname());
		ResultSet rset1=pstmt1.executeQuery();
		int deptID =rset1.getInt(1);
		pstmt1.close();
		PreparedStatement pstmt2=c.prepareStatement("update client set department.departmentID=?");
		pstmt2.setInt(1, deptID);
		pstmt2.executeUpdate();
		pstmt2.close();
		c.close();
		return clientObject;
	}

	@Override
	public DepartmentDTO insertDepartment(DepartmentDTO departmentObject) throws SQLException {
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ADDDEPARTMENT);
		pstmt.setString(1, departmentObject.getDepartmentName());
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
		return departmentObject;
	}

	@Override
	public EquipmentDTO insertEquipment(EquipmentDTO equipmentObject) throws SQLException {
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ADDEQUIPMENT);
		pstmt.setString(1, equipmentObject.getEquipmentName());
		pstmt.executeUpdate();
		pstmt.close();
		c.close();//have to incerment the count
		return equipmentObject;
	}

	@Override
	public WaitingListDTO insertWaitingList(WaitingListDTO waitingListObject) throws SQLException {
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(SELECTBOOKINGID);
		pstmt.setString(1, waitingListObject.getClientName());
		pstmt.setString(2, waitingListObject.getClientSurname());
		pstmt.setString(3, waitingListObject.getRoomName());
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		int bookingID =rs.getInt(1);
		pstmt.close();
		PreparedStatement pstmt1=c.prepareStatement(ADDWAITINGLIST);
		pstmt1.setInt(1, bookingID);
		pstmt1.setBoolean(2, waitingListObject.isWaitingStatus());
		pstmt1.executeUpdate();
		pstmt1.close();
		c.close();
		return waitingListObject;
	}
	@Override
	public RoomEquipmentDTO insertRoomEquipment(RoomEquipmentDTO roomEquipmentObject) throws SQLException {
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(SELECTROOMID,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		pstmt.setString(1, roomEquipmentObject.getRoomName());
		pstmt.setString(2, roomEquipmentObject.getClientName());
		pstmt.setString(3, roomEquipmentObject.getClientSurname());
		ResultSet rs=pstmt.executeQuery();
		rs.beforeFirst();
		rs.next();
		int roomID=rs.getInt(1);
		pstmt.close();
		
		PreparedStatement pstmt1=c.prepareStatement(SELECTEQUIPMENTID,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		pstmt1.setString(1, roomEquipmentObject.getRoomEquipment());
		ResultSet rs1=pstmt1.executeQuery();
		rs1.beforeFirst();
		rs1.next();
		int equipID =rs1.getInt(1);
		pstmt1.close();
		
		PreparedStatement pstmt2=c.prepareStatement(ADDROOMEQUIPMENT);
		pstmt2.setInt(1, equipID);
		pstmt2.setInt(2, roomID);
		pstmt2.setInt(3, roomEquipmentObject.getEquipmentQuantity());
		pstmt2.setBoolean(4, roomEquipmentObject.isStandardEquipment());
		pstmt2.executeUpdate();
		pstmt2.close();
		PreparedStatement pstmt3=c.prepareStatement(SETEQUIPMENTCOUNT);
		pstmt3.setInt(1, roomEquipmentObject.getEquipmentQuantity());
		pstmt3.setString(2, roomEquipmentObject.getRoomEquipment());
		pstmt3.executeUpdate();
		pstmt3.close();
		c.close();
		return roomEquipmentObject;
	}
	@Override
	public BookingDTO insertBooking(BookingDTO bookingObject) throws SQLException { //Have to update the room status
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(SELECTCLIENTID,ResultSet.TYPE_SCROLL_SENSITIVE);
		pstmt.setString(1, bookingObject.getClientName());
		pstmt.setString(2, bookingObject.getClientSurname());
		ResultSet rs=pstmt.executeQuery();
		rs.beforeFirst();
		rs.next();
		int clientID=rs.getInt(1);
		pstmt.close();
		PreparedStatement pstmt1=c.prepareStatement(ADDBOOKING);
		pstmt1.setInt(1, clientID);
		pstmt1.setString(2, bookingObject.getStartDate());
		pstmt1.setString(3, bookingObject.getEndDate());
		pstmt1.setString(4, bookingObject.isBooked());
		pstmt1.setString(5, bookingObject.getRoomName());
		pstmt1.setString(6, bookingObject.getMeetingDescription());
		pstmt1.setInt(7, bookingObject.getNumberOfAttendees());
		pstmt1.executeUpdate();
		pstmt1.close();
		PreparedStatement pstmt2=c.prepareStatement("update room set room.roomAvailable=0 where room.roomName=?");
		pstmt2.setString(1, bookingObject.getRoomName());
		pstmt2.executeUpdate();
		pstmt2.close();
		c.close();
		return bookingObject;
	}

	@Override
	public String RoomCancellations(String roomName,BookingDTO bookingObject) throws SQLException {
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(UPDATECANCELLATIONS);
		pstmt.setString(1, roomName);
		pstmt.executeUpdate();
		pstmt.close();
		PreparedStatement pstmt1=c.prepareStatement(SELECTCLIENTBOOKING,ResultSet.TYPE_SCROLL_SENSITIVE);
		pstmt1.setString(1, bookingObject.getClientName());
		pstmt1.setString(2, bookingObject.getClientSurname());
		pstmt1.setString(2, bookingObject.getRoomName());
		ResultSet rs=pstmt1.executeQuery();
		int clientID=rs.getInt(1);
		pstmt1.close();
		PreparedStatement pstmt2=c.prepareStatement(SETBOOKINGSTATUS);
		pstmt2.setInt(1, clientID);
		pstmt2.setString(2, bookingObject.getRoomName());
		pstmt2.executeUpdate();
		PreparedStatement pstmt3=c.prepareStatement(SETROOMFREE);
		pstmt3.setString(1, bookingObject.getRoomName());
		pstmt3.executeUpdate();
		pstmt3.close();
		c.close();
		return roomName;
	}
	public void updateRoomCount(String roomName) throws SQLException{
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(UPDATEROOMCOUNT);
		pstmt.setString(1, roomName);
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	public void updateRoomCancels(String roomName) throws SQLException{
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(UPDATEROOMCANCELS);
		pstmt.setString(1, roomName);
		pstmt.executeUpdate();
		pstmt.close();
		c.close();
	}
	public void updateBookingStatusBooked(String clientName,String clientSurname,String roomName) throws SQLException{
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement("SELECT client.clientID FROM client WHERE client.clientName=? AND client.clientSurname=?",ResultSet.TYPE_SCROLL_SENSITIVE);
		pstmt.setString(1, clientName);
		pstmt.setString(2, clientSurname);
		ResultSet rs=pstmt.executeQuery();
		rs.beforeFirst();
		rs.next();
		int clientID=rs.getInt(1);
		pstmt.close();
		PreparedStatement pstmt1=c.prepareStatement(UPDATEBOOKINGSTATUS);
		pstmt1.setString(1, "Booked");
		pstmt1.setInt(2, clientID);
		pstmt1.setString(3, roomName);
		pstmt1.executeUpdate();
		pstmt1.close();
		c.close();
	}
	public void updateBookingStatusCancel(String clientName,String clientSurname,String roomName) throws SQLException{
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement("SELECT client.clientID FROM client WHERE client.clientName=? AND client.clientSurname=?");
		pstmt.setString(1, clientName);
		pstmt.setString(2, clientSurname);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		int clientID=rs.getInt(1);
		pstmt.close();
		PreparedStatement pstmt1=c.prepareStatement(UPDATEBOOKINGSTATUS);
		pstmt.setString(1, "Cancelled");
		pstmt1.setInt(2, clientID);
		pstmt1.setString(3, roomName);
		pstmt1.executeUpdate();
		pstmt1.close();
		c.close();
	}
	
	public ClientDTO createClient(ResultSet rset) throws SQLException{
		ClientDTO client=new ClientDTO(rset.getString("clientName"), rset.getString("clientSurname"), rset.getString("clientTitle"), rset.getString("clientPhoneNumber"), rset.getString("clientEmail"), rset.getString("clientDepartment"));
		return client;
	}
	public EquipmentDTO createEquipment(ResultSet rset) throws SQLException{
		EquipmentDTO equipment=new EquipmentDTO(rset.getString("equipmentName"),rset.getInt("equipmentCount"));
		return equipment;
	}
	public DepartmentDTO createDepartment(ResultSet rset) throws SQLException{
		DepartmentDTO department=new DepartmentDTO(rset.getString("departmentName"));
		return department;
	}
	public BookingDTO createBooking(ResultSet rset) throws SQLException{
		BookingDTO booking=new BookingDTO(rset.getString("clientName"),rset.getString("clientSurname"), rset.getString("dateStart"), rset.getString("dateEnd"), rset.getString("booked"), rset.getString("roomName"),rset.getString("meetingDescription"),rset.getInt("numberOfAttendees"));
		return booking;
	}
	public WaitingListDTO createWaitingList(ResultSet rset) throws SQLException{
		WaitingListDTO waitingList=new WaitingListDTO(rset.getString("clientName"),rset.getString("clientSurname"),rset.getString("roomName"),rset.getString("dateStart"),rset.getString("dateEnd"),rset.getBoolean("waitingStatus"));
		return waitingList;
	}
	public UserDTO createUser(ResultSet rset) throws SQLException{
		UserDTO user = new UserDTO(rset.getString("clientEmail"), rset.getString("clientPassword"),rset.getBoolean("clientAdminStatus"));
		return user;
	}
	public RoomDTO createRoom(ResultSet rset) throws SQLException{
		RoomDTO room=new RoomDTO(rset.getString("roomName"), rset.getInt("roomCapacity"), rset.getInt("roomCount"), rset.getString("buildingName"));
		return room;
	}
	@Override
	public AvailableRoomDTO createAvailableRoom(ResultSet rset) throws SQLException {
		AvailableRoomDTO availableRoom=new AvailableRoomDTO(rset.getString("roomName"), rset.getBoolean("roomAvaiable"));
		return availableRoom;
	}
	@Override
	public RoomEquipmentDTO createRoomEquipment(ResultSet rset) throws SQLException {
		RoomEquipmentDTO roomEquipment=new RoomEquipmentDTO(rset.getString("clientName"),rset.getString("clientSurname"),rset.getString("roomName"),rset.getString("dateStart"),rset.getString("dateEnd"),rset.getString("equipmentName"), rset.getInt("equipmentQuantity"),rset.getBoolean("standardBoolean"));
		return roomEquipment;
	}
	@Override
	public RoomCancellationsDTO createRoomCancellations(ResultSet rset) throws SQLException {
		RoomCancellationsDTO roomCancel=new RoomCancellationsDTO(rset.getString("roomName"), rset.getInt("roomCancellations"));
		return roomCancel;
	}

}

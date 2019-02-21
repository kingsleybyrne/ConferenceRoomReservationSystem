package za.co.vzap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import za.co.vzap.dao.concrete.DaoFactory;
import za.co.vzap.dao.interfaces.IListDAO;
import za.co.vzap.dao.interfaces.MySqlICreateDTO;
import za.co.vzap.dto.AvailableRoomDTO;
import za.co.vzap.dto.BookingDTO;
import za.co.vzap.dto.ClientDTO;
import za.co.vzap.dto.DepartmentDTO;
import za.co.vzap.dto.EquipmentDTO;
import za.co.vzap.dto.RoomCancellationsDTO;
import za.co.vzap.dto.RoomDTO;
import za.co.vzap.dto.RoomEquipmentDTO;
import za.co.vzap.dto.RoomReportDTO;
import za.co.vzap.dto.UserDTO;
import za.co.vzap.dto.WaitingListDTO;

public class MySqlReportDAO implements IListDAO,MySqlICreateDTO {
	private static final String ALLCLIENTS="SELECT client.clientName,client.clientSurname,client.clientTitle,client.clientPhoneNumber,client.clientEmail,department.departmentName AS clientDepartment FROM client,department where client.departmentID=department.departmentID";
	private static final String ALLROOMS="SELECT room.roomName,room.roomCapacity,building.buildingName,room.roomCount FROM room,building where room.buildingID=building.buildingID";
	private static final String ALLEQUIPMENT="SELECT equipment.equipmentName,equipment.equipmentCount FROM equipment";
	private static final String ALLBOOKINGS="SELECT distinct client.clientName,client.clientSurname,room.roomName,booking.dateStart,booking.dateEnd,booking.booked,booking.meetingDescription,booking.numberOfAttendees FROM client,booking,room,roomequipment WHERE booking.clientID=client.clientID AND roomequipment.roomID=room.roomID AND booking.roomName=room.roomName";
	private static final String ALLWAITINGLIST="SELECT client.clientName,client.clientSurname,room.roomName,booking.dateStart,booking.dateEnd,waitinglist.waitingStatus from client,room,booking,waitinglist where booking.clientID=client.clientID AND booking.roomName=room.roomName";
	private static final String ALLDEPARTMENTS="SELECT department.departmentName FROM department";
	private static final String AVAILABLEROOMS="SELECT room.roomName,room.roomAvailable FROM room WHERE room.roomAvailable=1"; //do this in code with a for loop and if statement
	private static final String ALLUSERS="SELECT client.clientEmail,client.clientPassword,client.clientAdminStatus FROM client";
	private static final String ALLROOMEQUIPMENT="select distinct client.clientName,client.clientSurname,room.roomName,booking.dateStart,booking.dateEnd,equipment.equipmentName,roomequipment.equipmentQuantity,roomequipment.standardBoolean from client,booking,room,roomequipment,equipment where booking.clientID=client.clientID AND roomequipment.roomID=room.roomID AND booking.roomName=room.roomName AND roomequipment.equipmentID=equipment.equipmentID";
	private static final String ALLROOMCANCELS="select distinct room.roomName,room.roomCancellations from room,roomequipment where roomequipment.roomID=room.roomID";
	private static final String ALLROOMSWEEK="SELECT client.clientName,client.clientSurname,room.roomName,((booking.numberOfAttendees)/(room.roomCapacity))*100 AS percentage FROM booking,room,client WHERE booking.roomName=room.roomName AND booking.clientID=client.clientID AND WEEK(?)";
	private static final String ALLROOMSMONTH="SELECT client.clientName,client.clientSurname,room.roomName,((booking.numberOfAttendees)/(room.roomCapacity))*100 AS percentage FROM booking,room,client WHERE booking.roomName=room.roomName AND booking.clientID=client.clientID AND MONTH(?)";
	private static final String ALLROOMSYEAR="SELECT client.clientName,client.clientSurname,room.roomName,((booking.numberOfAttendees)/(room.roomCapacity))*100 AS percentage FROM booking,room,client WHERE booking.roomName=room.roomName AND booking.clientID=client.clientID AND YEAR(?)";
	
	@Override
	public List<ClientDTO> clientAll() throws SQLException {
		ArrayList<ClientDTO> client=new ArrayList<ClientDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		if(c == null)
		{
			System.out.println("c == null...>>>>>>");
		}
		PreparedStatement pstmt=c.prepareStatement(ALLCLIENTS);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			client.add(createClient(rs));
		}
		pstmt.close();
		c.close();
		return client;
	}
	@Override
	public List<EquipmentDTO> equipmentAll() throws SQLException {
		ArrayList<EquipmentDTO> equipment=new ArrayList<EquipmentDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLEQUIPMENT);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			equipment.add(createEquipment(rs));
		}
		pstmt.close();
		c.close();
		return equipment;
	}
	@Override
	public List<DepartmentDTO> departmentAll() throws SQLException {
		ArrayList<DepartmentDTO> department=new ArrayList<DepartmentDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLDEPARTMENTS);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			department.add(createDepartment(rs));
		}
		pstmt.close();
		c.close();
		return department;
	}
	@Override
	public List<BookingDTO> bookingAll() throws SQLException {
		ArrayList<BookingDTO> booking=new ArrayList<BookingDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLBOOKINGS);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			booking.add(createBooking(rs));
		}
		pstmt.close();
		c.close();
		return booking;
	}
	@Override
	public List<WaitingListDTO> waitingListAll() throws SQLException {
		ArrayList<WaitingListDTO> waitingList=new ArrayList<WaitingListDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLWAITINGLIST);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			waitingList.add(createWaitingList(rs));
		}
		pstmt.close();
		c.close();
		return waitingList;
	}
	@Override
	public List<UserDTO> userListAll() throws SQLException {
		System.out.println("Before list dao");
		ArrayList<UserDTO> user=new ArrayList<UserDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLUSERS);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			user.add(createUser(rs));
		}
		pstmt.close();
		c.close();
		System.out.println("After list dao");
		return user;
	}
	@Override
	public List<RoomDTO> roomListAll() throws SQLException {
		ArrayList<RoomDTO> room=new ArrayList<RoomDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLROOMS);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			room.add(createRoom(rs));
		}
		pstmt.close();
		c.close();
		return room;
	}
	@Override
	public List<AvailableRoomDTO> availableRoomListAll() throws SQLException {
		ArrayList<AvailableRoomDTO> availableRoom=new ArrayList<AvailableRoomDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(AVAILABLEROOMS);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			availableRoom.add(createAvailableRoom(rs));
		}
		pstmt.close();
		c.close();
		return availableRoom;
	}
	@Override
	public List<RoomEquipmentDTO> roomEquipmentListAll() throws SQLException {
		ArrayList<RoomEquipmentDTO> roomEquipment=new ArrayList<RoomEquipmentDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLROOMEQUIPMENT);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			roomEquipment.add(createRoomEquipment(rs));
		}
		pstmt.close();
		c.close();
		return roomEquipment;
	}
	@Override
	public List<RoomCancellationsDTO> roomCancellationsListAll() throws SQLException {
		ArrayList<RoomCancellationsDTO> roomCancels=new ArrayList<RoomCancellationsDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLROOMCANCELS);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			roomCancels.add(createRoomCancellations(rs));
		}
		pstmt.close();
		c.close();
		return roomCancels;
	}
	public List<RoomReportDTO> roomReportListWeekList(String date) throws SQLException{
		ArrayList<RoomReportDTO> roomReportWeek=new ArrayList<RoomReportDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLROOMSWEEK);
		pstmt.setString(1, "2018-07-11");
		ResultSet rs=pstmt.executeQuery();
		rs.beforeFirst();
		while(rs.next()){
			roomReportWeek.add(createRoomReport(rs));
		}
		pstmt.close();
		c.close();
		return roomReportWeek;
	}
	public List<RoomReportDTO> roomReportListMonthList(String date) throws SQLException{
		ArrayList<RoomReportDTO> roomReportMonth=new ArrayList<RoomReportDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLROOMSMONTH);
		pstmt.setString(1, date);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			roomReportMonth.add(createRoomReport(rs));
		}
		pstmt.close();
		c.close();
		return roomReportMonth;
	}
	public List<RoomReportDTO> roomReportListYearList(String date) throws SQLException{
		ArrayList<RoomReportDTO> roomReportYear=new ArrayList<RoomReportDTO>();
		Connection c=DaoFactory.getDatabase().openConnection();
		PreparedStatement pstmt=c.prepareStatement(ALLROOMSYEAR);
		pstmt.setString(1, date);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			roomReportYear.add(createRoomReport(rs));
		}
		pstmt.close();
		c.close();
		return roomReportYear;
	}
	public RoomReportDTO createRoomReport(ResultSet rset) throws SQLException{
		RoomReportDTO report=new RoomReportDTO(rset.getString("clientName"), rset.getString("clientSurname"), rset.getString("roomName"), rset.getInt("percentage"));
		return report;	
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

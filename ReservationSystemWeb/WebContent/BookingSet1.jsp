<%@page import="za.co.vzap.dto.ClientDTO"%>
<%@page import="za.co.vzap.dto.EquipmentDTO"%>
<%@page import="za.co.vzap.dao.MySqlReportDAO"%>
<%@page import="za.co.vzap.dto.RoomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.Select"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<nav class="navbar navbar-default">
<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="homePage.jsp">Conference Room Reservation
			System</a>
	</div>
	<ul class="nav navbar-nav">
		<li><a href="BookingSet1.jsp">Booking</a></li>
		<li><a href="Statistics.jsp">Statistics</a></li>
		<li><a href="Cancellation.jsp">Pending Bookings</a></li>
	</ul>
</div>
</nav>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="path/to/multiselect.css" media="screen" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="css/jquery.multiselect.css">
</head>
<body>
	<form method="get" action="booking">
		<div class="container">
			<div class="panel panel-primary">
				<div class="panel-heading">Schedule a Booking</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">First Name</label> <input
									type="text" class="form-control" name="fname" id="fname"
									value="${client.clientName}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Last Name</label> <input
									type="text" class="form-control" name="lname" id="lname"
									value="${client.clientSurname}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Email</label> <input type="text"
									class="form-control" name="email" id="email"
									value="${client.clientEmail}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Phone Number</label> <input
									type="text" class="form-control" name="phone" id="phone"
									value="${client.clientPhoneNumber}" style="width: 157px;">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Appointment Time Start</label>
								<div class="input-group date" id="datetimepicker1">
									<input type="text" class="form-control" name="dateStart"> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Appointment Time End</label>
								<div class="input-group date" id="datetimepicker2">
									<input type="text" class="form-control" name="dateEnd"> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Number of Attendees</label> <select
									id="box" name="noOfAttendees" class="selectpicker form-control"
									style="width: 311px;">
									<option value="" selected="selected">Select Number</option>
									<%for(int i=0;i<31;i++)
    {%>
									<option><%= i %></option>
									<% } %>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Meeting Description</label> <input
									type="text" class="form-control" name="meetingDescription" id="meetingDescription">
							</div>
						</div>
						<div class="col-md-6" style="width: 876px;">

							<div class="form-group" style="right: auto">
								<label class="control-label">Room<select id="box"
									name="roomName" class="selectpicker form-control"
									style="width: 454px; height: 34px">
										<option value="" selected="selected">Select Room</option>
										<% MySqlReportDAO listDao=new MySqlReportDAO();
    ArrayList<RoomDTO> room=(ArrayList<RoomDTO>)listDao.roomListAll();
    for(int i=0;i<room.size();i++)
    {%>
										<option><%= room.get(i).getRoomName() %></option>
										<% } %>
								</select></label>
							</div>
						</div>
						<center>
							<input type="submit" class="btn btn-primary" value="Submit">
						</center>
						<div style="height: 25px;"></div>




						<script>
  $(function () {
    $('#datetimepicker1').datetimepicker();
 });
  $(function () {
	    $('#datetimepicker2').datetimepicker();
	 });
</script>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
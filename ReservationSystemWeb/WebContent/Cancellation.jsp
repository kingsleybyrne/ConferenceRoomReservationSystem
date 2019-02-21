<%@page import="java.util.List"%>
<%@page import="za.co.vzap.dto.BookingDTO"%>
<%@page import="za.co.vzap.dao.MySqlReportDAO"%>
<%@page import="za.co.vzap.dto.RoomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.Select"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<nav class="navbar navbar-default">
<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="Login.jsp">Conference Room
			Reservation System</a>
	</div>
	<ul class="nav navbar-nav">
		<li><a href="BookingSet1.jsp">Booking</a></li>
		<li><a href="Statistics.jsp">Statistics</a></li>
		<li><a href="#">Pending Bookings</a></li>
	</ul>
</div>
</nav>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel a Booking</title>
</head>
<body>

	<form method="get" action="cancellation">
		<div class="container">
			<div class="panel panel-primary">
				<div class="panel-heading">Cancel a Booking</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Name</label> <input type="text"
									class="form-control" name="cancelName" id="fname"
									value="${booking.clientName}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Surname</label> <input type="text"
									class="form-control" name="cancelSurname" id="lname"
									value="${booking.clientSurname}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Start Date</label> <input
									type="text" class="form-control" name="cancelStartDate"
									id="lname" value="${booking.startDate}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">End Date</label> <input type="text"
									class="form-control" name="cancelEndDate" id="lname"
									value="${booking.endDate}">
							</div>
						</div>
						<div class="col-md-6" style="width: 876px;">

							<div class="form-group" style="right: auto">
								<label class="control-label">Room<select id="box"
									name="cancelRoomName" class="selectpicker form-control"
									style="width: 438px; height: 34px">
									<option>${booking.roomName}</option>
									
								</select></label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Meeting Description</label> <input
									type="text" class="form-control"
									name="cancelMeetingDescription" id="lname"
									value="${booking.meetingDescription}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Number of Attendees</label> <input
									type="text" class="form-control" name="cancelNoOfAttendees"
									id="lname" value="${booking.numberOfAttendees }">
							</div>
						</div>
					</div>
				</div>
				<center>
					<input type="submit" class="btn btn-primary" name="act"
						value="Confirm Booking"> <input type="submit"
						class="btn btn-primary" name="act" value="Cancel Booking">
				</center>
				<div></div>
				<br>
			</div>
		</div>
	</form>
</body>
</html>
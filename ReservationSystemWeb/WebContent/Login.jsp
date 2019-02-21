<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="Login.jsp">Conference Room Reservation
				System</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="#">Booking</a></li>
			<li><a href="#">Statistics</a></li>
			<li><a href="#">Pending Bookings</a></li>
		</ul>
	</div>
</nav>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
	<form method="post" action="login" style="height: 213px; ">
		<div class="container">
  <div class="panel panel-primary">
    <div class="panel-heading">Login</div>
      <div class="panel-body">
         <div class="row">
            <div class="col-md-6">
               <div class="form-group">
                  <label class="control-label">Email Address</label>
                  <input type="text" class="form-control" name="userID" id="fname">
               </div>
            </div>
            <div class="col-md-6">
               <div class="form-group">
                  <label class="control-label">Password</label>
                  <input type="password" class="form-control" name="password" id="lname">
               </div>
            </div>
         </div>
        </div>
        <center><input type="submit" class="btn btn-primary" value="Submit"><input type="reset" class="btn btn-primary1" value="Reset"></center>
     <div></div>
				<br>
      </div>
   </div>
	</form>
</body>
</html>

<%@page import="za.co.vzap.dto.RoomCancellationsDTO"%>
<%@page import="za.co.vzap.dto.RoomDTO"%>
<%@page import="za.co.vzap.dto.EquipmentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="za.co.vzap.dao.MySqlReportDAO"%>
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
		<li><a href="BookingSet1.jsp">Booking</a></li>
		<li><a href="Statistics.jsp">Statistics</a></li>
		<li><a href="Cancellation.jsp">Pending Bookings</a></li>
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
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages':['corechart']});

    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChart);

    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart() {

      // Create the data table.
      var data = new google.visualization.DataTable();
      data.addColumn('string', 'Building');
      data.addColumn('number', 'Uses');
      <%MySqlReportDAO listDao = new MySqlReportDAO();%>
      <%ArrayList<RoomDTO> buildingList = null;
			try {
				buildingList = (ArrayList<RoomDTO>) listDao.roomListAll();
			} catch (Exception e) {
				e.printStackTrace();
			}%>
     <%!int countNorth = 0;
	int countSouth = 0;
	int countEast = 0;
	int countWest = 0;%>
		<%for (int i = 0; i < buildingList.size(); i++) {
				if (buildingList.get(i).getBuildingName().equals("North")) {
					countNorth = countNorth + buildingList.get(i).getRoomCount();
				}
				if (buildingList.get(i).getBuildingName().equals("South")) {
					countSouth = countSouth + buildingList.get(i).getRoomCount();
				}
				if (buildingList.get(i).getBuildingName().equals("East")) {
					countEast = countEast + buildingList.get(i).getRoomCount();
				}
				if (buildingList.get(i).getBuildingName().equals("West")) {
					countWest = countWest + buildingList.get(i).getRoomCount();
				}
			}%>
		data.addRows([ ['South',<%=countSouth%>],['East',<%=countEast%>],['West',<%=countWest%>],['North',<%=countNorth%>] ]);
		
      // Set chart options
      var options = {'title':'Building Use Statistics',
                     'width':400,
                     'height':300};

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }
  </script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js">
      </script>
<script type="text/javascript">
         google.charts.load('current', {packages: ['corechart']});     
      </script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conference Room Statistics</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">Building</div>
			<div class="panel-body">
				<div class="row">
					<div id="chart_div"></div>
				</div>
			</div>
			<br>
		</div>
	</div>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">Room</div>
			<div class="panel-body">
				<div class="row">
					<table align="center">
					<tr>
							<td colSpan=2>
								<div id="chart_sort_div4"
									style="align: center; width: 700px; height: 300px;"></div>
							</td>
						</tr>
						<tr valign="top">
							<td style="width: 50%;"><script type="text/javascript"
									src="https://www.gstatic.com/charts/loader.js"></script> <script
									type="text/javascript">
  google.charts.load('current', {'packages': ['table', 'corechart']});
  google.charts.setOnLoadCallback(drawSort);
function drawSort() {
	  var data = new google.visualization.DataTable();
	  
	  data.addColumn('string', 'Equipment');
	  data.addColumn('number', 'Uses');
	  data.addColumn('number', 'Cancellations');
	  <%ArrayList<RoomCancellationsDTO> roomCancelList = (ArrayList<RoomCancellationsDTO>) listDao.roomCancellationsListAll();
	  ArrayList<RoomDTO> roomList = (ArrayList<RoomDTO>) listDao.roomListAll();
	  %>
	  data.addRows(<%=roomList.size()%>);
	  <%for (int a = 0; a < roomList.size()&&a<roomCancelList.size(); a++) {
%>
				
				 data.setCell(<%=a%>, 0, '<%=roomCancelList.get(a).getRoomName()%>');
				 data.setCell(<%=a%>, 1, <%=roomList.get(a).getRoomCount()%>);
				  data.setCell(<%=a%>, 2, <%=roomCancelList.get(a).getRoomCancellations()%>);
		  
		<%}%>
		var options = {
		        title: 'Uses/Cancellations',
		        chartArea: {width: '50%'},
		        colors: ['#b0120a', '#ffab91'],
		        hAxis: {
		          title: 'Room Statistics',
		          minValue: 0
		        },
		        vAxis: {
		          title: 'Room'
		        }
		      };
	  var view = new google.visualization.DataView(data);
	  view.setColumns([0, 1,2]);

	  var table = new google.visualization.Table(document.getElementById('table_sort_div4'));
	  table.draw(view, {width: '100%', height: '100%'});

	  var chart = new google.visualization.BarChart(document.getElementById('chart_sort_div4'));
	  chart.draw(view,options);

	  google.visualization.events.addListener(table, 'sort',
	      function(event) {
	        data.sort([{column: event.column, desc: !event.ascending}]);
	        chart.draw(view);
	      });
	}
    </script>
								<div id="table_sort_div4"></div></td>
						</tr>
					</table>
				</div>
				
			</div>
			
			<br>
		</div>
	</div>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">Equipment</div>
			<div class="panel-body">
				<div class="row">
					<table align="center">
					<tr>
							<td colSpan=2>
								<div id="chart_sort_div1"
									style="align: center; width: 700px; height: 300px;"></div>
							</td>
						</tr>
						<tr valign="top">
							<td style="width: 50%;"><script type="text/javascript"
									src="https://www.gstatic.com/charts/loader.js"></script> <script
									type="text/javascript">
  google.charts.load('current', {'packages': ['table', 'corechart']});
  google.charts.setOnLoadCallback(drawSort);
function drawSort() {
	  var data = new google.visualization.DataTable();
	  data.addColumn('string', 'Equipment');
	  data.addColumn('number', 'Uses');
	  <%ArrayList<EquipmentDTO> equipList = (ArrayList<EquipmentDTO>) listDao.equipmentAll();
	  %>
	  data.addRows(<%=equipList.size()%>);
	  <%for (int a = 0; a < equipList.size(); a++) {

				System.out.println("in loop equipment Name...>>>>>>" + equipList.get(a).getEquipmentName());
				System.out.println("in loop equipment Count...>>>>>>" + equipList.get(a).getEquipmentCount());%>
				
				 data.setCell(<%=a%>, 0, '<%=equipList.get(a).getEquipmentName()%>');
				  data.setCell(<%=a%>, 1, <%=equipList.get(a).getEquipmentCount()%>);
		  
		<%}%>

	  var view = new google.visualization.DataView(data);
	  view.setColumns([0, 1]);

	  var table = new google.visualization.Table(document.getElementById('table_sort_div'));
	  table.draw(view, {width: '100%', height: '100%'});

	  var chart = new google.visualization.Histogram(document.getElementById('chart_sort_div1'));
	  chart.draw(view);

	  google.visualization.events.addListener(table, 'sort',
	      function(event) {
	        data.sort([{column: event.column, desc: !event.ascending}]);
	        chart.draw(view);
	      });
	}
    </script>
								<div id="table_sort_div"></div></td>
						</tr>
					</table>
				</div>
			</div>
			<br>
		</div>
	</div>
</body>
</html>
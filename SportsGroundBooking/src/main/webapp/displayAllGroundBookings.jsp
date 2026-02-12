<%@ page import="java.util.List" %>
<%@ page import="com.wipro.sports.bean.GroundBookingBean" %>
<!DOCTYPE html>
<html>
<head>
<title>All Bookings</title>
</head>
<body>
<h2>All Ground Bookings</h2>
<%
List<GroundBookingBean> list=(List<GroundBookingBean>) request.getAttribute("allRecords");
if(list != null && !list.isEmpty()) {
%>
<table border="1">
<tr>
    <th>Record ID</th>
    <th>Team Name</th>
    <th>Ground Name</th>
    <th>Booking Date</th>
    <th>Time Slot</th>
    <th>Remarks</th>
</tr>
<%
for(GroundBookingBean bean : list) {
%>
<tr>
    <td><%=bean.getRecordId()%></td>
    <td><%=bean.getTeamName()%></td>
    <td><%=bean.getGroundName()%></td>
    <td><%=bean.getBookingDate()%></td>
    <td><%=bean.getTimeSlot()%></td>
    <td><%=bean.getRemarks()%></td>
</tr>
<%
}
%>
</table>
<%
} else {
%>
<p>No records found.</p>
<%
}
%>
<br>
<a href="menu.html">Back to Menu</a>
</body>
</html>

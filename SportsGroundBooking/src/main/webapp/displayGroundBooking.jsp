<%@ page import="com.wipro.sports.bean.GroundBookingBean" %>
<!DOCTYPE html>
<html>
<head>
<title>Booking Details</title>
</head>
<body>
<h2>Booking Details</h2>
<%
GroundBookingBean bean=(GroundBookingBean) request.getAttribute("record");
if(bean != null) {
%>
<table>
<tr>
    <th>Record ID</th>
    <th>Team Name</th>
    <th>Ground Name</th>
    <th>Booking Date</th>
    <th>Time Slot</th>
    <th>Remarks</th>
</tr>
<tr>
    <td><%=bean.getRecordId()%></td>
    <td><%=bean.getTeamName()%></td>
    <td><%=bean.getGroundName()%></td>
    <td><%=bean.getBookingDate()%></td>
    <td><%=bean.getTimeSlot()%></td>
    <td><%=bean.getRemarks()%></td>
</tr>

</table>

<%
} else {
%>

<p>No record found.</p>

<%
}
%>

<br><br>
<a href="menu.html">Back to Menu</a>

</body>
</html>

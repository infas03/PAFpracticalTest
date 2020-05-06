<%@ page import="com.PAF.Appointment_2"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
 //Insert app----------------------------------
if (request.getParameter("USER_ID") != null)
{
 	Appointment_2 AppointmentObj = new Appointment_2();
 	String stsMsg = AppointmentObj.addAppointment
 	(
 		request.getParameter("USER_ID"),
 		request.getParameter("APP_NO"),
 		request.getParameter("DOCTOR_NAME"),
 		request.getParameter("PAYMENT")
 	);
 	session.setAttribute("statusMsg", stsMsg);
}


 //delete app----------------------------------
if (request.getParameter("hidAppIDDelete") != null)
{
 	Appointment_2 AppointmentObj = new Appointment_2();
 	String stsMsg = AppointmentObj.deleteAppointment
 			(
 					request.getParameter("hidAppIDDelete")
 			);
 	session.setAttribute("statusMsg", stsMsg);
 }
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment_2</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
</head>
<body>

	<h1>Appointment</h1>
	
	<form id = "formApp" name="formApp" method="post" action="Appointment_2.jsp">
		
			<div class="col-7">
			User ID:<input id="USER_ID" input name="USER_ID" type="text" class="form-control"><br>
			</div> 
			<div class="col-7">
			Appointment ID:<input id="APP_NO" input name="APP_NO" type="text" class="form-control"><br> 
			</div>
			<div class="col-7">
			Doctor name:<input id="DOCTOR_NAME" input name="DOCTOR_NAME" type="text" class="form-control"><br>
			</div>
			<div class="col-7">
 			Payment Amount:<input id="PAYMENT" input name="PAYMENT" type="text" class="form-control"><br> 
 			</div>
 			
 			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 			<input type="hidden" id="hidAppIDSave" name="hidAppIDSave" value="">
	</form>
	
	<div class="alert alert-success">
	<%
	 	out.print(session.getAttribute("statusMsg"));
	%>
	</div>
	<br>
	<%
	 	Appointment_2 AppointmentObj = new Appointment_2();
	 	out.print(AppointmentObj.readAppointment_2());
	%>
	


</body>
</html>
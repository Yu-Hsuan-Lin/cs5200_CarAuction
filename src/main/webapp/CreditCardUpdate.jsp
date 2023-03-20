<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a CreditCard</title>
</head>
<body>
	<h1>Update CreditCard's Name On Card</h1>
	<form action="creditcardupdate" method="post">
		<p>
			<label for="cardnumber">Card Number</label>
			<input id="cardnumber" name="cardnumber" value="${fn:escapeXml(param.cardnumber)}">
		</p>
		<p>
			<label for="nameoncard">New Name On Card</label>
			<input id="nameoncard" name="nameoncard" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>
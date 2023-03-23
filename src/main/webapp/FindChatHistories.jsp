<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1" style="border:none;">
        <tr>
            <th style="text-align:left;vertical-align:top;border:none;padding-right:80px;">
	            <form action="SearchChatHistoryByCustomerServiceID" method="post">
					<h3>Search ChatHistory By Customer Service ID</h3>
						<p>
							<label for="customerServiceID">Customer Service ID</label>
							<input id="customerServiceID" name="customerServiceID" value="${fn:escapeXml(param.customerServiceID)}">
						</p>
						<p>
							<input type="submit">
							
						</p>
				
        </tr>
    </table>
    
    <br/><br/><br/>
	<span id="successMessage"><b>${messages.success}</b></span>
    
    <h1>${messages.title}</h1>
	<table border="1">
	    <tr>
	    
	    <th>ChatID</th>
	    <th>CustomerServiceID</th>
	    <th>UserID</th>
	    <th>Created Time</th>
	    <th>ServiceType</th>

	
	    </tr>
	    <c:forEach items="${chatHistories}" var="chatHistories" >
	        <tr>
	            <td><c:out value="${chatHistories.getChatID()}" /></td>
	            <td><c:out value="${chatHistories.getAuction().getAuctionID()}" /></td>
	            <td><c:out value="${chatHistories.getUser().getUserID()}" /></td>
	            <td><fmt:formatDate value="${chatHistories.getTimeStamp()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
	            <td><c:out value="${chatHistories.getServiceType()}" /></td>
	            
	            
	        </tr>
	    </c:forEach>
	</table>
	<br>
	
	<div><a href="ShowCSHomePage.jsp">Back to Customer Home Page</a></div>
	
</body>
</html>
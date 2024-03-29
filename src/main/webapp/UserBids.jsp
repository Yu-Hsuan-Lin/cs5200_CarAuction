<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bids List</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
             <th>AuctionID</th>
             <th>BidTime</th>
             <th>BidPrice</th>
            
            </tr>
            <c:forEach items="${bids}" var="bid" >
                <tr>
                    <td><a href="findbids?currentUserID=<%= request.getParameter("userID") %>&auctionid=<c:out value="${bid.getAuction().getAuctionID()}"/>"><c:out value="${bid.getAuction().getAuctionID()}" /></a></td>
                    <td><c:out value="${bid.getBidTime()}" /></td>
                    <td><c:out value="${bid.getBidPrice()}" /></td>
                </tr>
            </c:forEach>
       </table>
		<p>
		<button type="button" name="back" onclick="history.back()">back</button>
		</p>
</body>
</html>

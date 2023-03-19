<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Auctions</title>
</head>
<body>
	<form action="findbids?auctionid=<%= request.getParameter("auctionid") %>" method="post">
		<h1>Add a New Bid for Current Auction</h1>
		<p>
			<label for="userID">UserID</label>
			<input id="userID" name="userID" value="${fn:escapeXml(param. userID)}">
		</p>
		<p>
			<label for="bidPrice">Bid Price</label>
			<input id="bidPrice" name="bidPrice" value="${fn:escapeXml(param.bidPrice)}">
		</p>
		
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Current Auction</h1>
        <table border="1">
            <tr>
                <th>AuctionID</th>
                <th>Title</th>
                <th>StartTime</th>
                <th>EndTime</th>
                <th>CarID</th>
                <th>UserID</th>
                <th>Highlights</th>
                <th>Pictures</th>
                <th>StartPrice</th>
                <th>CurrentPrice</th>
                <th>AuctionStatus</th>
                <th>CustomerServiceID</th>
                <th>Year</th>
				<th>Maker</th>
				<th>Model</th>
				<th>Trim</th>
				<th>Body</th>
				<th>Transmission</th>
				<th>VIN</th>
				<th>State</th>
				<th>ConditionScore</th>
				<th>OdoMeter</th>
				<th>Color</th>
				<th>Interior</th>
				<th>MMR</th>
            </tr>
            <c:forEach items="${auctionsAndCars}" var="pair" >
                <tr>
                	<td><a href="findbids?auctionid=<c:out value="${pair.getT().getAuctionID()}"/>"><c:out value="${pair.getT().getAuctionID()}" /></a></td>
                    <td><c:out value="${pair.getT().getTitle()}" /></td>
                    <td><fmt:formatDate value="${pair.getT().getStartTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${pair.getT().getEndTime()}" pattern="yyyy-MM-dd"/></td>
                    <%-- <td><a href="finduser?userid=<c:out value="${pair.getT().getUser().getUserID()}"/>"><c:out value="${pair.getT().getUser().getUserID()}" /></a></td> --%>
                    <td><c:out value="${pair.getT().getCar().getCarID()}" /></td>
                    <td><c:out value="${pair.getT().getUser().getUserID()}" /></td>
                    <td><c:out value="${pair.getT().getHighlights()}" /></td>
                    <td><img src="${pair.getT().getPictures()}" alt="carPicture"></img></td>
                    <td><c:out value="${pair.getT().getMinimumPrice()}" /></td>
                    <td><c:out value="${pair.getT().getCurrentHighestPrice()}" /></td>
                    <td><c:out value="${pair.getT().getAuctionStatus().name()}" /></td>
                    <%-- <td><a href="findcustomerservice?customerserviceid=<c:out value="${pair.getT().getCustomerService().getCustomerServiceID()}"/>"><c:out value="${pair.getT().getCustomerService().getCustomerServiceID()}" /></a></td> --%>
                	<td><c:out value="${pair.getT().getCustomerService().getCustomerServiceID()}" /></td>
                	<td><c:out value="${pair.getU().getYear()}" /></td>
                	<td><c:out value="${pair.getU().getMaker()}" /></td>
                	<td><c:out value="${pair.getU().getModel()}" /></td>
                	<td><c:out value="${pair.getU().getTrim()}" /></td>
                	<td><c:out value="${pair.getU().getBody()}" /></td>
                	<td><c:out value="${pair.getU().getTransmission()}" /></td>
                	<td><c:out value="${pair.getU().getVIN()}" /></td>
                	<td><c:out value="${pair.getU().getState()}" /></td>
                	<td><c:out value="${pair.getU().getConditionScore()}" /></td>
                	<td><c:out value="${pair.getU().getOdoMeter()}" /></td>
                	<td><c:out value="${pair.getU().getColor()}" /></td>
                	<td><c:out value="${pair.getU().getInterior()}" /></td>
                	<td><c:out value="${pair.getU().getMMR()}" /></td>
                </tr>
            </c:forEach>
       </table>
	<h1>Bid history for current Auction</h1>
        <table border="1">
            <tr>
                <th>BidID</th>
                <th>AuctionID</th>
                <th>UserID</th>
                <th>BidTime</th>
                <th>BidPrice</th>
            </tr>
            <c:forEach items="${bids}" var="bid" >
                <tr>
                    <td><c:out value="${bid.getBidID()}" /></td>
                    <td><c:out value="${bid.getAuction().getAuctionID()}" /></td>
                    <%-- <td><a href="finduser?userid=<c:out value="${bid.getUser().getUserID()}"/>"><c:out value="${bid.getUser().getUserID()}" /></a></td> --%>
                    <td><c:out value="${bid.getUser().getUserID()}" /></td>
                    <td><c:out value="${bid.getBidTime()}" /></td>
                    <td><c:out value="${bid.getBidPrice()}" /></td>
                </tr>
            </c:forEach>
       </table>
       <a href="findauctions?auctionid=<c:out value="${bid.getAuction().getAuctionID()}"/>"><c:out value="Back to Auctions Page" /></a>
</body>
</html>

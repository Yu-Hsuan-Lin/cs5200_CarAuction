<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Collections List</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
            
            <th>CollectionID</th>
            <th>AuctionID</th>
            <th>PriceChangeAlert</th>
            <th>StatusChangeAlert</th>
           <!--  <th>Update PriceChangeAlert</th>
            <th>Update StatusChangeAlert</th> -->
            <th>Delete Record</th>
     
            </tr>
            <c:forEach items="${collections}" var="collection" >
                <tr>
                    <td><c:out value="${collection.getCollectionId()}" /></td>
                    <td><c:out value="${collection.getauction()}" /></td>
                    <td><c:out value="${collection.getPriceChangeAlert()}" /></td>
                    <td><c:out value="${collection.getStatusChangeAlert()}" /></td>
<%--                     <td><a href="collection_PriceChangeAlert_update?collectionid=<c:out value="${user.getCollectionId()}"/>">Update PriceChangeAlert</a></td>
                    <td><a href="collection_StatusChangeAlert_update?collectionid=<c:out value="${user.getCollectionId()}"/>">Update StatusChangeAlert</a></td>
 --%>                    <td><a href="collectiondelete?collectionid=<c:out value="${collection.getCollectionId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>

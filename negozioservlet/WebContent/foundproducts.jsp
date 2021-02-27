<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.dto.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<link rel="stylesheet" type="text/css" href="resources/css/table.css">
<html>
<head>
<title>Lista Products</title>
</head>
<body>
	<div id="page-wrap">
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>		
		<a href="${pageContext.request.contextPath}/Product?i&op=list"><img
			src="resources/images/back.png" width="25" height="25" /></a></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${prodottiTrovati}" var="prod">
					<tr>
						<td>${prod.id}</td>
						<td>${prod.name}</td>
						<td>${prod.description}</td>
						<td>${prod.price}&nbsp;&nbsp;€</td>
						<td align="center"><a
							href="${pageContext.request.contextPath }/carrello.do?op=aggiungi&id=${prod.id}"
							input type="submit" name="submit"><img  border="0" src="resources/images/carrello.png" width="20" height="20"/></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>
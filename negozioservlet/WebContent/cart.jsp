<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.dto.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<link rel="stylesheet" type="text/css" href="resources/css/table.css">
<html>
<head>
<title>Cart Page</title>
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
					<th>TOTAL PRICE: <c:out value="${totale}" />&nbsp;&nbsp;€

						<form action="carrello.do" method="get">
							<input placeholder="discountCode" type="text" name="discount" /> <img
								src="resources/images/discount.png" width="25" height="25" /> <input
								type="hidden" name="op" value="discount" /> <br />

						</form>
					</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${carrelloutente}" var="tmpDue">
					<tr>
						<td>${tmpDue.id}</td>
						<td>${tmpDue.name}</td>
						<td>${tmpDue.description}</td>
						<td>${tmpDue.price}&nbsp;&nbsp;€
						<td align="center">
						<td><a
							href="${pageContext.request.contextPath }/carrello.do?op=delete&id=${tmpDue.id}"><img
								src="resources/images/delete.png" width="25" height="25" /></a></td>


					</tr>
				</c:forEach>
		</table>


		<form action="${pageContext.request.contextPath }/carrello.do?op=new"
			method="get">
			<h2>Vuoi procedere al pagamento ?</h2>
			<input type="submit" value="Submit" /> <input type="hidden"
				name="op" value="new" />


		</form>
		<br> <br>
		<div class="goback">

			<a href="${pageContext.request.contextPath}/Product?i&op=list"><img
				src="resources/images/back.png" width="25" height="25" /></a>

		</div>
	</div>
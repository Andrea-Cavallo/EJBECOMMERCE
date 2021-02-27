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
		<div class="wrap">
			<div class="search">
				<form action="Product" method="get">
					<b>Ricerca Prodotto per Nome: <input type="text" name="name" />
						<input type="hidden" name="op" value="findByName" /></b>
				</form>

			</div>
		</div>
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price
						<form action="Product" method="get">
							<button type="submit" value="Submit">
								<img src="resources/images/ordina.png" width="25" height="25" />
							</button>
							<input type="hidden" name="op" value="findByDesc" />

						</form>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${elencoprodotti}" var="prod">
					<tr>
						<td>${prod.id}</td>
						<td>${prod.name}</td>
						<td>${prod.description}</td>
						<td>${prod.price}&nbsp;&nbsp;€</td>
						<td align="center"><a
							href="${pageContext.request.contextPath }/carrello.do?op=aggiungi&id=${prod.id}"
							input type="submit" name="submit"><img border="0"
								src="resources/images/carrello.png" width="20" height="20" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%--For displaying Previous link except for the 1st page --%>
<%-- 		<c:if test="${currentPage != 1}"> --%>
<%-- 			<td><a href="product.do?page=${currentPage - 1}">Previous</a></td> --%>
<%-- 		</c:if> --%>

		<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
<!-- 		<table border="1" cellpadding="5" cellspacing="5"> -->
<!-- 			<tr> -->
<%-- 				<c:forEach begin="1" end="${noOfPages}" var="i"> --%>
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${currentPage eq i}"> --%>
<%-- 							<td>${i}</td> --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 							<td><a href="product.do?page=${i}">${i}</a></td> --%>
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>
<%-- 				</c:forEach> --%>
<!-- 			</tr> -->
<!-- 		</table> -->

		<%--For displaying Next link --%>
<%-- 		<c:if test="${currentPage lt noOfPages}"> --%>
<%-- 			<td><a href="product.do?page=${currentPage + 1}">Next</a></td> --%>
<%-- 		</c:if> --%>
	</div>

</body>
</html>
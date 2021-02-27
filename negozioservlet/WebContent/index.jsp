<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/corso.css">

<meta charset="UTF-8">
<title>WelcomeMR.</title>
</head>
<body>

	<div class="container">
		<h2>
			bentornato
			<c:out value="${utenteLoggato.username}" />
		</h2>
		<hr>

		<h2>
			<p>
				Vuoi visualizzare i prodotti? >>><a
					href="${pageContext.request.contextPath}/Product?i&op=list">VIEW
					PRODUCTS </a>
			</p>
			<br>
			<p>
				Vuoi inserire un nuovo prodotto? >>><a
					href="${pageContext.request.contextPath}/productform.jsp">INSERT
					NEW PRODUCTS </a>
			</p>
			<br>
			<hr>
			<p>
				LOGOUT?? >>><a href="${pageContext.request.contextPath}/logout.do">LOGOUT</a>
			</p>
			<br>

		</h2>



	</div>



</body>
</html>
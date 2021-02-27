<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/corso.css">
<meta charset="UTF-8">
<title>Success</title>
</head>
<body>
	<div class="container">
		<h1>Complimenti</h1>
		<h2>
			Grazie per il tuo acquisto :
			<c:out value="${utenteLoggato.username}" />
		</h2>
		<h2>
			hai fatto una spesa di :

			<c:choose>
				<c:when test="${totaleScontato==0}">
					<c:out value="${totale}" />
				</c:when>
				<c:otherwise>
					<c:out value="${totaleScontato}" />
				</c:otherwise>
			</c:choose>
			<%-- 				<c:out value="${totale}" /> --%>

			<%-- 				<c:out value="${totaleScontato}}" /> --%>

			&nbsp;&nbsp;€
			<p>Hai guadagnato un coupon sconto per i prossimi acquisti</p>
			<p id="mostra" style="display: none">20off</p>
			<button type="button"
				onclick="document.getElementById('mostra').style.display='block'">CLICCA
				PER VISUALIZZARE IL CODICE</button>

			<p>vuoi continuare a fare shopping?</p>
			<div class="goback">

				<a href="${pageContext.request.contextPath}/Product?i&op=list"><img
					src="resources/images/back.png" width="25" height="25" /></a>

			</div>
		</h2>
	</div>
</body>
</html>
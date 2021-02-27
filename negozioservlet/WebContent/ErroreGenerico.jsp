<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String err = request.getAttribute("err") != null ? (String) request.getAttribute("err") : "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ErroreGenerico</title>
</head>
<body class="prova-image6">

	<h1>ERRORE DI SISTEMA CONTATTARE L'AMMINISTRATORE</h1>
	<div class="errore">
		<p>
			<b><%=err%></b>
		</p>
	</div>
</body>
</html>
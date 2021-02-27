<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String err = request.getAttribute("err") != null ? (String) request.getAttribute("err") : "";
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/corso.css">

<meta charset="UTF-8">
<title>login</title>


</head>
<body>



	<div class="container">
			<p>
				<b><%=err%></b>
			</p>
		<form action="User" method="get">
			<h2>Insert your Username and Password</h2>
			<h1>Log in!</h1>

			<div class="fields">
				<span> <input placeholder="username" type="text"
					name="username" />
				</span> <br /> <span> <input placeholder="Password" type="password"
					name="password" />
				</span>
			</div>
			<div class="submit">
				<hr>
				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
					value="submit" /> <input type="hidden" name="op" value="login" />
				<br />
			</div>
		</form>
	</div>





</body>
</html>
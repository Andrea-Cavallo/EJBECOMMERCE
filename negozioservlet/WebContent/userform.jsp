<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/corso.css">

<meta charset="UTF-8">
<title>Registrati</title>
</head>
<body>
		<h1>Registrati</h1>


	<div class="container">
	<form action="User" method="get">
			<h2>WELCOME - New USER</h2>
			<h1>Register, get in!</h1>

			<div class="fields">
			      <input type="hidden" name="id" >
			
				<span> <input placeholder="username" type="text"
					name="username" />
				</span> <br /> <span> <input placeholder="Password" type="password"
					name="password" />
				</span>
			</div>
			<div class="submit">
			<hr>
			<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="submit" /> <input type="hidden" name="op" value="new" /> <br />
			</div>
		</form>
	</div>

     
</body>
</html>
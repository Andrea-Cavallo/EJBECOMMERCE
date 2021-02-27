<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Form</title>
<link rel="stylesheet" type="text/css" href="resources/css/corso.css">

</head>
<body>
	<div class="container">
		<form action="Product" method="get">
			<h1>Insert a new Product!</h1>
			</br> </br> <input type="hidden" name="id"> <input type="text"
				name="name" placeholder="name" required="required"> <input
				type="text" name="description" placeholder="little description"
				required="required"> <input type="number" name="price"
				placeholder="$$$(price)" required="required">

			<!-- Pulsante invia -->
			<input type="submit" value="Insert" /> <input type="hidden"
				name="op" value="new" /> <br />

		</form>
	</div>
</body>
</html>
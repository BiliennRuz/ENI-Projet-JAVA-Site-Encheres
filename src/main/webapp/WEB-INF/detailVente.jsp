<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Détail vente</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./include/header.jsp"%>
	
	<!-- TITRE DE LA PAGE -->
	
	<section class="titrePage">
		<h1>Détail Vente</h1>
		<p>L'id est : ${id}</p>
	</section>

	<script src="js/script.js"></script>
</body>
</html>
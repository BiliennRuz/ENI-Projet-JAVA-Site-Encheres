<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name='viewport' content='width=device-width, initial-scale=1.0'>
	<title>Enchères</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
			integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="./">ENI Enchères</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <c:choose>
			<c:when test="${connexion}">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			      <li class="nav-item">
			        <a class="nav-link active" aria-current="page" href="#">Enchères</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="#">Vendre un article</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="./profil">Mon compte (${utilisateurConnecte.pseudo})</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="./deconnexion">Deconnexion</a>
			      </li>
				</ul>
				<ul>
			    	
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			      
				</ul>
				<ul>
			    	<li><a href="./inscription" class="btn btn-success">Inscription</a></li>
			    	<li><a href="./connexion" class="btn btn-success">Connexion</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
      
    </div>
  </div>
  
</nav>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Enchères</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<!-- HEADER AVEC TITRE ET LIENS CONNEXION -->
	
	<header>
		<div class="titre">
			<h2>ENI-Enchères</h2>
		</div>
		
		<div class="connexion">
			<c:choose>
				<c:when test="${utilisateurConnecte != null}">
					<p>Vous êtes connecté avec : ${utilisateurConnecte.username}</p>
					<a href="./DeconnexionServlet">Déconnexion</a>
				</c:when>
				<c:otherwise>
					<a href="./connexion" class="lienConnexion">S'inscrire - Se connecter</a>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
	
	
	<!-- TITRE DE LA PAGE -->
	
	<section class="titrePage">
		<h1>Liste des enchères</h1>
	</section>
	
	
	<!-- FORMULAIRE DE RECHERCHE -->
	
	<form class="formulaireRecherche" action="" method="POST">
	
		<!-- BLOC DES CHAMPS DE RECHERCHE -->
		
		<div class="blocRecherche">
			<div class="inputRecherche">
				<h3>Filtres :</h3>
				<input type="text" name="article" id="article" placeholder="Le nom de l'article contient">
				<div class="champsCategorie">
					<label for="categorie">Catégorie : </label>
					<select name="categorie" id="categorie">
					    <option value="">toutes</option>
					    <option value="dog">Informatique</option>
					    <option value="cat">Ameublement</option>
					    <option value="hamster">Vêtement</option>
					    <option value="parrot">SportETLoisir</option>
					</select>
				</div>
			</div>
		</div>
		
		<!-- BLOC DU BOUTON DE RECHERCHE -->
		
		<div class="blocBoutonRecherche">
			<button type="submit">Rechercher</button>
		</div>
	</form>
	
</body>
</html>
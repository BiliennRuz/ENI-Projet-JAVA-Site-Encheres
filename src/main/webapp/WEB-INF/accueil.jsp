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

	
	
	<%@ include file="./include/header.jsp"%>
	
	
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
					<label>Catégorie : </label>
					<select name="idcategorie">
						<option value="0">Toutes</option>
						<c:forEach var="categorie" items="${categories}">
				 			<option value="${categorie.idCategorie}"><c:out value="${categorie.libelle}" /></option>
				 		</c:forEach>
					</select>
				</div>
				<c:if test="${utilisateurConnecte != null}"> 
					<div>
						<input type="radio" name="achatvente" value="achat" />Achat<br />
						<input type="checkbox" name="achat" value="Enchère ouvertes" />Enchère ouvertes<br />
						<input type="checkbox" name="achat" value="Mes enchères" />Mes enchères<br />
						<input type="checkbox" name="achat" value="Mes enchères remportées" />Mes enchères remportées<br />
					<div>
					</div>
						<input type="radio" name="achatvente" value="vente" />Vente<br />
						<input type="checkbox" name="vente" value="Vente non débuté" />Vente non débuté<br />
						<input type="checkbox" name="vente" value="Vente en cours" />Vente en cours<br />
						<input type="checkbox" name="vente" value="Vente terminée" />Vente terminée<br />
					</div>
				</c:if>
			</div>
		</div>
		
		<!-- BLOC DU BOUTON DE RECHERCHE -->
		
		<div class="blocBoutonRecherche">
			<button type="submit">Rechercher</button>
		</div>
	</form>
	
	
	<!-- BLOC DE LA LISTE -->
	
	<table>
		<thead>
			<tr>
				<c:if test="${utilisateurConnecte == null}"> 
					<th>Nom</th>
					<th>Prix</th>
					<th>Fin de l'enchère</th>
					<th>Vendeur</th>
				</c:if>
				<c:if test="${utilisateurConnecte != null}"> 
					<th>Nom</th>
					<th>Description</th>
					<th>Catégorie</th>
					<th>Dernière enchère</th>
					<th>Prix initial</th>
					<th>Fin de l'enchère</th>
					<th>Retrait</th>
					<th>Vendeur</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<!-- pour chaque repas de ma liste contenue dans mon attribut de requête : listeRepas -->
			<c:forEach var="articles" items="${articles}">
				<!-- je crée une nouvelle ligne dans ma table HTML -->
				<tr>
					<c:if test="${utilisateurConnecte == null}"> 
						<td>${articles.nomArticle}</td> 
						<td>${articles.prixInitial}</td>
						<td>${articles.dateFinEncheres}</td>
						<td>${articles.vendeur.pseudo}</td>
					</c:if>
					<c:if test="${utilisateurConnecte != null}"> 
						<td>${articles.nomArticle}</td> 
						<td>${articles.description}</td>
						<td>${articles.categorieArticle.libelle}</td>
						<td></td>
						<td>${articles.prixInitial}</td>
						<td>${articles.dateFinEncheres}</td>
						<td>${articles.lieuRetrait.rue} ${articles.lieuRetrait.codePostal} ${articles.lieuRetrait.ville}</td>
						<td>${articles.vendeur.pseudo}</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="./include/head.jsp"%>
</head>
<body>

	
	
	<%@ include file="./include/header.jsp"%>
	
	<c:if test="${succes != null }">
		<div class="succes">${succes}</div>
	</c:if>
	
	
	<!-- TITRE DE LA PAGE -->
	
	<section class="titrePage">
		<h1>Liste des enchères</h1>
	</section>
	
	
	<!-- FORMULAIRE DE RECHERCHE -->
	
	<form class="formulaireRecherche" action="" method="POST">
	
		<!-- BLOC DES CHAMPS DE RECHERCHE -->
		
		<div class="blocRecherche">
			<div class="blocFiltres">
				<h3>Filtres :</h3>
				<input type="text" name="article" id="article" placeholder="Le nom de l'article contient">
			</div>
			<div class="blocCategories">
				<label>Catégorie : </label>
				<select name="idcategorie">
					<option value="0">Toutes</option>
					<c:forEach var="categorie" items="${categories}">
			 			<option value="${categorie.idCategorie}"><c:out value="${categorie.libelle}" /></option>
			 		</c:forEach>
				</select>
			</div>
			<div class="blocAchatVente">
				<c:if test="${utilisateurConnecte != null}"> 
					<div class="blocAchat">
						<div class="blocLabelInput">
							<input type="radio" name="achatvente" value="achat" id="boutonAchat">
							<label for="boutonAchat">Achat</label>
						</div>
						<div class="blocLabelInput">
							<input type="checkbox" name="achat" value="Enchère ouvertes" id="enchereOuverte">
							<label for="enchereOuverte">Enchère ouvertes</label>
						</div>
						<div class="blocLabelInput">
							<input type="checkbox" name="achat" value="Mes enchères" id="mesEncheres">
							<label for="mesEncheres">Mes enchères</label>
						</div>
						<div class="blocLabelInput">
							<input type="checkbox" name="achat" value="Mes enchères remportées" id="mesEncheresRemportees">
							<label for="MesEncheresRemportees">Mes enchères remportées</label>
						</div>
						
					</div>
					<div class="blocVente">
						<div class="blocLabelInput">
							<input type="radio" name="achatvente" value="vente" id="boutonVente">
							<label for="boutonVente">Vente</label>
						</div>
						<div class="blocLabelInput">
							<input type="checkbox" name="vente" value="vente" id="venteNonDebutee">
							<label for="venteNonDebutee">Vente non débutée</label>
						</div>
						<div class="blocLabelInput">
							<input type="checkbox" name="vente" value="Vente en cours" id="venteEnCours">
							<label for="venteEnCours">Vente en cours</label>
						</div>
						<div class="blocLabelInput">
							<input type="checkbox" name="vente" value="Vente terminée" id="venteTerminee">
							<label for="venteTerminee">Vente terminée</label>
						</div>
					</div>
				</c:if>
			</div>
			
			<div class="blocBoutonRecherche">
				<button type="submit">Rechercher</button>
			</div>
		</div>
		

		<!-- BLOC DU BOUTON DE RECHERCHE -->
		
		
	</form>
	
	
	<!-- BLOC DE LA LISTE -->
	
	<main>
	
		<c:forEach var="articles" items="${articles}">
			<div class="card" style="width: 18rem;">
			  <!-- <img src="..." class="card-img-top" alt="..."> -->
			  <div class="card-body">
			    <h5 class="card-title">${articles.nomArticle}</h5>
			    <p class="card-text">${articles.description}</p>
			    <p class="card-text"><span class="badge bg-secondary">${articles.categorieArticle.libelle}</span></p>
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">Prix : ${articles.prixInitial}</li>
			    <li class="list-group-item">Fin de l'enchère : ${articles.dateFinEncheres}</li>
			    <li class="list-group-item">Vendeur : ${articles.vendeur.pseudo}</li>
			  </ul>
			  <div class="card-body">
			    <a href="./DetailVente?id=${articles.idArticle}" class="card-link">Voir le détail</a>
			  </div>
			</div>
		</c:forEach>
	
	</main>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
			integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="js/script.js"></script>
	
	
	
</body>
</html>
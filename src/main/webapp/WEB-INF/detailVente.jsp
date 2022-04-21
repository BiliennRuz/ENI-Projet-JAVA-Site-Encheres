<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/head.jsp"%>
	
	<!-- TITRE DE LA PAGE -->
	
	<section class="titrePage">
		<h1>Détail Vente</h1>
	</section>
	
	<form class="detailVente" action="" method="POST">
		
		<div class="blocInscription blocChamps">
			
			<div class="blocVente">
				<label for="nomArticle">${article.nomArticle}</label>
			</div><br/>		
			<div class="blocVente">
				<label for="description">Description : </label>
				${article.description}
			</div><br/><br/>
			<div class="blocVente">
				<label for="categorie">Catégorie : </label>
				${categorie.libelle}
			</div><br/>
			<div class="blocVente">
				<label for="meilleureOffre">Meilleure offre : </label>
				2010 pts par Bob
			</div><br/>
			<div class="blocVente">
				<label for="miseAPrix">Mise à prix : </label>
				185 pts
			</div><br/>
			<div class="blocVente">
				<label for="finEnchere">Fin de l'enchère : </label>
				09/10/2018
			</div><br/>
			<div class="blocVente">
				<label for="retrait">Retrait : </label>
				10 allée des Alouettes 44800 St Herblain
			</div><br/>
			<div class="blocVente">
				<label for="vendeur">Vendeur : </label>
				jojo44
			</div><br/>
			<div class="blocVente">
				<label for="proposition">Ma proposition : </label>
				<input type="number" value="20">
			</div><br/>
	
		</div>
			
			<div class="blocInscription blocBoutonsInscription">
				<button class="boutonInscription" type="submit">Enchérir</button>
			</div>
	
	</form>
	
	<p>L'id est : ${article.idArticle}</p>
	
<%@ include file="./include/footer.jsp"%>
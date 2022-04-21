<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/head.jsp"%>
	
	<!-- TITRE DE LA PAGE -->
	
	<section class="titrePage">
		<h1>Détail Vente</h1>
	</section>
	
	<form class="detailVente" action="" method="POST">
		
		<div class="blocInscription blocChamps">
			
			<div class="blocLabelInput">
				<label for="nomArticle">${article.nomArticle}</label>
			</div><br/>		
			<div class="blocLabelInput">
				<label for="description">Description : </label>
				${article.description}
			</div><br/><br/>
			<div class="blocLabelInput">
				<label for="categorie">Catégorie : </label>
				Informatique
			</div><br/>
			<div class="blocLabelInput">
				<label for="meilleureOffre">Meilleure offre : </label>
				2010 pts par Bob
			</div><br/>
			<div class="blocLabelInput">
				<label for="miseAPrix">Mise à prix : </label>
				185 pts
			</div><br/>
			<div class="blocLabelInput">
				<label for="finEnchere">Fin de l'enchère : </label>
				09/10/2018
			</div><br/>
			<div class="blocLabelInput">
				<label for="retrait">Retrait : </label>
				10 allée des Alouettes 44800 St Herblain
			</div><br/>
			<div class="blocLabelInput">
				<label for="vendeur">Vendeur : </label>
				jojo44
			</div><br/>
			<div class="blocLabelInput">
				<label for="proposition">Ma proposition : </label>
				220
			</div><br/>
	
		</div>
			
			<div class="blocInscription blocBoutonsInscription">
				<button class="boutonInscription" type="submit">Enchérir</button>
			</div>
	
	</form>
	
	<p>L'id est : ${article.idArticle}</p>
	
<%@ include file="./include/footer.jsp"%>
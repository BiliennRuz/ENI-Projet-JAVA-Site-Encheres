<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/head.jsp"%>
	
	<!-- TITRE DE LA PAGE -->
	
	<section class="titrePage">
		<h4>Détail Vente</h4>
		<!-- <img class="img-fluid" src="images/vente.png"> -->
	</section>
	
	<form class="detailVente" action="" method="POST">
	
		<div class="blocInscription blocChamps">
			
			<div class="blocLabelinput">
				<label for="nomArticle">${article.nomArticle}</label>
			</div><br/>		
			<div class="blocLabelinput">
				<label for="description">Description : </label>
				${article.description}
			</div><br/><br/>
			<div class="blocLabelinput">
				<label for="categorie">Catégorie : </label>
				${categorie.libelle}
			</div><br/>
			<div class="blocLabelinput">
				<label for="meilleureOffre">Meilleure offre : </label>
				2010 pts par Bob
			</div><br/>
			<div class="blocLabelinput">
				<label for="miseAPrix">Mise à prix : </label>
				185 pts
			</div><br/>
			<div class="blocLabelinput">
				<label for="finEnchere">Fin de l'enchère : </label>
				09/10/2018
			</div><br/>
			<div class="blocLabelinput">
				<label for="retrait">Retrait : </label>
				10 allée des Alouettes 44800 St Herblain
			</div><br/>
			<div class="blocLabelinput">
				<label for="vendeur">Vendeur : </label>
				${utilisateur.pseudo}
			</div><br/>
			<div class="blocLabelinput">
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